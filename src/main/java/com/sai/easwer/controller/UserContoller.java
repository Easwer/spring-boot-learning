package com.sai.easwer.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sai.easwer.entity.UserDetails;
import com.sai.easwer.model.Response;

@RestController
public interface UserContoller
{

    @GetMapping(value = "/user")
    public ResponseEntity<Response> getUser(@RequestParam(required = false, name = "id") UUID userId);

    @PostMapping(value = "/user")
    public ResponseEntity<Response> createUser(@RequestBody UserDetails user);

    @PutMapping(value = "/user")
    public ResponseEntity<Response> updateUser(@RequestBody UserDetails user);

    @DeleteMapping(value = "/user")
    public ResponseEntity<Response> deleteUser(@RequestParam(name = "id") UUID userId);

}