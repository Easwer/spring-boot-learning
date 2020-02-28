package com.sai.easwer.constants;

public class SecurityConstants {

    public static final int IDLE_TIMEOUT_MIN = 15;

    public static final int IDLE_TIMEOUT_MAX = 120;

    public static final int ACCOUNT_EXPIRY_MIN = 0;

    public static final int ACCOUNT_EXPIRY_MAX = 120;

    public static final int PASSWORD_EXPIRY_MIN = 30;

    public static final int PASSWORD_EXPIRY_MAX = 120;

    public static final int PASSWORD_MIN_LENGTH = 8;

    public static final int PASSWORD_MAX_LENGTH = 120;

    public static final String USERNAME_PATTERN = "[^a-zA-Z0-9]";

    public static final String NAME_PATTERN = "[^a-zA-Z]";
  
    public static final String PASSWORD_PATTERN =
        "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[\\[\\]()!@#$%^&*])(?=\\S+$).{8,}";
    
}