package com.test.devops.config.batch;

import com.test.devops.config.properties.ProductBatchProperties;
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
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.context.annotation.*;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class SpringBatchConfig {
    private final static String STEP_ONE_NAME = "step1";

    private final BatchProperties batchProperties;
    private final ProductBatchProperties productBatchProperties;

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final ItemReader<ProductDTO> productItemReader;
    private final ItemProcessor<ProductDTO, ProductDTO> productItemProcessor;
    private final ItemWriter<ProductDTO> productItemWriter;

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
//    @Conditional(Class<A extends Condition>)
//    @ConditionalOn/*Property,Expression,Bean,Class,Java,WarDeployment*/
    public Step step() {
        return stepBuilderFactory.get(STEP_ONE_NAME)
                .<ProductDTO, ProductDTO>chunk(productBatchProperties.getChunkValue())
                .reader(productItemReader)
                .processor(productItemProcessor)
                .writer(productItemWriter)
                .build();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Job job(Step step1) {
        return jobBuilderFactory.get(batchProperties.getJob().getNames()).start(step1).build();
    }

}
