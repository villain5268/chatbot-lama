package com.boldest.chatbot_lama.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**") // Allow all endpoints
						.allowedOrigins("http://127.0.0.1:5173") // Allow your frontend origin
						.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allowed HTTP methods
						.allowedHeaders("*") // Allow all headers
						.allowCredentials(true); // Allow cookies if needed
			}
		};
	}
}
