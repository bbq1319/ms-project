package com.luxurystar.msproject.exception;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.luxurystar.msproject.exception.code.ErrorCode;

public record ExceptionResponse(String errorCode, String message) {

	public static ExceptionResponse from(ErrorCode errorCode) {
		return ExceptionResponse.of(errorCode.getCode(), errorCode.getMessage());
	}

	public static ExceptionResponse of(String code, String message) {
		return new ExceptionResponse(code, message);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}
