package com.fabiano.stockquotemanager.dto;

public class ServerDto {
	
	String host;	
	Integer port;

	public ServerDto(String host, Integer port) {
		super();
		this.host = host;
		this.port = port;
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
