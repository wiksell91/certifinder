package com.example.certifinder;

import com.example.certifinder.model.Authority;
import com.example.certifinder.model.Certuser;
import com.example.certifinder.service.CertuserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class CertifinderApplication {

	public static void main(String[] args) {
		SpringApplication.run(CertifinderApplication.class, args);
	}

	@PostConstruct
	protected void init(){
		List<Authority> authorityList = new ArrayList<>();
		authorityList.add(createAuthority("USER", "User role"));
		authorityList.add(createAuthority("Company", "Company role"));
	}


	private Authority createAuthority(String roleCode, String roleDescription){
		Authority authority = new Authority();
		authority.setRoleCode(roleCode);
		authority.setRoleDescription(roleDescription);
		return authority;
	}
	}


