package com.sai.easwer.controller;

import com.sai.easwer.model.Response;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller for system management.
 * 
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-03-10 17:55:36
 * @modify date 2020-03-10 18:04:49
 */
@RestController
public interface SystemController {

    @GetMapping(value = "/status")
    ResponseEntity<Response> getServerStatus();

    @GetMapping(value = "/isValid")
    ResponseEntity<Response> isValidToken(UUID authToken);
}
