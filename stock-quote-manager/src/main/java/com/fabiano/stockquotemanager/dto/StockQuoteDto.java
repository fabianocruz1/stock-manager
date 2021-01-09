package com.fabiano.stockquotemanager.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class StockQuoteDto {

//	String name;
	LocalDate date;
	BigDecimal price;

	public StockQuoteDto() {
		super();
	}

	public StockQuoteDto(LocalDate date, BigDecimal price) {
		super();
		this.date = date;
		this.price = price;
	}

//	public StockQuoteDto(String name, LocalDate date, BigDecimal price) {
//		super();
//		this.name = name;
//		this.date = date;
//		this.price = price;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
