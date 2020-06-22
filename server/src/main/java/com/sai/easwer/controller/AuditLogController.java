package com.sai.easwer.controller;

import java.util.UUID;

import com.sai.easwer.constants.SecurityConstants;
import com.sai.easwer.model.Response;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller for auditlog management.
 * 
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-14 15:11:45
 * @modify date 2020-03-10 18:04:32
 */
@RestController
public interface AuditLogController {
    /**
     * Get all audit log.
     * 
     * @param authToken  {@link UUID}
     * @param auditLogId {@link UUID}
     * @return {@link ResponseEntity}
     */
    // @GetMapping(value = "/auditlog")
    ResponseEntity<Response> getAllAuditLog(@RequestHeader(SecurityConstants.AUTH_TOKEN) UUID authToken,
            @RequestParam(required = false, name = "id") UUID auditLogId);

    /**
     * Get all audit log by user.
     * 
     * @param authToken {@link UUID}
     * @param username  {@link String}
     * @return {@link ResponseEntity}
     */
    // @GetMapping(value = "/auditlog")
    ResponseEntity<Response> getAllAuditLogByUser(@RequestHeader(SecurityConstants.AUTH_TOKEN) UUID authToken,
            @RequestParam(name = "user") String username);
}
