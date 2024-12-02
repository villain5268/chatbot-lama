package com.boldest.chatbot_lama.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GroqApiConfig {
	@Bean
	public WebClient groqApiClient() {
		return WebClient.builder().baseUrl("https://api.groq.com/openai/v1/chat/completions")
				.defaultHeader("Content-Type", "application/json")
				.defaultHeader("Authorization", "Bearer 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")
				.build();
	}
}
