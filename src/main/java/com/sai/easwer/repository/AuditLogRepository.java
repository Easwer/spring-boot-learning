package com.sai.easwer.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sai.easwer.entity.AuditLog;

@Repository
public interface AuditLogRepository extends PagingAndSortingRepository<AuditLog, UUID>
{

    public Optional<AuditLog> findById(UUID id);

    public List<AuditLog> findAll();

    public List<AuditLog> findByUsername(String username);

}