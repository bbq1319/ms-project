package com.luxurystar.msproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.luxurystar.msproject.exception.code.CommonErrorCode;

@RestControllerAdvice
public class WebExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(
		MethodArgumentNotValidException e) {
		BindingResult result = e.getBindingResult();

		if (result.getErrorCount() == 0) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(ExceptionResponse.from(CommonErrorCode.INVALID_PARAMETER));
		}

		ExceptionResponse body = result.getFieldErrors()
			.stream()
			.findFirst()
			.map(error -> ExceptionResponse.of(CommonErrorCode.INVALID_PARAMETER.getCode(),
				String.join("_", error.getField(), error.getDefaultMessage())))
			.orElseGet(() -> result.getAllErrors()
				.stream()
				.findFirst()
				.map(error -> ExceptionResponse.of(CommonErrorCode.INVALID_PARAMETER.getCode(),
					error.getDefaultMessage()))
				.orElse(null));

		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
			.body(body);
	}
}
