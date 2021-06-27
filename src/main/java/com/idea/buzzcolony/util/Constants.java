package com.idea.buzzcolony.util;

public class Constants {

    public static final String permitURLs[] = {"/api/v1/user/**", "/api/v1/master/**", "/ws", "/ws/**"};
    public static  final String AUTH_HEADER_TOKEN_PREFIX = "Bearer";
    public static final String AUTH_HEADER_NAME = "Authorization";
    public static final long JWT_VALIDITY = 1 * 24 * 60 * 60 * 1000;// 1 days Validity
    public static final long LOGIN_VALIDITY = 7 * 24 * 60 * 60 * 1000;// 1 days Validity
    public static final String DATE = "yyyy-MM-dd";
    public static final int PAGE_SIZE = 10;
    public static final String BASE_URL = "https://www.buzzcolony.com";
    public static final String Email_verify = BASE_URL + "/email-verify?token=";
    public static final String Forget_pass = BASE_URL + "/forgot-password-verify?token=";
}
