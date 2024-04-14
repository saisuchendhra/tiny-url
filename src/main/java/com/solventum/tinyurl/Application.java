package com.solventum.tinyurl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.solventum.tinyurl")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
