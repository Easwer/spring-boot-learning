package com.sai.easwer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController {

	@GetMapping(value = "/")
	public String getHome() {
		return "index.jsp";
	}

	@GetMapping(value = "/login")
	public String getLogin() {
		return "login.jsp";
	}

	@GetMapping(value = "/logout-success")
	public String getLogout() {
		return "logout.jsp";
	}
}