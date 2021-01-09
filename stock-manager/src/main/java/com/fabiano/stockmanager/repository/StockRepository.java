package com.fabiano.stockmanager.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.fabiano.stockmanager.model.Stock;

public interface StockRepository extends CrudRepository<Stock, Long> {

	@Query("From Stock where :name = name")
	Stock findByName(String name);

	@Query("From Stock where :name = name")
	void deleteByName(String name);

}
