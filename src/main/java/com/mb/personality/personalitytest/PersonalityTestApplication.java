package com.mb.personality.personalitytest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PersonalityTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalityTestApplication.class, args);
	}

}
