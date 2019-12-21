package com.sai.easwer.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class LogoutHandler implements org.springframework.security.web.authentication.logout.LogoutHandler
{

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
    {
        authentication.setAuthenticated(false);
    }

}