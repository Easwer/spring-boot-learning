package com.sai.easwer.constants;

import com.sai.easwer.util.GlobalSettingsUtil;

/**
 * Constants file to keep the error messeges.
 * 
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-28 14:24:18
 * @modify date 2020-06-29 20:00:57
 */
public class MessageConstants {

        private MessageConstants() {

        }

        public static final String SYSTEM_USER = "System";

        public static final String EMPTY = "";

        public static final String LOGIN_SUCCESSFUL = "Login successful.";

        public static final String LOGOUT_SUCCESSFUL = "Logout successful.";

        public static final String LOGIN_SUCCESSFUL_FOR_USER = "Login successful for user '";

        public static final String LOGOUT_SUCCESSFUL_FOR_USER = "Logout successful for user '";

        public static final String INVALID_SESSION_DETAILS = "Invalid Session details.";

        public static final String LOGOUT_FAILURE = "Logout Failure due to ";

        public static final String INVALID_USER_DETAILS = "Invalid User details.";

        public static final String AUTHENTICATION_ERROR = "Authentication Error.";

        public static final String PASSWORD_CANNOT_BE_EMPTY = "Password cannot be empty";

        public static final String PASSWORD_PATTERN_ERROR = "Password should contain " + "one uppercase character, "
                        + "one lowercase " + "character, one special character(@#!$%) and one number";

        public static final String USERNAME_PATTERN_ERROR = "Username can only have alphanumeric.";

        public static final String FIRST_NAME_ERROR = "Last name can only have alpha charachters.";

        public static final String LAST_NAME_ERROR = "Last name can only have alpha charachters.";

        public static final String PASSWORD_SHOULD_NOT_HAVE_SPACE = "Password should not have space";

        public static final String PASSWORD_SHOULD_NOT_CONTAIN_USERNAME = "Password should not contain username.";

        public static final String PASSWORD_SHOULD_NOT_CONTAIN_FIRSTNAME = "Password should not contain user's firstname.";

        public static final String PASSWORD_SHOULD_NOT_CONTAIN_LASTNAME = "Password should not contain user's lastname.";

        public static final String PASSWORD_SHOULD_NOT_CONTAIN_EMAIL = "Password should not contain user's email address.";

        public static final String EMAIL_CANNOT_BE_EMPTY = "Email address already exists.";

        public static final String EMAIL_SHOULD_BE_UNIQUE = "Email address should be unique.";

        public static final String EMAIL_ERROR = "Invalid Email address.";

        public static final String GLOBAL_SETTINGS_RETRIVED_SUCCESSFULLY = "Global Settings retrived Successfully.";

        public static final String GLOBAL_SETTINGS_NO_CONTENT = "No Global settings found.";

        public static final String GLOBAL_SETTINGS_INVALID_KEY = "Invalid key.";

        public static final String GLOBAL_SETTINGS_INVALID_MODULE = "Invalid module.";

        public static final String LAST_NAME_CANNOT_BE_EMPTY = "Last Name cannot be empty";

        public static final String FIRST_NAME_CANNOT_BE_EMPTY = "First name cannot be empty";

        public static final String INVALID_EMAIL = "Invalid Email address";

        public static final String USERNAME_CANNOT_BE_EMPTY = "Username cannot be empty";

        public static final String GROUPS_CANNOT_BE_EMPTY = "Groups cannot be empty";

        public static final String USER_DELETED_SUCCESSFULLY = "User deleted successfully.";

        public static final String IDLE_TIMOUT_ERROR = "Idle timeout value should be between "
                        + SecurityConstants.IDLE_TIMEOUT_MIN + " and " + SecurityConstants.IDLE_TIMEOUT_MAX;

        public static final String ACCOUNT_EXPIRY_ERROR = "Account expiry value should be between "
                        + SecurityConstants.ACCOUNT_EXPIRY_MIN + " and " + SecurityConstants.ACCOUNT_EXPIRY_MAX;

        public static final String PASSWORD_EXPIRY_ERROR = "Password expiry value should be between "
                        + SecurityConstants.PASSWORD_EXPIRY_MIN + " and " + SecurityConstants.PASSWORD_EXPIRY_MAX;

        public static final String PASSWORD_MIN_LENGTH_ERROR = "Password should have minimum " + GlobalSettingsUtil
                        .getInt(SecurityConstants.PASSWORD_MIN_LENGTH, SecurityConstants.DEFAULT_PASSWORD_MIN_LENGTH)
                        + " characters.";

        public static final String PASSWORD_MAX_LENGTH_ERROR = "Password should have maximum " + GlobalSettingsUtil
                        .getInt(SecurityConstants.PASSWORD_MAX_LENGTH, SecurityConstants.DEFAULT_PASSWORD_MAX_LENGTH)
                        + " characters.";

        public static final String USER_ACCOUNT_STATUS_ERROR = "Invalid User Account Status.";

        public static final String USER_NOT_FOUND = "User not found.";

        public static final String CANNOT_DELETE_ADMIN = "Administrator user canoot be deleted.";

        public static final String INVALID_USER_ID = "Invalid user id.";

        public static final String INVALID_AUDTILOG_ID = "Invalid user id.";

        public static final String NO_USERS_FOUND = "No users found.";

        public static final String USERS_FOUND_SUCCESSFULLY = "Users found successfully.";

        public static final String ERROR_USERNAME_ALREADY_EXISTS = "Username already exists.";

        public static final String INVALID_INPUT = "Invalid Input.";

        public static final String CANNOT_CHANGE_USERNAME_FOR_AN_USER = "Cannot change username for an user";

        public static final String USERS_CREATED_SUCCESSFULLY = "Users created successfully.";

        public static final String NO_AUDIT_LOG_FOUND = "No audit log found.";

        public static final String USER_FOUND_SUCCESSFULLY = "User found successfully.";

        public static final String AUDIT_LOGS_FOUND_SUCCESSFULLY = "Auditlog(s) found successfully.";

        public static final String NO_AUDIT_LOGS_FOUND = "No audit logs found.";

        public static final String ERROR_SESSION_TERMINATED_BY_INACTIVITY = "User session terminated due to inactivity.";

        public static final String ERROR_IN_GET_GLOBAL_SETTINGS_DUE_TO = "Error in getGlobalSettings due to ";

        public static final String MAIL_DEBUG = "mail.debug";

        public static final String MAIL_SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";

        public static final String MAIL_SMTP_AUTH = "mail.smtp.auth";

        public static final String TRUE = "true";

        public static final String FAILED_TO_SEND_EMAIL_DUE_TO = "Failed to send email due to ";
}
