package com.fabiano.stockmanager.model;

public class Stock {

	String name;
	String description;
	
	public Stock(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public Stock() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
