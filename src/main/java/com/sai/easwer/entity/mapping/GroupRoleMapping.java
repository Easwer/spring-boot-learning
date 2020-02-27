package com.sai.easwer.entity.mapping;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sai.easwer.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-17 15:18:25
 * @modify date 2020-02-17 15:18:25
 * @desc [description]
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "group_role")
public class GroupRoleMapping extends BaseEntity {

    private static final long serialVersionUID = 7014761524510174058L;

    @Column(name = "user_group")
    private UUID group;

    @Column(name = "user_role")
    private UUID role;

}