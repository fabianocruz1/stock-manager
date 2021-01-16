package com.fabiano.stockquotemanager.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fabiano.stockquotemanager.dto.StockDto;
import com.fabiano.stockquotemanager.interfaces.StockQuoteService;
import com.fabiano.stockquotemanager.model.Stock;
import com.fabiano.stockquotemanager.model.StockQuote;
import com.fabiano.stockquotemanager.repository.StockQuoteRepository;
import com.fabiano.stockquotemanager.repository.StockRepository;
import com.fabiano.stockquotemanager.util.StockConverter;

@Service
public class StockQuoteServiceImpl implements StockQuoteService {

	@Autowired
	StockRepository stockRepository;
	
	@Autowired
	StockQuoteRepository stockQuoteRepository;

	@Override
	@Transactional
	public StockDto save(StockDto dto) {
		final String name = dto.getId();
		if (Objects.isNull(dto.getQuotes()) || dto.getQuotes().size() == 0) {
			return new StockDto(name, Collections.emptyMap());
		}

		Stock findByName = stockRepository.findByName(name);
		if (Objects.isNull(findByName)) {
			Stock stock = new Stock();
			stock.setName(name);
			findByName = stockRepository.save(stock);
		}
		final Stock stock = findByName;
		List<StockQuote> collect = dto.getQuotes().entrySet().stream().map(q -> new StockQuote(stock, q.getKey(), new BigDecimal(q.getValue()))).collect(Collectors.toList());
		List<StockQuote> quotes = stockQuoteRepository.saveAll(collect);
		Map<LocalDate, String> quotesDto = StockConverter.convertToDto(quotes);
		
		return new StockDto(name, quotesDto);
	}

	@Override
	@Transactional(readOnly = true)
	public StockDto findByName(String name) {
		List<StockQuote> quotes = stockQuoteRepository.findAllByName(name);
		Map<LocalDate, String> quotesDto = StockConverter.convertToDto(quotes);
		return new StockDto(name, quotesDto);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<StockDto> findAll() {
		List<StockDto> list = new ArrayList<StockDto>();
		List<Stock> stocks = stockRepository.findAll();
		stocks.forEach(s -> list.add(new StockDto(s.getName(), StockConverter.convertToDto(s.getQuotes()))));
		return list;
	}

	@Override
	@Transactional
	public void deleteByName(String id) {
		Stock stock = stockRepository.findByName(id);
		if (Objects.nonNull(stock)) {
			stockRepository.delete(stock);
		}
	}
	
	@Override
	public void deleteCache() {
		// TODO: 
	}
	
}
