package com.luxurystar.msproject.security;

import java.io.IOException;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LoginAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final ObjectMapper mapper;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws
		AuthenticationException {
		Map<String, String> loginRequest;

		try {
			loginRequest = mapper.readValue(request.getInputStream(), new TypeReference<>() {
			});
		} catch (IOException e) {
			throw new AuthenticationServiceException("Invalid Login Request");
		}

		String email = loginRequest.getOrDefault("email", "");
		String password = loginRequest.getOrDefault("password", "");
		return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(email, password));
	}
}
