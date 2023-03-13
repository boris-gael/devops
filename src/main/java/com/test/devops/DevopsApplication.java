package com.test.devops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class DevopsApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(DevopsApplication.class)
				.profiles("dev", "qual", "prod")
				.run(args);
	}

}
