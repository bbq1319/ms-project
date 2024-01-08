package com.luxurystar.msproject.account.controller;

import static com.luxurystar.msproject.MockMvcSupport.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.ResultActions;

import com.luxurystar.msproject.MsProjectApplicationTests;
import com.luxurystar.msproject.account.AccountRole;

public class AccountControllerTest extends MsProjectApplicationTests {

	private final String API_PATH = "/account";

	@MockBean
	private PasswordEncoder passwordEncoder;

	@Test
	public void login_success() throws Exception {
		// Given
		Map<String, String> request = new LinkedHashMap<>();
		request.put("email", "qhshef@msproject.com");
		request.put("password", "test1234!");

		when(passwordEncoder.matches(anyString(), anyString()))
			.thenReturn(true);

		// When
		ResultActions result = mockMvc.perform(
			post("/login")
				.contentType(APPLICATION_JSON)
				.accept(APPLICATION_JSON)
				.content(serializeRequestBody(request)));

		// Then
		result.andExpect(status().isOk());
	}

	@Test
	public void register_success() throws Exception {
		// Given
		Map<String, String> request = new LinkedHashMap<>();
		request.put("email", "test@mscompany.com");
		request.put("password", "test1234!");
		request.put("confirmPassword", "test1234!");
		request.put("name", "테스트");
		request.put("role", AccountRole.ROLE_MEMBER.name());

		when(passwordEncoder.encode(anyString()))
			.thenReturn("TEST");

		// When
		ResultActions result = mockMvc.perform(
			post(API_PATH)
				.contentType(APPLICATION_JSON)
				.accept(APPLICATION_JSON)
				.content(serializeRequestBody(request)));

		// Then
		result.andExpect(status().isOk());
	}

	@Test
	public void register_fail_BadRequest() throws Exception {
		// Given
		Map<String, String> request = new LinkedHashMap<>();
		request.put("email", "fail@mscompany.com");
		request.put("password", "test1234!");
		request.put("confirmPassword", "test");
		request.put("name", "테스트");
		request.put("role", AccountRole.ROLE_MEMBER.name());

		when(passwordEncoder.encode(anyString()))
			.thenReturn("TEST");

		// When
		ResultActions result = mockMvc.perform(
			post(API_PATH)
				.contentType(APPLICATION_JSON)
				.accept(APPLICATION_JSON)
				.content(serializeRequestBody(request)));

		// Then
		result.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.errorCode", is("C001")))
			.andExpect(jsonPath("$.message", is("password_비밀번호가 서로 일치하지 않습니다.")));
	}
}