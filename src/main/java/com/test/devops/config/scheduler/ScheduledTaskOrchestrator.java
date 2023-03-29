package com.test.devops.config.scheduler;

import com.test.devops.domain.enumeration.TaskEnum;
import com.test.devops.service.impl.BatchProductSchedulerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ScheduledTaskOrchestrator {

    private Map<String, DynamicScheduler> schedulerTasks = new HashMap<>();
    private final BatchProductSchedulerService batchProductSchedulerService;

    @PostConstruct
    public void initSchedulers() {
        schedulerTasks.put(TaskEnum.BATCH_IMPORT_PRODUCT.getJob(), batchProductSchedulerService);
        startAll();
    }

    private void startAll() {
        schedulerTasks.values().stream().forEach(dynamicScheduler -> dynamicScheduler.start());
    }

    private void stopAll() {
        schedulerTasks.values().stream().forEach(dynamicScheduler -> dynamicScheduler.stop());
    }

}
