package com.boldest.chatbot_lama.dto;

import lombok.Data;

@Data
public class ChatResponseDTO {
	private String response;

	// Constructor with a response
	public ChatResponseDTO(String response) {
		this.response = response;
	}

	// Default no-argument constructor
	public ChatResponseDTO() {
	}
}
	