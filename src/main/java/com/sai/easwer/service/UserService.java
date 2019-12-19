package com.sai.easwer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.sai.easwer.controller.UserContoller;
import com.sai.easwer.entity.UserDetails;
import com.sai.easwer.repository.UserRepository;

@RestController
public class UserService implements UserContoller{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<UserDetails> getAllUsers() {
		return userRepository.findAll();
	}

}