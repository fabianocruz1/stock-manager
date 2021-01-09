package com.fabiano.stockmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fabiano.stockmanager.dto.ServerDto;

//@Slf4j
@Service
public class NotificationService {
	
	@Autowired 
	private ServerService serverService;
	
	private final RestTemplate restTemplate;

	public NotificationService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}
	
	@Transactional(readOnly = true)
	@Async
	public void notifyHosts() {
		List<ServerDto> servers = serverService.findAll();
		servers.forEach(server -> notify(server));
	}

	private void notify(ServerDto server) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			String url = "http://"+server.getHost()+":"+server.getPort()+"/stockcache";
			System.out.println("notifying "+server.getHost());
			restTemplate.delete(url);
		} catch (Exception e) {
			System.err.println("Error notifing host "+server.getHost()+" "+e.getMessage());
		}
	}
}
