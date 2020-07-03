-- User Roles
INSERT INTO user_role (id, version, created_time, last_updated_time, created_by, last_updated_by, name, description)
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'SYSTEM', 'SYSTEM', 'USER_READ', 'View User');

INSERT INTO user_role (id, version, created_time, last_updated_time, created_by, last_updated_by, name, description)
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'SYSTEM', 'SYSTEM', 'USER_CREATE', 'Create User');
INSERT INTO user_role (id, version, created_time, last_updated_time, created_by, last_updated_by, name, description)
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'SYSTEM', 'SYSTEM', 'USER_EDIT', 'Edit User');

INSERT INTO user_role (id, version, created_time, last_updated_time, created_by, last_updated_by, name, description)
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'SYSTEM', 'SYSTEM', 'USER_DELETE', 'Delete User');

INSERT INTO user_role (id, version, created_time, last_updated_time, created_by, last_updated_by, name, description)
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'SYSTEM', 'SYSTEM', 'AUDITLOG_READ', 'View Auditlog');

INSERT INTO user_role (id, version, created_time, last_updated_time, created_by, last_updated_by, name, description)
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'SYSTEM', 'SYSTEM', 'PURGE_AUDITLOG', 'Purge Auditlog');

INSERT INTO user_role (id, version, created_time, last_updated_time, created_by, last_updated_by, name, description)
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'SYSTEM', 'SYSTEM', 'GLOBAL_SETTINGS_READ', 'View Global Settings');

INSERT INTO user_role (id, version, created_time, last_updated_time, created_by, last_updated_by, name, description)
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'SYSTEM', 'SYSTEM', 'GLOBAL_SETTINGS_EDIT', 'Edit Global Settings');

INSERT INTO user_role (id, version, created_time, last_updated_time, created_by, last_updated_by, name, description)
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'SYSTEM', 'SYSTEM', 'DEFAULT', 'Default Role');

-- User Groups
INSERT INTO user_group (id, version, created_time, last_updated_time, created_by, last_updated_by, name, is_default, description)
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'SYSTEM', 'SYSTEM', 'Administrator', TRUE, 'Administrator Group');

INSERT INTO user_group (id, version, created_time, last_updated_time, created_by, last_updated_by, name, is_default, description)
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'SYSTEM', 'SYSTEM', 'Viewers', TRUE, 'Read Only User Group');

-- User Roles and Groups mapping
INSERT INTO group_role_mapping (id, version, created_time, last_updated_time, created_by, last_updated_by, group_id, role_id)
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'admin', 'admin', (SELECT id FROM user_group where name = 'Administrator'), (SELECT id FROM user_role where name = 'USER_READ'));

INSERT INTO group_role_mapping (id, version, created_time, last_updated_time, created_by, last_updated_by, group_id, role_id)
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'admin', 'admin', (SELECT id FROM user_group where name = 'Administrator'), (SELECT id FROM user_role where name = 'DEFAULT'));

INSERT INTO group_role_mapping (id, version, created_time, last_updated_time, created_by, last_updated_by, group_id, role_id)
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'admin', 'admin', (SELECT id FROM user_group where name = 'Viewers'), (SELECT id FROM user_role where name = 'DEFAULT'));

INSERT INTO group_role_mapping (id, version, created_time, last_updated_time, created_by, last_updated_by, group_id, role_id)
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'admin', 'admin', (SELECT id FROM user_group where name = 'Administrator'), (SELECT id FROM user_role where name = 'USER_CREATE'));

INSERT INTO group_role_mapping (id, version, created_time, last_updated_time, created_by, last_updated_by, group_id, role_id)
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'admin', 'admin', (SELECT id FROM user_group where name = 'Administrator'), (SELECT id FROM user_role where name = 'USER_EDIT'));

INSERT INTO group_role_mapping (id, version, created_time, last_updated_time, created_by, last_updated_by, group_id, role_id)
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'admin', 'admin', (SELECT id FROM user_group where name = 'Administrator'), (SELECT id FROM user_role where name = 'USER_DELETE'));

INSERT INTO group_role_mapping (id, version, created_time, last_updated_time, created_by, last_updated_by, group_id, role_id)
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'admin', 'admin', (SELECT id FROM user_group where name = 'Administrator'), (SELECT id FROM user_role where name = 'AUDITLOG_READ'));

INSERT INTO group_role_mapping (id, version, created_time, last_updated_time, created_by, last_updated_by, group_id, role_id)
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'admin', 'admin', (SELECT id FROM user_group where name = 'Administrator'), (SELECT id FROM user_role where name = 'PURGE_AUDITLOG'));

INSERT INTO group_role_mapping (id, version, created_time, last_updated_time, created_by, last_updated_by, group_id, role_id)
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'admin', 'admin', (SELECT id FROM user_group where name = 'Administrator'), (SELECT id FROM user_role where name = 'GLOBAL_SETTINGS_READ'));

INSERT INTO group_role_mapping (id, version, created_time, last_updated_time, created_by, last_updated_by, group_id, role_id)
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'admin', 'admin', (SELECT id FROM user_group where name = 'Viewers'), (SELECT id FROM user_role where name = 'GLOBAL_SETTINGS_READ'));

INSERT INTO group_role_mapping (id, version, created_time, last_updated_time, created_by, last_updated_by, group_id, role_id)
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'admin', 'admin', (SELECT id FROM user_group where name = 'Administrator'), (SELECT id FROM user_role where name = 'GLOBAL_SETTINGS_EDIT'));

