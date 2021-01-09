package com.fabiano.stockquotemanager.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table()
@Entity
public class Stock {

	@Id 
	@GeneratedValue
	Long idStock;
	
	@Column(unique=true)
	String name;
	
	String description;
	
//	@OneToMany(orphanRemoval = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "stock", fetch = FetchType.EAGER)
    @OneToMany(cascade = CascadeType.ALL, mappedBy="stock")
    List<StockQuote> quotes = new ArrayList<>();
	
	public Stock() {
		super();
	}

	public Long getIdStock() {
		return idStock;
	}

	public void setIdStock(Long idStock) {
		this.idStock = idStock;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<StockQuote> getQuotes() {
		return quotes;
	}

	public void setQuotes(List<StockQuote> quotes) {
		this.quotes = quotes;
	}

    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
