package com.fabiano.stockquotemanager.interfaces;

import java.util.List;

import com.fabiano.stockquotemanager.dto.StockDto;

public interface StockController {

	StockDto createStockQuote(StockDto dto);
	List<StockDto> readStockQuotes();
	StockDto readStockQuotesByStockId(String strockId);
	
}
