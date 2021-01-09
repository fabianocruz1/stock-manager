//package com.fabiano.stockmanager.service;
//
//import static org.junit.Assert.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.fabiano.stockmanager.dto.StockDto;
//import com.fabiano.stockmanager.dto.StockQuoteDto;
//import com.fabiano.stockmanager.model.Stock;
//import com.fabiano.stockmanager.model.StockQuote;
//import com.fabiano.stockmanager.util.StockConverter;
//import com.fabiano.stockmanager.util.StockQuoteConverter;
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//class StockQuoteServiceTest {
//
//	@Autowired private StockQuoteService service;
//	@Autowired private StockService stockService;
//	private StockQuoteDto petrQuoteDto;
//	private StockQuoteDto valeQuoteDto;
//	private StockQuoteDto petrQuoteDto2;
//	
//	@TestConfiguration
//    static class StockQuoteServiceTestContextConfiguration {
////        @Bean
////        public StockQuoteService stockQuoteService() {
////            return new StockQuoteService();
////        }
//        
//        @Bean
//        public StockQuoteService stockQuoteService() {
//            return new StockQuoteService();
//        }
//    }
//
//	@BeforeEach
//	public void setUp() throws Exception {
//		System.out.println("setup");
//		petrQuoteDto = new StockQuoteDto("petr4", LocalDate.of(2000, 12, 30), new BigDecimal("10.00"));
//		petrQuoteDto2 = new StockQuoteDto("petr4", LocalDate.of(2010, 11, 03), new BigDecimal("15.00"));
//		valeQuoteDto = new StockQuoteDto("vale7", LocalDate.of(2020, 01, 01), new BigDecimal("33.00"));
//	}
//	
//	@Test
//	void testSave() {
//		StockQuoteDto saved = service.save(petrQuoteDto);
//		isEquals(saved, petrQuoteDto);
//		
//		StockQuoteDto saved2 = service.save(petrQuoteDto2);
//		isEquals(saved2, petrQuoteDto2);
//		
//		StockQuoteDto vale = service.save(valeQuoteDto);
//		isEquals(vale, valeQuoteDto);
//		
//	}
//
//	private void isEquals(StockQuoteDto saved, StockQuoteDto input) {
//		assertNotNull(saved);
//		assertTrue(Objects.equals(saved.getDate(), input.getDate()));
//		assertTrue(Objects.equals(saved.getName(), input.getName()));
//		assertTrue(Objects.equals(saved.getPrice(), input.getPrice()));
//	}
//
//	@Test
//	void testFindByName() {
//		StockQuoteDto saved = service.save(petrQuoteDto);
//		assertNotNull(saved);
//		assertNotNull(saved.getName());
//
//		StockQuoteDto quote = service.findByName(saved.getName());
//		assertNotNull(quote);
//		assertNotNull(quote.getName());
//		
//		isEquals(saved, quote);
//	}
//
//	@Test
//	void testFindAll() {
//		StockDto saved = service.save(petrQuoteDto);
//		assertStock(saved);
//
//		List<StockDto> findAll = service.findAll();
//		assertNotNull(findAll);
//		findAll.stream().forEach(s -> assertStock(s));
//	}
//
//	@Test
//	void testDeleteByName() {
//		StockDto saved = service.save(stockPetr7);
//		assertStock(saved);
//
//		service.deleteByName(saved.getId());
//		List<StockDto> findAll = service.findAll();
//		assertNotNull(findAll);
//		assertTrue(findAll.isEmpty());
//	}
//
//}
