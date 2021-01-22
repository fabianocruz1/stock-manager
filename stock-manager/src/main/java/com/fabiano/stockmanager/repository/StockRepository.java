package com.fabiano.stockmanager.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import com.fabiano.stockmanager.model.Stock;

public interface StockRepository {

	@Query("From Stock where :name = name")
	Stock findByName(String name);

	@Query("From Stock where :name = name")
	void deleteByName(String name);

	Collection<Stock> findAll();

	Stock save(Stock stock);

}
