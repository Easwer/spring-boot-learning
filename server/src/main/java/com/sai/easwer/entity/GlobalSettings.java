package com.sai.easwer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entity class used to store user details.
 * 
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-03-02 14:47:52
 * @modify date 2020-03-10 18:05:21
 */
@Entity
@Table(name = "global_settings")
@Data
@EqualsAndHashCode(callSuper = true)
public class GlobalSettings extends BaseEntity {

    private static final long serialVersionUID = -4858888297085591802L;

    @Column(name = "module")
    private String module;

    @Column(name = "sub_module")
    private String subModule;

    @Column(name = "key")
    private String key;

    @Column(name = "value")
    private String value;
}
