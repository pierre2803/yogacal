package com.pierre2803.yogacal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class YogacalApplication {

	public static void main(String[] args) {
		SpringApplication.run(YogacalApplication.class, args);
	}
}
