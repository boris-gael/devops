package com.test.devops;

import com.test.devops.config.properties.ProductBatchProperties;
import com.test.devops.config.properties.RSAKeysProperties;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@EnableConfigurationProperties({
        ProductBatchProperties.class,
        RSAKeysProperties.class
})
@OpenAPIDefinition(
        info = @Info(
                title = "Devops API Documentation",
                version = "1.0",
                description = "Doc for all devops API"
        )
)
public class DevopsApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(DevopsApplication.class)
                .profiles("dev", "qual", "prod")
                .run(args);
    }

}
