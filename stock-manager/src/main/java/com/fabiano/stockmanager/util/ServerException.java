package com.fabiano.stockmanager.util;

public class ServerException extends RuntimeException {

	public ServerException(String invalidInput) {
		super(invalidInput);
	}

	private static final long serialVersionUID = 1L;

}
