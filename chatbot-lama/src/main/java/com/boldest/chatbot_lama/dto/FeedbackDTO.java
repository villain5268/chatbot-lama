package com.boldest.chatbot_lama.dto;

import lombok.Data;

@Data
public class FeedbackDTO {
	private String sessionId;
	private String feedbackType; // e.g., THUMBS_UP or THUMBS_DOWN
}
