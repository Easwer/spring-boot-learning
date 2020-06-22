package com.sai.easwer.controller;

import com.sai.easwer.model.Response;
import com.sai.easwer.constants.SecurityConstants;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
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

    /**
     * API to get server running status.
     * 
     * @param authToken
     * @return
     */
    @GetMapping(value = "/status")
    ResponseEntity<Response> getServerStatus(@RequestHeader(SecurityConstants.AUTH_TOKEN) UUID authToken);

    /**
     * API to check whether the given authentication token is valid or not.
     * 
     * @param authToken
     * @return
     */
    @GetMapping(value = "/isValid")
    ResponseEntity<Response> isValidToken(@RequestHeader(SecurityConstants.AUTH_TOKEN) UUID authToken);
}
