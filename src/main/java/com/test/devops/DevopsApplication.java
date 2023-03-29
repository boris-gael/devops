package com.test.devops;

import com.test.devops.config.ProductBatchProperties;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ProductBatchProperties.class})
public class DevopsApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(DevopsApplication.class)
				.profiles("dev", "qual", "prod")
				.run(args);
	}

}
