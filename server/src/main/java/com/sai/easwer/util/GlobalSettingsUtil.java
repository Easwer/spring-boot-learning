package com.sai.easwer.util;

import java.util.Optional;

import com.sai.easwer.constants.MessageConstants;
import com.sai.easwer.entity.GlobalSettings;
import com.sai.easwer.repository.GlobalSettingsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Global Settings Util.
 * 
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-03-09 16:31:20
 * @modify date 2020-03-10 18:08:43
 */
@Slf4j
@Component
public class GlobalSettingsUtil {

    private static GlobalSettingsRepository globalSettingsRepository;

    @Autowired
    public void setGlobalRepository(final GlobalSettingsRepository globalSettingsRepositoryObj) {
        GlobalSettingsUtil.globalSettingsRepository = globalSettingsRepositoryObj;
    }

    /**
     * Get Global Settings as {@link String}.
     * 
     * @param <T>          Generic Object.
     * @param key          {@link String}
     * @param defaultValue default value {@link String}.
     * @return {@link String}
     */
    public static String getString(final String key, final String defaultValue) {
        Optional<GlobalSettings> settings = null;
        try {
            settings = globalSettingsRepository.findByKey(key);
            if (settings.isPresent()) {
                return settings.get().getValue();
            }
        } catch (final Exception e) {
            log.error(MessageConstants.ERROR_IN_GET_GLOBAL_SETTINGS_DUE_TO, e);
        }
        return defaultValue;
    }

    /**
     * Get Global Settings as boolean.
     * 
     * @param key          {@link String}
     * @param defaultValue default value {@link boolean}.
     * @return {@link boolean}
     */
    public static boolean getBoolean(final String key, final boolean defaultValue) {
        Optional<GlobalSettings> settings = null;
        try {
            settings = globalSettingsRepository.findByKey(key);
            if (settings.isPresent()) {
                return Boolean.parseBoolean(settings.get().getValue());
            }
        } catch (final Exception e) {
            log.error(MessageConstants.ERROR_IN_GET_GLOBAL_SETTINGS_DUE_TO, e);
        }
        return defaultValue;
    }

    /**
     * Get Global Settings as int.
     * 
     * @param key          {@link String}
     * @param defaultValue default value {@link int}.
     * @return {@link int}
     */
    public static int getInt(final String key, final int defaultValue) {
        Optional<GlobalSettings> settings = null;
        try {
            settings = globalSettingsRepository.findByKey(key);
            if (settings.isPresent()) {
                return Integer.parseInt(settings.get().getValue());
            }
        } catch (final Exception e) {
            log.error(MessageConstants.ERROR_IN_GET_GLOBAL_SETTINGS_DUE_TO, e);
        }
        return defaultValue;
    }
}
