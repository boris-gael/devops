package com.test.devops.service.impl;

import com.test.devops.config.scheduler.DynamicScheduler;
import com.test.devops.domain.enumeration.TaskEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class BatchProductSchedulerService extends DynamicScheduler {

    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job job1;

    public BatchProductSchedulerService() {
        super();
        this.cronExpression = TaskEnum.BATCH_IMPORT_PRODUCT.getCronExpression();
    }

    @Override
    protected void runTask() {
        log.info("########## Start Running Scheduled Batch Products ... ##########");
        Map<String, JobParameter> params = new HashMap<>();
        params.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters jobParameters = new JobParameters(params);
        try {
            JobExecution jobExecution = jobLauncher.run(job1, jobParameters);
            while (jobExecution.isRunning()) {
                log.info("job1 is still running ...");
            }
//            return jobExecution.getStatus().name();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
//            throw new DevopsExeption(ex.getMessage(), ex.getCause());
        }
        log.info("########## End Scheduled Batch Products ... ##########");
    }
}
