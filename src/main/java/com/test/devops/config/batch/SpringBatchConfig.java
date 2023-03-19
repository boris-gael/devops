package com.test.devops.config.batch;

import com.test.devops.service.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class SpringBatchConfig {
    private final static String STEP_ONE_NAME = "step1";

    @Value("${spring.batch.job.names}")
    private String jobName;

    @Value("${batch.chunk-value}")
    private int chunkValue;

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final ItemReader<ProductDTO> productItemReader;
    private final ItemProcessor<ProductDTO, ProductDTO> productItemProcessor;
    private final ItemWriter<ProductDTO> productItemWriter;

    @Bean
    public Step step() {
        return stepBuilderFactory.get(STEP_ONE_NAME)
                .<ProductDTO, ProductDTO>chunk(chunkValue)
                .reader(productItemReader)
                .processor(productItemProcessor)
                .writer(productItemWriter)
                .build();
    }

    @Bean
    public Job job(Step step1) {
        return jobBuilderFactory.get(jobName).start(step1).build();
    }

}
