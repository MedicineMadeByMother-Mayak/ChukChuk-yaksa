package com.mayak.chuckchuck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ChuckchuckApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChuckchuckApplication.class, args);
	}

}
