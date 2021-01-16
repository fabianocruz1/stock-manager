package com.fabiano.stockquotemanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fabiano.stockquotemanager.model.Stock;

public interface StockRepository extends JpaRepository<Stock, String> {

	@Query("From Stock where :name = name")
	Stock findByName(String name);

	@Query("From Stock where :name = name")
	void deleteByName(String name);

}
