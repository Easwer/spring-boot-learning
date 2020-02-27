package com.sai.easwer.scheduler;

import java.util.Calendar;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-14 15:12:28
 * @modify date 2020-02-27 10:56:16
 * @desc [description]
 */
@Slf4j
@EnableScheduling
@Component
public class SecurityScheduler {

    /**
     * 
     */
    @Scheduled(cron = "0 0/30 * * * *")
    public void printLog() {
        log.info(Calendar.getInstance().getTime().toString());
    }

}