package com.luxurystar.msproject.exception;

import org.springframework.http.HttpStatus;

import com.luxurystar.msproject.exception.code.ErrorCode;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

	private final HttpStatus status;
	private final String errorCode;

	protected BaseException(ErrorCode errorCode) {
		this(null, errorCode.getStatus(), errorCode.getCode(), errorCode.getMessage());
	}

	private BaseException(Throwable cause, HttpStatus status, String errorCode, String errorMessage) {
		super(errorMessage, cause);
		this.status = status;
		this.errorCode = errorCode;
	}

	public static BaseException from(ErrorCode errorCode) {
		return new BaseException(null, errorCode.getStatus(), errorCode.getCode(), errorCode.getMessage());
	}
}
