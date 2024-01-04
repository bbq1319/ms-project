package com.luxurystar.msproject.security.token;

import java.sql.Date;
import java.time.Instant;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.luxurystar.msproject.account.entity.Account;
import com.luxurystar.msproject.security.AccountUserDetail;

@Component
public class TokenProvider {

	private final TokenProperties PROPERTIES;
	private final Algorithm ALGORITHM;
	private final String TOKEN_PREFIX = "Bearer ";

	public TokenProvider(TokenProperties properties) {
		this.PROPERTIES = properties;
		this.ALGORITHM = Algorithm.HMAC512(PROPERTIES.getSecret());
	}

	public String generateToken(Authentication authentication) {
		AccountUserDetail accountUserDetail = (AccountUserDetail)authentication.getPrincipal();
		Account account = accountUserDetail.getAccount();
		return TOKEN_PREFIX + JWT.create()
			.withSubject(account.getId().toString())
			.withClaim("email", account.getEmail())
			.withClaim("role", account.getRole().name())
			.withIssuer("account")
			.withExpiresAt(Date.from(Instant.now().plusSeconds(PROPERTIES.getValidity())))
			.sign(ALGORITHM);
	}

	public DecodedJWT parseToken(String token) {
		return JWT.require(ALGORITHM)
			.build()
			.verify(token.replaceFirst(TOKEN_PREFIX, ""));
	}
}
