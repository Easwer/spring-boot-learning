package com.sai.easwer.repository;

import com.sai.easwer.entity.GlobalSettings;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Global settings repository.
 * 
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-14 15:12:28
 * @modify date 2020-03-10 18:06:34
 */
@Repository
public interface GlobalSettingsRepository extends PagingAndSortingRepository<GlobalSettings, UUID> {

    Optional<GlobalSettings> findByKey(String key);

    List<GlobalSettings> findAll();

    List<GlobalSettings> findByModule(String module);
}
