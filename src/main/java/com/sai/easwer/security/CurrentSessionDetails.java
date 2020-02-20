package com.sai.easwer.security;

/**
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-14 14:57:17
 * @modify date 2020-02-14 14:58:03
 * @desc [description]
 */
public class CurrentSessionDetails {

    private static final ThreadLocal<String> userHolder = new ThreadLocal<>();

    private static final String DEFAULT_USER = "System";

    public static void login(String user) {
        userHolder.set(user);
    }

    public static void logout() {
        userHolder.remove();
    }

    public static String getUsername() {
        if (null == userHolder.get() || userHolder.get().isEmpty()) {
            return DEFAULT_USER;
        }
        return userHolder.get();
    }
}