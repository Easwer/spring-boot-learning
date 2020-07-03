package com.sai.easwer.util;

import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import com.sai.easwer.constants.MessageConstants;
import com.sai.easwer.constants.SecurityConstants;
import com.sai.easwer.entity.UserDetails;
import com.sai.easwer.entity.UserGroup;
import com.sai.easwer.entity.UserRole;
import com.sai.easwer.entity.UserSession;
import com.sai.easwer.entity.mapping.GroupRoleMapping;
import com.sai.easwer.entity.mapping.UserGroupMapping;
import com.sai.easwer.model.AuditLogType;
import com.sai.easwer.model.Modules;
import com.sai.easwer.model.UserAccountStatus;
import com.sai.easwer.model.UserDto;
import com.sai.easwer.repository.UserRepository;
import com.sai.easwer.repository.UserRoleRepository;
import com.sai.easwer.repository.UserSessionRepository;
import com.sai.easwer.repository.mapping.GroupRoleMappingRepository;
import com.sai.easwer.repository.mapping.UserGroupMappingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Util file to handle Security operations.
 * 
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-28 15:43:56
 * @modify date 2020-02-28 15:43:56
 */
@Component
public class SecurityUtils {

    @Autowired
    private AuditLogger auditLogger;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserSessionRepository userSessionRepository;

    @Autowired
    private GroupRoleMappingRepository groupRoleMappingRepository;

    @Autowired
    private UserGroupMappingRepository userGroupMappingRepository;

    private static final String GIVEN_TOKEN_IS_NOT_VALID = "Given token is not valid";
    private static final String USER_IS_DELETED = "User is deleted.";

    /**
     * Checks whether provided role is available for the current user and user
     * session is active.
     * 
     * @param role      {@link String}
     * @param authToken {@link UUID}
     * @return {@link boolean}
     * @throws Exception Exception
     */
    public boolean checkAuthorization(final String role, final UUID authToken) throws Exception {
        boolean isAuthorized = false;
        final Optional<UserSession> userSession = userSessionRepository.findByAuthToken(authToken);
        if (userSession.isPresent()) {
            if (userSession.get().getLastAccessTime() != null) {
                final Optional<UserDetails> userDetails = userRepository.findById(userSession.get().getUserId());
                if (userDetails.isPresent()) {
                    isAuthorized = checkRole(userDetails.get(), role)
                            && checkIdleTimeout(userDetails.get(), userSession.get(), authToken);
                } else {
                    userSessionRepository.deleteByUserId(userSession.get().getUserId());
                    throw new Exception(USER_IS_DELETED);
                }
            }
        } else {
            throw new Exception(GIVEN_TOKEN_IS_NOT_VALID);
        }
        return isAuthorized;
    }

    /**
     * Checks whether provided user session is active.
     * 
     * @param userDetails {@link UserDetails}
     * @param userSession {@link UserSession}
     * @param authToken   {@link UUID}
     * @return {@link boolean}
     */
    private boolean checkIdleTimeout(final UserDetails userDetails, final UserSession userSession,
            final UUID authToken) {
        final Long limit = Long.sum((long) userDetails.getIdleTimeout() * 60 * 1000, userSession.getLastAccessTime());
        final Long currentMilliseconds = Calendar.getInstance().getTimeInMillis();

        if (limit.compareTo(currentMilliseconds) <= 0) {
            userSessionRepository.deleteByAuthToken(authToken);
            // WebSocketHandler.terminateUserSession(authToken);
            auditLogger.auditLog(MessageConstants.ERROR_SESSION_TERMINATED_BY_INACTIVITY, Modules.SECURITY,
                    AuditLogType.LOGOUT);
            return false;
        } else {
            userSession.setLastAccessTime(currentMilliseconds);
            userSessionRepository.save(userSession);
            return true;
        }
    }

