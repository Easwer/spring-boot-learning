package com.sai.easwer.entity.mapping;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sai.easwer.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "user_group_mapping")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserGroupMapping extends BaseEntity {

  private static final long serialVersionUID = -7138799110284203538L;

  @Column(name = "user_id", nullable = false)
  private UUID userId;

  @Column(name = "group_id", nullable = false)
  private UUID groupId;
}