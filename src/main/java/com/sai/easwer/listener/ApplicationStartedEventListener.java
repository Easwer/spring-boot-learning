package com.sai.easwer.listener;

import com.sai.easwer.repository.UserSessionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Application Started Event Listener.
 * 
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-17 16:49:25
 * @modify date 2020-03-10 18:06:00
 */
@Slf4j
@Component
public class ApplicationStartedEventListener
        implements ApplicationListener<ApplicationStartedEvent> {

    @Autowired
    private UserSessionRepository userSessionRepository;

    @Override
    public void onApplicationEvent(final ApplicationStartedEvent event) {
        if (userSessionRepository.findAll().size() != 0) {
            log.info("Stale user sessions are removed.");
            userSessionRepository.deleteAll();
        }
    }

}
