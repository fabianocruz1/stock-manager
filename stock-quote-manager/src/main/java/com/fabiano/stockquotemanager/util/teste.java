package com.fabiano.stockquotemanager.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.fabiano.stockquotemanager.model.Stock;
import com.fabiano.stockquotemanager.model.StockQuote;

public class teste {

	private static final String KEY_VALUE_SEPARATOR = ":";
	private static final String START_LIST = "{";
	private static final String END_OF_LIST = "}";
	private static final String LIST_SEPARATOR = ",";
	private static final String ID_TOKEN = "id";
	private static final String QUOTES_TOKEN = "quotes";
	private static final String INVALID_INPUT = "Invalid input";

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String info = "{\r\n" + 
				" \"id\": \"petr3\",\r\n" + 
				" \"quotes\":\r\n" + 
				" {\r\n" + 
				" \"2019-01-01\" : \"10\",\r\n" + 
				" \"2019-01-02\" : \"11\",\r\n" + 
				" \"2019-01-03\" : \"14\"\r\n" + 
				" }\r\n" + 
				"} ";
	
	
		int indexOfId = info.indexOf(ID_TOKEN);
		int indexOfQuotes = info.indexOf(QUOTES_TOKEN);
		int indexOfStartListQuotes = info.indexOf(START_LIST, indexOfQuotes);
		int indexOfEndListQuotes = info.indexOf(END_OF_LIST, indexOfQuotes);
		
		String id = info.substring(indexOfId, indexOfQuotes);
		String quotes = info.substring(indexOfStartListQuotes+1, indexOfEndListQuotes);
		List<String> quotesList = Arrays.asList(quotes.split(LIST_SEPARATOR));
		
		fromString(info);

	}

	public static Stock fromString(String info) {
		System.out.println(info);
		
		
		String id = getStockName(info);
		List<StockQuote> quotes = getQuotes(info);
		
		

		Stock stock = new Stock();
		stock.setName(id);
		stock.setQuotes(quotes);
		
		return stock;
	}

	private static List<StockQuote> getQuotes(String info) {
		int indexOfQuotes = info.indexOf(QUOTES_TOKEN);
		int indexOfStartListQuotes = info.indexOf(START_LIST, indexOfQuotes);
		int indexOfEndListQuotes = info.indexOf(END_OF_LIST, indexOfQuotes);

		String quotesStr = info.substring(indexOfStartListQuotes+1, indexOfEndListQuotes);
		List<String> quotesList = Arrays.asList(quotesStr.split(LIST_SEPARATOR));
		List<StockQuote> quotes = quotesList.stream().map(s -> getQuote(s)).collect(Collectors.toList());
		
		System.out.println(quotesList);
		return quotes;
		
	}

	private static StockQuote getQuote(String info) {
		String keyValue[] = info.split(KEY_VALUE_SEPARATOR);
		if (!isQuoteKeyValueValid(keyValue)) throw new RuntimeException(INVALID_INPUT);
		LocalDate date = LocalDate.parse(removeInvalidChars(keyValue[0]), DateTimeFormatter.ISO_DATE);
		BigDecimal price = new BigDecimal(removeInvalidChars(keyValue[1]));
		return new StockQuote("", date, price);
	}
	
	private static String getStockName(String info) {
		int indexOfId = info.indexOf(ID_TOKEN);
		int indexOfQuotes = info.indexOf(QUOTES_TOKEN);
		
		if (!isIndexValid(indexOfId, indexOfQuotes)) throw new RuntimeException(INVALID_INPUT);
		
		String keyValue[] = info.substring(indexOfId, indexOfQuotes).split(KEY_VALUE_SEPARATOR);
		
		if (!isIdKeyValueValid(keyValue)) throw new RuntimeException(INVALID_INPUT);
		
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
