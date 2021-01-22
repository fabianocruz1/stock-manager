package com.fabiano.stockmanager.repository;

import java.util.Collection;

import com.fabiano.stockmanager.model.Server;

public interface ServerRepository {

	Server findByHost(String host);

	Server save(Server server);

	Collection<Server> findAll();

}
