package com.luxurystar.msproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith({SpringExtension.class, MockitoExtension.class})
public class MsProjectApplicationTests {

	protected MockMvc mockMvc;
	protected WebApplicationContext context;

	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcSupport.createMockMvc(this.context);
	}

	@Autowired
	public void setContext(WebApplicationContext context) {
		this.context = context;
	}
}
