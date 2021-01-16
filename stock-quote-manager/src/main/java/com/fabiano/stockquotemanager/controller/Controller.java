package com.fabiano.stockquotemanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fabiano.stockquotemanager.dto.StockDto;
import com.fabiano.stockquotemanager.interfaces.StockController;
import com.fabiano.stockquotemanager.interfaces.StockQuoteService;
import com.fabiano.stockquotemanager.service.StockQuoteServiceImpl;

@RestController
public class Controller implements StockController {
 
	@Autowired
	private final StockQuoteService service;
 
    public Controller(StockQuoteServiceImpl service) {
        this.service = service;
    }
 
    @GetMapping("/stock")
	@Override
	public List<StockDto> readStockQuotes() {
    	List<StockDto> stockDtos = service.findAll();
		return stockDtos;
    }
 
    @GetMapping(value = "/stock/{id}")
	@Override
	public StockDto readStockQuotesByStockId(@PathVariable String id) {
    	return service.findByName(id);
    }
 
    @PostMapping("/stock")
	@Override
    public StockDto createStockQuote(@RequestBody StockDto dto) {
    	StockDto save = service.save(dto);
        return save;
    }
 
    @DeleteMapping(value = "/stockcache")
    public void deleteCache() {
        service.deleteCache();
    }

}