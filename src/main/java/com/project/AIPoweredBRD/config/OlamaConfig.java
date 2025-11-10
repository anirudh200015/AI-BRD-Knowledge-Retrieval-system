package com.project.AIPoweredBRD.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class OlamaConfig {

	@Bean
	public WebClient OlamaWebclient() {
		
		return WebClient.builder()
				.baseUrl("http://localhost:11434")
				.build();
		
	}
	
}
