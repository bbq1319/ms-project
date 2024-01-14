package com.luxurystar.msproject.biz.controller;

import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import com.luxurystar.msproject.MsProjectApplicationTests;

public class MenuGroupControllerTest extends MsProjectApplicationTests {

	private final String API_PATH = "/menu-group";

	@Test
	public void getList_success() throws Exception {
		// When
		ResultActions result = mockMvc.perform(
			get(API_PATH)
				.accept(APPLICATION_JSON));

		// Then
		result.andExpect(status().isOk());
	}
}