package com.sai.easwer.service;

import static com.sai.easwer.model.ResponseStatus.FAILURE;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.sai.easwer.annotation.RoleAccess;
import com.sai.easwer.constants.MessageConstants;
import com.sai.easwer.controller.UserContoller;
import com.sai.easwer.entity.UserDetails;
import com.sai.easwer.entity.UserPasswordHistory;
import com.sai.easwer.model.Response;
import com.sai.easwer.model.ResponseStatus;
import com.sai.easwer.model.UserAccountStatus;
import com.sai.easwer.model.UserDto;
import com.sai.easwer.model.UserRoleEnum;
import com.sai.easwer.repository.UserPasswordHistoryRepository;
import com.sai.easwer.repository.UserRepository;
import com.sai.easwer.util.MailUtils;
import com.sai.easwer.util.SecurityUtils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

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
@Tag(name = "User", description = "User API's")
public class UserService extends BaseService implements UserContoller {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserPasswordHistoryRepository userPasswordHistoryRepository;

    @Autowired
    private SecurityUtils securityUtils;

    @Autowired
    private MailUtils mailUtils;

    @Override
    public ResponseEntity<Response> sendMail() {
        try {
            String password = securityUtils.createNewPassword("easwer");
            mailUtils.sendEmail("rameas100@gmail.com", "Account created successfully.",
                    "Hi Sai rajeswari,\nYour new account has been created.\nYour account details as follows.\n\nUsername: sai\nPassword: "
                            + password + "\n\nThanks,\nAdmin");
        } catch (Exception e) {
            return createResponse("Mail send error due to: " + e.getMessage(), ResponseStatus.FAILURE, null,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return createResponse("Mail send successfully", ResponseStatus.SUCCESS, null, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> _getUsers(@RoleAccess(role = UserRoleEnum.USER_READ) final UUID authToken,
            final UUID userId) {
        if (userId == null) {
            final List<UserDetails> users = userRepository.findAll();

            if (users.isEmpty()) {
                return createResponse(MessageConstants.NO_USERS_FOUND, ResponseStatus.SUCCESS, null,
                        HttpStatus.NO_CONTENT);
            }

            return createResponse(MessageConstants.USERS_FOUND_SUCCESSFULLY, ResponseStatus.SUCCESS, users,
                    HttpStatus.OK);
        } else {
            final Optional<UserDetails> user = userRepository.findById(userId);
            if (user == null) {
                return createResponse(MessageConstants.INVALID_USER_ID, FAILURE, null, HttpStatus.BAD_REQUEST);
            } else {
                return createResponse(MessageConstants.USERS_FOUND_SUCCESSFULLY, ResponseStatus.SUCCESS, user,
                        HttpStatus.OK);
            }
        }
    }

    @Override
    public ResponseEntity<Response> _createUsers(@RoleAccess(role = UserRoleEnum.USER_CREATE) final UUID authToken,
            final UserDto user) {
        try {
            securityUtils.validateCreateUserRequest(user);
            user.setAccountStatus(UserAccountStatus.CHANGE_PASSWORD_ON_LOGIN);
            user.setId(UUID.randomUUID());
            String password = securityUtils.createNewPassword(user.getUsername());
            final ModelMapper modelMapper = new ModelMapper();
            final UserDetails userDetails = modelMapper.map(user, UserDetails.class);
            userDetails.setPassword(password);
            userRepository.save(userDetails);
            final UserPasswordHistory history = new UserPasswordHistory();
            history.setId(UUID.randomUUID());
            history.setUserId(user.getId());
            history.setPassword(password);
            mailUtils.sendEmail(user.getEmail(), "Account created successfully.",
                    "Hi " + user.getFirstName() + " " + user.getLastName()
                            + ",\nYour new account has been created.\nYour account details as follows.\n\nUsername: "
                            + user.getUsername() + "\nPassword: " + user.getPassword() + "\n\nThanks,\nAdmin");
            userPasswordHistoryRepository.save(history);
        } catch (final Exception e) {
            return createResponse(e.getMessage(), FAILURE, null, HttpStatus.BAD_REQUEST);
        }
        log.info(MessageConstants.USERS_CREATED_SUCCESSFULLY);
        return createResponse(MessageConstants.USERS_CREATED_SUCCESSFULLY, ResponseStatus.SUCCESS, user, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> _updateUsers(@RoleAccess(role = UserRoleEnum.USER_EDIT) final UUID authToken,
            final UserDto user) {
        try {
            final Optional<UserDetails> dbUserDetails = userRepository.findById(user.getId());
            if (dbUserDetails.isPresent()) {
                securityUtils.validateUpdateUserRequest(user);
                final ModelMapper modelMapper = new ModelMapper();
                final UserDetails providedUserDetails = modelMapper.map(user, UserDetails.class);

                if (!dbUserDetails.get().getUsername().equals(providedUserDetails.getUsername())) {
                    throw new IllegalArgumentException(MessageConstants.CANNOT_CHANGE_USERNAME_FOR_AN_USER);
                }

                if (null == providedUserDetails.getPassword()) {
                    providedUserDetails.setPassword(dbUserDetails.get().getPassword());
                }
                userRepository.save(providedUserDetails);
            } else {
                throw new IllegalArgumentException(MessageConstants.USER_NOT_FOUND);
            }
        } catch (final IllegalArgumentException e) {
            return createResponse(e.getMessage(), FAILURE, null, HttpStatus.BAD_REQUEST);
        } catch (final Exception e) {
            return createResponse(MessageConstants.INVALID_INPUT, FAILURE, null, HttpStatus.BAD_REQUEST);
        }
        return

        createResponse(MessageConstants.USERS_CREATED_SUCCESSFULLY, ResponseStatus.SUCCESS, user, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> _deleteUsers(@RoleAccess(role = UserRoleEnum.USER_DELETE) final UUID authToken,
            final UUID userId) {
        final Optional<UserDetails> userDetails = userRepository.findById(userId);
        if (userDetails.isPresent()) {
            if (userDetails.get().getUsername().equalsIgnoreCase("admin")) {
                return createResponse(MessageConstants.CANNOT_DELETE_ADMIN, FAILURE, null, HttpStatus.BAD_REQUEST);
            }
            userRepository.delete(userDetails.get());
        } else {
            return createResponse(MessageConstants.USER_NOT_FOUND, FAILURE, null, HttpStatus.BAD_REQUEST);
        }
        return createResponse(MessageConstants.USER_DELETED_SUCCESSFULLY, ResponseStatus.SUCCESS, null, HttpStatus.OK);
    }

}
