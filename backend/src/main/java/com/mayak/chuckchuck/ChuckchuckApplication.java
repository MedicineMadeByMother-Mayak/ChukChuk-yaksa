package com.mayak.chuckchuck;

import com.mayak.chuckchuck.config.AppProperties;
import com.mayak.chuckchuck.config.CorsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableConfigurationProperties({
		CorsProperties.class,
		AppProperties.class
})
public class ChuckchuckApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChuckchuckApplication.class, args);
	}

}
