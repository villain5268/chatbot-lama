package com.boldest.chatbot_lama.service;

import com.boldest.chatbot_lama.entities.Session;

public interface ConversationService {

	Session startChat();

	String processMessage(String userMessage, String sessionId);

	void resetChat(String sessionId);

}
