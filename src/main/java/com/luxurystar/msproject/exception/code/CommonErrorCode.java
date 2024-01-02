package com.luxurystar.msproject.exception.code;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode {
	SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "E001", "서버에 문제가 발생했습니다.");

	private final HttpStatus status;
	private final String code;
	private final String message;
}
