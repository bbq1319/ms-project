package com.luxurystar.msproject.security;

import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class LoginConfigurer<T extends HttpSecurityBuilder<T>>
	extends AbstractAuthenticationFilterConfigurer<T, LoginConfigurer<T>, AbstractAuthenticationProcessingFilter> {

	public LoginConfigurer(AbstractAuthenticationProcessingFilter filter) {
		super(filter, "/login");
	}

	@Override
	protected RequestMatcher createLoginProcessingUrlMatcher(String loginProcessingUrl) {
		return new AntPathRequestMatcher(loginProcessingUrl, "/login");
	}
}
