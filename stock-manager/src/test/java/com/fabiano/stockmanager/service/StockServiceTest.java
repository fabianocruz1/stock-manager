package com.fabiano.stockmanager.service;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.fabiano.stockmanager.dto.StockDto;
import com.fabiano.stockmanager.util.StockException;

@DataJpaTest
class StockServiceTest {

	@Autowired StockService stockService;

	@TestConfiguration
	static class StockServiceTestContextConfiguration {
		@Bean
		public StockService stockService() {
			return new StockService();
		}
	}

	@Test()
	void testSave() throws Exception {
		StockDto dto = new StockDto("petr7", "test petr7");
		StockDto saved = stockService.save(dto);
		assertNotNull(saved);

		StockDto dto2 = new StockDto("petr7", "test petr7");
		Assertions.assertThrows(StockException.class, () -> {
			stockService.save(dto2);
		});

	}

}