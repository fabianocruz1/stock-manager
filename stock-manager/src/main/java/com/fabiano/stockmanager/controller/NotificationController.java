package com.fabiano.stockmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fabiano.stockmanager.dto.ServerDto;
import com.fabiano.stockmanager.service.ServerService;
import com.fabiano.stockmanager.util.StockException;

@RestController
public class NotificationController {
 
	@Autowired
	private final ServerService serverService;
 
    public NotificationController(ServerService serverService) {
    	this.serverService = serverService;
    }
 
    @PostMapping("/notification")
    public ResponseEntity<?> notification(@RequestBody ServerDto dto) {
    	try{
    		ServerDto save = serverService.save(dto);
    		return ResponseEntity.status(HttpStatus.OK).body(save);
    	} catch (StockException e) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
    }
 
}