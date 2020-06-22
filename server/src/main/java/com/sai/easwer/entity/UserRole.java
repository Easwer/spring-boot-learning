package com.sai.easwer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entity class used to store roles details.
 * 
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-26 22:46:43
 * @modify date 2020-03-10 18:05:45
 * @Table user_role
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "user_role")
public class UserRole extends BaseEntity {

    private static final long serialVersionUID = 77119111372041725L;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

}
