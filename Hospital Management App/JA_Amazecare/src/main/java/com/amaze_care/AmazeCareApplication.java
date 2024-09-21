package com.amaze_care;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class AmazeCareApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(AmazeCareApplication.class, args);
	}

}
