package com.luxurystar.msproject;

import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MockMvcSupport {

	private static final ObjectMapper mapper = new ObjectMapper();

	public static MockMvc createMockMvc(WebApplicationContext context) {
		return MockMvcBuilders.webAppContextSetup(context)
			.addFilter(new CharacterEncodingFilter("UTF-8", true))
			.apply(SecurityMockMvcConfigurers.springSecurity())
			.alwaysDo(MockMvcResultHandlers.print())
			.build();
	}

	public static String serializeRequestBody(Object request) {
		try {
			return mapper.writeValueAsString(request);
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException("Failed to Serialize Test Request");
		}
	}
}
