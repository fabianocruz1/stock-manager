package com.fabiano.stockquotemanager.interfaces;

import java.util.List;

import com.fabiano.stockquotemanager.dto.StockDto;

public interface StockQuoteService {

	StockDto save(StockDto dto);

	void deleteCache();

	void deleteByName(String id);

	List<StockDto> findAll();

	StockDto findByName(String name);

}
