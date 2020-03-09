package com.sai.easwer.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.sai.easwer.entity.GlobalSettings;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-14 15:12:28
 * @modify date 2020-03-09 11:27:59
 * @desc Global settings repository
 */
@Repository
public interface GlobalSettingsRepository extends PagingAndSortingRepository<GlobalSettings, UUID> {

    public Optional<GlobalSettings> findByKey(String key);

    public List<GlobalSettings> findAll();

    public List<GlobalSettings> findByModule(String module);
}
