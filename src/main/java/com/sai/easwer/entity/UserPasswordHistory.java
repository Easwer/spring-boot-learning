package com.sai.easwer.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-24 21:47:42
 * @modify date 2020-02-24 22:02:39
 * @desc Entity to store users password history.
 */
@Entity
@Data
@Table(name = "user_password_history")
public class UserPasswordHistory extends BaseEntity {

    private static final long serialVersionUID = -8426428148266322094L;

    @Column(name = "user_id",nullable = false)
    private UUID userId;

    @Column(name = "password",nullable = false)
    private String password;

}