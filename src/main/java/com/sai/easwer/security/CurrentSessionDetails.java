package com.sai.easwer.security;

/**
 * Manage Current Session Details.
 * 
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-14 14:57:17
 * @modify date 2020-03-10 18:07:00
 */
public class CurrentSessionDetails {

    private CurrentSessionDetails() {

    }

    private static final ThreadLocal<String> userHolder = new ThreadLocal<>();

    private static final String DEFAULT_USER = "System";

    /**
     * Stores the current username in cache during login.
     * 
     * @param user {@link String}
     */
    public static void login(String user) {
        userHolder.set(user);
    }

    /**
     * Clears the cache during logout.
     */
    public static void logout() {
        userHolder.remove();
    }

    /**
     * Gets current login username.
     * 
     * @return username {@link String}
     */
    public static String getUsername() {
        if (null == userHolder.get() || userHolder.get().isEmpty()) {
            return DEFAULT_USER;
        }
        return userHolder.get();
    }
}
