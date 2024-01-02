package com.luxurystar.msproject.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luxurystar.msproject.exception.AuthException;
import com.luxurystar.msproject.exception.ExceptionResponse;
import com.luxurystar.msproject.exception.InvalidJwtException;
import com.luxurystar.msproject.exception.code.AuthErrorCode;
import com.luxurystar.msproject.exception.code.CommonErrorCode;
import com.luxurystar.msproject.exception.code.ErrorCode;
import com.luxurystar.msproject.security.token.TokenProvider;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthenticationHandler implements AuthenticationSuccessHandler, AuthenticationFailureHandler,
	AuthenticationEntryPoint {

	private final TokenProvider provider;
	private final ObjectMapper mapper;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
		AuthenticationException authException) throws IOException, ServletException {
		handlerErrorResponse(response, authException);
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
		AuthenticationException exception) throws IOException, ServletException {
		handlerErrorResponse(response, exception);
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
		Authentication authentication) throws IOException, ServletException {
		response.setStatus(HttpStatus.OK.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setCharacterEncoding(StandardCharsets.UTF_8.name());

		// token 넣기
		Map<String, Object> responseBody = new HashMap<>();

		try (PrintWriter writer = response.getWriter()) {
			writer.write(mapper.writeValueAsString(responseBody));
			writer.flush();
		}
	}

	private void handlerErrorResponse(HttpServletResponse response, AuthenticationException e) throws IOException {
		ErrorCode errorCode;
		if (e instanceof AuthException) {
			errorCode = ((AuthException)e).getErrorCode();
		} else if (e instanceof InvalidJwtException) {
			errorCode = ((InvalidJwtException)e).getErrorCode();
		} else if (e instanceof DisabledException) {
			errorCode = AuthErrorCode.ACCOUNT_DISABLED;
		} else if (e instanceof BadCredentialsException) {
			errorCode = AuthErrorCode.BAD_CREDENTIAL;
		} else if (e instanceof InsufficientAuthenticationException) {
			errorCode = AuthErrorCode.FORBIDDEN;
		} else {
			errorCode = CommonErrorCode.SERVER_ERROR;
		}

		response.setStatus(errorCode.getStatus().value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setCharacterEncoding(StandardCharsets.UTF_8.name());
		try (PrintWriter writer = response.getWriter()) {
			writer.write(mapper.writeValueAsString(ExceptionResponse.from(errorCode)));
			writer.flush();
		}
	}
}
