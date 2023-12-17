package com.luxurystar.msproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.luxurystar.msproject.security.token.TokenProperties;

@SpringBootApplication
@EnableConfigurationProperties({TokenProperties.class})
public class MsProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsProjectApplication.class, args);
	}

}
