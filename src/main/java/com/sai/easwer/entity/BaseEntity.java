package com.sai.easwer.entity;

import java.util.UUID;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity
{
    @Id
    private UUID id;

    private int version;

    private Long createdTime;

    private Long updatedTime;

    public UUID getId()
    {
        return id;
    }

    public void setId(UUID id)
    {
        this.id = id;
    }

    public int getVersion()
    {
        return version;
    }

    public void setVersion(int version)
    {
        this.version = version;
    }

    public Long getCreatedTime()
    {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime)
    {
        this.createdTime = createdTime;
    }

    public Long getUpdatedTime()
    {
        return updatedTime;
    }

    public void setUpdatedTime(Long updatedTime)
    {
        this.updatedTime = updatedTime;
    }
}
