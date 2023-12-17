package com.luxurystar.msproject.common;

import java.util.Arrays;

import jakarta.persistence.AttributeConverter;

public interface CodeBaseEnum {

	int getCode();

	abstract class AbstractConverter<E extends Enum<E> & CodeBaseEnum> implements AttributeConverter<E, Integer> {

		@Override
		public Integer convertToDatabaseColumn(E attribute) {
			return attribute == null ? null : attribute.getCode();
		}

		public E convertToEnum(Class<E> clazz, Integer dbData) {
			if (dbData == null) {
				return null;
			}

			return Arrays.stream(clazz.getEnumConstants())
				.filter(each -> each.getCode() == dbData)
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Invalid Enum Code"));
		}
	}
}
