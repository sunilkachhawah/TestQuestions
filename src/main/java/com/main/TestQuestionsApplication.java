package com.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class TestQuestionsApplication {

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		ConfigurableApplicationContext context=SpringApplication.run(TestQuestionsApplication.class, args);
	}

}
