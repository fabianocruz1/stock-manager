package com.fabiano.stockquotemanager.dto;

import java.util.ArrayList;
import java.util.List;

public class StockDto {

	String id;
	List<StockQuoteDto> quotes = new ArrayList<StockQuoteDto>();
	
	public StockDto() {
		super();
	}
	
	public StockDto(String id, List<StockQuoteDto> quotes) {
		super();
		this.id = id;
		this.quotes = quotes;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<StockQuoteDto> getQuotes() {
		return quotes;
	}

	public void setQuotes(List<StockQuoteDto> quotes) {
		this.quotes = quotes;
	}

}
