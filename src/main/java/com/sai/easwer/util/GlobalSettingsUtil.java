package com.sai.easwer.util;

import java.util.Optional;

import com.sai.easwer.constants.SecurityConstants;
import com.sai.easwer.entity.GlobalSettings;
import com.sai.easwer.repository.GlobalSettingsRepository;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-03-09 16:31:20
 * @modify date 2020-03-09 16:31:20
 * @Description Global Settings Util
 */
@Slf4j
public class GlobalSettingsUtil {

    @Autowired
    private static GlobalSettingsRepository globalSettingsRepository;

    public static <T extends Object> T getGlobalSettings(final String key, final T defaultValue) {
        Optional<GlobalSettings> settings;
        try {
            settings = globalSettingsRepository.findByKey(SecurityConstants.FORCE_SPECIAL_CHAR);
            if (settings.isPresent()) {
                return (T) settings.get().getValue();
            }
        } catch (final Exception e) {
            log.error("Error in getGlobalSettings due to ", e);
        }
        return defaultValue;
    }
}