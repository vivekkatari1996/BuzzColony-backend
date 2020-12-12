package com.idea.buzzcolony.util;

public class Constants {

    public static final String permitURLs[] = {"/api/v1/user/**", "/api/v1/master/**", "/ws", "/ws/**"};
    public static  final String AUTH_HEADER_TOKEN_PREFIX = "Bearer";
    public static final String AUTH_HEADER_NAME = "Authorization";
    public static final long JWT_VALIDITY = 1 * 24 * 60 * 60 * 1000;// 1 days Validity
}
