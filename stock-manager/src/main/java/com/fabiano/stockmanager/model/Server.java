package com.fabiano.stockmanager.model;

public class Server {

	String host;
	Integer port;
	
	public Server(String host, Integer port) {
		super();
		this.host = host;
		this.port = port;
	}

	public Server() {
		super();
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

}
