package com.sai.easwer.listener;

import com.sai.easwer.repository.UserSessionRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-17 16:49:25
 * @modify date 2020-02-17 16:49:25
 * @desc [description]
 */
@Component
public class ApplicationStartedEventListener implements ApplicationListener<ApplicationStartedEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationStartedEventListener.class);

    @Autowired
    private UserSessionRepository userSessionRepository;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        if (userSessionRepository.findAll().size() != 0 ) {
            LOGGER.info("Stale user sessions are removed.");
            userSessionRepository.deleteAll();
        }
    }

}