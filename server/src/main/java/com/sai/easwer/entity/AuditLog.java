package com.sai.easwer.entity;

import com.sai.easwer.model.AuditLogType;
import com.sai.easwer.model.AuditLogModules;
import com.sai.easwer.model.ResponseStatus;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entity class used to store audit logs.
 * 
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-14 15:12:28
 * @modify date 2020-03-10 18:05:08
 * @Table audit_log
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "audit_log")
public class AuditLog extends BaseEntity {
    private static final long serialVersionUID = -2440675000750394264L;

    @Column(name = "type")
    private AuditLogType type = AuditLogType.SYSTEM;

    @Column(name = "description")
    private String description;

    @Column(name = "userId")
    private UUID userId;

    @Column(name = "username")
    private String username;

    @Column(name = "ip")
    private String ipAddress;

    @Column(name = "status")
    private ResponseStatus status;

    @Column(name = "modules")
    private AuditLogModules modules;

}