    /**
     * Checks whether provided role is available for the current user.
     * 
     * @param userDetails {@link UserDetails}
     * @param role        {@link String}
     * @return {@link boolean}
     */
    private boolean checkRole(final UserDetails userDetails, final String role) {
        final List<UserGroupMapping> userGroupMappingList = userGroupMappingRepository
                .findByUserId(userDetails.getId());
        for (final UserGroupMapping userGroupMapping : userGroupMappingList) {
            final List<GroupRoleMapping> groupRoleMappingList = groupRoleMappingRepository
                    .findByGroupId(userGroupMapping.getGroupId());
            for (final GroupRoleMapping groupRoleMapping : groupRoleMappingList) {
                final Optional<UserRole> userRole = userRoleRepository.findById(groupRoleMapping.getRoleId());
                if (userRole.isPresent() && userRole.get().getName().equalsIgnoreCase(role)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Validates email address format.
     * 
     * @param email    {@link String}
     * @param username {@link String}
     * @throws IllegalArgumentException if not a valid email.
     */
    public void validateEmailAddress(final String email, final String username) throws IllegalArgumentException {
        if (email == null || email.trim().equals(MessageConstants.EMPTY)) {
            throw new IllegalArgumentException(MessageConstants.EMAIL_CANNOT_BE_EMPTY);
        }

        try {
            final InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
            final Optional<UserDetails> userDetails = userRepository.findByEmailAndUsernameNot(email, username);
            if (userDetails.isPresent()) {
                throw new IllegalArgumentException(MessageConstants.EMAIL_SHOULD_BE_UNIQUE);
            }
        } catch (final AddressException e) {
            throw new IllegalArgumentException(MessageConstants.INVALID_EMAIL);
        }
    }

    /**
     * Validates the username, first name and last name.
     * 
     * @param username  {@link String}
     * @param firstName {@link String}
     * @param lastName  {@link String}
     * @throws IllegalArgumentException if not valid.
     */
    public void validateUserName(final String username, final String firstName, final String lastName)
            throws IllegalArgumentException {

        if (username == null || username.trim().equals(MessageConstants.EMPTY)) {
            throw new IllegalArgumentException(MessageConstants.USERNAME_CANNOT_BE_EMPTY);
        }

        Pattern pattern = Pattern.compile(SecurityConstants.USERNAME_PATTERN);

        if (pattern.matcher(username).find()) {
            throw new IllegalArgumentException(MessageConstants.USERNAME_PATTERN_ERROR);
        }

        if (firstName == null || firstName.trim().equals(MessageConstants.EMPTY)) {
            throw new IllegalArgumentException(MessageConstants.FIRST_NAME_CANNOT_BE_EMPTY);
        }

        if (lastName == null || lastName.trim().equals(MessageConstants.EMPTY)) {
            throw new IllegalArgumentException(MessageConstants.LAST_NAME_CANNOT_BE_EMPTY);
        }

        pattern = Pattern.compile(SecurityConstants.NAME_PATTERN);

        if (pattern.matcher(firstName).find()) {
            throw new IllegalArgumentException(MessageConstants.FIRST_NAME_ERROR);
        }

        if (pattern.matcher(lastName).find()) {
            throw new IllegalArgumentException(MessageConstants.LAST_NAME_ERROR);
        }

    }

    /**
     * Validates the password.
     * 
     * <pre>
     * Valid password should satisfy the following criteria's
     *  - Should not contains username.
     *  - Should not contains user's first name.
     *  - Should not contains user's last name.
     *  - Should not contains user's email without domain.
     *  - Should satisfy minimum and maximum length criteria.
     *  - Should satisfy password pattern criteria based on global settings.
     * </pre>
     * 
     * @param password  {@link String}
     * @param username  {@link String}
     * @param firstname {@link String}
     * @param lastname  {@link String}
     * @param email     {@link String}
     * @throws IllegalArgumentException if not valid
     */
    public void validatePassword(final String password, final String username, final String firstname,
            final String lastname, final String email) throws IllegalArgumentException {

        final int minLength = GlobalSettingsUtil.getInt(SecurityConstants.PASSWORD_MIN_LENGTH,
                SecurityConstants.DEFAULT_PASSWORD_MIN_LENGTH);

        final int maxLength = GlobalSettingsUtil.getInt(SecurityConstants.PASSWORD_MAX_LENGTH,
                SecurityConstants.DEFAULT_PASSWORD_MAX_LENGTH);

        if (password == null || password.equals("")) {
            throw new IllegalArgumentException(MessageConstants.PASSWORD_CANNOT_BE_EMPTY);
        }

        if (password.length() < minLength) {
            throw new IllegalArgumentException(MessageConstants.PASSWORD_MIN_LENGTH_ERROR);
        }

        if (password.length() > maxLength) {
            throw new IllegalArgumentException(MessageConstants.PASSWORD_MAX_LENGTH_ERROR);
        }

        if (password.contains(" ")) {
            throw new IllegalArgumentException(MessageConstants.PASSWORD_SHOULD_NOT_HAVE_SPACE);
        }

        if (password.toLowerCase().contains(firstname.toLowerCase())) {
            throw new IllegalArgumentException(MessageConstants.PASSWORD_SHOULD_NOT_CONTAIN_FIRSTNAME);
        }

        if (password.toLowerCase().contains(username.toLowerCase())) {
            throw new IllegalArgumentException(MessageConstants.PASSWORD_SHOULD_NOT_CONTAIN_USERNAME);
        }

        if (password.toLowerCase().contains(lastname.toLowerCase())) {
            throw new IllegalArgumentException(MessageConstants.PASSWORD_SHOULD_NOT_CONTAIN_LASTNAME);
        }

        if (password.toLowerCase().contains(email.split("@")[0].toLowerCase())) {
            throw new IllegalArgumentException(MessageConstants.PASSWORD_SHOULD_NOT_CONTAIN_EMAIL);
        }

        final StringBuilder patternBuilder = new StringBuilder(SecurityConstants.SMALLL_LETTER_REGEX);

        final boolean forceSpecialChar = GlobalSettingsUtil.getBoolean(SecurityConstants.FORCE_SPECIAL_CHAR, true);

        final boolean forceCapitalLetter = GlobalSettingsUtil.getBoolean(SecurityConstants.FORCE_SPECIAL_CHAR, true);

        final boolean forceNumber = GlobalSettingsUtil.getBoolean(SecurityConstants.FORCE_SPECIAL_CHAR, true);

        if (forceSpecialChar) {
            patternBuilder.append(SecurityConstants.SYMBOLS_REGEX);
        }

        if (forceCapitalLetter) {
            patternBuilder.append(SecurityConstants.CAPTIAL_LETTER_REGEX);
        }

        if (forceNumber) {
            patternBuilder.append(SecurityConstants.NUMBER_REGEX);
        }

        patternBuilder.append(".{" + minLength + "," + maxLength + "})");

        final Pattern p = Pattern.compile(patternBuilder.toString());

        if (!p.matcher(password).find()) {
            throw new IllegalArgumentException(MessageConstants.PASSWORD_PATTERN_ERROR);
        }

    }

    /**
     * Validates user request while updating the existing user.
     * 
     * @param user {@link UserDto}
     * @throws IllegalArgumentException if not valid.
     */
    public void validateUpdateUserRequest(final UserDto user) throws IllegalArgumentException {
        validateUserName(user.getUsername(), user.getFirstName(), user.getLastName());
        validateEmailAddress(user.getEmail(), user.getUsername());
        if (user.getPassword() != null && !user.getPassword().trim().equals("")) {
            validatePassword(user.getPassword(), user.getUsername(), user.getFirstName(), user.getLastName(),
                    user.getEmail());
        }
        validateTimeouts(user);
        validateUserAccountStatus(user);
    }

    /**
     * Validates user request while creating the new user.
     * 
     * @param user {@link UserDto}
     * @throws IllegalArgumentException if not valid.
     */
    public void validateCreateUserRequest(final UserDto user) throws IllegalArgumentException {
        validateUserName(user.getUsername(), user.getFirstName(), user.getLastName());
        validateUserExists(user.getUsername());
        validateEmailAddress(user.getEmail(), user.getUsername());
        final boolean configureSmtp = GlobalSettingsUtil.getBoolean(SecurityConstants.CONFIGURE_SMTP, true);
        final boolean passwordAutoGenerate = GlobalSettingsUtil.getBoolean(SecurityConstants.PASSWORD_AUTO_GENERATE,
                true);
        if (passwordAutoGenerate && configureSmtp) {
            validatePassword(user.getPassword(), user.getUsername(), user.getFirstName(), user.getLastName(),
                    user.getEmail());
        }
        validateTimeouts(user);
    }

    /**
     * Validates whether the user exists or not by username.
     * 
     * @param username {@link String}
     * @throws IllegalArgumentException - if not valid
     */
    public void validateUserExists(final String username) throws IllegalArgumentException {
        final Optional<UserDetails> userDetails = userRepository.findByUsername(username);
        if (userDetails.isPresent()) {
            throw new IllegalArgumentException(MessageConstants.ERROR_USERNAME_ALREADY_EXISTS);
        }
    }

    /**
     * Checks wether User Account Status string is a valid user account status.
     * 
     * @param user {@link UserDto}
     * @throws IllegalArgumentException if not valid
     */
    public void validateUserAccountStatus(final UserDto user) throws IllegalArgumentException {
        if (null != user.getAccountStatus()
                && null == UserAccountStatus.fromValue(user.getAccountStatus().getValue())) {
            throw new IllegalArgumentException(MessageConstants.USER_ACCOUNT_STATUS_ERROR);
        }
    }

    /**
     * <pre>
     * Validates user password expiry, account expiry and idle timeout values int the user request.
     * - Password Expiry: Minimum = 30 days and Maximum = 120 days.
     * - Account Expiry: Minimum = 0 days and Maximum = 120 days.
     * - Idle Timeout: Minimum = 15 minutes and Maximum = 120 minutes.
     * </pre>
     * 
     * @param user {@link UserDto}
     * @throws IllegalArgumentException if not valid
     */
    public void validateTimeouts(final UserDto user) throws IllegalArgumentException {
        if (user.getPasswordExpiry() < SecurityConstants.PASSWORD_EXPIRY_MIN
                || user.getPasswordExpiry() > SecurityConstants.PASSWORD_EXPIRY_MAX) {
            throw new IllegalArgumentException(MessageConstants.PASSWORD_EXPIRY_ERROR);
        }
        if (user.getAccountExpiry() < SecurityConstants.ACCOUNT_EXPIRY_MIN
                || user.getAccountExpiry() > SecurityConstants.ACCOUNT_EXPIRY_MAX) {
            throw new IllegalArgumentException(MessageConstants.ACCOUNT_EXPIRY_ERROR);
        }
        if (user.getIdleTimeout() < SecurityConstants.IDLE_TIMEOUT_MIN
                || user.getIdleTimeout() > SecurityConstants.IDLE_TIMEOUT_MAX) {
            throw new IllegalArgumentException(MessageConstants.IDLE_TIMOUT_ERROR);
        }
    }

    /**
     * Validates group list.
     * 
     * @param groupList {@link List} of {@link UserGroup}
     * @throws Exception if not valid
     */
    public void validateGroupInfo(final List<UserGroup> groupList) throws Exception {
        if (groupList.isEmpty()) {
            throw new Exception(MessageConstants.GROUPS_CANNOT_BE_EMPTY);
        }
    }

    /**
     * Converts milliseconds to date format.
     * 
     * @param milliSecond {@link Long}
     * @return Date format {@link String}
     */
    public String milliSecondsToDateString(final long milliSecond) {
        final DateFormat simple = new SimpleDateFormat("dd MMM yyyy HH:mm:ss:SSS");
        final Date result = new Date(milliSecond);
        return simple.format(result);
    }

    public String createNewPassword(final String username) {
        final String password = generatingRandomAlphanumericString();
        return password;
    }

    public String generatingRandomAlphanumericString() {
        final String lowerCharacters = "abcdefghijklmnopqrstuvwxyz";
        final String upperCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String specialCharacters = "@#!$%";
        final boolean forceSpecialChar = GlobalSettingsUtil.getBoolean(SecurityConstants.FORCE_SPECIAL_CHAR, true);
        final boolean forceCapitalLetter = GlobalSettingsUtil.getBoolean(SecurityConstants.FORCE_SPECIAL_CHAR, true);
        final boolean forceNumber = GlobalSettingsUtil.getBoolean(SecurityConstants.FORCE_SPECIAL_CHAR, true);

        StringBuilder sb = new StringBuilder();
        final SecureRandom random = new SecureRandom();

        for (int i = 0; i < 8; i++) {
            sb.append(lowerCharacters.charAt(random.nextInt(lowerCharacters.length())));
        }

        if (forceSpecialChar) {
            sb.append(specialCharacters.charAt(random.nextInt(specialCharacters.length())));
        }

        if (forceNumber) {
            sb.append(random.nextInt(99));
        }
        if (forceCapitalLetter) {
            for (int i = 0; i < 4; i++) {
                sb.append(upperCharacters.charAt(random.nextInt(upperCharacters.length())));
            }
        }

        return sb.toString();

    }
}
