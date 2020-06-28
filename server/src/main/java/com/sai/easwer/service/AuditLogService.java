package com.sai.easwer.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.sai.easwer.annotation.RoleAccess;
import com.sai.easwer.constants.MessageConstants;
import com.sai.easwer.controller.AuditLogController;
import com.sai.easwer.entity.AuditLog;
import com.sai.easwer.entity.UserDetails;
import com.sai.easwer.model.Response;
import com.sai.easwer.model.ResponseStatus;
import com.sai.easwer.model.UserRoleEnum;
import com.sai.easwer.repository.AuditLogRepository;
import com.sai.easwer.repository.UserRepository;
import com.sai.easwer.repository.UserSessionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Implementation class for auditlog service.
 * 
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-03-10 18:08:13
 * @modify date 2020-03-10 18:08:13
 */
@Component
@Tag(name = "Auditlog", description = "Auditlog API's")
public class AuditLogService extends BaseService implements AuditLogController {

    @Autowired
    private AuditLogRepository auditLogRepository;

    @Autowired
    private UserSessionRepository userSessionRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<Response> _getAuditLogs(@RoleAccess(role = UserRoleEnum.AUDITLOG_READ) final UUID authToken,
            final UUID auditlogId) {
        final UUID userId = userSessionRepository.findByAuthToken(authToken).get().getUserId();
        final UserDetails user = userRepository.findById(userId).get();
        // TODO: Change the username based check to group name based check.
        if (user.getUsername() == "admin") {
            final List<AuditLog> auditLogs = auditLogRepository.findAll();
            if (auditLogs.isEmpty()) {
                return createResponse(MessageConstants.NO_AUDIT_LOGS_FOUND, ResponseStatus.SUCCESS, null,
                        HttpStatus.NO_CONTENT);
            }
            return createResponse(MessageConstants.AUDIT_LOGS_FOUND_SUCCESSFULLY, ResponseStatus.SUCCESS, auditLogs,
                    HttpStatus.OK);
        } else {
            if (null != auditlogId) {
                final Optional<AuditLog> auditLog = auditLogRepository.findByIdAndUserId(auditlogId, userId);
                if (auditLog.isPresent()) {
                    return createResponse(MessageConstants.AUDIT_LOGS_FOUND_SUCCESSFULLY, ResponseStatus.SUCCESS,
                            auditLog, HttpStatus.OK);
                } else {
                    return createResponse(MessageConstants.INVALID_AUDTILOG_ID, ResponseStatus.FAILURE, null,
                            HttpStatus.BAD_REQUEST);
                }
            } else {
                final List<AuditLog> auditLogs = auditLogRepository.findByUserId(userId);
                if (auditLogs.isEmpty()) {
                    return createResponse(MessageConstants.NO_AUDIT_LOGS_FOUND, ResponseStatus.SUCCESS, null,
                            HttpStatus.NO_CONTENT);
                }
                return createResponse(MessageConstants.AUDIT_LOGS_FOUND_SUCCESSFULLY, ResponseStatus.SUCCESS, auditLogs,
                        HttpStatus.OK);
            }
        }
    }

}
