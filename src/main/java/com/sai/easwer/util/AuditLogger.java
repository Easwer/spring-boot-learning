package com.sai.easwer.util;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sai.easwer.constants.AuditLogType;
import com.sai.easwer.constants.Modules;
import com.sai.easwer.constants.ResponseStatus;
import com.sai.easwer.entity.AuditLog;
import com.sai.easwer.entity.UserDetails;
import com.sai.easwer.entity.UserSession;
import com.sai.easwer.repository.AuditLogRepository;
import com.sai.easwer.repository.UserRepository;

/**
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-14 15:12:28
 * @modify date 2020-02-17 20:16:44
 * @desc [description]
 */
@Component
public class AuditLogger {
    @Autowired
    private AuditLogRepository auditLogRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * 
     * @param description
     * @param modules
     */
    public void auditLog(final String description, final Modules modules) {
        auditLog(description, modules, AuditLogType.SYSTEM, null, null, null);
    }

    /**
     * 
     * @param description
     * @param modules
     * @param type
     */
    public void auditLog(final String description, final Modules modules, final AuditLogType type) {
        auditLog(description, modules, type, null, null, null);
    }

    /**
     * 
     * @param description
     * @param modules
     * @param type
     * @param responseStatus
     */
    public void auditLog(final String description, final Modules modules, final AuditLogType type,
            final ResponseStatus responseStatus) {
        auditLog(description, modules, type, responseStatus, null, null);
    }

    /**
     * 
     * @param description
     * @param modules
     * @param type
     * @param responseStatus
     * @param userSession
     */
    public void auditLog(final String description, final Modules modules, final AuditLogType type,
            final ResponseStatus responseStatus, final UserSession userSession) {
        final Optional<UserDetails> user = userRepository.findById(userSession.getUserId());
        auditLog(description, modules, type, responseStatus, user.get(), userSession);
    }

    /**
     * 
     * @param description
     * @param modules
     * @param type
     * @param userSession
     */
    public void auditLog(final String description, final Modules modules, final AuditLogType type,
            final UserSession userSession) {
        final Optional<UserDetails> user = userRepository.findById(userSession.getUserId());
        auditLog(description, modules, type, null, user.get(), userSession);
    }

    /**
     * 
     * @param description
     * @param modules
     * @param type
     * @param responseStatus
     * @param user
     */
    public void auditLog(final String description, final Modules modules, final AuditLogType type,
            final ResponseStatus responseStatus, final UserDetails user) {
        auditLog(description, modules, type, responseStatus, user, null);
    }

    /**
     * 
     * @param description
     * @param modules
     * @param type
     * @param user
     */
    public void auditLog(final String description, final Modules modules, final AuditLogType type,
            final UserDetails user) {
        auditLog(description, modules, type, null, user, null);
    }

    /**
     * 
     * @param description
     * @param modules
     * @param type
     * @param userId
     */
    public void auditLog(final String description, final Modules modules, final AuditLogType type, final UUID userId) {
        final Optional<UserDetails> user = userRepository.findById(userId);

        auditLog(description, modules, type, user.get());
    }

    /**
     * 
     * @param description
     * @param modules
     * @param type
     * @param responseStatus
     * @param user
     * @param userSession
     */
    public void auditLog(final String description, final Modules modules, final AuditLogType type,
            final ResponseStatus responseStatus, final UserDetails user, final UserSession userSession) {
        final AuditLog auditLog = new AuditLog();

        auditLog.setId(UUID.randomUUID());
        auditLog.setDescription(description);
        auditLog.setModules(modules);

        if (null != userSession) {
            auditLog.setIpAddress(userSession.getIpAddress());
        }

        if (null != user) {
            auditLog.setUsername(user.getUsername());
        }

        if (null != responseStatus) {
            auditLog.setStatus(responseStatus);
        }

        auditLogRepository.save(auditLog);
    }

}