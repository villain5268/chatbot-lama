package com.boldest.chatbot_lama.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boldest.chatbot_lama.dto.ChatRequestDTO;
import com.boldest.chatbot_lama.dto.ChatResponseDTO;
import com.boldest.chatbot_lama.entities.Session;
import com.boldest.chatbot_lama.repository.SessionRepository;
import com.boldest.chatbot_lama.service.ConversationService;
import com.boldest.chatbot_lama.service.NLPService;

@Service
public class ConversationServiceImpl implements ConversationService {

	@Autowired
	private SessionRepository sessionRepository;

	@Autowired
	private NLPService nlpService;

	@Override
	public Session startChat() {
		Session session = Session.builder().sessionId(UUID.randomUUID().toString()).context("").tone("neutral")
				.createdAt(LocalDateTime.now()).updatedAt(LocalDateTime.now()).build();
		return sessionRepository.save(session);
	}

	@Override
	public String processMessage(String userMessage, String sessionId) {
		// Fetch the session from the database
		Session session = sessionRepository.findById(sessionId)
				.orElseThrow(() -> new RuntimeException("Session not found"));

		// Call the NLP service to get the chatbot response
		ChatRequestDTO requestDTO = new ChatRequestDTO();
		requestDTO.setMessage(userMessage);
		ChatResponseDTO response = nlpService.processMessage(requestDTO);

		// Update the context with the user's message and chatbot's response
		String updatedContext = session.getContext() + "\nUser: " + userMessage + "\nChatbot: "
				+ response.getResponse();
		session.setContext(updatedContext);
		session.setUpdatedAt(LocalDateTime.now());

		// Save the updated session
		sessionRepository.save(session);

		return response.getResponse();
	}

	@Override
	public void resetChat(String sessionId) {
		sessionRepository.deleteById(sessionId);
	}
}
