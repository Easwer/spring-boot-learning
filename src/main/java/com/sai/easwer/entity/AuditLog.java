package com.sai.easwer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sai.easwer.constants.AuditLogType;
import com.sai.easwer.constants.Modules;
import com.sai.easwer.constants.ResponseStatus;

@Entity
@Table(name = "audit_log")
public class AuditLog extends BaseEntity
{
    @Column(name = "type")
    private AuditLogType type = AuditLogType.SYSTEM;

    @Column(name = "description")
    private String description;

    @Column(name = "username")
    private String username;

    @Column(name = "ip")
    private String ipAddress;

    @Column(name = "status")
    private ResponseStatus status;

    @Column(name = "modules")
    private Modules modules;

    public AuditLogType getType()
    {
        return type;
    }

    public void setType(AuditLogType type)
    {
        this.type = type;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public ResponseStatus getStatus()
    {
        return status;
    }

    public void setStatus(ResponseStatus status)
    {
        this.status = status;
    }
    
    public String getIpAddress()
    {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress)
    {
        this.ipAddress = ipAddress;
    }

    public Modules getModules()
    {
        return modules;
    }

    public void setModules(Modules modules)
    {
        this.modules = modules;
    }

}