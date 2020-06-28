package com.sai.easwer.repository;

import com.sai.easwer.entity.UserDetails;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * User repository.
 * 
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-14 15:12:28
 * @modify date 2020-03-10 18:06:42
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<UserDetails, UUID> {

    Optional<UserDetails> findById(UUID id);

    List<UserDetails> findAll();

    Optional<UserDetails> findByUsername(String username);

    Optional<UserDetails> findByEmailAndUsernameNot(String email, String username);
}
