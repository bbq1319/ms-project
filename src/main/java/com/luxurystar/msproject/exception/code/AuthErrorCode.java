package com.luxurystar.msproject.exception.code;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthErrorCode implements ErrorCode {
	BAD_CREDENTIAL(HttpStatus.UNAUTHORIZED, "A0001", "아이디 혹은 비밀번호가 잘못되었습니다."),
	ACCOUNT_DISABLED(HttpStatus.UNAUTHORIZED, "A0002", "정지된 계정입니다"),
	TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "A0003", "토큰이 만료되었습니다."),
	INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "A0004", "잘못된 토큰입니다."),
	FORBIDDEN(HttpStatus.FORBIDDEN, "A0005", "접근권한이 없습니다");

	private final HttpStatus status;
	private final String code;
	private final String message;
}
