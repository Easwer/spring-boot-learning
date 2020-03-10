package com.sai.easwer.service;

import com.sai.easwer.constants.AuditLogType;
import com.sai.easwer.constants.MessageConstants;
import com.sai.easwer.constants.Modules;
import com.sai.easwer.constants.ResponseStatus;
import com.sai.easwer.constants.UserAccountStatus;
import com.sai.easwer.controller.UserContoller;
import com.sai.easwer.entity.UserDetails;
import com.sai.easwer.entity.UserSession;
import com.sai.easwer.model.LoginResponse;
import com.sai.easwer.model.Response;
import com.sai.easwer.repository.UserRepository;
import com.sai.easwer.repository.UserSessionRepository;
import com.sai.easwer.util.AuditLogger;
import com.sai.easwer.util.SecurityUtils;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * Implementation class for user service.
 * 
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-14 15:12:28
 * @modify date 2020-02-14 15:12:49
 */
@Slf4j
@Component
public class UserService extends BaseService implements UserContoller {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserSessionRepository userSessionRepository;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private AuditLogger auditLogger;

    @Autowired
    private SecurityUtils securityUtils;

    @Override
    public ResponseEntity<Response> getUser(final UUID userId) {
        if (userId == null) {
            final List<UserDetails> users = userRepository.findAll();

            if (users.isEmpty()) {
                return createResponse(MessageConstants.NO_USERS_FOUND, ResponseStatus.SUCCESS, null,
                        HttpStatus.NO_CONTENT);
            }

            return createResponse(MessageConstants.USERS_FOUND_SUCCESSFULLY, ResponseStatus.SUCCESS,
                    users, HttpStatus.OK);
        } else {
            final Optional<UserDetails> user = userRepository.findById(userId);
            if (user == null) {
                return createResponse(MessageConstants.INVALID_USER_ID, ResponseStatus.FAILURE,
                        null, HttpStatus.BAD_REQUEST);
            } else {
                return createResponse(MessageConstants.USERS_FOUND_SUCCESSFULLY,
                        ResponseStatus.SUCCESS, user, HttpStatus.OK);
            }
        }
    }

    @Override
    public ResponseEntity<Response> createUser(final UserDetails user) {
        try {
            securityUtils.validateCreateUserRequest(user);

            user.setUserAccountStatus(
                    UserAccountStatus.CHANGE_PASSWORD_ON_LOGIN.getAccountStatus());

            user.setId(UUID.randomUUID());

            userRepository.save(user);
        } catch (final IllegalArgumentException e) {
            return createResponse(e.getMessage(), ResponseStatus.FAILURE, null,
                    HttpStatus.BAD_REQUEST);
        } catch (final Exception e) {
            return createResponse(MessageConstants.INVALID_INPUT, ResponseStatus.FAILURE, null,
                    HttpStatus.BAD_REQUEST);
        }
        log.info(MessageConstants.USERS_CREATED_SUCCESSFULLY);
        return createResponse(MessageConstants.USERS_CREATED_SUCCESSFULLY, ResponseStatus.SUCCESS,
                user, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> updateUser(final UserDetails user) {
        try {
            securityUtils.validateUpdateUserRequest(user);
            if (user.getId() != null) {
                final Optional<UserDetails> userDetails = userRepository.findById(user.getId());
                if (userDetails.isPresent()) {
                    userRepository.save(user);
                } else {
                    throw new IllegalArgumentException(MessageConstants.USER_NOT_FOUND);
                }
            } else {
                throw new IllegalArgumentException(MessageConstants.USER_NOT_FOUND);
            }
        } catch (final IllegalArgumentException e) {
            return createResponse(e.getMessage(), ResponseStatus.FAILURE, null,
                    HttpStatus.BAD_REQUEST);
        } catch (final Exception e) {
            return createResponse(MessageConstants.INVALID_INPUT, ResponseStatus.FAILURE, null,
                    HttpStatus.BAD_REQUEST);
        }
        return createResponse(MessageConstants.USERS_CREATED_SUCCESSFULLY, ResponseStatus.SUCCESS,
                user, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> deleteUser(final UUID userId) {
        final Optional<UserDetails> userDetails = userRepository.findById(userId);
        if (userDetails.isPresent()) {
            userRepository.delete(userDetails.get());
        } else {
            return createResponse(MessageConstants.USER_NOT_FOUND, ResponseStatus.FAILURE, null,
                    HttpStatus.BAD_REQUEST);
        }
        return createResponse(MessageConstants.USER_DELETED_SUCCESSFULLY, ResponseStatus.SUCCESS,
                null, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> login(final String username, final String password) {
        if (null == username) {
            return createResponse(MessageConstants.USERNAME_CANNOT_BE_EMPTY, ResponseStatus.FAILURE,
                    null, HttpStatus.BAD_REQUEST);
        }

        if (null == password) {
            return createResponse(MessageConstants.PASSWORD_CANNOT_BE_EMPTY, ResponseStatus.FAILURE,
                    null, HttpStatus.BAD_REQUEST);
        }

        final Optional<UserDetails> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            if (!user.get().getPassword().equals(password)) {
                return createResponse(MessageConstants.AUTHENTICATION_ERROR, ResponseStatus.FAILURE,
                        null, HttpStatus.FORBIDDEN);
            }
        } else {
            return createResponse(MessageConstants.INVALID_USER_DETAILS, ResponseStatus.FAILURE,
                    null, HttpStatus.BAD_REQUEST);
        }

        final UserSession userSession = createUserSession(user.get());

        final LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUser(user.get());
        loginResponse.setAuthToken(userSession.getAuthToken());

        auditLogger.auditLog(MessageConstants.LOGIN_SUCCESSFUL_FOR_USER + username + "'.",
                Modules.SECURITY, AuditLogType.LOGIN, userSession);

        return createResponse(MessageConstants.LOGIN_SUCCESSFUL, ResponseStatus.SUCCESS,
                loginResponse, HttpStatus.OK);
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
        Optional<UserDetails> loginUser = null;
        Optional<UserSession> userSession = null;
        try {
            userSession = userSessionRepository.findByAuthToken(authToken);
            loginUser = userRepository.findById(userSession.get().getUserId());
            if (userSession.isPresent()) {
                userSessionRepository.delete(userSession.get());
            } else {
                return createResponse(MessageConstants.INVALID_SESSION_DETAILS,
                        ResponseStatus.FAILURE, null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (final Exception e) {
            if (loginUser != null && loginUser.isPresent()) {
                auditLogger
                        .auditLog(
                                MessageConstants.LOGOUT_SUCCESSFUL_FOR_USER
                                        + loginUser.get().getUsername() + "'.",
                                Modules.SECURITY, AuditLogType.LOGOUT);
            }
            return createResponse(MessageConstants.LOGOUT_FAILURE, ResponseStatus.FAILURE, null,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        auditLogger.auditLog(
                MessageConstants.LOGOUT_SUCCESSFUL_FOR_USER + loginUser.get().getUsername() + "'.",
                Modules.SECURITY, AuditLogType.LOGOUT);
        return createResponse(MessageConstants.LOGOUT_SUCCESSFUL, ResponseStatus.SUCCESS, null,
                HttpStatus.OK);
    }

}
