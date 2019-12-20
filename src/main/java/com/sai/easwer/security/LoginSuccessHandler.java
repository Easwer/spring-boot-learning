package com.sai.easwer.security;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.sai.easwer.entity.UserDetails;
import com.sai.easwer.entity.UserSession;
import com.sai.easwer.repository.UserRepository;
import com.sai.easwer.repository.UserSessionRepository;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler
{
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserSessionRepository userSessionRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException
    {
        Optional<UserDetails> user = userRepository.findByUsername(authentication.getName());
        
        UserSession userSession = new UserSession();
        userSession.setId(UUID.randomUUID());
        userSession.setLastAccsessTime(Timestamp.valueOf(LocalDateTime.now()));
        userSession.setStartedTime(Timestamp.valueOf(LocalDateTime.now()));
        userSession.setUser(user.get());
        userSessionRepository.save(userSession);
        
        response.sendRedirect("/home");
    }

}
