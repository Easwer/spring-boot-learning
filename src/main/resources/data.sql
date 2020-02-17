-- Insert default roles
INSERT INTO
    user_role (
        id,
        version,
        created_time,
        last_updated_time,
        created_by,
        last_updated_by,
        name,
        description
    )
VALUES
    (
        RANDOM_UUID(),
        1,
        (
            DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000
        ),
        (
            DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000
        ),
        'admin',
        'admin',
        'USER_READ',
        ''
    );

INSERT INTO
    user_role (
        id,
        version,
        created_time,
        last_updated_time,
        created_by,
        last_updated_by,
        name,
        description
    )
VALUES
    (
        RANDOM_UUID(),
        1,
        (
            DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000
        ),
        (
            DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000
        ),
        'admin',
        'admin',
        'USER_WRITE',
        ''
    );

INSERT INTO
    user_role (
        id,
        version,
        created_time,
        last_updated_time,
        created_by,
        last_updated_by,
        name,
        description
    )
VALUES
    (
        RANDOM_UUID(),
        1,
        (
            DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000
        ),
        (
            DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000
        ),
        'admin',
        'admin',
        'USER_DELETE',
        ''
    );

INSERT INTO
    user_role (
        id,
        version,
        created_time,
        last_updated_time,
        created_by,
        last_updated_by,
        name,
        description
    )
VALUES
    (
        RANDOM_UUID(),
        1,
        (
            DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000
        ),
        (
            DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000
        ),
        'admin',
        'admin',
        'AUDITLOG_READ',
        ''
    );

-- Insert default user_group
INSERT INTO
    user_group (
        id,
        version,
        created_time,
        last_updated_time,
        name,
        is_default,
        description
    )
VALUES
    (
        RANDOM_UUID(),
        1,
        (
            DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000
        ),
        (
            DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000
        ),
        'Administrator',
        TRUE,
        ''
    );

-- Group and role mapping
INSERT INTO
    group_role (
        id,
        version,
        created_time,
        last_updated_time,
        created_by,
        last_updated_by,
        user_group,
        user_role
    )
VALUES
    (
        RANDOM_UUID(),
        1,
        (
            DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000
        ),
        (
            DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000
        ),
        'admin',
        'admin',
        (
            SELECT id FROM user_group where name='Administrator'
        ),
        (
            SELECT id FROM user_role where name='USER_READ'
        )
    );
INSERT INTO
    group_role (
        id,
        version,
        created_time,
        last_updated_time,
        created_by,
        last_updated_by,
        user_group,
        user_role
    )
VALUES
    (
        RANDOM_UUID(),
        1,
        (
            DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000
        ),
        (
            DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000
        ),
        'admin',
        'admin',
        (
            SELECT id FROM user_group where name='Administrator'
        ),
        (
            SELECT id FROM user_role where name='USER_WRITE'
        )
    );
INSERT INTO
    group_role (
        id,
        version,
        created_time,
        last_updated_time,
        created_by,
        last_updated_by,
        user_group,
        user_role
    )
VALUES
    (
        RANDOM_UUID(),
        1,
        (
            DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000
        ),
        (
            DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000
        ),
        'admin',
        'admin',
        (
            SELECT id FROM user_group where name='Administrator'
        ),
        (
            SELECT id FROM user_role where name='USER_DELETE'
        )
    );
INSERT INTO
    group_role (
        id,
        version,
        created_time,
        last_updated_time,
        created_by,
        last_updated_by,
        user_group,
        user_role
    )
VALUES
    (
        RANDOM_UUID(),
        1,
        (
            DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000
        ),
        (
            DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000
        ),
        'admin',
        'admin',
        (
            SELECT id FROM user_group where name='Administrator'
        ),
        (
            SELECT id FROM user_role where name='AUDITLOG_READ'
        )
    );

-- Insert default user
INSERT INTO
    user_details (
        id,
        version,
        created_time,
        last_updated_time,
        created_by,
        last_updated_by,
        username,
        first_name,
        last_name,
        password,
        failed_login_attempt_count
    )
VALUES
    (
        RANDOM_UUID(),
        1,
        (
            DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000
        ),
        (
            DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000 - 19800000
        ),
        'SYSTEM',
        'SYSTEM',
        'admin',
        'Administrator',
        'User',
        'admin',
        0
    );