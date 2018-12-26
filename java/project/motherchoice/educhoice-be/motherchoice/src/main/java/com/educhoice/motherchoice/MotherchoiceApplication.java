package com.educhoice.motherchoice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.educhoice.motherchoice.models.persistent.repositories")
@EnableJpaAuditing
public class MotherchoiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MotherchoiceApplication.class, args);
	}
}
