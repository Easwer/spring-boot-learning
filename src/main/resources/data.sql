-- Insert default user
INSERT INTO user_details
    (id, version, created_time, updated_time, username, first_name, last_name, password, failed_login_attempt_count)
VALUES
    (RANDOM_UUID(), 1, (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000),
    (DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000),
    'admin', 'Administrator', 'User', 'admin', 0);