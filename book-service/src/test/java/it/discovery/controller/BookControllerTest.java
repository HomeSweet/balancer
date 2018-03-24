package it.discovery.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import it.discovery.BookApplication;

@AutoConfigureMockMvc
@SpringJUnitWebConfig(BookApplication.class)
public class BookControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void getBooks_NoBooksInRepository_returnEmptyList() throws Exception {
		//Given
		//When
		ResultActions resultActions = mockMvc
				.perform(get("/book")).andDo(print());
		//Then
		resultActions.andExpect(content()
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", Matchers.hasSize(0)));
	}

}
