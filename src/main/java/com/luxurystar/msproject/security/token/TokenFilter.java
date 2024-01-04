package com.luxurystar.msproject.security.token;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.luxurystar.msproject.account.repository.AccountRepository;
import com.luxurystar.msproject.exception.ExceptionResponse;
import com.luxurystar.msproject.exception.code.AuthErrorCode;
import com.luxurystar.msproject.exception.code.ErrorCode;
import com.luxurystar.msproject.security.AccountUserDetail;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TokenFilter extends OncePerRequestFilter {

	private final TokenProvider provider;
	private final AccountRepository repository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {
		String token = resolveToken(request);

		if (StringUtils.isNotEmpty(token)) {
			DecodedJWT decodedJWT;
			try {
				decodedJWT = provider.parseToken(token);
			} catch (TokenExpiredException e) {
				handleResponse(response, AuthErrorCode.TOKEN_EXPIRED);
				return;
			} catch (JWTVerificationException e) {
				handleResponse(response, AuthErrorCode.INVALID_TOKEN);
				return;
			}

			AbstractAuthenticationToken authenticationToken = parsePrincipal(decodedJWT);
			if (authenticationToken != null) {
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}

		filterChain.doFilter(request, response);
		SecurityContextHolder.clearContext();
	}

	private String resolveToken(HttpServletRequest request) {
		String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (StringUtils.isEmpty(bearerToken) || !bearerToken.startsWith("Bearer ")) {
			return null;
		}
		return bearerToken.substring(7);
	}

	private AbstractAuthenticationToken parsePrincipal(DecodedJWT jwt) {
		return repository.findById(Long.parseLong(jwt.getSubject()))
			.map(AccountUserDetail::new)
			.map(each -> new UsernamePasswordAuthenticationToken(each.getAccount(), null, each.getAuthorities()))
			.orElse(null);
	}

	private void handleResponse(HttpServletResponse response, ErrorCode errorCode) throws IOException {
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setCharacterEncoding(StandardCharsets.UTF_8.name());

		try (PrintWriter writer = response.getWriter()) {
			writer.write(ExceptionResponse.from(errorCode).toString());
			writer.flush();
		}
	}
}
