package com.test.devops.config.scheduler;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.support.CronTrigger;

import java.util.concurrent.ScheduledFuture;

@EnableScheduling
@NoArgsConstructor
public abstract class DynamicScheduler {

    @Autowired
    private TaskScheduler taskScheduler;
    protected ScheduledFuture scheduledFuture;
    protected String cronExpression;

    protected void start() {
        scheduledFuture = taskScheduler.schedule(this::runTask, new CronTrigger(cronExpression));
    }

    protected void stop() {
        scheduledFuture.cancel(false);
    }

    protected abstract void runTask();

}
