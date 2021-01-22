package com.fabiano.stockmanager.repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fabiano.stockmanager.model.Stock;

@Component
public class StockInMemoryRepository implements StockRepository {

	private ConcurrentHashMap<String, Stock> stocks = new ConcurrentHashMap<>(); 
	
	@Override
	public Stock findByName(String name) {
		Stock stock = stocks.get(name);
		return stock;
	}

	@Override
	public void deleteByName(String name) {
		Stock stock = findByName(name);
		if (stock != null) {
			stocks.remove(name);
		}
	}

	@Override
	public Stock save(Stock stock) {
		return stocks.put(stock.getName(), stock);
	}

	@Override
	public Collection<Stock> findAll() {
		return stocks.values();
	}

}
