package com.fabiano.stockmanager.repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.fabiano.stockmanager.model.Server;

@Component
public class ServerInMemoryRepository implements ServerRepository {

	private ConcurrentHashMap<String, Server> servers; 
	
	@Override
	public Server findByHost(String host) {
		Server server = servers.get(host);
		return server;
	}

	@Override
	public Server save(Server server) {
		servers.put(server.getHost(), server);
		return server;
	}

	@Override
	public Collection<Server> findAll() {
		return servers.values();
	}

}
