package com.cbasalloteteba.tinpet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.cbasalloteteba.tinpet.model.User;
import com.cbasalloteteba.tinpet.repository.UserRepository;

@SpringBootApplication
public class TinpetApplication {

		
	@Autowired
	UserRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(TinpetApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(UserRepository repositorioUsuario) {
		return args -> {
			
			

		};
	};
}
