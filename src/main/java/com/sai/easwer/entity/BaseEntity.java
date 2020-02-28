package com.sai.easwer.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.sai.easwer.listener.EntityChangeListener;

import lombok.Data;

/**
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-14 15:12:28
 * @modify date 2020-02-28 11:55:26
 * @Description Base entity used to hold common variables like Id, Version, Created date, Last updated date etc.
 */
@Data
@MappedSuperclass
@EntityListeners(EntityChangeListener.class)
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = -439857631252669874L;

    @Id
    private UUID id;

    @Column(name = "version")
    private int version;

    @Column(name = "created_time")
    private Long createdTime;

    @Column(name = "last_updated_time")
    private Long lastUpdatedTime;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

}