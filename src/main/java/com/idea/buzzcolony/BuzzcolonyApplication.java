package com.idea.buzzcolony;

import com.idea.buzzcolony.util.AppMessage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BuzzcolonyApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuzzcolonyApplication.class, args);
	}

	@Bean
	public AppMessage appMessage() {
		return new AppMessage();
	}
}
