package com.sai.easwer.util;

import com.sai.easwer.constants.SecurityConstants;
import com.sai.easwer.entity.GlobalSettings;
import com.sai.easwer.repository.GlobalSettingsRepository;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Global Settings Util.
 * 
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-03-09 16:31:20
 * @modify date 2020-03-10 18:08:43
 */
@Slf4j
public class GlobalSettingsUtil {

    private GlobalSettingsUtil() {

    }

    @Autowired
    private static GlobalSettingsRepository globalSettingsRepository;

    /**
     * Get Global Settings.
     * 
     * @param <T> Generic Object.
     * @param key {@link String}
     * @param defaultValue default value.
     * @return
     */
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
