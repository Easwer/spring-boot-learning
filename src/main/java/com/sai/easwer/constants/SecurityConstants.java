package com.sai.easwer.constants;

/**
 * Security constants.
 * 
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-03-09 18:08:22
 * @modify date 2020-03-10 18:04:14
 */
public class SecurityConstants {

    private SecurityConstants() {

    }

    public static final int IDLE_TIMEOUT_MIN = 15;

    public static final int IDLE_TIMEOUT_MAX = 120;

    public static final int ACCOUNT_EXPIRY_MIN = 0;

    public static final int ACCOUNT_EXPIRY_MAX = 120;

    public static final int PASSWORD_EXPIRY_MIN = 30;

    public static final int PASSWORD_EXPIRY_MAX = 120;

    public static final int DEFAULT_PASSWORD_MIN_LENGTH = 8;

    public static final int DEFAULT_PASSWORD_MAX_LENGTH = 120;

    public static final String PASSWORD_MIN_LENGTH = "passwordMinLength";

    public static final String PASSWORD_MAX_LENGTH = "passwordMaxLength";

    public static final String USERNAME_PATTERN = "[^a-zA-Z0-9]";

    public static final String NAME_PATTERN = "[^a-zA-Z]";

    public static final String PASSWORD_PATTERN =
            "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[\\[\\]()!@#$%^&*])(?=\\S+$).{8,}";

    public static final String FORCE_SPECIAL_CHAR = "forceSpecialChar";

    public static final String FORCE_CAPITAL_LETTER = "forceCapitalLetter";

    public static final String FORCE_NUMBER = "forceNumber";

    public static final String CAPTIAL_LETTER_REGEX = "(?=.*[A-Z])";

    public static final String SYMBOLS_REGEX = "(?=.*[@#$%])";

    public static final String SMALLL_LETTER_REGEX = "((?=.*[a-z])";

    public static final String NUMBER_REGEX = "(?=.*d)";

}
