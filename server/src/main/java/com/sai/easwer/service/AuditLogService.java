package com.sai.easwer.service;

import com.sai.easwer.annotation.RoleAccess;
import com.sai.easwer.constants.MessageConstants;
import com.sai.easwer.constants.UserRoleEnum;
import com.sai.easwer.controller.AuditLogController;
import com.sai.easwer.entity.AuditLog;
import com.sai.easwer.model.Response;
import com.sai.easwer.model.ResponseStatus;
import com.sai.easwer.repository.AuditLogRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * Implementation class for auditlog service.
 * 
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-03-10 18:08:13
 * @modify date 2020-03-10 18:08:13
 */
@Component
public class AuditLogService extends BaseService implements AuditLogController {

    @Autowired
    private AuditLogRepository auditLogRepository;

    @Override
    public ResponseEntity<Response> getAllAuditLog(@RoleAccess(role = UserRoleEnum.AUDITLOG_READ) final UUID authToken,
            final UUID auditLogId) {
        if (auditLogId == null) {
            final List<AuditLog> auditLogs = auditLogRepository.findAll();

            if (auditLogs.isEmpty()) {
                return createResponse(MessageConstants.NO_AUDIT_LOGS_FOUND, ResponseStatus.SUCCESS, null,
                        HttpStatus.NO_CONTENT);
            }

            return createResponse(MessageConstants.AUDIT_LOGS_FOUND_SUCCESSFULLY, ResponseStatus.SUCCESS, auditLogs,
                    HttpStatus.OK);
        } else {
            final Optional<AuditLog> auditLog = auditLogRepository.findById(auditLogId);
            if (auditLog == null) {
                return createResponse(MessageConstants.INVALID_USER_ID, ResponseStatus.FAILURE, null,
                        HttpStatus.BAD_REQUEST);
            } else {
                return createResponse(MessageConstants.USER_FOUND_SUCCESSFULLY, ResponseStatus.SUCCESS, auditLog,
                        HttpStatus.OK);
            }
        }
    }

    @Override
    public ResponseEntity<Response> getAllAuditLogByUser(
            @RoleAccess(role = UserRoleEnum.AUDITLOG_READ) final UUID authToken, final String username) {
        if (username == null) {
            final List<AuditLog> auditLogs = auditLogRepository.findByUsername(username);

            if (auditLogs.isEmpty()) {
                return createResponse(MessageConstants.NO_AUDIT_LOG_FOUND, ResponseStatus.SUCCESS, auditLogs,
                        HttpStatus.NO_CONTENT);
            }

            return createResponse(MessageConstants.AUDIT_LOGS_FOUND_SUCCESSFULLY, ResponseStatus.SUCCESS, auditLogs,
                    HttpStatus.OK);
        }
        return null;
    }

}
