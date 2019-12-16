package com.sai.easwer.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sai.easwer.entity.UserDetails;

@RestController
public interface UserContoller {

	@GetMapping(value = "/users")
	public List<UserDetails> getAllUsers();

}