package com.luxurystar.msproject.exception.code;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode {
	SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "E001", "서버에 문제가 발생했습니다."),
	
	INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "C001", "잘못된 요청 파라미터입니다."),
	NOT_FOUND(HttpStatus.NOT_FOUND, "C002", "존재하지 않는 데이터입니다."),
	KEY_ALREADY_IN_USE(HttpStatus.CONFLICT, "C003", "고유키 중복 오류입니다.");

	private final HttpStatus status;
	private final String code;
	private final String message;
}
