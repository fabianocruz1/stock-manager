package com.fabiano.stockquotemanager.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.fabiano.stockquotemanager.model.StockQuote;

public interface StockQuoteRepository extends CrudRepository<StockQuote, Long> {

	StockQuote findByName(String name);

	List<StockQuote> findAllByName(String name);

}
