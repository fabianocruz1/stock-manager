package com.fabiano.stockquotemanager.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fabiano.stockquotemanager.dto.StockDto;
import com.fabiano.stockquotemanager.dto.StockQuoteDto;
import com.fabiano.stockquotemanager.model.Stock;
import com.fabiano.stockquotemanager.model.StockQuote;
import com.fabiano.stockquotemanager.repository.StockQuoteRepository;
import com.fabiano.stockquotemanager.repository.StockRepository;
import com.fabiano.stockquotemanager.util.StockQuoteConverter;

@Service
public class StockService {

	@Autowired
	StockRepository stockRepository;
	
	@Autowired
	StockQuoteRepository stockQuoteRepository;
	
	@Autowired
	private StockQuoteDaoService stockQuoteDao;

	@Transactional
	public StockDto save(StockDto dto) {
		final String name = dto.getId();
		if (Objects.isNull(dto.getQuotes()) || dto.getQuotes().size() == 0) {
			return new StockDto(name, Collections.emptyList());
		}

		Stock findByName = stockRepository.findByName(name);
		if (Objects.isNull(findByName)) {
			Stock stock = new Stock();
			stock.setName(name);
			findByName = stockRepository.save(stock);
		}
		final Stock stock = findByName;
//		List<StockQuote> collect = dto.getQuotes().stream().map(q -> new StockQuote(name , q.getDate(), q.getPrice())).collect(Collectors.toList());
		List<StockQuote> collect = dto.getQuotes().stream().map(q -> new StockQuote(stock, q.getDate(), q.getPrice())).collect(Collectors.toList());
				List<StockQuote> quotes = stockQuoteDao.saveAll(collect);
		List<StockQuoteDto> quotesDto = quotes.stream().map(q -> new StockQuoteDto(q.getDate(), q.getPrice())).collect(Collectors.toList());
		
		return new StockDto(name, quotesDto);
	}

	@Transactional(readOnly = true)
	public StockDto findByName(String name) {
		List<StockQuote> quotes = stockQuoteDao.findByName(name);
		List<StockQuoteDto> quotesDto = quotes.stream().map(q -> new StockQuoteDto(q.getDate(), q.getPrice())).collect(Collectors.toList());
		return new StockDto(name, quotesDto);
	}
	
	@Transactional(readOnly = true)
	public List<StockDto> findAll() {
		List<StockDto> list = new ArrayList<StockDto>();
		Iterable<Stock> stocks = stockRepository.findAll();
		stocks.forEach(s -> list.add(new StockDto(s.getName(), StockQuoteConverter.toListDto(s.getQuotes()))));
		return list;
	}

	@Transactional
	public void deleteByName(String id) {
		Stock stock = stockRepository.findByName(id);
		if (Objects.nonNull(stock)) {
			stockRepository.delete(stock);
		}
	}
	
	public void deleteCache() {
		// TODO: 
	}
	
}
