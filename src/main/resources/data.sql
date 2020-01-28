-- Insert default user
INSERT INTO user_details(id, username, first_name, last_name, password, failed_login_attempt_count)
VALUES
    (RANDOM_UUID(), 'admin@eas.com', 'Administrator', 'User', 'admin', 0);