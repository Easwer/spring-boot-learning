package com.sai.easwer.constants;

import java.util.stream.Stream;

/**
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-24 21:46:53
 * @modify date 2020-02-28 15:43:20
 * @desc User Account Status
 */
public enum UserAccountStatus {
    ACTIVE("Active"),
    LOCKED("Locked"),
    BLOCKED("Blocked"),
    ACCOUNT_EXPIRED("Account Expired"),
    PASSWORD_EXPIRED("Password Expired"),
    DORMANCY_PERIOD_EXCEEDED("Dormancy Period Exceeded"),
    CHANGE_PASSWORD_ON_LOGIN("Change Password on Login");

    private String accountStatus;

    UserAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public static boolean contains(String accountStatus) {
        return Stream.of(UserAccountStatus.values()).anyMatch(v -> v.getAccountStatus().equals(accountStatus));

    }
}