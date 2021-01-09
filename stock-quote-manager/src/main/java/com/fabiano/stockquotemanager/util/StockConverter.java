package com.fabiano.stockquotemanager.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.fabiano.stockquotemanager.dto.StockDto;
import com.fabiano.stockquotemanager.dto.StockQuoteDto;
import com.fabiano.stockquotemanager.model.Stock;

public class StockConverter {

	public static StockDto toDto(Stock stock) {
		if (Objects.isNull(stock)) return null;
		StockDto dto = new StockDto();
		dto.setId(stock.getName());
		dto.setQuotes(new ArrayList<>());
		stock.getQuotes().stream().forEach(q -> dto.getQuotes().add(StockQuoteConverter.toDto(q)));
		return dto;
	}

	public static Stock fromDto(StockDto dto) {
		if (Objects.isNull(dto)) return null;
		Stock stock = new Stock();
		stock.setName(dto.getId());
		stock.setQuotes(new ArrayList<>());
		dto.getQuotes().stream().forEach(q -> stock.getQuotes().add(StockQuoteConverter.fromDto(q)));
		return stock;
	}

	public static StockDto fromString(String info) {
		System.out.println(info);
		
		String id = getStockName(info);
		List<StockQuoteDto> quotes = getQuotes(info);

		StockDto stock = new StockDto(id, quotes);
		return stock;
	}
	
	public static String toString(StockDto dto) {		
		return String.format(Contants.DTO_FORMAT, dto.getId(), getQuotesAsString(dto.getQuotes()));
	}

	private static List<StockQuoteDto> getQuotes(String info) {
		int indexOfQuotes = info.indexOf(Contants.QUOTES_TOKEN);
		int indexOfStartListQuotes = info.indexOf(Contants.START_LIST, indexOfQuotes);
		int indexOfEndListQuotes = info.indexOf(Contants.END_OF_LIST, indexOfQuotes);

		String quotesStr = info.substring(indexOfStartListQuotes+1, indexOfEndListQuotes);
		List<String> quotesList = Arrays.asList(quotesStr.split(Contants.LIST_SEPARATOR));
		List<StockQuoteDto> quotes = quotesList.stream().map(s -> getQuote(s)).collect(Collectors.toList());
		
		System.out.println(quotesList);
		return quotes;
		
	}

	private static StockQuoteDto getQuote(String info) {
		String keyValue[] = info.split(Contants.KEY_VALUE_SEPARATOR);
		if (!isQuoteKeyValueValid(keyValue)) throw new RuntimeException(Contants.INVALID_INPUT);
		LocalDate date = LocalDate.parse(removeInvalidChars(keyValue[0]), DateTimeFormatter.ISO_DATE);
		BigDecimal price = new BigDecimal(removeInvalidChars(keyValue[1]));
		return new StockQuoteDto(date, price);
	}
	
	private static String getQuotesAsString(List<StockQuoteDto> quotes) {
		return 
		quotes.stream().map(q -> getQuoteAsString(q)).collect(Collectors.joining(Contants.LIST_SEPARATOR+Contants.NEW_LINE));
	}

	private static String getQuoteAsString(StockQuoteDto q) {
		return Contants.QUOTES + q.getDate().format(DateTimeFormatter.ISO_DATE) + Contants.QUOTES + 
				Contants.KEY_VALUE_SEPARATOR + 
				Contants.QUOTES + q.getPrice() + Contants.QUOTES;
	}

	private static String getStockName(String info) {
		int indexOfId = info.indexOf(Contants.ID_TOKEN);
		int indexOfQuotes = info.indexOf(Contants.QUOTES_TOKEN);
		
		if (!isIndexValid(indexOfId, indexOfQuotes)) throw new StockException(Contants.INVALID_INPUT);
		
		String keyValue[] = info.substring(indexOfId, indexOfQuotes).split(Contants.KEY_VALUE_SEPARATOR);
		
		if (!isIdKeyValueValid(keyValue)) throw new StockException(Contants.INVALID_INPUT);
		
		String stockName = removeInvalidChars(keyValue[1]);
		return stockName;
	}

	private static boolean isIdKeyValueValid(String[] keyValue) {
		return keyValue.length == 2 && keyValue[1].length() >= 5;
	}

	private static boolean isQuoteKeyValueValid(String[] keyValue) {
		return keyValue.length == 2 && keyValue[0].length() >= 10 && keyValue[1].length() >= 1;
	}

	private static boolean isIndexValid(int indexOfId, int indexOfQuotes) {
		return indexOfId > 0 && indexOfQuotes > 0 && indexOfId < indexOfQuotes;
	}

	private static String removeInvalidChars(String string) {
		return string.replaceAll(",","").replaceAll("\"", "").trim();
	}

}
