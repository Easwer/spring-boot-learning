package com.sai.easwer.controller;

import com.sai.easwer.model.Response;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface GlobalSettingsController {

    @GetMapping(name = "/settings")
    public ResponseEntity<Response> getGlobalSettings(@RequestParam(name = "key", required = false) String key,
            @RequestParam(name = "module", required = false) String module);
}