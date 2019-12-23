package com.sai.easwer.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sai.easwer.constants.ResponseStatus;
import com.sai.easwer.controller.AuditLogController;
import com.sai.easwer.entity.AuditLog;
import com.sai.easwer.model.Response;
import com.sai.easwer.repository.AuditLogRepository;

public class AuditLogService extends BaseService implements AuditLogController
{

    @Autowired
    private AuditLogRepository auditLogRepository;

    @Override
    public ResponseEntity<Response> getAllAuditLog(UUID auditLogId)
    {
        if (auditLogId == null)
        {
            List<AuditLog> auditLogs = auditLogRepository.findAll();
            
            if (auditLogs.isEmpty())
            {
                return createResponse("No audit logs found.", ResponseStatus.SUCCESS, null, HttpStatus.NO_CONTENT);
            }
            
            return createResponse("Audit Logs found successfully.", ResponseStatus.SUCCESS, auditLogs, HttpStatus.OK);
        }
        else
        {
            Optional<AuditLog> auditLog = auditLogRepository.findById(auditLogId);
            if (auditLog == null)
            {
                return createResponse("Invalid user id.", ResponseStatus.FAILURE, null, HttpStatus.BAD_REQUEST);
            }
            else
            {
                return createResponse("User found successfully.", ResponseStatus.SUCCESS, auditLog, HttpStatus.OK);
            }
        }
    }

    @Override
    public ResponseEntity<Response> getAllAuditLogByUser(String username)
    {
        if (userId == null)
        {
            List<AuditLog> auditLogs = auditLogRepository.findByUsername(username)
            
            if (auditLogs.isEmpty())
            {
                return createResponse("No audit log found.", ResponseStatus.SUCCESS, auditLogs, HttpStatus.NO_CONTENT);
            }
            
            return createResponse("Audit Logs found successfully.", ResponseStatus.SUCCESS, auditLogs, HttpStatus.OK);
        }
        return null;
    }

}