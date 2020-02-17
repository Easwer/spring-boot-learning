package com.sai.easwer.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-14 15:12:28
 * @modify date 2020-02-14 15:12:49
 * @desc [description]
 */
@Entity
@Table(name = "user_session")
public class UserSession {
    
    @Id
    private UUID id;

    @Column(name = "auth_token")
    private UUID authToken;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "started_time")
    private Long startedTime;

    @Column(name = "last_accsess_time")
    private Long lastAccsessTime;

    @Column(name = "ip")
    private String ipAddress;

    /**
     * @return the id
     */
    public UUID getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(final UUID id) {
        this.id = id;
    }

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
     * @return the userId
     */
    public UUID getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(final UUID userId) {
        this.userId = userId;
    }

    /**
     * @return the startedTime
     */
    public Long getStartedTime() {
        return startedTime;
    }

    /**
     * @param startedTime the startedTime to set
     */
    public void setStartedTime(final Long startedTime) {
        this.startedTime = startedTime;
    }

    /**
     * @return the lastAccsessTime
     */
    public Long getLastAccsessTime() {
        return lastAccsessTime;
    }

    /**
     * @param lastAccsessTime the lastAccsessTime to set
     */
    public void setLastAccsessTime(final Long lastAccsessTime) {
        this.lastAccsessTime = lastAccsessTime;
    }

    /**
     * @return the ipAddress
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * @param ipAddress the ipAddress to set
     */
    public void setIpAddress(final String ipAddress) {
        this.ipAddress = ipAddress;
    }
}