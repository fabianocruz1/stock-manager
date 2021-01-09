package com.fabiano.stockquotemanager.service;

import javax.annotation.PostConstruct;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fabiano.stockquotemanager.dto.ServerDto;

@Service
public class SubscriberService {

	private final RestTemplate restTemplate;

	public SubscriberService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	@PostConstruct
	void postConstruct() {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			String url = "http://localhost:8080/notification";
			System.out.println("subscribing ..."+url);
			 ServerDto dto = new ServerDto("localhost", 8081);
			restTemplate.postForEntity(url, new HttpEntity<>(dto, headers), Void.class);
		} catch (Exception e) {
			System.err.println("Error subscribing "+e.getMessage());
		}
	}
}
