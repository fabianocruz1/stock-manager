package com.fabiano.stockmanager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fabiano.stockmanager.dto.StockDto;
import com.fabiano.stockmanager.model.Stock;
import com.fabiano.stockmanager.repository.StockRepository;
import com.fabiano.stockmanager.util.StockException;

@Service
public class StockService {

	@Autowired
	StockRepository stockRepository;
	
	@Transactional
	public StockDto save(StockDto dto) {
		final String name = dto.getId();

		if (Objects.nonNull(stockRepository.findByName(name))) throw new StockException("Stock id already registered.");

		Stock stock = new Stock();
		stock.setName(name);
		stock.setDescription(dto.getDescription());
		
		Stock save = stockRepository.save(stock);
		
		return new StockDto(save.getName(), save.getDescription());
	}

	@Transactional(readOnly = true)
	public List<StockDto> findAll() {
		List<StockDto> list = new ArrayList<StockDto>();
		Iterable<Stock> stocks = stockRepository.findAll();
		stocks.forEach(s -> list.add(new StockDto(s.getName(), s.getDescription())));
		return list;
	}

}
