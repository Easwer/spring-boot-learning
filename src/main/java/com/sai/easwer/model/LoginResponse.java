package com.sai.easwer.model;

import com.sai.easwer.entity.UserDetails;
import java.util.UUID;

/**
 * Login REST API Response model.
 * 
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-14 15:12:28
 * @modify date 2020-03-10 18:06:13
 */
public class LoginResponse {

    private UUID authToken;

    private UserDetails user;

    private boolean isChangePassword = false;

    /**
     * @return the authToken
     */
    public UUID getAuthToken() {
        return authToken;
    }

    /**
     * @param authToken the authToken to set
     */
    public void setAuthToken(final UUID authToken) {
        this.authToken = authToken;
    }

    /**
     * @return the user
     */
    public UserDetails getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(final UserDetails user) {
        this.user = user;
    }

    /**
     * @return the isChangePassword
     */
    public boolean isChangePassword() {
        return isChangePassword;
    }

    /**
     * @param isChangePassword the isChangePassword to set
     */
    public void setChangePassword(final boolean isChangePassword) {
        this.isChangePassword = isChangePassword;
    }
}