INSERT INTO group_role_mapping (id, version, created_time, last_updated_time, created_by, last_updated_by, group_id, role_id)
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'admin', 'admin', (SELECT id FROM user_group where name = 'Viewers'), (SELECT id FROM user_role where name = 'AUDITLOG_READ'));

INSERT INTO group_role_mapping (id, version, created_time, last_updated_time, created_by, last_updated_by, group_id, role_id)
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'admin', 'admin', (SELECT id FROM user_group where name = 'Viewers'), (SELECT id FROM user_role where name = 'USER_READ'));

-- User details
INSERT INTO user_details (id, version, created_time, last_updated_time, created_by, last_updated_by, username, first_name, last_name, password, email, idle_timeout, account_status, account_expiry, password_expiry)
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'SYSTEM', 'SYSTEM', 'admin', 'Administrator', 'User', 'admin', 'admin@easwer.com', 15, 'ACTIVE', 0, 30);

-- User Group Mapping
INSERT INTO user_group_mapping (id, version, created_time, last_updated_time, created_by, last_updated_by, user_id, group_id)
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'SYSTEM', 'SYSTEM', (SELECT id from user_details where username='admin'), (SELECT id FROM user_group where name = 'Administrator'));

-- Global Settings
INSERT INTO global_settings (id, version, created_time, last_updated_time, created_by, last_updated_by, module, sub_module, key, value, description)
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'SYSTEM', 'SYSTEM', 'USER', 'PASSWORD', 'forceSpecialChar', 'true', 'Is special character required in user password.');

INSERT INTO global_settings (id, version, created_time, last_updated_time, created_by, last_updated_by, module, sub_module, key, value, description)
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'SYSTEM', 'SYSTEM', 'USER', 'PASSWORD', 'forceNumber', 'true', 'Is special number required in user password.');

INSERT INTO global_settings (id, version, created_time, last_updated_time, created_by, last_updated_by, module, sub_module, key, value, description)
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'SYSTEM', 'SYSTEM', 'USER', 'PASSWORD', 'forceCapitalLetter', 'true', 'Is upper case character required in user password.');

INSERT INTO global_settings (id, version, created_time, last_updated_time, created_by, last_updated_by, module, sub_module, key, value, description)
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'SYSTEM', 'SYSTEM', 'USER', 'PASSWORD', 'passwordMinLength', '8', 'Minimum length allowed for the user password.');

INSERT INTO global_settings (id, version, created_time, last_updated_time, created_by, last_updated_by, module, sub_module, key, value, description)
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'SYSTEM', 'SYSTEM', 'USER', 'PASSWORD', 'passwordMaxLength', '120', 'Maximum length allowed for the user password.');

INSERT INTO global_settings (id, version, created_time, last_updated_time, created_by, last_updated_by, module, sub_module, key, value, description)
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'SYSTEM', 'SYSTEM', 'USER', 'PASSWORD', 'passwordAutoGenerate', 'true', 'Password will be generated during user creation and mailed to the user. This configuration will work only if the smtp server is configured properly.');

INSERT INTO global_settings (id, version, created_time, last_updated_time, created_by, last_updated_by, module, sub_module, key, value, description)
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'SYSTEM', 'SYSTEM', 'USER', 'USER', 'changePasswordOnLogin', 'true', 'Does user needs to change password on first login.');

INSERT INTO global_settings (id, version, created_time, last_updated_time, created_by, last_updated_by, module, sub_module, key, value, description)
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'SYSTEM', 'SYSTEM', 'MAIL_SERVER', 'SMTP', 'configureSmtp', 'false', 'Configure SMTP server.');

INSERT INTO global_settings (id, version, created_time, last_updated_time, created_by, last_updated_by, module, sub_module, key, value, description)
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'SYSTEM', 'SYSTEM', 'MAIL_SERVER', 'SMTP', 'smtpHost', 'smtp.host', 'Host name of the SMTP server.');

INSERT INTO global_settings (id, version, created_time, last_updated_time, created_by, last_updated_by, module, sub_module, key, value, description)
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'SYSTEM', 'SYSTEM', 'MAIL_SERVER', 'SMTP', 'smtpPort', '587', 'Port name of the SMTP server.');

INSERT INTO global_settings (id, version, created_time, last_updated_time, created_by, last_updated_by, module, sub_module, key, value, description)
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'SYSTEM', 'SYSTEM', 'MAIL_SERVER', 'SMTP', 'smtpUsername', 'example@domail.com', 'Username of the SMTP server.');

INSERT INTO global_settings (id, version, created_time, last_updated_time, created_by, last_updated_by, module, sub_module, key, value, description)
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'SYSTEM', 'SYSTEM', 'MAIL_SERVER', 'SMTP', 'smtpPassword', 'testPassword', 'Password of the SMTP server.');

INSERT INTO global_settings (id, version, created_time, last_updated_time, created_by, last_updated_by, module, sub_module, key, value, description)
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'SYSTEM', 'SYSTEM', 'MAIL_SERVER', 'SMTP', 'smtpAuth', 'true', 'Enable authentication for the SMTP server.');

INSERT INTO global_settings (id, version, created_time, last_updated_time, created_by, last_updated_by, module, sub_module, key, value, description)
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'SYSTEM', 'SYSTEM', 'MAIL_SERVER', 'SMTP', 'smtpTls', 'true', 'Enable TLS for the SMTP server.');

INSERT INTO global_settings (id, version, created_time, last_updated_time, created_by, last_updated_by, module, sub_module, key, value, description)
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'SYSTEM', 'SYSTEM', 'MAIL_SERVER', 'SMTP', 'smtpDebug', 'true', 'Enable debug log for the SMTP communication.');