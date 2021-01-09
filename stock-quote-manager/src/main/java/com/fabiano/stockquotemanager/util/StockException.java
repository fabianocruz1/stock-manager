package com.fabiano.stockquotemanager.util;

public class StockException extends RuntimeException {

	public StockException(String invalidInput) {
		super(invalidInput);
	}

	private static final long serialVersionUID = 1L;

}
