package com.sai.easwer.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sai.easwer.entity.UserDetails;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserDetails, UUID>{

	public Optional<UserDetails> findById(UUID id);
	
	public List<UserDetails> findAll();
	
	public Optional<UserDetails> findByUsername(String id);
}
