package com.sai.easwer.entity.mapping;

import com.sai.easwer.entity.BaseEntity;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entity class used to store mapping between Groups and Roles.
 * 
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-17 15:18:25
 * @modify date 2020-03-10 18:05:01
 * @Table group_role
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "group_role_mapping")
public class GroupRoleMapping extends BaseEntity {

    private static final long serialVersionUID = 7014761524510174058L;

    @Column(name = "group_id", nullable = false)
    private UUID groupId;

    @Column(name = "role_id", nullable = false)
    private UUID roleId;

}
