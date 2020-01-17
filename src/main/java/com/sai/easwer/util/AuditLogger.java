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

@Component
public class AuditLogger
{
    @Autowired
    private AuditLogRepository auditLogRepository;

    @Autowired
    private UserRepository userRepository;

    public void auditLog(String description, Modules modules)
    {
        auditLog(description, modules, AuditLogType.SYSTEM, null, null, null);
    }

    public void auditLog(String description, Modules modules, AuditLogType type)
    {
        auditLog(description, modules, type, null, null, null);
    }

    public void auditLog(String description, Modules modules, AuditLogType type, ResponseStatus responseStatus)
    {
        auditLog(description, modules, type, responseStatus, null, null);
    }

    public void auditLog(String description, Modules modules, AuditLogType type, ResponseStatus responseStatus, UserSession userSession)
    {
        Optional<UserDetails> user = userRepository.findById(userSession.getUserId());
        auditLog(description, modules, type, responseStatus, user.get(), userSession);
    }

    public void auditLog(String description, Modules modules, AuditLogType type, UserSession userSession)
    {
        Optional<UserDetails> user = userRepository.findById(userSession.getUserId());
        auditLog(description, modules, type, null, user.get(), userSession);
    }

    public void auditLog(String description, Modules modules, AuditLogType type, ResponseStatus responseStatus, UserDetails user)
    {
        auditLog(description, modules, type, responseStatus, user, null);
    }

    public void auditLog(String description, Modules modules, AuditLogType type, UserDetails user)
    {
        auditLog(description, modules, type, null, user, null);
    }

    public void auditLog(String description, Modules modules, AuditLogType type, UUID userId)
    {
        Optional<UserDetails> user = userRepository.findById(userId);

        auditLog(description, modules, type, user.get());
    }

    public void auditLog(String description, Modules modules, AuditLogType type, ResponseStatus responseStatus, UserDetails user, UserSession userSession)
    {
        AuditLog auditLog = new AuditLog();

        auditLog.setId(UUID.randomUUID());
        auditLog.setDescription(description);
        auditLog.setModules(modules);

        if (null != userSession)
        {
            auditLog.setIpAddress(userSession.getIpAddress());
        }

        if (null != user)
        {
            auditLog.setUsername(user.getUsername());
        }

        if (null != responseStatus)
        {
            auditLog.setStatus(responseStatus);
        }

        auditLogRepository.save(auditLog);
    }

}