package com.fabiano.stockquotemanager.dto;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class StockDto {

	String id;
	Map<LocalDate, String> quotes = new HashMap<>();
	
	public StockDto(String id, Map<LocalDate, String> quotes) {
		super();
		this.id = id;
		this.quotes = quotes;
	}
	
	public StockDto() {
		super();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Map<LocalDate, String> getQuotes() {
		return quotes;
	}
	public void setQuotes(Map<LocalDate, String> quotes) {
		this.quotes = quotes;
	}


}
