package com.sai.easwer.servermodel;

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
     * Get auth token.
     * @return the authToken {@link UUID}
     */
    public UUID getAuthToken() {
        return authToken;
    }

    /**
     * Set auth token.
     * @param authToken the authToken to set. {@link UUID}
     */
    public void setAuthToken(final UUID authToken) {
        this.authToken = authToken;
    }

    /**
     * Get the user details.
     * @return the user {@link UserDetails}
     */
    public UserDetails getUser() {
        return user;
    }

    /**
     * Set the user details.
     * @param user the user to set {@link UserDetails}
     */
    public void setUser(final UserDetails user) {
        this.user = user;
    }

    /**
     * Get change password flag.
     * @return the isChangePassword {@link Boolean}
     */
    public boolean isChangePassword() {
        return isChangePassword;
    }

    /**
     * Set change password flag.
     * @param isChangePassword the isChangePassword to set. {@link Boolean}
     */
    public void setChangePassword(final boolean isChangePassword) {
        this.isChangePassword = isChangePassword;
    }
}
