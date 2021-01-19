package com.fabiano.stockmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fabiano.stockmanager.dto.StockDto;
import com.fabiano.stockmanager.service.NotificationService;
import com.fabiano.stockmanager.service.StockService;
import com.fabiano.stockmanager.util.StockException;

@RestController
public class StockController {
 
	@Autowired
	private final StockService service;
	
	@Autowired
	private NotificationService notificationService;
 
    public StockController(StockService service) {
    	this.service = service;
    }
 
    @GetMapping("/stock")
    public List<StockDto> readAllStocks() {
    	List<StockDto> stockDtos = service.findAll();
		return stockDtos;
    }
 
    @PostMapping("/stock")
    public ResponseEntity<?> register(@RequestBody StockDto dto) {
    	try{
    		StockDto save = service.save(dto);
    		notificationService.notifyHosts();
    		return ResponseEntity.status(HttpStatus.OK).body(save);
    	} catch (StockException e) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
    }
 
}