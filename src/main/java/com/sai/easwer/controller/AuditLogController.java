package com.sai.easwer.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sai.easwer.model.Response;

public interface AuditLogController
{
    @GetMapping(name = "/auditlog")
    public ResponseEntity<Response> getAllAuditLog(@RequestParam(required = false, name = "id") UUID auditLogId);
    
    @GetMapping(name = "/auditlog")
    public ResponseEntity<Response> getAllAuditLogByUser(@RequestParam(name = "user") String username);
}