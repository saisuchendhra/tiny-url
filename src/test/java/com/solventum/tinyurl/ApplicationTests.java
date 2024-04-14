package com.solventum.tinyurl;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.solventum.tinyurl.service"})
@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
	}

}
