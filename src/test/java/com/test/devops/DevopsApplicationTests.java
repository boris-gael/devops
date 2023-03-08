package com.test.devops;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DevopsApplicationTests {

	@Test
	void contextLoads() {
		assertThat(Double.valueOf(2)).isGreaterThan(1);
	}

}
