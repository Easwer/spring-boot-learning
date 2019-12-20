package com.sai.easwer.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sai.easwer.entity.UserSession;

@Repository
public interface UserSessionRepository extends PagingAndSortingRepository<UserSession, UUID>
{
    Optional<UserSession> findById(UUID id);
    
    List<UserSession> findAll();
    
    Optional<UserSession> findBySessionId(UUID sessionId);
    
    Optional<UserSession> findByUserId(UUID userId);
}