package com.fabiano.stockmanager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fabiano.stockmanager.dto.ServerDto;
import com.fabiano.stockmanager.model.Server;
import com.fabiano.stockmanager.repository.ServerInMemoryRepository;
import com.fabiano.stockmanager.repository.ServerRepository;
import com.fabiano.stockmanager.util.ServerException;

@Service
public class ServerService {

	ServerRepository serverRepository;
	
	public ServerService(ServerInMemoryRepository serverRepository) {
		this.serverRepository = serverRepository;
	}
	
	@Transactional
	public ServerDto save(ServerDto dto) {
		final String host = dto.getHost();

		if (Objects.nonNull(serverRepository.findByHost(host))) throw new ServerException("Server host already registered.");

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
