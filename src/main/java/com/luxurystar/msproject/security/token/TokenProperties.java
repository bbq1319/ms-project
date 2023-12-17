package com.luxurystar.msproject.security.token;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "token")
public class TokenProperties {

	private String secret;
	private int validity;
}
