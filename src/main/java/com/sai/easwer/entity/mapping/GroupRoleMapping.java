package com.sai.easwer.entity.mapping;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sai.easwer.entity.BaseEntity;

/**
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-17 15:18:25
 * @modify date 2020-02-17 15:18:25
 * @desc [description]
 */
@Entity
@Table(name = "group_role")
public class GroupRoleMapping extends BaseEntity {

    private static final long serialVersionUID = 7014761524510174058L;

    @Column(name = "user_group")
    private UUID group;

    @Column(name = "user_role")
    private UUID role;

    /**
     * @return the group
     */
    public UUID getGroup() {
        return group;
    }

    /**
     * @param group the group to set
     */
    public void setGroup(UUID group) {
        this.group = group;
    }

    /**
     * @return the role
     */
    public UUID getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(UUID role) {
        this.role = role;
    }

}
