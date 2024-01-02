package com.luxurystar.msproject.exception;

import org.springframework.security.core.AuthenticationException;

import com.luxurystar.msproject.exception.code.ErrorCode;

import lombok.Getter;

@Getter
public class AuthException extends AuthenticationException {

	private final ErrorCode errorCode;

	public AuthException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}
}
