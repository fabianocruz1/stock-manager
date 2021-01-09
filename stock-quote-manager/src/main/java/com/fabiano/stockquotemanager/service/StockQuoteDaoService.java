package com.fabiano.stockquotemanager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fabiano.stockquotemanager.model.StockQuote;
import com.fabiano.stockquotemanager.repository.StockQuoteRepository;
import com.fabiano.stockquotemanager.util.StockQuoteConverter;

@Service
public class StockQuoteDaoService {

	@Autowired
	private StockQuoteRepository stockQuoteRepository;
	
	@Transactional
	public StockQuote save(StockQuote quote) {
		return stockQuoteRepository.save(quote);
	}

	@Transactional(readOnly = true)
	public StockQuote findById(Long id) {
		Optional<StockQuote> stockQuote = stockQuoteRepository.findById(id);
		return stockQuote.orElse(new StockQuote());
	}
	
	@Transactional(readOnly = true)
	public List<StockQuote> findByName(String name) {
		List<StockQuote> stockQuote = stockQuoteRepository.findAllByName(name);
		return stockQuote;
	}
	
	@Transactional(readOnly = true)
	public List<StockQuote> findAll() {
		Iterable<StockQuote> findAll = stockQuoteRepository.findAll();
		List<StockQuote> list = convertToList(findAll);
		System.out.println(list);
		return list;
	}

	private List<StockQuote> convertToList(Iterable<StockQuote> findAll) {
		List<StockQuote> list = new ArrayList<StockQuote>();
		if (Objects.nonNull(findAll)) {
			findAll.forEach(s -> list.add(s));
		}
		return list;
	}

	@Transactional
	public List<StockQuote> saveAll(List<StockQuote> collect) {
		Iterable<StockQuote> all = stockQuoteRepository.saveAll(collect);	
		return convertToList(all);
	}


}
