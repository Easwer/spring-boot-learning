package com.sai.easwer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-17 15:18:25
 * @modify date 2020-02-17 15:18:25
 * @desc [description]
 */
@Entity
@Table(name = "user_group")
public class UserGroup extends BaseEntity {

    private static final long serialVersionUID = 9080005721697107786L;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "is_default")
    private boolean isDefault = false;
    
}