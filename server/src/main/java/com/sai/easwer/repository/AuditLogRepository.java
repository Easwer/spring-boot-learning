package com.sai.easwer.repository;

import com.sai.easwer.entity.AuditLog;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Audit log repository.
 * 
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-14 15:12:28
 * @modify date 2020-03-10 18:06:29
 */
@Repository
public interface AuditLogRepository extends PagingAndSortingRepository<AuditLog, UUID> {

    Optional<AuditLog> findById(UUID id);

    Optional<AuditLog> findByIdAndUserId(UUID id, UUID userId);

    List<AuditLog> findByUserId(UUID userId);

    List<AuditLog> findAll();

    List<AuditLog> findByUsername(String username);

}
