package com.fabiano.stockmanager.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fabiano.stockquotemanager.dto.StockDto;
import com.fabiano.stockquotemanager.dto.StockQuoteDto;
import com.fabiano.stockquotemanager.model.Stock;
import com.fabiano.stockquotemanager.service.StockQuoteDaoService;
import com.fabiano.stockquotemanager.service.StockService;

@RunWith(SpringRunner.class)
@DataJpaTest
class StockServiceTest {

	@Autowired StockService stockService;
	private Stock stockPetr7;
	
	@TestConfiguration
    static class StockServiceTestContextConfiguration {
        @Bean
        public StockService stockService() {
            return new StockService();
        }
        @Bean
        public StockQuoteDaoService stockQuoteDaoService() {
            return new StockQuoteDaoService();
        }
        
    }

//	@BeforeEach
//	public void setUp() throws Exception {
//		System.out.println("setup");
//		Stock stock;
//
//		stock = new Stock();
//		stock.setDescription("test petr7");
//		stock.setId("petr7");
//		List<StockQuote> quotes = new ArrayList<>();
//		StockQuote q = new StockQuote();
//		q.setDate(LocalDate.of(2000, 12, 30));
//		q.setPrice(new BigDecimal("10.00"));
//		q.setStock(stock);
//		quotes.add(q);
//		stock.setQuotes(quotes);
//		System.out.println("setup "+stock);
////		stockPetr7 = ConvertStock.toDto(stock);
//		stockPetr7 = stock;
//	}
	
	@Test
	void testSave() {
		List<StockQuoteDto> quotes = Arrays.asList(new StockQuoteDto(LocalDate.of(2000, 12, 21), new BigDecimal("12.00")));
		StockDto dto = new StockDto("petr7", quotes);
		StockDto saved = stockService.save(dto);
		assertStock(saved);
		
		List<StockQuoteDto> quotes2 = Arrays.asList(new StockQuoteDto(LocalDate.of(2001, 01, 01), new BigDecimal("20.00")));
		StockDto dto2 = new StockDto("petr7", quotes);
		StockDto saved2 = stockService.save(dto2);
		assertStock(saved2);

		StockDto findByName = stockService.findByName("petr7");
		assertStock(findByName);
		assertTrue(saved.getQuotes().size() == 2);

	}

	private void assertStock(StockDto saved) {
		assertNotNull(saved);
		assertNotNull(saved.getQuotes());
		assertTrue(saved.getQuotes().size() > 0);
	}

//	@Test
//	void testFindByName() {
//		StockDto saved = stockService.save(stockPetr7);
//		assertStock(saved);
//
//		StockDto stock = stockService.findByName(saved.getId());
//		assertStock(stock);
//		assertTrue(Objects.equals(saved.getId(), stock.getId()));
//
//	}
//
//	@Test
//	void testFindAll() {
//		StockDto saved = stockService.save(stockPetr7);
//		assertStock(saved);
//
//		List<StockDto> findAll = stockService.findAll();
//		assertNotNull(findAll);
//		findAll.stream().forEach(s -> assertStock(s));
//	}
//
//	@Test
//	void testDeleteByName() {
//		StockDto saved = stockService.save(stockPetr7);
//		assertStock(saved);
//
//		stockService.deleteByName(saved.getId());
//		List<StockDto> findAll = stockService.findAll();
//		assertNotNull(findAll);
//		assertTrue(findAll.isEmpty());
//	}

}
