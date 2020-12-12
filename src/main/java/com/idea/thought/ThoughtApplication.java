package com.idea.thought;

import com.idea.thought.util.AppMessage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ThoughtApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThoughtApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AppMessage appMessage() {
		return new AppMessage();
	}
}
