package com.test.devops.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "batch.product")
@Getter
@Setter
public class ProductBatchProperties {

    private int chunkValue;
    private String filePath;

}
