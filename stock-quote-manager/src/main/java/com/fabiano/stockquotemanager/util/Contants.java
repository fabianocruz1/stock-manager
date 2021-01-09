package com.fabiano.stockquotemanager.util;

public class Contants {

	public static final String START_LIST = "{";
	public static final String END_OF_LIST = "}";
	public static final String LIST_SEPARATOR = ",";
	public static final String ID_TOKEN = "id";
	public static final String QUOTES_TOKEN = "quotes";
	public static final String INVALID_INPUT = "Invalid input";
	public static final String QUOTES = "\"";
	public static final String KEY_VALUE_SEPARATOR = ":";
	public static final String NEW_LINE = System.lineSeparator();
	public static final String DTO_FORMAT = START_LIST + NEW_LINE + 
			QUOTES + ID_TOKEN + QUOTES + KEY_VALUE_SEPARATOR + 
			QUOTES + "%s" +QUOTES + 
			LIST_SEPARATOR + NEW_LINE +
			QUOTES + QUOTES_TOKEN + QUOTES + KEY_VALUE_SEPARATOR + NEW_LINE +
			START_LIST + NEW_LINE +
			 "%s" +
			 NEW_LINE + END_OF_LIST + 
			 NEW_LINE + END_OF_LIST;

}
