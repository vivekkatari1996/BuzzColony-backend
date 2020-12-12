package com.idea.thought.util;

import com.idea.thought.model.base.AppUser;
import com.idea.thought.repo.AppUserRepo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.context.SecurityContextHolder;

public class Utility {

    private final static String secret = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.XIN2WY1zBS_ql1H-Dnz0wHrjYkZHjmvkdYAngL-vtN4";

    public static AppUser getApplicationUserFromAuthentication(AppUserRepo appUserRepo) throws Exception {
        AppUser appUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return appUser;
    }

    public static String getEmailFromToken(String token) {
        Jws<Claims> claims = Jwts.parser().setSigningKey(secret)
                .parseClaimsJws(token.replace(Constants.AUTH_HEADER_TOKEN_PREFIX, ""));
        String email = claims.getBody().getSubject();
        return email;
    }
}

