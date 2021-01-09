package com.fabiano.stockmanager.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fabiano.stockmanager.dto.StockDto;
import com.fabiano.stockmanager.util.StockException;

@RunWith(SpringRunner.class)
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
	
    @Test(expected=StockException.class)
	void testSave() throws Exception {
		StockDto dto = new StockDto("petr7", "test petr7");
		StockDto saved = stockService.save(dto);
		assertNotNull(saved);
		
		StockDto dto2 = new StockDto("petr7", "test petr7");
		StockDto saved2 = stockService.save(dto2);
		assertNotNull(saved2);

		List<StockDto> findByName = stockService.findAll();

	}

}