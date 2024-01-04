package com.luxurystar.msproject.account.controller;

import static com.luxurystar.msproject.MockMvcSupport.*;
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

public class AccountControllerTest extends MsProjectApplicationTests {

	@MockBean
	private PasswordEncoder passwordEncoder;

	@Test
	public void login_success() throws Exception {
		// Given
		Map<String, String> request = new LinkedHashMap<>();
		request.put("email", "qhshef@gmail.com");
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
}