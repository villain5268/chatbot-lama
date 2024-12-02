package com.boldest.chatbot_lama.util;

import org.springframework.http.ResponseEntity;

public class ApiResponseUtil {

	public static <T> ResponseEntity<T> successResponse(T data) {
		return ResponseEntity.ok(data);
	}

	public static <T> ResponseEntity<T> errorResponse(T error, int statusCode) {
		return ResponseEntity.status(statusCode).body(error);
	}
}
