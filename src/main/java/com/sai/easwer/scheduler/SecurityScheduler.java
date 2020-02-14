package com.sai.easwer.scheduler;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-14 15:12:28
 * @modify date 2020-02-14 15:12:49
 * @desc [description]
 */
@EnableScheduling
@Component
public class SecurityScheduler {
    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityScheduler.class);

    /**
     * 
     */
    @Scheduled(cron = "0 0/30 * * * *")
    public void printLog() {
        LOGGER.error(Calendar.getInstance().getTime().toString());
    }

}