package com.sai.easwer.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.sai.easwer.entity.UserDetails;
import com.sai.easwer.repository.UserRepository;


@Component
public class LoginFailureHandler implements AuthenticationFailureHandler
{
    @Autowired
    private UserRepository userRepository;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException
    {
        if(request.getParameter("username") != null) {
            Optional<UserDetails> user = userRepository.findByUsername(request.getParameter("username"));
            if(user.isPresent()) {
                user.get().setFailedLoginAttemptCount(user.get().getFailedLoginAttemptCount() + 1);
            }
        }
    }

}