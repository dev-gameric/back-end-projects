package com.projects.desafio.plano_de_saude;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class PlanoDeSaudeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlanoDeSaudeApplication.class, args);
	}

}
