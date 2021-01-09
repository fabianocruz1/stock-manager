package com.fabiano.stockmanager.util;

import java.util.Objects;

import com.fabiano.stockmanager.dto.StockDto;
import com.fabiano.stockmanager.model.Stock;

public class StockConverter {

	public static StockDto toDto(Stock stock) {
		if (Objects.isNull(stock)) return null;
		StockDto dto = new StockDto();
		dto.setId(stock.getName());
		return dto;
	}

	public static Stock fromDto(StockDto dto) {
		if (Objects.isNull(dto)) return null;
		Stock stock = new Stock();
		stock.setName(dto.getId());
		return stock;
	}

}
