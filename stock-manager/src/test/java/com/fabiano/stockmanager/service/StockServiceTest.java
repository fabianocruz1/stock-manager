package com.fabiano.stockmanager.service;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.fabiano.stockmanager.dto.StockDto;
import com.fabiano.stockmanager.repository.StockInMemoryRepository;
import com.fabiano.stockmanager.util.StockException;

class StockServiceTest {

	StockService stockService = new StockService(new StockInMemoryRepository());

	@TestConfiguration
	static class StockServiceTestContextConfiguration {
		@Bean
		public StockService stockService() {
			return new StockService(new StockInMemoryRepository());
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

		List<StockDto> findAll = stockService.findAll();
		assertNotNull(findAll);
		assertTrue(findAll.size() == 1);
		
	}

}