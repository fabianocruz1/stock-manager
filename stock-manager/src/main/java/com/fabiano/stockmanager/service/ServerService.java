package com.fabiano.stockmanager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fabiano.stockmanager.dto.ServerDto;
import com.fabiano.stockmanager.dto.StockDto;
import com.fabiano.stockmanager.model.Server;
import com.fabiano.stockmanager.model.Stock;
import com.fabiano.stockmanager.repository.ServerRepository;
import com.fabiano.stockmanager.util.StockException;

@Service
public class ServerService {

	@Autowired
	ServerRepository serverRepository;
	
	@Transactional
	public ServerDto save(ServerDto dto) {
		final String host = dto.getHost();

		if (Objects.nonNull(serverRepository.findByHost(host))) throw new StockException("Server host already registered.");

		Server server = new Server(dto.getHost(), dto.getPort());
		Server save = serverRepository.save(server);
		return new ServerDto(save.getHost(), save.getPort());
	}

	@Transactional(readOnly = true)
	public List<ServerDto> findAll() {
		Iterable<Server> stocks = serverRepository.findAll();
		List<ServerDto> list = new ArrayList<>();
		stocks.forEach(s -> list.add(new ServerDto(s.getHost(), s.getPort())));
		return list;
	}

}
