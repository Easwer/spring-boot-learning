package com.sai.easwer.controller;

import com.sai.easwer.entity.UserDetails;
import com.sai.easwer.model.Response;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
     * 
     * @param name
     * @param password
     * @return
     */
    @GetMapping(value = "/login")
    ResponseEntity<Response> login(@RequestParam(name = "username") String name,
            @RequestParam(name = "password") String password);

    /**
     * 
     * @param authToken
     * @return
     */
    @GetMapping(value = "/logout")
    ResponseEntity<Response> logout(@RequestParam(name = "authToken") UUID authToken);

    /**
     * 
     * @param userId
     * @return
     */
    @GetMapping(value = "/user")
    ResponseEntity<Response> getUser(@RequestParam(required = false, name = "id") UUID userId);

    /**
     * 
     * @param user
     * @return
     */
    @PostMapping(value = "/user")
    ResponseEntity<Response> createUser(@RequestBody UserDetails user);

    /**
     * 
     * @param user
     * @return
     */
    @PutMapping(value = "/user")
    ResponseEntity<Response> updateUser(@RequestBody UserDetails user);

    /**
     * 
     * @param userId
     * @return
     */
    @DeleteMapping(value = "/user")
    ResponseEntity<Response> deleteUser(@RequestParam(name = "id") UUID userId);

}
