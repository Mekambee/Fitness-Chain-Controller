package com.fitnesschain.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
}

/*
docker exec -it fitnesschain_db bash
psql -U fitness_user -d fitness_chain
*/