package com.sai.easwer.repository;

import java.util.List;
import java.util.UUID;

import com.sai.easwer.entity.UserPasswordHistory;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserPasswordHistoryRepository extends PagingAndSortingRepository<UserPasswordHistory, UUID> {

  List<UserPasswordHistory> findByUserId(UUID userId);

}