package com.sai.easwer.scheduler;

import java.util.Calendar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Schedulers for security actions.
 * 
 * @author Easwer AP
 * @email easwerms@gmail.com
 * @create date 2020-02-14 15:12:28
 * @modify date 2020-03-10 18:06:55
 */
@Slf4j
@EnableScheduling
@Component
public class SecurityScheduler {

    /**
     * Security scheduler which runs every 30 minutes.
     */
    @Scheduled(cron = "0 0/30 * * * *")
    public void printLog() {
        log.info(Calendar.getInstance().getTime().toString());
    }

}
