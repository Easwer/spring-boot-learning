package com.sai.easwer.model;

import java.util.UUID;

import com.sai.easwer.entity.UserDetails;

public class LoginResponse {

    private UUID authToken;
    
    private UserDetails user;
    
    private boolean isChangePassword = false;

    public UUID getAuthToken() {
        return authToken;
    }

    public void setAuthToken(UUID authToken) {
        this.authToken = authToken;
    }

    public UserDetails getUser() {
        return user;
    }

    public void setUser(UserDetails user) {
        this.user = user;
    }

    public boolean isChangePassword() {
        return isChangePassword;
    }

    public void setChangePassword(boolean isChangePassword) {
        this.isChangePassword = isChangePassword;
    }

}