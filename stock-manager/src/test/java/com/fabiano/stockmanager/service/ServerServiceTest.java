package com.fabiano.stockmanager.service;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.fabiano.stockmanager.dto.ServerDto;
import com.fabiano.stockmanager.repository.ServerInMemoryRepository;
import com.fabiano.stockmanager.util.ServerException;

class ServerServiceTest {

	ServerService serverService = new ServerService(new ServerInMemoryRepository());

	@TestConfiguration
	static class ServerServiceTestContextConfiguration {
		@Bean
		public ServerService serverService() {
			return new ServerService(new ServerInMemoryRepository());
		}
	}

	@Test()
	void testSave() throws Exception {
		ServerDto dto = new ServerDto("localhost", 8080);
		ServerDto saved = serverService.save(dto);
		assertNotNull(saved);

		ServerDto dto2 = new ServerDto("localhost", 8080);
		Assertions.assertThrows(ServerException.class, () -> {
			serverService.save(dto2);
		});

		List<ServerDto> findAll = serverService.findAll();
		assertNotNull(findAll);
		assertTrue(findAll.size() == 1);
		
	}

}