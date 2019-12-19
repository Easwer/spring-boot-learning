package com.sai.easwer.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sai.easwer.model.Response;

@RestController
public interface UserContoller
{

    @GetMapping(value = "/user")
    public ResponseEntity<Response> getUser(@RequestParam(required = false, name = "id") UUID userId);

}