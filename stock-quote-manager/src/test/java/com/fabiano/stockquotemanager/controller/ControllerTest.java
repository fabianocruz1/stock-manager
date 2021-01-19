package com.fabiano.stockquotemanager.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fabiano.stockquotemanager.dto.StockDto;
import com.fabiano.stockquotemanager.service.StockQuoteServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@Profile("test")
@WebMvcTest(Controller.class)
class ControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private StockQuoteServiceImpl service;
	
	ObjectMapper objectMapper;

	@BeforeEach
	void setUp() throws Exception {
        objectMapper = new ObjectMapper();
	}

	@Test
	void testGetAll() throws Exception {
		List<StockDto> stocks = new ArrayList<StockDto>();
		when(service.findAll()).thenReturn(stocks);
		mockMvc.perform(get("/stock")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	void testGetByStockId() throws Exception {
		StockDto stock = new StockDto();
		String name = "test9";
		when(service.findByName(name)).thenReturn(stock);
		mockMvc.perform(get("/stock/test9")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	void testPostStock() throws Exception {
		StockDto stock = new StockDto();
		when(service.save(stock)).thenReturn(stock);
		String jsonContent = objectMapper.writeValueAsString(stock);
		
		mockMvc.perform(post("/stock")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonContent)
				).andDo(print()).andExpect(status().isOk());
	}

}
