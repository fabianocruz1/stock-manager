package com.fabiano.stockquotemanager.service;
//package com.fabiano.stockmanager.service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.fabiano.stockmanager.dto.StockQuoteDto;
//import com.fabiano.stockmanager.model.StockQuote;
//import com.fabiano.stockmanager.repository.StockQuoteRepository;
//import com.fabiano.stockmanager.util.StockQuoteConverter;
//
//@Service
//public class StockQuoteService {
//
//	@Autowired
//	private StockQuoteDaoService stockQuoteDao;
//	
//	@Transactional
//	public StockQuoteDto save(StockQuoteDto quoteDto) {
//		StockQuote quote = StockQuoteConverter.fromDto(quoteDto);
//		StockQuote newStock = saveNewStock(quote);
//		return StockQuoteConverter.toDto(newStock);
//	}
//
//	private StockQuote saveNewStock(StockQuote quote) {
//		return stockQuoteDao.save(quote);
//	}
//
//	@Transactional(readOnly = true)
//	public StockQuoteDto findByName(String name) {
//		List<StockQuote> stockQuote = stockQuoteDao.findByName(name);
//		return StockQuoteConverter.toDto(stockQuote);
//	}
//	
//	@Transactional(readOnly = true)
//	public List<StockQuoteDto> findAll() {
//		Iterable<StockQuote> findAll = stockQuoteRepository.findAll();
//		List<StockQuoteDto> list = new ArrayList<StockQuoteDto>();
//		if (Objects.nonNull(findAll)) {
//			findAll.forEach(s -> list.add(StockQuoteConverter.toDto(s)));
//		}
//		System.out.println(list);
//		return list;
//	}
//
//
//}
