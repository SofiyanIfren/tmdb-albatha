package com.albatha.tmdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.albatha.tmdb.models")
@EnableJpaRepositories("com.albatha.tmdb.dao")
public class TmdbApplication {

	public static void main(String[] args) {
		SpringApplication.run(TmdbApplication.class, args);
	}

}
