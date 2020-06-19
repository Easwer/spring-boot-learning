package com.sai.easwer.entity;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * Entity used to store active user sessions.
 * 
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-14 15:12:28
 * @modify date 2020-03-10 18:05:49
 * @Table user_session
 */
@Data
@Entity
@Table(name = "user_session")
public class UserSession {

    @Id
    private UUID id;

    @Column(name = "auth_token")
    private UUID authToken;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "started_time")
    private Long startedTime;

    @Column(name = "last_access_time")
    private Long lastAccessTime;

    @Column(name = "ip")
    private String ipAddress;

}
