package com.fabiano.stockmanager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fabiano.stockmanager.dto.StockDto;
import com.fabiano.stockmanager.model.Stock;
import com.fabiano.stockmanager.repository.StockInMemoryRepository;
import com.fabiano.stockmanager.repository.StockRepository;
import com.fabiano.stockmanager.util.StockException;

@Service
public class StockService {

	StockRepository stockRepository;
	
	public StockService(StockInMemoryRepository stockRepository) {
		this.stockRepository = stockRepository;
	}
	
	@Transactional
	public StockDto save(StockDto dto) {
		final String name = dto.getId();

		if (Objects.nonNull(stockRepository.findByName(name))) throw new StockException("Stock id already registered.");

		stockRepository.save(new Stock(name, dto.getDescription()));
		
		return dto;
	}

	@Transactional(readOnly = true)
	public List<StockDto> findAll() {
		List<StockDto> list = new ArrayList<StockDto>();
		Iterable<Stock> stocks = stockRepository.findAll();
		stocks.forEach(s -> list.add(new StockDto(s.getName(), s.getDescription())));
		return list;
	}

}
