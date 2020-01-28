package com.sai.easwer.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_session")
public class UserSession
{
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

    public UUID getId()
    {
        return id;
    }

    public void setId(UUID id)
    {
        this.id = id;
    }

    public UUID getAuthToken()
    {
        return authToken;
    }

    public void setAuthToken(UUID authToken)
    {
        this.authToken = authToken;
    }

    public UUID getUserId()
    {
        return userId;
    }

    public void setUserId(UUID userId)
    {
        this.userId = userId;
    }

    public Long getStartedTime()
    {
        return startedTime;
    }

    public void setStartedTime(Long startedTime)
    {
        this.startedTime = startedTime;
    }

    public Long getLastAccsessTime()
    {
        return lastAccsessTime;
    }

    public void setLastAccsessTime(Long lastAccsessTime)
    {
        this.lastAccsessTime = lastAccsessTime;
    }

    public String getIpAddress()
    {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress)
    {
        this.ipAddress = ipAddress;
    }

}