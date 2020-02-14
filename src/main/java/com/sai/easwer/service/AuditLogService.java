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

/**
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-14 15:12:28
 * @modify date 2020-02-14 15:12:49
 * @desc [description]
 */
public class AuditLogService extends BaseService implements AuditLogController {

    @Autowired
    private AuditLogRepository auditLogRepository;

    @Override
    public ResponseEntity<Response> getAllAuditLog(final UUID auditLogId) {
        if (auditLogId == null) {
            final List<AuditLog> auditLogs = auditLogRepository.findAll();

            if (auditLogs.isEmpty()) {
                return createResponse("No audit logs found.", ResponseStatus.SUCCESS, null, HttpStatus.NO_CONTENT);
            }

            return createResponse("Audit Logs found successfully.", ResponseStatus.SUCCESS, auditLogs, HttpStatus.OK);
        } else {
            final Optional<AuditLog> auditLog = auditLogRepository.findById(auditLogId);
            if (auditLog == null) {
                return createResponse("Invalid user id.", ResponseStatus.FAILURE, null, HttpStatus.BAD_REQUEST);
            } else {
                return createResponse("User found successfully.", ResponseStatus.SUCCESS, auditLog, HttpStatus.OK);
            }
        }
    }

    @Override
    public ResponseEntity<Response> getAllAuditLogByUser(final String username) {
        if (username == null) {
            final List<AuditLog> auditLogs = auditLogRepository.findByUsername(username);

            if (auditLogs.isEmpty()) {
                return createResponse("No audit log found.", ResponseStatus.SUCCESS, auditLogs, HttpStatus.NO_CONTENT);
            }

            return createResponse("Audit Logs found successfully.", ResponseStatus.SUCCESS, auditLogs, HttpStatus.OK);
        }
        return null;
    }

}