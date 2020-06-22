package com.sai.easwer.util;

import com.sai.easwer.entity.UserSession;
import com.sai.easwer.repository.UserSessionRepository;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * User session utils.
 * 
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-03-10 17:40:54
 * @modify date 2020-03-10 18:09:04
 */
public class SessionUtils {

    private SessionUtils() {

    }

    @Autowired
    private static UserSessionRepository userSessionRepository;

    /**
     * Checks whether the session is active for the provided auth Token.
     * 
     * @param authToken {@link UUID}
     * @return true is valid session else false. {@link Boolean}
     */
    public static boolean isSessionActive(final UUID authToken) {
        boolean result = false;
        final Optional<UserSession> userSession = userSessionRepository.findByAuthToken(authToken);
        if (userSession.isPresent()) {
            result = true;
        }
        return result;
    }

}
