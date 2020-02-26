package com.sai.easwer.util;

import java.util.Optional;
import java.util.UUID;

import com.sai.easwer.entity.UserSession;
import com.sai.easwer.repository.UserSessionRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class SessionUtils {

    @Autowired
    private static UserSessionRepository userSessionRepository;

    public static boolean isSessionActive(UUID authToken) {
        boolean result = false;
        Optional<UserSession> userSession = userSessionRepository.findByAuthToken(authToken);
        if (userSession.isPresent()) {
            result = true;
        }
        return result;
    }

}