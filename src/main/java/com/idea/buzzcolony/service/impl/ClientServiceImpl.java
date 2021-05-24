package com.idea.buzzcolony.service.impl;

import com.idea.buzzcolony.dto.client.CreatePostRespDto;
import com.idea.buzzcolony.dto.client.InsiderDto;
import com.idea.buzzcolony.dto.client.PostAddressDto;
import com.idea.buzzcolony.dto.client.PostDto;
import com.idea.buzzcolony.dto.login.SignUpDto;
import com.idea.buzzcolony.dto.master.*;
import com.idea.buzzcolony.dto.vimeo.FileDto;
import com.idea.buzzcolony.dto.vimeo.VimeoRespDto;
import com.idea.buzzcolony.enums.base.FileType;
import com.idea.buzzcolony.enums.post.PostRequest;
import com.idea.buzzcolony.enums.post.PostStatus;
import com.idea.buzzcolony.model.base.AppUser;
import com.idea.buzzcolony.model.base.BaseEntity;
import com.idea.buzzcolony.model.base.FileEntity;
import com.idea.buzzcolony.model.client.Post;
import com.idea.buzzcolony.model.client.PostAddress;
import com.idea.buzzcolony.model.client.PostReport;
import com.idea.buzzcolony.model.client.PostResp;
import com.idea.buzzcolony.model.master.*;
import com.idea.buzzcolony.repo.AppUserRepo;
import com.idea.buzzcolony.repo.FileEntityRepo;
import com.idea.buzzcolony.repo.client.PostRepo;
import com.idea.buzzcolony.repo.client.PostReportRepo;
import com.idea.buzzcolony.repo.client.PostRespRepo;
import com.idea.buzzcolony.repo.master.*;
import com.idea.buzzcolony.service.ClientService;
import com.idea.buzzcolony.service.S3Service;
import com.idea.buzzcolony.service.VimeoService;
import com.idea.buzzcolony.util.ApiResponse;
import com.idea.buzzcolony.util.AppMessage;
import com.idea.buzzcolony.util.Constants;
import com.idea.buzzcolony.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Anand Ramesh
 * @version 1.0
 * @since 19/12/20
 */
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private AppMessage appMessage;

    @Autowired
    private AppUserRepo appUserRepo;

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private MtCategoryRepo mtCategoryRepo;

    @Autowired
    private MtCountryRepo mtCountryRepo;

    @Autowired
    private MtSubBtypeRepo mtSubBtypeRepo;

    @Autowired
    private MtBTypeRepo mtBTypeRepo;

    @Autowired
    private VimeoService vimeoService;

    @Autowired
    private FileEntityRepo fileEntityRepo;

    @Autowired
    private MtEstPartRepo mtEstPartRepo;

    @Autowired
    private MtEstAmountRepo mtEstAmountRepo;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PostRespRepo postRespRepo;

    @Autowired
    private PostReportRepo postReportRepo;

    @Autowired
    private S3Service s3Service;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ApiResponse saveOrUpdatePost(PostDto postDto) throws Exception {
        if (postDto.getVideoDto() == null || postDto.getVideoDto().getSize() == null) {
            throw new Exception(appMessage.getMessage("data.not.found"));
        }
        AppUser appUser = Utility.getApplicationUserFromAuthentication(appUserRepo);
        CreatePostRespDto createPostRespDto = createPost(postDto, appUser);
        return new ApiResponse(HttpStatus.OK, appMessage.getMessage("success"), createPostRespDto);
    }

    private CreatePostRespDto createPost(PostDto postDto, AppUser appUser) throws Exception {
        Post post = postRepo.findByIdAndAppUser(postDto.getId(), appUser).orElse(new Post());
        MtSubBtype mtSubBtype = mtSubBtypeRepo.findById(postDto.getMtSubBTypeId()).orElseThrow(() -> new Exception(appMessage.getMessage("data.not.found")));
        MtEstAmount mtEstAmount = mtEstAmountRepo.findById(postDto.getMtEstAmountId()).orElseThrow(() -> new Exception(appMessage.getMessage("data.not.found")));
        MtEstPart mtEstPart = mtEstPartRepo.findById(postDto.getMtEstPartId()).orElseThrow(() -> new Exception(appMessage.getMessage("data.not.found")));
        MtCategory mtCategory = mtCategoryRepo.findById(postDto.getMtCategoryId()).orElseThrow(() -> new Exception(appMessage.getMessage("data.not.found")));
        post.setAppUser(appUser);
        post.setMtSubBtype(mtSubBtype);
        post.setBType(postDto.getBType());
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setIsPhNoHidden(postDto.getIsPhNoHidden());
        post.setOccupation(postDto.getOccupation());
        post.setPostAddress(convertPostAddressDtoToEntity(postDto.getPostAddressDto(), post));
        post.setMtCategory(mtCategory);
        post.setMtEstAmount(mtEstAmount);
        post.setMtEstPart(mtEstPart);
        post = postRepo.save(post);
        String uploadLink = createVideo(appUser, postDto.getVideoDto(), post);
        return new CreatePostRespDto(post.getId(), uploadLink);
    }

    private PostAddress convertPostAddressDtoToEntity(PostAddressDto postAddressDto, Post post) throws Exception {
        PostAddress postAddress;
        if (post.getPostAddress() == null) {
            postAddress = new PostAddress();
        } else {
            postAddress = post.getPostAddress();
        }
        MtCountry mtCountry = mtCountryRepo.findById(postAddressDto.getMtCountryId()).orElseThrow(() -> new Exception(appMessage.getMessage("data.not.found")));
        postAddress.setCity(postAddress.getCity());
        postAddress.setDistrict(postAddress.getDistrict());
        postAddress.setState(postAddress.getState());
        postAddress.setTown(postAddress.getTown());
        postAddress.setMtCountry(mtCountry);
        return postAddress;
    }

    private String createVideo(AppUser appUser, FileDto fileDto, Post post) throws Exception {
        Optional<FileEntity> optionalFileEntity = fileEntityRepo.findByRefIdAndFileType(post.getId(), FileType.POST);
        if (optionalFileEntity.isPresent()) {
            vimeoService.delete(optionalFileEntity.get().getUuid());
            fileEntityRepo.deleteByIdManual(optionalFileEntity.get().getUuid());
        }
        VimeoRespDto vimeoRespDto = vimeoService.resumableUpload(fileDto);
        FileEntity fileEntity = new FileEntity();
        fileEntity.setUuid(vimeoRespDto.getEndPoint());
        fileEntity.setFileType(FileType.POST);
        fileEntity.setName(fileDto.getName());
        fileEntity.setRefId(post.getId());
        fileEntity.setSize(fileDto.getSize());
        fileEntity.setType(fileDto.getType());
        fileEntityRepo.save(fileEntity);
        return vimeoRespDto.getUploadLink();
    }

    @Override
    public ApiResponse getCategories() {
        List<MtCategory> mtCategoryList = mtCategoryRepo.findByIsActiveTrueOrderBySeqAsc();
        return new ApiResponse(HttpStatus.OK, appMessage.getMessage("success"), mtCategoryList.stream().map(MtCategoryDto::new).collect(Collectors.toList()));
    }

    @Override
    public ApiResponse getBtypes() {
        List<MtBtype> mtBtypes = mtBTypeRepo.findByIsActiveTrueOrderBySeqAsc();
        return new ApiResponse(HttpStatus.OK, appMessage.getMessage("success"), mtBtypes.stream().map(MtBtypeDto::new).collect(Collectors.toList()));
    }

    @Override
    public ApiResponse getEstPartners() {
        List<MtEstPart> mtEstParts = mtEstPartRepo.findByIsActiveTrueOrderBySeqAsc();
        return new ApiResponse(HttpStatus.OK, appMessage.getMessage("success"), mtEstParts.stream().map(MtEstPartDto::new).collect(Collectors.toList()));
    }

    @Override
    public ApiResponse getEstAmounts() {
        List<MtEstAmount> mtEstAmounts = mtEstAmountRepo.findByIsActiveTrueOrderBySeqAsc();
        return new ApiResponse(HttpStatus.OK, appMessage.getMessage("success"), mtEstAmounts.stream().map(MtEstAmountDto::new).collect(Collectors.toList()));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ApiResponse uploadVidep(FileDto fileDto) throws Exception {
        ApiResponse apiResponse = ApiResponse.getFailureResponse();
        AppUser appUser = Utility.getApplicationUserFromAuthentication(appUserRepo);
        Post post = postRepo.findByIdAndAppUserAndIsActiveTrue(fileDto.getRefId(), appUser).orElseThrow(() -> new Exception(appMessage.getMessage("data.not.found")));
        Optional<FileEntity> optionalFileEntity = fileEntityRepo.findByRefIdAndFileType(post.getId(), FileType.POST);
        if (optionalFileEntity.isPresent()) {
            vimeoService.delete(optionalFileEntity.get().getUuid());
            fileEntityRepo.deleteByIdManual(optionalFileEntity.get().getUuid());
        }
        VimeoRespDto vimeoRespDto = vimeoService.resumableUpload(fileDto);
        FileEntity fileEntity = new FileEntity();
        fileEntity.setUuid(vimeoRespDto.getEndPoint());
        fileEntity.setFileType(FileType.POST);
        fileEntity.setName(fileDto.getName());
        fileEntity.setRefId(post.getId());
        fileEntity.setSize(fileDto.getSize());
        fileEntity.setType(fileDto.getType());
        fileEntityRepo.save(fileEntity);
        apiResponse.setStatus(HttpStatus.OK);
        apiResponse.setMessage(appMessage.getMessage("success"));
        apiResponse.setData(vimeoRespDto);
        return apiResponse;
    }

    @Override
    public ApiResponse getTransCodeStatus(Long id) throws Exception {
        ApiResponse apiResponse = ApiResponse.getFailureResponse();
        AppUser appUser = Utility.getApplicationUserFromAuthentication(appUserRepo);
        Post post = postRepo.findByIdAndAppUserAndIsActiveTrue(id, appUser).orElseThrow(() -> new Exception(appMessage.getMessage("data.not.found")));
        Optional<FileEntity> optionalFileEntity = fileEntityRepo.findByRefIdAndFileType(post.getId(), FileType.POST);
        if (optionalFileEntity.isPresent()) {
            apiResponse.setStatus(HttpStatus.OK);
            apiResponse.setMessage(appMessage.getMessage("success"));
            apiResponse.setData(vimeoService.getTransCodeStatus(optionalFileEntity.get().getUuid()));
        }
        return apiResponse;
    }

    @Override
    public ApiResponse getPosts(PostDto postDto) throws Exception {
        ApiResponse apiResponse = ApiResponse.getFailureResponse();
        AppUser appUser = Utility.getApplicationUserFromAuthentication(appUserRepo);
        Page<Post> posts = getPostsUsingCriteriaBuilder(postDto, appUser);
        List<PostDto> postDtos = getPostDtos(posts.getContent(), appUser, null);
        apiResponse.setStatus(HttpStatus.OK);
        apiResponse.setMessage(appMessage.getMessage("success"));
        apiResponse.setData(new PageImpl<>(postDtos, posts.getPageable(), posts.getTotalElements()));
        return apiResponse;
    }

    private List<PostDto> getPostDtos(List<Post> posts, AppUser appUser, List<PostResp> postRespsDepends) throws Exception {
        List<FileEntity> videos = fileEntityRepo.findByRefIdInAndFileType(posts.stream().map(BaseEntity::getId).collect(Collectors.toList()), FileType.POST);
        if (posts.size() != videos.size()) {
            throw new Exception(appMessage.getMessage("data.not.found"));
        }
        List<PostResp> postResps;
        if (postRespsDepends == null) {
            postResps = postRespRepo.findByPostInAndAppUser(posts, appUser);
        } else {
            postResps = postRespsDepends;
        }
        List<Long> appUserIds = posts.stream().map(i -> i.getAppUser().getId()).collect(Collectors.toList());
        List<FileEntity> profilePics = fileEntityRepo.findByRefIdInAndFileType(appUserIds, FileType.PROFILE_PIC);
        List<PostDto> postDtos = posts.stream().map(post -> new PostDto(post,
                videos.stream().filter(i -> i.getRefId().longValue() == post.getId().longValue()).findFirst().get().getUuid(), Boolean.FALSE,
                postResps.stream().filter(i -> i.getPost().getId().longValue() == post.getId().longValue()).findFirst(), profilePics, s3Service)).collect(Collectors.toList());
        return postDtos;
    }

    public Page<Post> getPostsUsingCriteriaBuilder(PostDto postDto, AppUser loggedInUser) {
        int page = postDto.getPage();
        String search = postDto.getSearch();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Post> criteriaQuery = cb.createQuery(Post.class);
        Root<Post> root = criteriaQuery.from(Post.class);
        Path<Object> appUser = root.get("appUser");
        root.alias("rootAlias");
        List<Predicate> predicateList = new ArrayList<>();

        Predicate statusPredicate;
        if (postDto.getIsOwnPosts()) {
            statusPredicate = cb.and(cb.equal(root.get("status"), PostStatus.DONE), cb.equal(appUser.get("id"), loggedInUser.getId()));
        } else {
            statusPredicate = cb.and(cb.equal(root.get("status"), PostStatus.DONE), cb.notEqual(appUser.get("id"), loggedInUser.getId()));
        }
        predicateList.add(statusPredicate);

        Predicate isActiveTrue = cb.and(cb.equal(appUser.get("isActive"), Boolean.TRUE), cb.equal(root.get("isActive"), Boolean.TRUE));
        predicateList.add(isActiveTrue);

        if (search != null && !search.isEmpty()) {
            search = search.replaceAll("\\s", "");
            Expression<String> function = cb.function("replace",
                    String.class, cb.concat(cb.upper(appUser.get("firstName")), cb.upper(appUser.get("lastName"))), cb.literal(" "),
                    cb.literal(""));
            Predicate searchQueryPredicate = cb.or(cb.like(function, "%" + search.toUpperCase() + "%"), cb.like(cb.upper(root.get("title")), "%" + search.toUpperCase() + "%")
                                                    , cb.like(cb.upper(appUser.get("email")), "%" + search.toUpperCase() + "%"));
            predicateList.add(searchQueryPredicate);
        }

        if (postDto.getMtCategoryId() != null && postDto.getMtCategoryId() != 0L) {
            Predicate mtCategoryId = cb.or(cb.equal(root.get("mtCategory").get("id"), postDto.getMtCategoryId()));
            predicateList.add(mtCategoryId);
        }

        if (postDto.getMtEstAmountId() != null && postDto.getMtEstAmountId() != 0L) {
            Predicate mtEstAmount = cb.or(cb.equal(root.get("mtEstAmount").get("id"), postDto.getMtEstAmountId()));
            predicateList.add(mtEstAmount);
        }

        if (postDto.getMtEstPartId() != null && postDto.getMtEstPartId() != 0L) {
            Predicate mtEstPart = cb.or(cb.equal(root.get("mtEstPart").get("id"), postDto.getMtEstPartId()));
            predicateList.add(mtEstPart);
        }

        if (postDto.getMtSubBTypeId() != null && postDto.getMtSubBTypeId() != 0L) {
            Predicate mtSubBtype = cb.or(cb.equal(root.get("mtSubBtype").get("id"), postDto.getMtSubBTypeId()));
            predicateList.add(mtSubBtype);
        }

        if (postDto.getMtCountryId() != null && postDto.getMtCountryId() != 0L) {
            Predicate mtEstPart = cb.or(cb.equal(root.get("postAddress").get("mtCountry").get("id"), postDto.getMtCountryId()));
            predicateList.add(mtEstPart);
        }

        criteriaQuery.where(predicateList.toArray(new Predicate[0]));
        if (search != null && !search.isEmpty()) {
            criteriaQuery.orderBy(Arrays.asList(cb.asc(root.get("appUser").get("firstName")), cb.asc(root.get("appUser").get("lastName"))));
        } else {
            criteriaQuery.orderBy(cb.desc(root.get("createdOn")));
        }
        CriteriaQuery<Long> criteriaQueryForCount = cb.createQuery(Long.class);
        Root<Post> root1 = criteriaQueryForCount.from(criteriaQuery.getResultType());
        root1.alias("rootAlias");
        criteriaQueryForCount.select(cb.count(root1));
        criteriaQueryForCount.where(predicateList.toArray(new Predicate[0]));

        Long total = entityManager.createQuery(criteriaQueryForCount).getSingleResult();

        TypedQuery<Post> typedQuery = entityManager.createQuery(criteriaQuery);

        if (page == 0) typedQuery.setFirstResult(page);
        else typedQuery.setFirstResult(page * Constants.PAGE_SIZE);
        typedQuery.setMaxResults(Constants.PAGE_SIZE);

        List<Post> posts = typedQuery.getResultList();
        return new PageImpl<>(posts, PageRequest.of(page, Constants.PAGE_SIZE), total);
    }

    @Override
    public ApiResponse confirmVideoUpload(Long id) throws Exception {
        AppUser appUser = Utility.getApplicationUserFromAuthentication(appUserRepo);
        Post post = postRepo.findByIdAndAppUserAndIsActiveTrue(id, appUser).orElseThrow(() -> new Exception(appMessage.getMessage("data.not.found")));
        post.setStatus(PostStatus.DONE);
        postRepo.save(post);
        return ApiResponse.getSuccessResponse();
    }

    @Override
    public ApiResponse getPostDetails(Long id) throws Exception {
        PostDto postDto;
        try {
            AppUser loggedInUser = Utility.getApplicationUserFromAuthentication(appUserRepo);
            Post post = postRepo.findByIdAndIsActiveTrue(id).orElseThrow(() -> new Exception(appMessage.getMessage("data.not.found")));
            FileEntity video = fileEntityRepo.findByRefIdAndFileType(post.getId(), FileType.POST).orElseThrow(() -> new Exception(appMessage.getMessage("data.not.found")));
            Optional<PostResp> postResps = postRespRepo.findByPostAndAppUser(post,loggedInUser);
            Optional<FileEntity> profilePics = fileEntityRepo.findByRefIdAndFileType(post.getAppUser().getId(), FileType.PROFILE_PIC);
            postDto = new PostDto(post, video.getUuid(), Boolean.TRUE, postResps,profilePics.isPresent() ? Arrays.asList(profilePics.get()) : new ArrayList<>(), s3Service);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return new ApiResponse(HttpStatus.OK, appMessage.getMessage("success"), postDto);
    }

    @Override
    public ApiResponse getProfileDetails(Long id) throws Exception {
        AppUser appUser;
        if (id != null && !id.equals(0L)) {
            Optional<AppUser> optionalAppUser = appUserRepo.findById(id);
            if (!optionalAppUser.isPresent()) {
                throw new Exception(appMessage.getMessage("data.not.found"));
            }
            appUser = optionalAppUser.get();
        } else {
            appUser = Utility.getApplicationUserFromAuthentication(appUserRepo);
        }
        return new ApiResponse(HttpStatus.OK, appMessage.getMessage("success"), new SignUpDto(appUser));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ApiResponse updateProfileDetails(SignUpDto signUpDto) throws Exception{
        AppUser appUser = Utility.getApplicationUserFromAuthentication(appUserRepo);
        if (!new BCryptPasswordEncoder().matches(signUpDto.getPassword(), appUser.getPassword())) {
            return new ApiResponse(HttpStatus.UNAUTHORIZED, appMessage.getMessage("invalid.credentials"), null);
        }
        appUser.setFirstName(signUpDto.getFirstName());
        appUser.setLastName(signUpDto.getLastName());
        appUser.setPermanentAddress(signUpDto.getPermanentAddress());
        appUser.setTempAddress(signUpDto.getTempAddress());
        appUser.setOccupation(signUpDto.getOccupation());
        appUser.setAboutMe(signUpDto.getAboutMe());
        appUser.setPhoneNo(signUpDto.getPhoneNo());
        appUser.setDateOfBirth(LocalDate.parse(signUpDto.getDateOfBirth(), DateTimeFormatter.ofPattern(Constants.DATE)));
        appUser.setIsActive(signUpDto.getIsActive());
        appUser = appUserRepo.save(appUser);
        FileDto fileDto = signUpDto.getProfilePicDto();
        if (fileDto != null) {
            fileDto = uploadProfilePic(appUser, fileDto);
            return new ApiResponse(HttpStatus.OK, appMessage.getMessage("success"), fileDto.getDocumentUrl());
        }
        return ApiResponse.getSuccessResponse();
    }

    private FileDto uploadProfilePic(AppUser appUser, FileDto fileDto) throws Exception {
        Optional<FileEntity> optionalFileEntity = fileEntityRepo.findByRefIdAndFileType(appUser.getId(), FileType.PROFILE_PIC);
        if (optionalFileEntity.isPresent()) {
            fileDto.setUuid(optionalFileEntity.get().getUuid());
        }
        fileDto = s3Service.getPreSignedUrlForUpload(fileDto);
        if (!optionalFileEntity.isPresent()){
            FileEntity fileEntity = new FileEntity();
            fileEntity.setUuid(fileDto.getUuid());
            fileEntity.setRefId(appUser.getId());
            fileEntity.setName(fileDto.getName());
            fileEntity.setType(fileDto.getType());
            fileEntity.setSize(fileDto.getSize());
            fileEntity.setFileType(FileType.PROFILE_PIC);
            fileEntityRepo.save(fileEntity);
        }
        return fileDto;
    }

    @Override
    public ApiResponse makeUserInActive() throws Exception {
        AppUser appUser = Utility.getApplicationUserFromAuthentication(appUserRepo);
        appUser.setIsActive(Boolean.FALSE);
        appUserRepo.save(appUser);
        return ApiResponse.getSuccessResponse();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ApiResponse saveOrUnsavePost(Long postId) throws Exception {
        PostResp postResp = commonMethodForSaveAndReq(postId);
        postResp.setIsSaved(!postResp.getIsSaved());
        if (postResp.getIsSaved()) {
            postResp.setSavedAt(LocalDateTime.now());
        }
        postRespRepo.save(postResp);
        return ApiResponse.getSuccessResponse();
    }

    private PostResp commonMethodForSaveAndReq(Long postId) throws Exception {
        AppUser appUser = Utility.getApplicationUserFromAuthentication(appUserRepo);
        Post post = postRepo.findByIdAndAppUserNotAndIsActiveTrue(postId, appUser).orElseThrow(() -> new Exception(appMessage.getMessage("cannot.save.own.posts")));
        PostResp postResp = postRespRepo.findByPostAndAppUser(post, appUser).orElse(new PostResp());
        postResp.setPost(post);
        postResp.setAppUser(appUser);
        return postResp;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ApiResponse sendReqToPost(Long postId) throws Exception {
        PostResp postResp = commonMethodForSaveAndReq(postId);
        if (!postResp.getReqStatus().equals(PostRequest.NOT_YET_SENT)) {
            throw new Exception(appMessage.getMessage("req.already.sent"));
        }
        postResp.setReqStatus(PostRequest.SENT);
        postResp.setReqSentAt(LocalDateTime.now());
        postRespRepo.save(postResp);
        return ApiResponse.getSuccessResponse();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ApiResponse reportPost(Long postId, String reason) throws Exception {
        AppUser appUser = Utility.getApplicationUserFromAuthentication(appUserRepo);
        Post post = postRepo.findByIdAndAppUserNotAndIsActiveTrue(postId, appUser).orElseThrow(() -> new Exception(appMessage.getMessage("cannot.report.to.own.post")));
        Optional<PostReport> optionalPostReport = postReportRepo.findByPostAndAppUser(post, appUser);
        if (!optionalPostReport.isPresent()) {
            PostReport postReport = new PostReport(post, appUser, reason);
            postReportRepo.save(postReport);
        }
        return ApiResponse.getSuccessResponse();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ApiResponse deletehisOwnPost(Long postId) throws Exception {
        AppUser appUser = Utility.getApplicationUserFromAuthentication(appUserRepo);
        Post post = postRepo.findByIdAndAppUser(postId, appUser).orElseThrow(() -> new Exception(appMessage.getMessage("cannot.delete.others.post")));
        post.setIsActive(Boolean.FALSE);
        postRepo.save(post);
        return ApiResponse.getSuccessResponse();
    }

    @Override
    public ApiResponse getOthersPosts(Long id, Integer page) throws Exception {
        Pageable pageable = PageRequest.of(page, Constants.PAGE_SIZE);
        AppUser appUser = appUserRepo.findById(id).orElseThrow(() -> new Exception(appMessage.getMessage("data.not.found")));
        Page<Post> posts = postRepo.findByAppUserAndIsActiveTrue(appUser, pageable);
        List<PostDto> postDtos = getPostDtos(posts.getContent(), Utility.getApplicationUserFromAuthentication(appUserRepo), null);
        Page<PostDto> result = new PageImpl<>(postDtos, pageable, posts.getTotalElements());
        return new ApiResponse(HttpStatus.OK, appMessage.getMessage("success"), result);
    }

    @Override
    public ApiResponse getSavedOrRequestedPosts(Integer page, Boolean isSaved) throws Exception {
        Pageable pageable = PageRequest.of(page, Constants.PAGE_SIZE);
        AppUser appUser = Utility.getApplicationUserFromAuthentication(appUserRepo);
        Page<PostResp> postResps;
        if (isSaved) {
            postResps = postRespRepo.findByPostIsActiveTrueAndAppUserAndIsSavedTrueOrderBySavedAtDesc(appUser, pageable);
        } else {
            postResps = postRespRepo.findByPostIsActiveTrueAndAppUserAndReqStatusOrderByReqSentAtDesc(appUser, PostRequest.ACCEPTED, pageable);
        }
        List<PostDto> postDtos = getPostDtos(postResps.getContent().stream().map(PostResp::getPost).collect(Collectors.toList()), Utility.getApplicationUserFromAuthentication(appUserRepo), postResps.getContent());
        Page<PostDto> result = new PageImpl<>(postDtos, pageable, postResps.getTotalElements());
        return new ApiResponse(HttpStatus.OK, appMessage.getMessage("success"), result);
    }

    @Override
    public ApiResponse getRequestsRecieved(Integer page) throws Exception {
        Pageable pageable = PageRequest.of(page, Constants.PAGE_SIZE);
        AppUser appUser = Utility.getApplicationUserFromAuthentication(appUserRepo);
        Page<PostResp> postResps = postRespRepo.findByPostIsActiveTrueAndPostAppUserAndReqStatusNotOrderByReqSentAtDesc(appUser, PostRequest.NOT_YET_SENT, pageable);
        List<PostDto> postDtos = postResps.getContent().stream().map(PostDto::new).collect(Collectors.toList());
        Page<PostDto> result = new PageImpl<>(postDtos, pageable, postResps.getTotalElements());
        return new ApiResponse(HttpStatus.OK, appMessage.getMessage("success"), result);
    }

    @Override
    public ApiResponse acceptOrRejRequests(Long id, PostRequest postRequest) throws Exception {
        if (!postRequest.equals(PostRequest.ACCEPTED) && !postRequest.equals(PostRequest.REJECTED)) {
            throw new Exception(appMessage.getMessage("data.not.found"));
        }
        AppUser appUser = Utility.getApplicationUserFromAuthentication(appUserRepo);
        PostResp postResp = postRespRepo.findByPostIsActiveTrueAndIdAndPostAppUserAndReqStatusNot(id, appUser, PostRequest.NOT_YET_SENT).orElseThrow(() -> new Exception(appMessage.getMessage("data.not.found")));
        postResp.setReqStatus(postRequest);
        if (postRequest.equals(PostRequest.ACCEPTED)) {
            postResp.setAcceptedAt(LocalDateTime.now());
        } else if (postRequest.equals(PostRequest.REJECTED)) {
            postResp.setRejectedAt(LocalDateTime.now());
        }
        postRespRepo.save(postResp);
        return ApiResponse.getSuccessResponse();
    }

    @Override
    public ApiResponse getInsiders(Integer page) throws Exception {
        AppUser appUser = Utility.getApplicationUserFromAuthentication(appUserRepo);
        List<Long> appUsersIds = postRespRepo.findByPostAppUserAndReqStatus(appUser.getId(), PostRequest.ACCEPTED.name(), Constants.PAGE_SIZE, page*Constants.PAGE_SIZE);
        Long count = postRespRepo.countOfInsiders(appUser.getId(), PostRequest.ACCEPTED.name());
        List<AppUser> appUsers = appUserRepo.findAllById(appUsersIds);
        List<FileEntity> profilePics = fileEntityRepo.findByRefIdInAndFileType(appUsers.stream().map(i -> i.getId()).collect(Collectors.toList()), FileType.PROFILE_PIC);
        List<InsiderDto> insiderDtos = appUsers.stream().map(i -> new InsiderDto(i, profilePics, s3Service)).collect(Collectors.toList());
        return new ApiResponse(HttpStatus.OK, appMessage.getMessage("success"), new PageImpl<>(insiderDtos, PageRequest.of(page, Constants.PAGE_SIZE), count));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ApiResponse unknown(Long id) throws Exception {
        AppUser loggedInUSer = Utility.getApplicationUserFromAuthentication(appUserRepo);
        AppUser appUser = appUserRepo.findById(id).orElseThrow(() -> new Exception(appMessage.getMessage("data.not.found")));
        postRespRepo.updatePostRespRequestStatusToRejected(PostRequest.REJECTED.name(), appUser.getId(), PostRequest.ACCEPTED.name(), loggedInUSer.getId());
        return ApiResponse.getSuccessResponse();
    }
}
