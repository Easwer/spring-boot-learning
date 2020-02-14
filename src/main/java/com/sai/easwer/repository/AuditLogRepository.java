package com.sai.easwer.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.sai.easwer.entity.AuditLog;

/**
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-14 15:12:28
 * @modify date 2020-02-14 15:12:49
 * @desc [description]
 */
@Repository
public interface AuditLogRepository extends PagingAndSortingRepository<AuditLog, UUID> {

    public Optional<AuditLog> findById(UUID id);

    public List<AuditLog> findAll();

    public List<AuditLog> findByUsername(String username);

}