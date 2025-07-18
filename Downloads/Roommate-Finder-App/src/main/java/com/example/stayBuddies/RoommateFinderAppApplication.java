package com.example.stayBuddies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = UserDetailsServiceAutoConfiguration.class)
public class RoommateFinderAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoommateFinderAppApplication.class, args);
	}

}
