package com.sai.easwer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-17 15:18:25
 * @modify date 2020-02-28 11:55:17
 * @Table user_group
 * @Description Entity class used to store group details.
 */
@Data
@EqualsAndHashCode(callSuper = true)
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