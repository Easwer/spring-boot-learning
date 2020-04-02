package com.sai.easwer.controller;

import com.sai.easwer.model.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller for global settings management.
 * 
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-03-10 17:55:15
 * @modify date 2020-03-10 18:04:38
 */
@RestController
public interface GlobalSettingsController {

    /**
     * Get Global setiings.
     * 
     * @param key {@link String}
     * @param module {@link String}
     * @return REST API response. {@link ResponseEntity}<{@link Response}>
     */
    @GetMapping(value = "/settings")
    ResponseEntity<Response> getGlobalSettings(
            @RequestParam(name = "key", required = false) String key,
            @RequestParam(name = "module", required = false) String module);
}
