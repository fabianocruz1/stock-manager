package com.fabiano.stockquotemanager.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.fabiano.stockquotemanager.dto.StockDto;
import com.fabiano.stockquotemanager.model.Stock;
import com.fabiano.stockquotemanager.model.StockQuote;

public class StockConverter {

	public static StockDto toDto(Stock stock) {
		if (Objects.isNull(stock)) return null;
		StockDto dto = new StockDto(stock.getName(), convertToDto(stock.getQuotes()));
		return dto;
	}

	public static Stock fromDto(StockDto dto) {
		if (Objects.isNull(dto)) return null;
		Stock stock = new Stock();
		stock.setName(dto.getId());
		stock.setQuotes(convertFromDto(dto, stock));
		return stock;
	}

	public static List<StockQuote> convertFromDto(StockDto dto, Stock stock) {
		return dto.getQuotes().entrySet().stream().map(q -> new StockQuote(stock, q.getKey(), new BigDecimal(q.getValue()))).collect(Collectors.toList());
	}

	public static Map<LocalDate, String> convertToDto(List<StockQuote> quotes) {
		Map<LocalDate, String> quotesDto = new HashMap<>();
		quotes.stream().map(q -> quotesDto.put(q.getDate(), q.getPrice().toString())).collect(Collectors.toList());
		return quotesDto;
	}

	
}
