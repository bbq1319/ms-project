package com.luxurystar.msproject.exception;

import org.springframework.security.core.AuthenticationException;

import com.luxurystar.msproject.exception.code.ErrorCode;

import lombok.Getter;

@Getter
public class InvalidJwtException extends AuthenticationException {

	private final ErrorCode errorCode;

	public InvalidJwtException(ErrorCode errorCode) {
		super(errorCode.getCode());
		this.errorCode = errorCode;
	}
}
