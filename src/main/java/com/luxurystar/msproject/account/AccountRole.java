package com.luxurystar.msproject.account;

import com.luxurystar.msproject.common.CodeBaseEnum;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum AccountRole implements CodeBaseEnum {
	ROLE_ADMIN(0, "관리자"),
	ROLE_MEMBER(1, "회원"),
	ROLE_GUEST(2, "게스트");

	private final int code;
	private final String description;

	@jakarta.persistence.Converter
	public static class Converter extends AbstractConverter<AccountRole> {

		@Override
		public AccountRole convertToEntityAttribute(Integer dbData) {
			return super.convertToEnum(AccountRole.class, dbData);
		}
	}
}
