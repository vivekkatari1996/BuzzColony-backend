package com.idea.buzzcolony.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.idea.buzzcolony.model.base.AppUser;
import com.idea.buzzcolony.repo.AppUserRepo;
import com.idea.buzzcolony.util.ApiResponse;
import com.idea.buzzcolony.util.Constants;
import com.idea.buzzcolony.util.Utility;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
This filter checks if there is a token in the Request service header and the token is not expired
it is applied to all the routes which are protected
*/
public class VerifyTokenFilter extends OncePerRequestFilter {

    private static final Logger classLogger = LoggerFactory.getLogger(VerifyTokenFilter.class);

    private AppUserRepo appUserRepo;

    public VerifyTokenFilter(AppUserRepo appUserRepo) {
        this.appUserRepo = appUserRepo;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String idToken = request.getHeader(Constants.AUTH_HEADER_NAME);
            if (idToken == null) {
                SecurityContextHolder.getContext().setAuthentication(null);
            } else {
                idToken = extractAndDecodeJwt(idToken);
                String username = Utility.getEmailFromToken(idToken);
                Optional<AppUser> optionalAppUser = appUserRepo.findByEmailIgnoreCaseAndIsActiveTrue(username);

                if (!optionalAppUser.isPresent()) {
                    throw new Exception("User not found");
                }

                List<GrantedAuthority> grantedAuthorities = getAuthorities();
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(optionalAppUser.get(), null, grantedAuthorities);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        } catch (ExpiredJwtException e) {
            SecurityContextHolder.clearContext();
            classLogger.error(e.getMessage());
            setResponse(response, "Session Expired", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST);
            return;
        }catch (Exception e) {
            SecurityContextHolder.clearContext();
            classLogger.error(e.getMessage());
            setResponse(response, e.getMessage(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST);
            return;
        }

        filterChain.doFilter(request, response);

    }

    private List<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        list.add(new SimpleGrantedAuthority("USER"));
        return list;
    }

    private void setResponse(HttpServletResponse response, String message, int httpStatusValue, HttpStatus httpStatus)
            throws IOException {
        response.setStatus(httpStatusValue);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(httpStatus);
        apiResponse.setStatusCode(httpStatusValue);
        apiResponse.setMessage(message);
        String jsonRespString = ow.writeValueAsString(apiResponse);
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().write(jsonRespString);
        response.getWriter().flush();
        response.getWriter().close();
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        for (String url : Constants.permitURLs) {
            if (request.getRequestURI().startsWith(url.replace("/**", ""))) {
                return true;
            }
        }
        return super.shouldNotFilter(request);
    }

    private String extractAndDecodeJwt(String token) {
        String tokenResult = token;

        if (token != null && token.startsWith("Bearer ")) {
            tokenResult = token.substring("Bearer ".length());
        }
        return tokenResult;
    }
}
