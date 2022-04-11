package com.example.certifinder;

import com.example.certifinder.model.Certuser;
import com.example.certifinder.model.Role;
import com.example.certifinder.service.CertuserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@SpringBootApplication
@RestController
public class CertifinderApplication {

	public static void main(String[] args) {
		SpringApplication.run(CertifinderApplication.class, args);
	}


	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(CertuserService certuserService){
		return args -> {
			certuserService.saveRole(new Role(null,"ROLE_USER"));
			certuserService.saveRole(new Role(null,"ROLE_ADMIN"));
			certuserService.saveRole(new Role(null,"ROLE_COMPANY"));

			certuserService.addCertuser(new Certuser(null,"pelle@gmail.com","1234", "Pelle Gurkan", new ArrayList<>(), "Stockholm"));

			certuserService.addRoleToUser("pelle@gmail.com", "ROLE_USER");

		};
	}

}
