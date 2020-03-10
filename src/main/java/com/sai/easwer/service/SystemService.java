package com.sai.easwer.service;

import com.sai.easwer.constants.ResponseStatus;
import com.sai.easwer.controller.SystemController;
import com.sai.easwer.entity.UserSession;
import com.sai.easwer.model.Response;
import com.sai.easwer.repository.UserSessionRepository;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class SystemService extends BaseService implements SystemController {

    @Autowired
    private UserSessionRepository userSessionRepository;

    @Override
    public ResponseEntity<Response> getServerStatus() {
        return createResponse("Alive", ResponseStatus.SUCCESS, null, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> isValidToken(UUID authToken) {
        Optional<UserSession> userSession = userSessionRepository.findByAuthToken(authToken);
        if (userSession.isPresent()) {
            return createResponse("Active", ResponseStatus.SUCCESS, null, HttpStatus.OK);
        } else {
            return createResponse("In-Active", ResponseStatus.FAILURE, null,
                    HttpStatus.UNAUTHORIZED);
        }
    }

}
