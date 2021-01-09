package com.fabiano.stockmanager.repository;

import org.springframework.data.repository.CrudRepository;

import com.fabiano.stockmanager.model.Server;

public interface ServerRepository extends CrudRepository<Server, Long> {

	Server findByHost(String host);

}
