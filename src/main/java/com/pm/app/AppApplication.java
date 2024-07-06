package com.pm.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.pm.app.repository.RoleRepository;
import com.pm.app.service.DataInitialisationService;

@SpringBootApplication
@EnableTransactionManagement
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	private DataInitialisationService dataInitialisationService;

	public AppApplication(DataInitialisationService dataInitialisationService) {
		this.dataInitialisationService = dataInitialisationService;
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository, PasswordEncoder passwordEncode) {

		return args -> dataInitialisationService.initialiseData();
	}

}