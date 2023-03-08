package com.test.devops;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DevopsApplicationTests {

	@Test
	void contextLoads() {
		assertThat("2").isInstanceOf(String.class);
	}

}
