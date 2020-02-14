package com.sai.easwer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sai.easwer.constants.AuditLogType;
import com.sai.easwer.constants.Modules;
import com.sai.easwer.constants.ResponseStatus;

/**
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-14 15:12:28
 * @modify date 2020-02-14 15:12:28
 * @desc [description]
 */
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

    /**
     * @return the type
     */
    public AuditLogType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(final AuditLogType type) {
        this.type = type;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(final String username) {
        this.username = username;
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

    /**
     * @return the status
     */
    public ResponseStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(final ResponseStatus status) {
        this.status = status;
    }

    /**
     * @return the modules
     */
    public Modules getModules() {
        return modules;
    }

    /**
     * @param modules the modules to set
     */
    public void setModules(final Modules modules) {
        this.modules = modules;
    }
}