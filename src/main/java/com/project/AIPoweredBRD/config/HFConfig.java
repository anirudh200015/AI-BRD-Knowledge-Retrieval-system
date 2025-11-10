package com.project.AIPoweredBRD.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class HFConfig {
	
	@Value("${huggingface.token}")
	private String HFToken;
	
	@Bean
	public WebClient HFWebclient() {
		
		return WebClient.builder()
				.baseUrl("https://api-inference.huggingface.co/models/sentence-transformers/all-MiniLM-L6-v2")
				.defaultHeader("Authorization","Bearer "+HFToken)
				.build();
		
	}
	
}
