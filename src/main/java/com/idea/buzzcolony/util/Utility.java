package com.idea.buzzcolony.util;

import com.idea.buzzcolony.model.base.AppUser;
import com.idea.buzzcolony.repo.AppUserRepo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;

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

    public static String createToken(String email, long VALIDITY_TIME_MS) {
        String hash = RandomStringUtils.randomAlphabetic(64);
        String token = null;
        if (VALIDITY_TIME_MS != 0l) {
            Date date = new Date(System.currentTimeMillis() + VALIDITY_TIME_MS);
            token = Jwts.builder().setExpiration(date).setSubject(email).setIssuedAt(new Date(System.currentTimeMillis()))
                    .claim("email", email).claim("userKey", hash)
                    .signWith(SignatureAlgorithm.HS256, secret).compact();
        } else {
            token = Jwts.builder().setSubject(email).setIssuedAt(new Date(System.currentTimeMillis()))
                    .claim("email", email).claim("userKey", hash)
                    .signWith(SignatureAlgorithm.HS256, secret).compact();
        }
        return token;
    }
}

