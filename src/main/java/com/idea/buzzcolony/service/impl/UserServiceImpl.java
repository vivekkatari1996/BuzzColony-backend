package com.idea.buzzcolony.service.impl;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.idea.buzzcolony.dto.login.ForgetPassDto;
import com.idea.buzzcolony.dto.login.LoginDto;
import com.idea.buzzcolony.dto.login.SignUpDto;
import com.idea.buzzcolony.dto.master.MtCountryDto;
import com.idea.buzzcolony.dto.ses.SesDto;
import com.idea.buzzcolony.enums.base.FileType;
import com.idea.buzzcolony.enums.base.TokenType;
import com.idea.buzzcolony.enums.ses.EmailTemplate;
import com.idea.buzzcolony.model.base.AppUser;
import com.idea.buzzcolony.model.base.FileEntity;
import com.idea.buzzcolony.model.base.TokenStore;
import com.idea.buzzcolony.model.master.MtCountry;
import com.idea.buzzcolony.repo.AppUserRepo;
import com.idea.buzzcolony.repo.FileEntityRepo;
import com.idea.buzzcolony.repo.TokenStoreRepo;
import com.idea.buzzcolony.repo.master.MtCountryRepo;
import com.idea.buzzcolony.service.S3Service;
import com.idea.buzzcolony.service.UserService;
import com.idea.buzzcolony.util.ApiResponse;
import com.idea.buzzcolony.util.AppMessage;
import com.idea.buzzcolony.util.Constants;
import com.idea.buzzcolony.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AppUserRepo appUserRepo;

    @Autowired
    private AppMessage appMessage;

    @Autowired
    private MtCountryRepo mtCountryRepo;

    @Autowired
    private TokenStoreRepo tokenStoreRepo;

    @Autowired
    private FileEntityRepo fileEntityRepo;

    @Autowired
    private S3Service s3Service;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ApiResponse createUser(SignUpDto signUpDto) throws Exception {
        if (appUserRepo.existsByEmailIgnoreCaseOrUserIdIgnoreCase(signUpDto.getEmail(), signUpDto.getUserId())) {
            throw new Exception(appMessage.getMessage("user.already.exists"));
        }
        AppUser appUser = new AppUser();
        appUser.setEmail(signUpDto.getEmail());
        appUser.setFirstName(signUpDto.getFirstName());
        appUser.setLastName(signUpDto.getLastName());
        appUser.setPhoneNo(signUpDto.getPhoneNo());
        appUser.setUserId(signUpDto.getUserId());
        appUser.setPassword(new BCryptPasswordEncoder().encode(signUpDto.getPassword()));
        appUser.setDateOfBirth(LocalDate.parse(signUpDto.getDateOfBirth(), DateTimeFormatter.ofPattern(Constants.DATE)));
        Optional<MtCountry> optionalMtCountry = mtCountryRepo.findById(signUpDto.getCountryId());
        if (optionalMtCountry.isPresent()) {
            appUser.setMtCountry(optionalMtCountry.get());
        }
        appUser = appUserRepo.save(appUser);
        String token = createToken(appUser, TokenType.VERIFY_EMAIL);
        String link = Constants.Email_verify + token;
        AwsSESServiceImpl.sendSESMail(new SesDto(appUser.getFirstName(), link), appUser.getEmail(), EmailTemplate.EmailVerify);
        return new ApiResponse(HttpStatus.OK, appMessage.getMessage("success"), null);
    }

    @Override
    public ApiResponse login(LoginDto loginDto) {
        Optional<AppUser> optionalAppUser = appUserRepo.findByEmailIgnoreCaseOrUserIdIgnoreCaseAndIsActiveTrue(loginDto.getUserId(), loginDto.getUserId());
        if (!optionalAppUser.isPresent() || !new BCryptPasswordEncoder().matches(loginDto.getPassword(), optionalAppUser.get().getPassword())) {
            return new ApiResponse(HttpStatus.BAD_REQUEST, appMessage.getMessage("invalid.credentials"), null);
        }
        if (!optionalAppUser.get().getIsEmailVerified()) {
            return new ApiResponse(HttpStatus.BAD_REQUEST, appMessage.getMessage("error.verify.email"), null);
        }
        if (!optionalAppUser.get().getIsActive()) {
            return new ApiResponse(HttpStatus.BAD_REQUEST, appMessage.getMessage("error.deactivate.account"), null);
        }
        loginDto = new LoginDto();
        loginDto.setId(optionalAppUser.get().getId());
        loginDto.setUserId(optionalAppUser.get().getUserId());
        loginDto.setEmail(optionalAppUser.get().getEmail());
        loginDto.setToken(Utility.createToken(optionalAppUser.get().getEmail(), Constants.LOGIN_VALIDITY));
        Optional<FileEntity> profilePic = fileEntityRepo.findByRefIdAndFileType(optionalAppUser.get().getId(), FileType.PROFILE_PIC);
        if (profilePic.isPresent()) {
            loginDto.setProfilePicUrl(s3Service.getPreSignedUrlForDownload(profilePic.get().getUuid()));
        }
        return new ApiResponse(HttpStatus.OK, appMessage.getMessage("success"), loginDto);
    }

    @Override
    public ApiResponse socialLogin(String code) {
        ApiResponse apiResponse = ApiResponse.getFailureResponse();
        try {
            GoogleIdToken.Payload payload = callGoogleForSocialLogin(code);

            String email = payload.getEmail();
            if (email == null) {
                throw new Exception(appMessage.getMessage("something.went.wrong"));
            }

            Optional<AppUser> optionalAppUser = appUserRepo.findByEmailIgnoreCase(email);
            if (!optionalAppUser.isPresent()) {
                AppUser appUser = new AppUser();
                appUser.setEmail(email);
                appUser.setIsEmailVerified(Boolean.TRUE);
                appUser.setFirstName((String) payload.get("given_name"));
                appUser.setLastName((String) payload.get("family_name"));
                if (payload.getSubject() != null && appUser.getGoogleId() == null) {
                    appUser.setGoogleId(payload.getSubject());
                }
                appUser = appUserRepo.save(appUser);
                optionalAppUser = Optional.of(appUser);
            } else {
                Boolean canSave = Boolean.FALSE;
                if (optionalAppUser.isPresent() && payload.getSubject() != null && optionalAppUser.get().getGoogleId() == null) {
                    optionalAppUser.get().setGoogleId(payload.getSubject());
                    canSave = Boolean.TRUE;
                }
                if (!optionalAppUser.get().getIsEmailVerified()) {
                    optionalAppUser.get().setIsEmailVerified(Boolean.TRUE);
                    canSave = Boolean.TRUE;
                }
                if (canSave) {
                    appUserRepo.save(optionalAppUser.get());
                }
            }
            if (!optionalAppUser.get().getIsActive()) {
                throw new Exception(appMessage.getMessage("access.denied"));
            }
            LoginDto loginDto = new LoginDto();
            loginDto.setUserId(optionalAppUser.get().getEmail());
            loginDto.setToken(Utility.createToken(optionalAppUser.get().getEmail(), Constants.LOGIN_VALIDITY));
            apiResponse = new ApiResponse(HttpStatus.OK, appMessage.getMessage("success"), loginDto);
        } catch (Exception e) {
            return apiResponse;
        }
        return apiResponse;
    }

    private GoogleIdToken.Payload callGoogleForSocialLogin(String code) throws Exception {

        GoogleTokenResponse tokenResponse =
                new GoogleAuthorizationCodeTokenRequest(
                        new NetHttpTransport(),
                        JacksonFactory.getDefaultInstance(),
                        "https://oauth2.googleapis.com/token",
                        "619000586523-410qoquaqoadt73f4mb95c3ptujddotv.apps.googleusercontent.com",
                        "hlASSDZqzj5zmebUXE5OQYLI",
                        code,
                        "http://localhost:4200/login")  // Specify the same redirect URI that you use with your web
                        // app. If you don't have a web version of your app, you can
                        // specify an empty string.
                        .execute();

        String accessToken = tokenResponse.getAccessToken();

        // Get profile info from ID token
        GoogleIdToken idToken = tokenResponse.parseIdToken();
        GoogleIdToken.Payload payload = idToken.getPayload();
        String userId = payload.getSubject();  // Use this value as a key to identify a user.
        String email = payload.getEmail();
        boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
        String name = (String) payload.get("name");
        String pictureUrl = (String) payload.get("picture");
        String locale = (String) payload.get("locale");
        String familyName = (String) payload.get("family_name");
        String givenName = (String) payload.get("given_name");

        return payload;
    }

    @Override
    public ApiResponse getCountries() {
        List<MtCountry> mtCountries = mtCountryRepo.findByIsActiveTrueOrderBySeqAsc();
        return new ApiResponse(HttpStatus.OK, appMessage.getMessage("success"), mtCountries.stream().map(MtCountryDto::new).collect(Collectors.toList()));
    }

    @Override
    public ApiResponse getForgetPassMail(String email) throws Exception {
        AppUser appUser = appUserRepo.findByEmailIgnoreCaseAndIsActiveTrue(email).orElseThrow(() -> new Exception(appMessage.getMessage("something.went.wrong")));
        String token = createToken(appUser, TokenType.FORGET_PASS);
        String link = Constants.Forget_pass + token;
        AwsSESServiceImpl.sendSESMail(new SesDto(appUser.getFirstName(), link), appUser.getEmail(), EmailTemplate.ForgetPass);
        return new ApiResponse(HttpStatus.OK, appMessage.getMessage("success"), null);
    }

    private String createToken(AppUser appUser, TokenType type) throws Exception{
        String token = Utility.createToken(appUser.getEmail(), Constants.JWT_VALIDITY);
        TokenStore tokenStore = new TokenStore(token, type, appUser);
        tokenStoreRepo.save(tokenStore);
        return token;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ApiResponse verifyForgetPassMail(ForgetPassDto forgetPassDto) throws Exception {
        TokenStore tokenStore = verifyToken(forgetPassDto.getToken());
        AppUser appUser = tokenStore.getAppUser();
        appUser.setPassword(new BCryptPasswordEncoder().encode(forgetPassDto.getPassword()));
        appUserRepo.save(appUser);
        tokenStoreRepo.delete(tokenStore);
        return ApiResponse.getSuccessResponse();
    }

    private TokenStore verifyToken(String token) throws Exception {
        String email = Utility.getEmailFromToken(token);
        TokenStore tokenStore = tokenStoreRepo.findByToken(token).orElseThrow(() -> new Exception(appMessage.getMessage("token.already.used")));;
        return tokenStore;
    }

    @Override
    public ApiResponse sendVerifyMail(String email) throws Exception {
        AppUser appUser = appUserRepo.findByEmailIgnoreCaseAndIsActiveTrue(email).orElseThrow(() -> new Exception(appMessage.getMessage("data.not.found")));
        String token = createToken(appUser, TokenType.VERIFY_EMAIL);
        return new ApiResponse(HttpStatus.OK, appMessage.getMessage("success"), token);
    }

    @Override
    public ApiResponse verifyEmail(String token) throws Exception {
        TokenStore tokenStore = verifyToken(token);
        AppUser appUser = tokenStore.getAppUser();
        appUser.setIsEmailVerified(Boolean.TRUE);
        appUserRepo.save(appUser);
        tokenStoreRepo.delete(tokenStore);
        return ApiResponse.getSuccessResponse();
    }

}
