package com.sai.easwer.controller;

import com.sai.easwer.constants.SecurityConstants;
import com.sai.easwer.entity.UserDetails;
import com.sai.easwer.servermodel.LoginRequest;
import com.sai.easwer.model.Response;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller for user management.
 * 
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-14 15:12:01
 * @modify date 2020-03-10 18:04:54
 */
@RestController
public interface UserContoller {

        /**
         * API to login into the application. It will create new user session and
         * returns the auth token.
         * 
         * @param loginRequest {@link LoginRequest}
         * @return {@link ResponseEntity}<{@link Response}>
         */
        @PostMapping(value = "/login")
        ResponseEntity<Response> login(@RequestBody LoginRequest loginRequest);

        /**
         * API to logout of the application. It will remove the user session.
         * 
         * @param authToken {@link UUID}
         * @return {@link ResponseEntity}<{@link Response}>
         */
        @GetMapping(value = "/logout")
        ResponseEntity<Response> logout(@RequestHeader(name = SecurityConstants.AUTH_TOKEN) UUID authToken);

        /**
         * API to retrive the user. It will retrive the particular user if id is
         * provided else retives all the users.
         * 
         * @param userId {@link UUID}
         * @return {@link ResponseEntity}<{@link Response}>
         */
        ResponseEntity<Response> getUser(@RequestHeader(SecurityConstants.AUTH_TOKEN) UUID authToken,
                        @RequestParam(required = false, name = "id") UUID userId);

        /**
         * API to create new user.
         * 
         * @param user {@link UserDetails}
         * @return {@link ResponseEntity}<{@link Response}>
         */
        @PostMapping(value = "/user")
        ResponseEntity<Response> createUser(@RequestHeader(SecurityConstants.AUTH_TOKEN) UUID authToken,
                        @RequestBody UserDetails user);

        /**
         * API to update the existing user.
         * 
         * @param user {@link UserDetails}
         * @return {@link ResponseEntity}<{@link Response}>
         */
        @PutMapping(value = "/user")
        ResponseEntity<Response> updateUser(@RequestHeader(SecurityConstants.AUTH_TOKEN) UUID authToken,
                        @RequestBody UserDetails user);

        /**
         * API to delete the existing user.
         * 
         * @param userId {@link UUID}
         * @return {@link ResponseEntity}<{@link Response}>
         */
        @DeleteMapping(value = "/user")
        ResponseEntity<Response> deleteUser(@RequestHeader(SecurityConstants.AUTH_TOKEN) UUID authToken,
                        @RequestParam(name = "id") UUID userId);

}
