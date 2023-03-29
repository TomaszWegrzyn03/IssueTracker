package com.example.IssueTracker.user.security;

public class SecurityContants {
    public static final long EXPIRATION_DATE = 4504504;
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String JWT_TOKEN_HEADER = "Jwt-Token";
    public static final String TOKEN_CANT_BE_VERIFIED = "Token can't be verified";
    public static final String TOMASZ_LLC = "Tomasz, LCC";
    public static final String TOMASZ_ADMINISTRATION = "User Management Portal";
    public static final String AUTHORITIES = "Authorities";
    public static final String FORBIDDEN_MSG = "You need to login to acces this page";
    public static final String ACCES_DENIED_MSG = "You do not have permission to access this page";
    public static final String OPTIONS_HTTP_METHOD = "OPTIONS";
    public static final String[] PUBLIC_URLS = {"api/login", "api/register", "api/resetpassword/**", "api/image/**"};
}
