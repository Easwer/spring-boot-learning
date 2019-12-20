package com.sai.easwer.entity;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user_session")
public class UserSession
{
    @Id
    private UUID id;

    @Column(name = "session_id")
    private String sessionId;

    @OneToOne
    private UserDetails user;

    @Column(name = "started_time")
    private Timestamp startedTime;

    @Column(name = "last_accsess_time")
    private Timestamp lastAccsessTime;

    public UUID getId()
    {
        return id;
    }

    public void setId(UUID id)
    {
        this.id = id;
    }

    public String getSessionId()
    {
        return sessionId;
    }

    public void setSessionId(String sessionId)
    {
        this.sessionId = sessionId;
    }

    public UserDetails getUser()
    {
        return user;
    }

    public void setUser(UserDetails user)
    {
        this.user = user;
    }

    public Timestamp getStartedTime()
    {
        return startedTime;
    }

    public void setStartedTime(Timestamp startedTime)
    {
        this.startedTime = startedTime;
    }

    public Timestamp getLastAccsessTime()
    {
        return lastAccsessTime;
    }

    public void setLastAccsessTime(Timestamp lastAccsessTime)
    {
        this.lastAccsessTime = lastAccsessTime;
    }

}