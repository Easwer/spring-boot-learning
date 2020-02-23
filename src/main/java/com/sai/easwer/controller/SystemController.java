package com.sai.easwer.controller;

import java.util.UUID;

import com.sai.easwer.model.Response;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface SystemController {

    @GetMapping(name = "/status")
    public ResponseEntity<Response> getServerStatus();

    @GetMapping(name = "/isValid")
    public ResponseEntity<Response> isValidToken(UUID authToken);
}