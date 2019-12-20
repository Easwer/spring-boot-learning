package com.sai.easwer.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.sai.easwer.entity.UserDetails;
import com.sai.easwer.entity.UserSession;
import com.sai.easwer.repository.UserRepository;
import com.sai.easwer.repository.UserSessionRepository;

@Component
public class LogoutSuccessHandler implements org.springframework.security.web.authentication.logout.LogoutSuccessHandler
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserSessionRepository userSessionRepository;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException
    {
        Optional<UserDetails> user = userRepository.findByUsername(authentication.getName());
        
        if (user.isPresent())
        {
            Optional<UserSession> userSession = userSessionRepository.findByUser(user.get());
            if (userSession.isPresent())
            {
                userSessionRepository.delete(userSession.get());
            } 
        }
        
    }

}
