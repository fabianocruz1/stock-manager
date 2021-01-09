package com.fabiano.stockquotemanager.util;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.fabiano.stockquotemanager.dto.StockQuoteDto;
import com.fabiano.stockquotemanager.model.StockQuote;

public class StockQuoteConverter {

	public static StockQuoteDto toDto(StockQuote stockQuote) {
		if (Objects.isNull(stockQuote)) return null;
		StockQuoteDto dto = new StockQuoteDto();
		dto.setDate(stockQuote.getDate());
		dto.setPrice(stockQuote.getPrice());
		return dto;
	}

	public static StockQuote fromDto(StockQuoteDto dto) {
		if (Objects.isNull(dto)) return null;
		StockQuote stock = new StockQuote();
		stock.setDate(dto.getDate());
		stock.setPrice(dto.getPrice());
		return stock;
	}

	public static List<StockQuoteDto> toListDto(List<StockQuote> quotes) {
		if (Objects.isNull(quotes)) return Collections.emptyList();
		return quotes.stream().map(q -> new StockQuoteDto(q.getDate(), q.getPrice())).collect(Collectors.toList());
	}
}
