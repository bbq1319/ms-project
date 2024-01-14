package com.luxurystar.msproject.exception;

import com.luxurystar.msproject.exception.code.CommonErrorCode;

public class NotFoundException extends BaseException {

	public NotFoundException() {
		super(CommonErrorCode.NOT_FOUND);
	}
}
