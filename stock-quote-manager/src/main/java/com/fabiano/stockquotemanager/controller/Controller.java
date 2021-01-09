package com.fabiano.stockquotemanager.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fabiano.stockquotemanager.dto.StockDto;
import com.fabiano.stockquotemanager.service.StockService;
import com.fabiano.stockquotemanager.util.Contants;
import com.fabiano.stockquotemanager.util.StockConverter;

@RestController
public class Controller {
 
	@Autowired
	private final StockService service;
 
    public Controller(StockService service) {
        this.service = service;
    }
 
    @GetMapping("/stock")
    public String readAllStockQuotes() {
    	List<StockDto> stockDtos = service.findAll();
		return stockDtos.stream().map(dto -> StockConverter.toString(dto)).collect(Collectors.joining(Contants.LIST_SEPARATOR+Contants.NEW_LINE));
    }
 
    @GetMapping(value = "/stock/{id}")
    public String readStockQuotesById(@PathVariable String id) {
    	return StockConverter.toString(service.findByName(id));
    }
 
    @PostMapping("/stock")
    public String createQuote(@RequestBody String info) {
    	StockDto dto = StockConverter.fromString(info);
    	StockDto save = service.save(dto);
        return StockConverter.toString(save);
    }
 
    @DeleteMapping(value = "/stockcache")
    public void deleteCache() {
        service.deleteCache();
    }
}