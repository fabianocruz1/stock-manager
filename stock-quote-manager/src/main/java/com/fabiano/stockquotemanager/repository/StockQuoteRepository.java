package com.fabiano.stockquotemanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.fabiano.stockquotemanager.model.StockQuote;

public interface StockQuoteRepository extends JpaRepository<StockQuote, Long> {

	StockQuote findByName(String name);

	List<StockQuote> findAllByName(String name);

}
