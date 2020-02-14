package com.sai.easwer.service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.sai.easwer.constants.ResponseStatus;
import com.sai.easwer.controller.UserContoller;
import com.sai.easwer.entity.UserDetails;
import com.sai.easwer.entity.UserSession;
import com.sai.easwer.model.LoginResponse;
import com.sai.easwer.model.Response;
import com.sai.easwer.repository.UserRepository;
import com.sai.easwer.repository.UserSessionRepository;

/**
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-14 15:12:28
 * @modify date 2020-02-14 15:12:49
 * @desc [description]
 */
@RestController
public class UserService extends BaseService implements UserContoller {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserSessionRepository userSessionRepository;

    @Autowired
    private HttpServletRequest request;

    @Override
    public ResponseEntity<Response> getUser(final UUID userId) {
        if (userId == null) {
            final List<UserDetails> users = userRepository.findAll();

            if (users.isEmpty()) {
                return createResponse("No users found.", ResponseStatus.SUCCESS, null, HttpStatus.NO_CONTENT);
            }

            return createResponse("Users found successfully.", ResponseStatus.SUCCESS, users, HttpStatus.OK);
        } else {
            final Optional<UserDetails> user = userRepository.findById(userId);
            if (user == null) {
                return createResponse("Invalid user id.", ResponseStatus.FAILURE, null, HttpStatus.BAD_REQUEST);
            } else {
                return createResponse("User found successfully.", ResponseStatus.SUCCESS, user, HttpStatus.OK);
            }
        }
    }

    @Override
    public ResponseEntity<Response> createUser(final UserDetails user) {
        try {
            validateUserInput(user);

            user.setId(UUID.randomUUID());

            userRepository.save(user);
        } catch (final IllegalArgumentException e) {
            return createResponse(e.getMessage(), ResponseStatus.FAILURE, null, HttpStatus.BAD_REQUEST);
        } catch (final Exception e) {
            return createResponse("Invalid Input.", ResponseStatus.FAILURE, null, HttpStatus.BAD_REQUEST);
        }
        return createResponse("Users created successfully.", ResponseStatus.SUCCESS, user, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> updateUser(final UserDetails user) {
        try {
            validateUserInput(user);
            if (user.getId() != null) {
                final Optional<UserDetails> userDetails = userRepository.findById(user.getId());
                if (userDetails.isPresent()) {
                    userRepository.save(user);
                } else {
                    throw new IllegalArgumentException("User not found.");
                }
            } else {
                throw new IllegalArgumentException("User not found.");
            }
        } catch (final IllegalArgumentException e) {
            return createResponse(e.getMessage(), ResponseStatus.FAILURE, null, HttpStatus.BAD_REQUEST);
        } catch (final Exception e) {
            return createResponse("Invalid Input.", ResponseStatus.FAILURE, null, HttpStatus.BAD_REQUEST);
        }
        return createResponse("Users created successfully.", ResponseStatus.SUCCESS, user, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> deleteUser(final UUID userId) {
        final Optional<UserDetails> userDetails = userRepository.findById(userId);
        if (userDetails.isPresent()) {
            userRepository.delete(userDetails.get());
        } else {
            return createResponse("User not found.", ResponseStatus.FAILURE, null, HttpStatus.BAD_REQUEST);
        }
        return createResponse("Users deleted successfully.", ResponseStatus.SUCCESS, null, HttpStatus.OK);
    }

    private void validateUserInput(final UserDetails user) throws Exception {
        if (user == null) {
            throw new IllegalArgumentException("Invalid Input.");
        }

        if (user.getUsername() == null || user.getUsername().trim().equals("")) {
            throw new IllegalArgumentException("Username cannot be empty");
        }

        if (user.getFirstName() == null || user.getFirstName().trim().equals("")) {
            throw new IllegalArgumentException("First name cannot be empty");
        }

        if (user.getLastName() == null || user.getLastName().trim().equals("")) {
            throw new IllegalArgumentException("Last Name cannot be empty");
        }

        if (user.getPassword() == null || user.getPassword().trim().equals("")) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
    }

    @Override
    public ResponseEntity<Response> login(final String username, final String password) {
        if (null == username) {
            return createResponse("Username cannot be empty.", ResponseStatus.FAILURE, null, HttpStatus.BAD_REQUEST);
        }

        if (null == password) {
            return createResponse("Password cannot be empty.", ResponseStatus.FAILURE, null, HttpStatus.BAD_REQUEST);
        }

        final Optional<UserDetails> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            if (!user.get().getPassword().equals(password)) {
                return createResponse("Authentication Error.", ResponseStatus.FAILURE, null, HttpStatus.FORBIDDEN);
            }
        } else {
            return createResponse("Invalid User details.", ResponseStatus.FAILURE, null, HttpStatus.BAD_REQUEST);
        }

        final UserSession userSession = createUserSession(user.get());

        final LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUser(user.get());
        loginResponse.setAuthToken(userSession.getAuthToken());

        return createResponse("Login successfull.", ResponseStatus.SUCCESS, loginResponse, HttpStatus.OK);
    }

    private UserSession createUserSession(final UserDetails userDetails) {
        final UserSession userSession = new UserSession();

        userSession.setId(UUID.randomUUID());
        userSession.setUserId(userDetails.getId());
        userSession.setAuthToken(UUID.randomUUID());
        userSession.setIpAddress(request.getRemoteAddr());
        userSession.setStartedTime(Calendar.getInstance().getTimeInMillis());
        userSession.setLastAccsessTime(Calendar.getInstance().getTimeInMillis());

        userSessionRepository.save(userSession);

        return userSession;
    }

    @Override
    public ResponseEntity<Response> logout(final UUID authToken) {
        try {
            final Optional<UserSession> userSession = userSessionRepository.findByAuthToken(authToken);
            if (userSession.isPresent()) {
                userSessionRepository.delete(userSession.get());
            }
        } catch (final Exception e) {
            return createResponse("Logout Failure.", ResponseStatus.FAILURE, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return createResponse("Logout successfull.", ResponseStatus.SUCCESS, null, HttpStatus.OK);
    }

}