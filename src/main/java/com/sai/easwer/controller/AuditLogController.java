package com.sai.easwer.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sai.easwer.model.Response;

/**
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-14 15:11:45
 * @modify date 2020-02-14 15:11:45
 * @desc [description]
 */
public interface AuditLogController {
    /**
     * 
     * @param auditLogId
     * @return
     */
    @GetMapping(name = "/auditlog")
    public ResponseEntity<Response> getAllAuditLog(@RequestParam(required = false, name = "id") UUID auditLogId);

    /**
     * 
     * @param username
     * @return
     */
    @GetMapping(name = "/auditlog")
    public ResponseEntity<Response> getAllAuditLogByUser(@RequestParam(name = "user") String username);
}