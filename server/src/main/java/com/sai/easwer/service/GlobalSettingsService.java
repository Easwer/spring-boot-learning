package com.sai.easwer.service;

import com.sai.easwer.annotation.RoleAccess;
import com.sai.easwer.constants.MessageConstants;
import com.sai.easwer.model.ResponseStatus;
import com.sai.easwer.constants.UserRoleEnum;
import com.sai.easwer.controller.GlobalSettingsController;
import com.sai.easwer.entity.GlobalSettings;
import com.sai.easwer.model.Response;
import com.sai.easwer.repository.GlobalSettingsRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class GlobalSettingsService extends BaseService implements GlobalSettingsController {

    @Autowired
    private GlobalSettingsRepository globalSettingsRepository;

    @Override
    public ResponseEntity<Response> getGlobalSettings(
            @RoleAccess(role = UserRoleEnum.GLOBAL_SETTINGS_READ) final UUID authToken, final String key,
            final String module) {

        List<GlobalSettings> settings = new ArrayList<GlobalSettings>();
        if (key != null) {
            final Optional<GlobalSettings> result = globalSettingsRepository.findByKey(key);
            if (result.isPresent()) {
                settings.add(result.get());
            } else {
                return createResponse(MessageConstants.GLOBAL_SETTINGS_INVALID_KEY, ResponseStatus.FAILURE, settings,
                        HttpStatus.OK);
            }
        } else if (key != module) {
            settings = globalSettingsRepository.findByModule(module);
            if (settings == null || settings.isEmpty()) {
                return createResponse(MessageConstants.GLOBAL_SETTINGS_INVALID_MODULE, ResponseStatus.FAILURE, settings,
                        HttpStatus.BAD_REQUEST);
            }
        } else {
            settings = globalSettingsRepository.findAll();
        }

        if (settings == null || settings.isEmpty()) {
            return createResponse(MessageConstants.GLOBAL_SETTINGS_NO_CONTENT, ResponseStatus.SUCCESS, null,
                    HttpStatus.NO_CONTENT);
        }

        return createResponse(MessageConstants.GLOBAL_SETTINGS_RETRIVED_SUCCESSFULLY, ResponseStatus.SUCCESS, settings,
                HttpStatus.OK);
    }

}