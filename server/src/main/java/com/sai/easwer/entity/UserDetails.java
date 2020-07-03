package com.sai.easwer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entity class used to store user details.
 * 
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-14 15:12:28
 * @modify date 2020-03-10 18:05:31
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

	@Column(name = "idle_timeout", nullable = false)
	private int idleTimeout = 15;

	@Column(name = "account_status", nullable = false)
	private String accountStatus;

	@Column(name = "account_expiry", nullable = false)
	private int accountExpiry;

	@Column(name = "password_expiry", nullable = false)
	private int passwordExpiry;

	public String setPassword() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2Y,
				31);
		return bCryptPasswordEncoder.encode(this.password);
	}

}
