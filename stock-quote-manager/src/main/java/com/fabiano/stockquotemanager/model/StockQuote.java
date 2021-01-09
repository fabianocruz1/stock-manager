package com.fabiano.stockquotemanager.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table()
@Entity
public class StockQuote {

	@Id
	@GeneratedValue
	@JsonIgnore
	Long idStockQuote;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stock_id", nullable = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
    Stock stock;
	
	String name;
	
	LocalDate date;
	
	@Digits(integer=5, fraction=2)
	BigDecimal price;

	public StockQuote() {
		super();
	}

	public StockQuote(Stock stock, LocalDate date, @Digits(integer = 5, fraction = 2) BigDecimal price) {
		super();
		this.stock = stock;
		this.date = date;
		this.price = price;
	}


	public StockQuote(String name, LocalDate date, BigDecimal price) {
		super();
		this.name = name;
		this.date = date;
		this.price = price;
	}

	public Long getIdStockQuote() {
		return idStockQuote;
	}

	public void setIdStockQuote(Long idStockQuote) {
		this.idStockQuote = idStockQuote;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
}
