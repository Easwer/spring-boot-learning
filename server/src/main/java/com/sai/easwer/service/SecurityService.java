package com.sai.easwer.service;

import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.sai.easwer.annotation.RoleAccess;
import com.sai.easwer.constants.MessageConstants;
import com.sai.easwer.constants.SecurityConstants;
import com.sai.easwer.controller.SecurityController;
import com.sai.easwer.entity.UserDetails;
import com.sai.easwer.entity.UserSession;
import com.sai.easwer.model.AuditLogType;
import com.sai.easwer.model.LoginRequest;
import com.sai.easwer.model.LoginResponse;
import com.sai.easwer.model.AuditLogModules;
import com.sai.easwer.model.Response;
import com.sai.easwer.model.ResponseStatus;
import com.sai.easwer.model.UserDto;
import com.sai.easwer.model.UserRoleEnum;
import com.sai.easwer.repository.UserRepository;
import com.sai.easwer.repository.UserSessionRepository;
import com.sai.easwer.util.AuditLogger;
import com.sai.easwer.util.GlobalSettingsUtil;
import com.sai.easwer.util.MailUtils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import io.swagger.v3.oas.annotations.tags.Tag;

@Component
@Tag(name = "Security", description = "Security API's")
public class SecurityService extends BaseService implements SecurityController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserSessionRepository userSessionRepository;

  @Autowired
  private HttpServletRequest request;

  @Autowired
  private AuditLogger auditLogger;

  @Autowired
  private HttpServletRequest httpServletRequest;

  @Autowired
  private MailUtils mailUtils;

  @Value("${server.name}")
  private String serverName;

  @Override
  public ResponseEntity<Response> login(final LoginRequest loginRequest) {
    final String username = loginRequest.getUsername();
    final String password = loginRequest.getPassword();
    if (null == username) {
      return createResponse(MessageConstants.USERNAME_CANNOT_BE_EMPTY, ResponseStatus.FAILURE, null,
          HttpStatus.BAD_REQUEST);
    }

    if (null == password) {
      return createResponse(MessageConstants.PASSWORD_CANNOT_BE_EMPTY, ResponseStatus.FAILURE, null,
          HttpStatus.BAD_REQUEST);
    }

    final Optional<UserDetails> user = userRepository.findByUsername(username);
    if (user.isPresent()) {
      if (!user.get().getPassword().equals(password)) {
        final boolean configureSmtp = GlobalSettingsUtil.getBoolean(SecurityConstants.CONFIGURE_SMTP, true);
        final boolean passwordAutoGenerate = GlobalSettingsUtil.getBoolean(SecurityConstants.PASSWORD_AUTO_GENERATE,
            true);
        boolean isMailSendSuccessfully = false;
        if (passwordAutoGenerate && configureSmtp) {
          try {
            mailUtils.sendEmail(user.get().getEmail(), "SECURITY ALERT: Failure login attempt in " + serverName,
                "Hi " + user.get().getFirstName() + " " + user.get().getLastName() + ","
                    + "\n\nThere is an failed login attempt for the user linked to this email in the " + serverName
                    + " from IP:" + httpServletRequest.getRemoteAddr()
                    + ". If this is not you, please reset your password immediately in " + serverName
                    + "\n\n NOTE: This is an auto generated email. Please do not reply.\n\n Thanks,\n Administrator\n("
                    + serverName + ")");
            isMailSendSuccessfully = true;
          } catch (Exception e) {
            isMailSendSuccessfully = false;
          }
        }
        String failMessage = "Failed login attempt for user: " + username + " from IP: "
            + httpServletRequest.getRemoteAddr() + ".";
        if (passwordAutoGenerate && configureSmtp) {
          failMessage += " Warning mail send to user ";
          if (isMailSendSuccessfully) {
            failMessage += "successful.";
          } else {
            failMessage += "un-successful.";
          }
        }
        auditLogger.auditLog(failMessage, AuditLogModules.SECURITY, AuditLogType.LOGIN, ResponseStatus.FAILURE);
        return createResponse(MessageConstants.AUTHENTICATION_ERROR, ResponseStatus.FAILURE, null,
            HttpStatus.FORBIDDEN);
      }
    } else {
      return createResponse(MessageConstants.INVALID_USER_DETAILS, ResponseStatus.FAILURE, null,
          HttpStatus.BAD_REQUEST);
    }

    final UserSession userSession = createUserSession(user.get());

    final LoginResponse loginResponse = new LoginResponse();
    final ModelMapper modelMapper = new ModelMapper();
    final UserDto userDto = modelMapper.map(user.get(), UserDto.class);
    loginResponse.setUser(userDto);
    loginResponse.setAuthToken(userSession.getAuthToken());

    auditLogger.auditLog(MessageConstants.LOGIN_SUCCESSFUL_FOR_USER + username + "'.", AuditLogModules.SECURITY,
        AuditLogType.LOGIN, userSession);

    return createResponse(MessageConstants.LOGIN_SUCCESSFUL, ResponseStatus.SUCCESS, loginResponse, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Response> logout(final UUID authToken) {
    Optional<UserDetails> loginUser = null;
    Optional<UserSession> userSession = null;
    try {
      userSession = userSessionRepository.findByAuthToken(authToken);
      if (userSession.isPresent()) {
        loginUser = userRepository.findById(userSession.get().getUserId());
        userSessionRepository.delete(userSession.get());
        auditLogger.auditLog(MessageConstants.LOGOUT_SUCCESSFUL_FOR_USER + loginUser.get().getUsername() + "'.",
            AuditLogModules.SECURITY, AuditLogType.LOGOUT);
      } else {
        return createResponse(MessageConstants.INVALID_SESSION_DETAILS, ResponseStatus.FAILURE, null,
            HttpStatus.INTERNAL_SERVER_ERROR);
      }
    } catch (final Exception e) {
      return createResponse(MessageConstants.LOGOUT_FAILURE + e.getMessage(), ResponseStatus.FAILURE, null,
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
    auditLogger.auditLog(MessageConstants.LOGOUT_SUCCESSFUL_FOR_USER + loginUser.get().getUsername() + "'.",
        AuditLogModules.SECURITY, AuditLogType.LOGOUT);
    return createResponse(MessageConstants.LOGOUT_SUCCESSFUL, ResponseStatus.SUCCESS, null, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Response> _getServerStatus(@RoleAccess(role = UserRoleEnum.DEFAULT) final UUID authToken) {
    return createResponse("Alive", ResponseStatus.SUCCESS, null, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Response> _isValidToken(@RoleAccess(role = UserRoleEnum.DEFAULT) final UUID authToken) {
    final Optional<UserSession> userSession = userSessionRepository.findByAuthToken(authToken);
    if (userSession.isPresent()) {
      return createResponse("Active", ResponseStatus.SUCCESS, null, HttpStatus.OK);
    } else {
      return createResponse("In-Active", ResponseStatus.FAILURE, null, HttpStatus.UNAUTHORIZED);
    }
  }

  private UserSession createUserSession(final UserDetails userDetails) {
    final UserSession userSession = new UserSession();

    userSession.setId(UUID.randomUUID());
    userSession.setUserId(userDetails.getId());
    userSession.setAuthToken(UUID.randomUUID());
    userSession.setIpAddress(request.getRemoteAddr());
    userSession.setStartedTime(Calendar.getInstance().getTimeInMillis());
    userSession.setLastAccessTime(Calendar.getInstance().getTimeInMillis());

    userSessionRepository.save(userSession);

    return userSession;
  }

}