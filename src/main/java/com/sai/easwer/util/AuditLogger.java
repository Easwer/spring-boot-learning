package com.sai.easwer.util;

import com.sai.easwer.constants.AuditLogType;
import com.sai.easwer.constants.Modules;
import com.sai.easwer.constants.ResponseStatus;
import com.sai.easwer.entity.AuditLog;
import com.sai.easwer.entity.UserDetails;
import com.sai.easwer.entity.UserSession;
import com.sai.easwer.repository.AuditLogRepository;
import com.sai.easwer.repository.UserRepository;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Util to add audit logger.
 * 
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-14 15:12:28
 * @modify date 2020-02-17 20:16:44
 */
@Component
public class AuditLogger {
    @Autowired
    private AuditLogRepository auditLogRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Add new audit log entry.
     * 
     * @param description {@link String}
     * @param modules     {@link Modules}
     */
    public void auditLog(final String description, final Modules modules) {
        auditLog(description, modules, AuditLogType.SYSTEM, null, null, null);
    }

    /**
     * Add new audit log entry.
     * 
     * @param description {@link String}
     * @param modules     {@link Modules}
     * @param type        {@link AuditLogType}
     */
    public void auditLog(final String description, final Modules modules, final AuditLogType type) {
        auditLog(description, modules, type, null, null, null);
    }

    /**
     * Add new audit log entry.
     * 
     * @param description    {@link String}
     * @param modules        {@link Modules}
     * @param type           {@link AuditLogType}
     * @param responseStatus {@link ResponseStatus}
     */
    public void auditLog(final String description, final Modules modules, final AuditLogType type,
            final ResponseStatus responseStatus) {
        auditLog(description, modules, type, responseStatus, null, null);
    }

    /**
     * Add new audit log entry.
     * 
     * @param description    {@link String}
     * @param modules        {@link Modules}
     * @param type           {@link AuditLogType}
     * @param responseStatus {@link ResponseStatus}
     * @param userSession    {@link UserSession}
     */
    public void auditLog(final String description, final Modules modules, final AuditLogType type,
            final ResponseStatus responseStatus, final UserSession userSession) {
        final Optional<UserDetails> user = userRepository.findById(userSession.getUserId());
        auditLog(description, modules, type, responseStatus, user.get(), userSession);
    }

    /**
     * Add new audit log entry.
     * 
     * @param description {@link String}
     * @param modules     {@link Modules}
     * @param type        {@link AuditLogType}
     * @param userSession {@link UserSession}
     */
    public void auditLog(final String description, final Modules modules, final AuditLogType type,
            final UserSession userSession) {
        final Optional<UserDetails> user = userRepository.findById(userSession.getUserId());
        auditLog(description, modules, type, null, user.get(), userSession);
    }

    /**
     * Add new audit log entry.
     * 
     * @param description    {@link String}
     * @param modules        {@link Modules}
     * @param type           {@link AuditLogType}
     * @param responseStatus {@link ResponseStatus}
     * @param user           {@link UserDetails}
     */
    public void auditLog(final String description, final Modules modules, final AuditLogType type,
            final ResponseStatus responseStatus, final UserDetails user) {
        auditLog(description, modules, type, responseStatus, user, null);
    }

    /**
     * Add new audit log entry.
     * 
     * @param description {@link String}
     * @param modules     {@link Modules}
     * @param type        {@link AuditLogType}
     * @param user        {@link UserDetails}
     */
    public void auditLog(final String description, final Modules modules, final AuditLogType type,
            final UserDetails user) {
        auditLog(description, modules, type, null, user, null);
    }

    /**
     * Add new audit log entry.
     * 
     * @param description {@link String}
     * @param modules     {@link Modules}
     * @param type        {@link AuditLogType}
     * @param userId      {@link UUID}
     */
    public void auditLog(final String description, final Modules modules, final AuditLogType type, final UUID userId) {
        final Optional<UserDetails> user = userRepository.findById(userId);

        auditLog(description, modules, type, user.get());
    }

    /**
     * Add new audit log entry.
     * 
     * @param description    {@link String}
     * @param modules        {@link Modules}
     * @param type           {@link AuditLogType}
     * @param responseStatus {@link ResponseStatus}
     * @param user           {@link UserDetails}
     * @param userSession    {@link UserSession}
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
