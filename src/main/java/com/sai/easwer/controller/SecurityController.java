package com.sai.easwer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController
{
    @GetMapping(value = "/login-page")
    public String getLogin()
    {
        return "login.jsp";
    }

    @GetMapping(value = "/unauthorized")
    public String getUnauthorized()
    {
        return "unauthorized.jsp";
    }

    @GetMapping(value = "/expired")
    public String getExpired()
    {
        return "expired.jsp";
    }

    @GetMapping(value = "/logout-success")
    public String getLogout()
    {
        return "logout.jsp";
    }
}
