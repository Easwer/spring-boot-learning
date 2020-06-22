package com.sai.easwer.repository;

import java.util.Optional;
import java.util.UUID;

import com.sai.easwer.entity.UserGroup;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGroupRepository extends PagingAndSortingRepository<UserGroup, UUID> {

  Optional<UserGroup> findById(UUID groupId);

}