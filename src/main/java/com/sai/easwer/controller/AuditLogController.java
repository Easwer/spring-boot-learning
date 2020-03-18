package com.sai.easwer.controller;

import com.sai.easwer.model.Response;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Rest controller for auditlog management.
 * 
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-14 15:11:45
 * @modify date 2020-03-10 18:04:32
 */
public interface AuditLogController {
    /**
     * Get all audit log.
     * 
     * @param auditLogId {@link UUID}
     * @return {@link ResponseEntity}
     */
    @GetMapping(value = "/auditlog")
    ResponseEntity<Response> getAllAuditLog(
            @RequestParam(required = false, name = "id") UUID auditLogId);

    /**
     * Get all audit log by user.
     * 
     * @param username {@link String}
     * @return {@link ResponseEntity}
     */
    @GetMapping(value = "/auditlog")
    ResponseEntity<Response> getAllAuditLogByUser(@RequestParam(name = "user") String username);
}
