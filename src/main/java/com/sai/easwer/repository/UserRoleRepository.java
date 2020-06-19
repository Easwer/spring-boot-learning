package com.sai.easwer.repository;

import java.util.Optional;
import java.util.UUID;

import com.sai.easwer.entity.UserRole;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRoleRepository extends PagingAndSortingRepository<UserRole, UUID> {

  Optional<UserRole> findById(UUID roleId);

}