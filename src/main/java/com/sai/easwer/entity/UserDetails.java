package com.sai.easwer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-14 15:12:28
 * @modify date 2020-02-28 11:55:21
 * @Description Entity class used to store user details.
 */
@Entity
@Table(name = "user_details")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserDetails extends BaseEntity {

	private static final long serialVersionUID = 4427706888381308132L;

	@Column(name = "username", nullable = false)
	private String username;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "failed_login_attempt_count")
	private int failedLoginAttemptCount = 0;

	@Column(name = "idle_timeout", nullable = false)
	private int idleTimeout = 15;

	@Column(name = "user_account_status", nullable = false)
	private String userAccountStatus;

	@Column(name = "account_expiry", nullable = false)
	private int accountExpiry;

	@Column(name = "password_expiry", nullable = false)
	private int passwordExpiry;

}