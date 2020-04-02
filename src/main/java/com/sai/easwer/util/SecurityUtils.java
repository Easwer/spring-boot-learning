package com.sai.easwer.util;

import com.sai.easwer.constants.MessageConstants;
import com.sai.easwer.constants.SecurityConstants;
import com.sai.easwer.constants.UserAccountStatus;
import com.sai.easwer.entity.UserDetails;
import com.sai.easwer.entity.UserGroup;
import com.sai.easwer.repository.UserRepository;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private UserRepository userRepository;

    // public boolean validToken(UUID authKey) {

    // boolean isValid = false;
    // UserSession userSessionDetails =
    // userSessionRepository.findByAuthToken(authKey);
    // if (userSessionDetails != null && userSessionDetails.getLastAccessTime() !=
    // null) {
    // isValid = true;
    // }
    // return isValid;
    // }

    // public boolean checkAuthorization(RoleOperation roleKey, UUID authKey) throws
    // Exception {

    // boolean isAuthorized = false;

    // UserSessionDetails userSessionDetails =
    // userSessionRepository.findByAuthToken(authKey);

    // if (userSessionDetails != null && userSessionDetails.getLastAccessTime() !=
    // null) {

    // UserDetails userDetails =
    // userRepository.findById(userSessionDetails.getUserId());

    // if (null != userDetails) {

    // isAuthorized = checkRole(userDetails, roleKey)

    // && checkIdleTimeout(userDetails, userSessionDetails, authKey);

    // } else {

    // userSessionRepository.deleteByUserId(userSessionDetails.getUserId());

    // throw new ServiceException("User is deleted.");

    // }

    // } else {

    // throw new ServiceException("Given token is not valid");

    // }

    // return isAuthorized;

    // }

    // private boolean checkIdleTimeout(UserDetails userDetails, UserSessionDetails
    // userSessionDetails,
    // UUID authKey) throws Exception {

    // boolean result = false;

    // Long limit = Long.sum(((long) userDetails.getIdleTimeout() * 60 * 1000),
    // userSessionDetails.getLastAccessTime());

    // Long currentMilliseconds = Calendar.getInstance().getTimeInMillis();

    // if (limit.compareTo(currentMilliseconds) <= 0) {

    // userSessionRepository.deleteByAuthToken(authKey);

    // WebSocketHandler.terminateUserSession(authKey);

    // auditLogger.addLog(userDetails, CommonConstants.LOGOUT,
    // SecurityConstants.ERROR_SESSION_TERMINATED_BY_INACTIVITY,

    // userSessionDetails.getIp());

    // } else {

    // userSessionDetails.setLastAccessTime(currentMilliseconds);

    // userSessionRepository.save(userSessionDetails);

    // result = true;

    // }

    // return result;

    // }

    // private boolean checkRole(UserDetails userDetails, RoleOperation roleKey) {

    // for (GroupDetails groupDetails : userDetails.getGroups()) {

    // groupDetails.getRoles().contains(roleKey);

    // for (RoleDetail role : groupDetails.getRoles()) {

    // if (role.getName().equals(roleKey.toString())) {

    // return true;

    // }

    // }

    // }

    // return false;

    // }

    // public void userStatusCheck(UserDetails userDetails) throws ServiceException
    // {

    // checkUserAccountStatus(userDetails);

    // checkDeptEntStatus(userDetails);

    // }

    // public void checkDeptEntStatus(UserDetails userDetails) throws
    // ServiceException {

    // if (enterpriseRepository.findOne(userDetails.getEntId()).getStatus()) {

    // if
    // (!departmentRepository.findOne(userDetails.getDepartmentDetails().getId()).isStatus())
    // {

    // throw new ServiceException(SecurityConstants.ERROR_DEPARTMENT_DISABLED);

    // }

    // } else {

    // throw new ServiceException(SecurityConstants.ERROR_ENTERPRISE_DISABLED);

    // }

    // }

    // public void checkUserAccountStatus(UserDetails user) throws Exception {

    // String accountStatus = user.getUserAccountStatus();

    // if (
    // accountStatus.equalsIgnoreCase(UserAccountStatus.LOCKED.getAccountStatus())

    // ||
    // accountStatus.equalsIgnoreCase(UserAccountStatus.BLOCKED.getAccountStatus())

    // ||
    // accountStatus.equalsIgnoreCase(UserAccountStatus.D
    // ORMANCY_PERIOD_EXCEEDED.getAccountStatus()))
    // {

    // throw new Exception(SecurityConstants.ERROR_AUTHENTICATION);

    // } else if (user.getAccountExpiry() != 0) {

    // if (accountStatus.equalsIgnoreCase(SecurityConstants.ACCOUNT_EXPIRED)) {

    // throw new Exception(SecurityConstants.ERROR_AUTHENTICATION);

    // } else if (user.getAccountExpiryTime() != null

    // &&
    // user.getAccountExpiryTime().compareTo(Calendar.getInstance().getTimeInMillis())
    // < 0) {

    // user.setAccountStatus(
    // userAccountStatusRepository.findByName(SecurityConstants.ACCOUNT_EXPIRED));

    // String description = "User account for user " + user.getUsername() + " has
    // been expired.";

    // auditLogger.addLog(user, CommonConstants.UPDATE, description);

    // userSessionRepository.deleteByUserId(user.getId());

    // userRepository.save(user);

    // throw new ServiceException(SecurityConstants.ERROR_AUTHENTICATION);

    // }

    // } else if (user.getPasswordExpiry() != 0) {

    // if (user.getPasswordExpiryTime() != null

    // && user.getPasswordExpiryTime().compareTo((new Date()).getTime()) < 0) {

    // user.setAccountStatus(
    // userAccountStatusRepository.findByName(SecurityConstants.PASSWORD_EXPIRED));

    // String description = "Password for user " + user.getUsername() + " has been
    // expired.";

    // auditLogger.addLog(user, CommonConstants.UPDATE, description);

    // userRepository.save(user);

    // }

    // }

    // }

    /**
     * Validates email address format.
     * 
     * @param email {@link String}
     * @throws Exception if not a valid email.
     */
    public void validateEmailAddress(final String email) throws Exception {

        if (email == null || email.equals("")) {

            throw new Exception(MessageConstants.EMAIL_CANNOT_BE_EMPTY);

        }

        try {

            final InternetAddress emailAddr = new InternetAddress(email);

            emailAddr.validate();

        } catch (final AddressException e) {

            throw new Exception(MessageConstants.INVALID_EMAIL);

        }

    }

    /**
     * Validates the username, first name and last name.
     * 
     * @param username {@link String}
     * @param firstName {@link String}
     * @param lastName {@link String}
     * @throws Exception if not valid.
     */
    public void validateUserName(final String username, final String firstName,
            final String lastName) throws Exception {

        if (username == null || username.equals("")) {
            throw new Exception(MessageConstants.USERNAME_CANNOT_BE_EMPTY);
        }

        Pattern p = Pattern.compile(SecurityConstants.USERNAME_PATTERN);

        if (!p.matcher(username).find()) {
            throw new Exception(MessageConstants.USERNAME_PATTERN_ERROR);
        }

        if (firstName == null || firstName.equals("")) {
            throw new Exception(MessageConstants.FIRST_NAME_CANNOT_BE_EMPTY);
        }
        
        if (lastName == null || lastName.equals("")) {
            throw new Exception(MessageConstants.LAST_NAME_CANNOT_BE_EMPTY);
        }

        p = Pattern.compile(SecurityConstants.NAME_PATTERN);

        if (!p.matcher(firstName).find()) {
            throw new Exception(MessageConstants.FIRST_NAME_ERROR);
        }    
        
        if (!p.matcher(lastName).find()) {
            throw new Exception(MessageConstants.LAST_NAME_ERROR);
        }

    }

    
    /**
     * Validates the password.<pre>
     * Valid password should satisfy the following criteria's
     *  - Should not contains username.
     *  - Should not contains user's first name.
     *  - Should not contains user's last name.
     *  - Should not contains user's email without domain.
     *  - Should satisfy minimum and maximum length criteria.
     *  - Should satisfy password pattern criteria based on global settings.</pre>
     * @param password {@link String}
     * @param username {@link String}
     * @param firstname {@link String}
     * @param lastname {@link String}
     * @param email {@link String}
     * @throws Exception if not valid
     */
    public void validatePassword(final String password, final String username,
            final String firstname, final String lastname, final String email) throws Exception {

        final int minLength =
                GlobalSettingsUtil.getGlobalSettings(SecurityConstants.PASSWORD_MIN_LENGTH,
                        SecurityConstants.DEFAULT_PASSWORD_MIN_LENGTH);

        final int maxLength =
                GlobalSettingsUtil.getGlobalSettings(SecurityConstants.PASSWORD_MAX_LENGTH,
                        SecurityConstants.DEFAULT_PASSWORD_MAX_LENGTH);

        if (password == null || password.equals("")) {
            throw new Exception(MessageConstants.PASSWORD_CANNOT_BE_EMPTY);
        }

        if (password.length() < minLength) {
            throw new Exception(MessageConstants.PASSWORD_MIN_LENGTH_ERROR);
        }

        if (password.length() > maxLength) {
            throw new Exception(MessageConstants.PASSWORD_MAX_LENGTH_ERROR);
        }

        if (password.contains(" ")) {
            throw new Exception(MessageConstants.PASSWORD_SHOULD_NOT_HAVE_SPACE);
        }

        if (password.toLowerCase().contains(firstname.toLowerCase())) {
            throw new Exception(MessageConstants.PASSWORD_SHOULD_NOT_CONTAIN_FIRSTNAME);
        }

        if (password.toLowerCase().contains(username.toLowerCase())) {
            throw new Exception(MessageConstants.PASSWORD_SHOULD_NOT_CONTAIN_USERNAME);
        }

        if (password.toLowerCase().contains(lastname.toLowerCase())) {
            throw new Exception(MessageConstants.PASSWORD_SHOULD_NOT_CONTAIN_LASTNAME);
        }

        if (password.toLowerCase().contains(email.split("@")[0].toLowerCase())) {
            throw new Exception(MessageConstants.PASSWORD_SHOULD_NOT_CONTAIN_EMAIL);
        }

        final StringBuilder patternBuilder =
                new StringBuilder(SecurityConstants.SMALLL_LETTER_REGEX);

        final boolean forceSpecialChar =
                GlobalSettingsUtil.getGlobalSettings(SecurityConstants.FORCE_SPECIAL_CHAR, true);

        final boolean forceCapitalLetter =
                GlobalSettingsUtil.getGlobalSettings(SecurityConstants.FORCE_SPECIAL_CHAR, true);

        final boolean forceNumber =
                GlobalSettingsUtil.getGlobalSettings(SecurityConstants.FORCE_SPECIAL_CHAR, true);

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
            throw new Exception(MessageConstants.PASSWORD_PATTERN_ERROR);
        }

    }

    // public void checkPasswordHistory(String password, UUID userId) throws
    // Exception {

    // List<UserPasswordHistory> userPasswordDetails = userPasswordRepository
    // .findByUserIdOrderByUpdatedTimeAsc(userId);
    // for (UserPasswordHistory userPasswordDetail : userPasswordDetails) {

    // if (new BCryptPasswordEncoder().matches(password,
    // userPasswordDetail.getPassword())) {
    // throw new
    // Exception(SecurityConstants.ERROR_PASSWORD_CANNOT_REUSE_OLD_PASSWORD);
    // }

    // }

    // updateUserPasswordDetails(password, userId, userPasswordDetails);

    // }

    // public void saveUserPasswordDetails(String passwordHash, UUID userId) {

    // UserPasswordHistoryDetails userPasswordDetail = new
    // UserPasswordHistoryDetails();

    // userPasswordDetail.setId(UUID.randomUUID());

    // userPasswordDetail.setUserId(userId);

    // userPasswordDetail.setPassword(passwordHash);

    // userPasswordDetail.setUpdatedTime(Calendar.getInstance().getTimeInMillis());

    // userPasswordRepository.save(userPasswordDetail);

    // }

    // public void updateUserPasswordDetails(String password, UUID userId,
    // List<UserPasswordHistoryDetails> userPasswordDetails) {

    // SystemSettings systemSettings = systemSettingsRepository.findAll().get(0);

    // if (userPasswordDetails != null && userPasswordDetails.size() >=
    // systemSettings.getUserPasswordHistoryLimit()) {

    // userPasswordRepository.delete(userPasswordDetails.get(0));

    // }

    // saveUserPasswordDetails(new BCryptPasswordEncoder().encode(password),
    // userId);

    // }

    /**
     *  Validates user resquest while updating the existing user.
     * 
     * @param user {@link UserDetails}
     * @throws Exception if not valid.
     */
    public void validateUpdateUserRequest(final UserDetails user) throws Exception {

        validateUserName(user.getUsername(), user.getFirstName(), user.getLastName());

        validateEmailAddress(user.getEmail());

        if (user.getPassword() != null && !user.getPassword().trim().equals("")) {

            validatePassword(user.getPassword(), user.getUsername(), user.getFirstName(),
                    user.getLastName(), user.getEmail());

            // checkPasswordHistory(user.getPassword(), user.getId());

        }

        // validateGroupInfo(user.getGroups());

        validateTimeouts(user);

        validateUserAccountStatus(user);

    }


    /**
     *  Validates user resquest while creating the new user.
     * 
     * @param user {@link UserDetails}
     * @throws Exception if not valid.
     */
    public void validateCreateUserRequest(final UserDetails user) throws Exception {

        validateUserName(user.getUsername(), user.getFirstName(), user.getLastName());

        validateEmailAddress(user.getEmail());

        validateUserExists(user.getUsername());

        validatePassword(user.getPassword(), user.getUsername(), user.getFirstName(),
                user.getLastName(), user.getEmail());

        // validateGroupInfo(user.getGroups());

        validateTimeouts(user);

    }

    /**
     * Validates whether the user exists or not by username.
     * 
     * @param username {@link String}
     * @throws Exception - if not valid
     */
    public void validateUserExists(final String username) throws Exception {

        final Optional<UserDetails> userDetails = userRepository.findByUsername(username);

        if (userDetails.isPresent()) {
            throw new Exception(MessageConstants.ERROR_USERNAME_ALREADY_EXISTS);
        }

    }

    /**
     * Checks wether User Account Status string is a valid user account status.
     * @param user {@link UserDetails}
     * @throws Exception if not valid
     */
    public void validateUserAccountStatus(final UserDetails user) throws Exception {

        if (!UserAccountStatus.contains(user.getUserAccountStatus())) {
            throw new Exception(MessageConstants.USER_ACCOUNT_STATUS_ERROR);
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
     * @param user {@link UserDetails}
     * @throws Exception if not valid
     */
    public void validateTimeouts(final UserDetails user) throws Exception {

        if (user.getPasswordExpiry() < SecurityConstants.PASSWORD_EXPIRY_MIN
                || user.getPasswordExpiry() > SecurityConstants.PASSWORD_EXPIRY_MAX) {
            throw new Exception(MessageConstants.PASSWORD_EXPIRY_ERROR);
        }

        if (user.getAccountExpiry() < SecurityConstants.ACCOUNT_EXPIRY_MIN
                || user.getAccountExpiry() > SecurityConstants.ACCOUNT_EXPIRY_MAX) {
            throw new Exception(MessageConstants.ACCOUNT_EXPIRY_ERROR);
        }

        if (user.getIdleTimeout() < SecurityConstants.IDLE_TIMEOUT_MIN
                || user.getIdleTimeout() > SecurityConstants.IDLE_TIMEOUT_MAX) {
            throw new Exception(MessageConstants.IDLE_TIMOUT_ERROR);
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

}
