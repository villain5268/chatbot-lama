package com.boldest.chatbot_lama.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	private static final ObjectMapper objectMapper = new ObjectMapper();

	public static String toJson(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Error converting object to JSON", e);
		}
	}
}
