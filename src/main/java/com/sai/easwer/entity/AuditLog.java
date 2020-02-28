package com.sai.easwer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sai.easwer.constants.AuditLogType;
import com.sai.easwer.constants.Modules;
import com.sai.easwer.constants.ResponseStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-14 15:12:28
 * @modify date 2020-02-28 11:55:31
 * @Table audit_log
 * @Description Entity class used to store audit logs.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "audit_log")
public class AuditLog extends BaseEntity
{
    private static final long serialVersionUID = -2440675000750394264L;

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

}