package com.sai.easwer.repository.mapping;

import java.util.List;
import java.util.UUID;

import com.sai.easwer.entity.mapping.GroupRoleMapping;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface GroupRoleMappingRepository extends PagingAndSortingRepository<GroupRoleMapping, UUID> {

  List<GroupRoleMapping> findByGroupId(UUID groupId);

}