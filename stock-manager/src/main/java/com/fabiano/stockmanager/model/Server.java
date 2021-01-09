package com.fabiano.stockmanager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table()
@Entity
public class Server {

	@Id 
	@GeneratedValue
	Long idServer;
	
	@Column(unique=true)
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

	public Long getIdServer() {
		return idServer;
	}

	public void setIdServer(Long idServer) {
		this.idServer = idServer;
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
