package com.test.devops;

import com.test.devops.config.properties.ProductBatchProperties;
import com.test.devops.config.properties.RSAKeysProperties;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ProductBatchProperties.class, RSAKeysProperties.class})
public class DevopsApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(DevopsApplication.class)
				.profiles("dev", "qual", "prod")
				.run(args);
	}

}
