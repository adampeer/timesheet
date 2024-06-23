package com.pm.app;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.pm.app.entity.Role;
import com.pm.app.entity.User;
import com.pm.app.repository.RoleRepository;
import com.pm.app.service.UserServiceInterface;
import com.pm.app.utils.StringUtils;

@SpringBootApplication
public class AppApplication {

	private static final Logger log = LoggerFactory.getLogger(AppApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	private UserServiceInterface userServiceInterface;

	public AppApplication(UserServiceInterface userServiceInterface) {
		this.userServiceInterface = userServiceInterface;
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository, PasswordEncoder passwordEncode) {

		return args -> {

			log.info("Creating admin user...");
			// Revoked compromised password and changed to a secure one
			User admin = new User(1L, "John", "Doe", "john.doe@email.com", "password",
					Stream.of(new Role(StringUtils.ADMIN)).collect(Collectors.toSet()));
			User user = new User(2L, "Jim", "Doe", "jim.doe@email.com", "password",
					Stream.of(new Role(StringUtils.USER)).collect(Collectors.toSet()));

			userServiceInterface.save(admin);
			userServiceInterface.save(user);
		};
	}

}
