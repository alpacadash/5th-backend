package com.spring.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Step01SpringDataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(Step01SpringDataJpaApplication.class, args);
	}

}
