-- User Roles
 INSERT INTO user_role (id, version, created_time, last_updated_time, created_by, last_updated_by, name, description) 
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'SYSTEM', 'SYSTEM', 'USER_READ', ''); 

 INSERT INTO user_role (id, version, created_time, last_updated_time, created_by, last_updated_by, name, description) 
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'SYSTEM', 'SYSTEM', 'USER_WRITE', ''); 

 INSERT INTO user_role (id, version, created_time, last_updated_time, created_by, last_updated_by, name, description) 
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'SYSTEM', 'SYSTEM', 'USER_DELETE', ''); 

 INSERT INTO user_role (id, version, created_time, last_updated_time, created_by, last_updated_by, name, description) 
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'SYSTEM', 'SYSTEM', 'AUDITLOG_READ', ''); 

-- User Groups
 INSERT INTO user_group (id, version, created_time, last_updated_time, created_by, last_updated_by, name, is_default, description) 
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'SYSTEM', 'SYSTEM', 'Administrator', TRUE, ''); 

 INSERT INTO user_group (id, version, created_time, last_updated_time, created_by, last_updated_by, name, is_default, description) 
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'SYSTEM', 'SYSTEM', 'Viewers', TRUE, ''); 

-- User Roles and Groups mapping
 INSERT INTO group_role_mapping (id, version, created_time, last_updated_time, created_by, last_updated_by, group_id, role_id) 
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'admin', 'admin', (SELECT id FROM user_group where name = 'Administrator'), (SELECT id FROM user_role where name = 'USER_READ')); 

 INSERT INTO group_role_mapping (id, version, created_time, last_updated_time, created_by, last_updated_by, group_id, role_id) 
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'admin', 'admin', (SELECT id FROM user_group where name = 'Viewers'), (SELECT id FROM user_role where name = 'USER_READ')); 

 INSERT INTO group_role_mapping (id, version, created_time, last_updated_time, created_by, last_updated_by, group_id, role_id) 
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'admin', 'admin', (SELECT id FROM user_group where name = 'Administrator'), (SELECT id FROM user_role where name = 'USER_WRITE')); 

 INSERT INTO group_role_mapping (id, version, created_time, last_updated_time, created_by, last_updated_by, group_id, role_id) 
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'admin', 'admin', (SELECT id FROM user_group where name = 'Administrator'), (SELECT id FROM user_role where name = 'USER_DELETE')); 

 INSERT INTO group_role_mapping (id, version, created_time, last_updated_time, created_by, last_updated_by, group_id, role_id) 
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'admin', 'admin', (SELECT id FROM user_group where name = 'Administrator'), (SELECT id FROM user_role where name = 'AUDITLOG_READ')); 
 INSERT INTO group_role_mapping (id, version, created_time, last_updated_time, created_by, last_updated_by, group_id, role_id) 
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'admin', 'admin', (SELECT id FROM user_group where name = 'Viewers'), (SELECT id FROM user_role where name = 'AUDITLOG_READ')); 

-- User details
 INSERT INTO user_details (id, version, created_time, last_updated_time, created_by, last_updated_by, username, first_name, last_name, password, email, failed_login_attempt_count, idle_timeout, user_account_status, account_expiry, password_expiry) 
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'SYSTEM', 'SYSTEM', 'admin', 'Administrator', 'User', 'admin', 'admin@easwer.com', 0, 15, 'ACTIVE', 0, 30); 

-- User Group Mapping
 INSERT INTO user_group_mapping (id, version, created_time, last_updated_time, created_by, last_updated_by, user_id, group_id) 
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'SYSTEM', 'SYSTEM', (SELECT id from user_details where username='admin'), (SELECT id FROM user_group where name = 'Administrator')); 

-- Global Settings
 INSERT INTO global_settings (id, version, created_time, last_updated_time, created_by, last_updated_by, module, sub_module, key, value) 
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'SYSTEM', 'SYSTEM', 'USER', 'USER', 'forceSpecialChar', 'true'); 

 INSERT INTO global_settings (id, version, created_time, last_updated_time, created_by, last_updated_by, module, sub_module, key, value) 
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'SYSTEM', 'SYSTEM', 'USER', 'USER', 'forceNumber', 'true'); 

 INSERT INTO global_settings (id, version, created_time, last_updated_time, created_by, last_updated_by, module, sub_module, key, value) 
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'SYSTEM', 'SYSTEM', 'USER', 'USER', 'forceCapitalLetter', 'true'); 

 INSERT INTO global_settings (id, version, created_time, last_updated_time, created_by, last_updated_by, module, sub_module, key, value) 
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'SYSTEM', 'SYSTEM', 'USER', 'USER', 'passwordMinLength', '8'); 

 INSERT INTO global_settings (id, version, created_time, last_updated_time, created_by, last_updated_by, module, sub_module, key, value) 
	VALUES
	 (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000), 'SYSTEM', 'SYSTEM', 'USER', 'USER', 'passwordMaxLength', '120');