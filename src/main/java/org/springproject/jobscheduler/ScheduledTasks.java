package org.springproject.jobscheduler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private static final Logger logging = LogManager.getLogger(ScheduledTasks.class);

    @Scheduled(fixedDelay = 10000, initialDelay = 5000)
    public void performTask(){
        logging.info("Task performed with a fixed delay of 10 seconds and an initial delay of 5 seconds");
    }

    @Scheduled(fixedRate = 5000)
    public void performTasks() {
        logging.info("Task performed at fixed rate of 5 seconds");
    }

    @Scheduled(cron = "0 0 * * * ?")
    public void performTaskUsingCron() {
        logging.info("Task performed using cron expression at the top of every hour");
    }
}
