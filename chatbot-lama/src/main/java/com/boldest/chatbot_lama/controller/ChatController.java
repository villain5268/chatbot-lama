package com.boldest.chatbot_lama.controller;

import static com.boldest.chatbot_lama.constant.ApiPath.API_CHAT;
import static com.boldest.chatbot_lama.constant.ApiPath.API_CHAT_MESSAGE;
import static com.boldest.chatbot_lama.constant.ApiPath.API_CHAT_RESET;
import static com.boldest.chatbot_lama.constant.ApiPath.API_CHAT_START;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.boldest.chatbot_lama.dto.ChatRequestDTO;
import com.boldest.chatbot_lama.dto.ChatResponseDTO;
import com.boldest.chatbot_lama.entities.Session;
import com.boldest.chatbot_lama.service.ConversationService;
import com.boldest.chatbot_lama.service.NLPService;

@RestController
@RequestMapping(API_CHAT)
public class ChatController {

	@Autowired
	private ConversationService conversationService;

	@Autowired
	private NLPService nlpService;

	@PostMapping(API_CHAT_START)
	public Session startChat() {
		return conversationService.startChat();
	}

	@PostMapping(API_CHAT_MESSAGE)
	public ChatResponseDTO processMessage(@RequestParam String sessionId, @RequestBody ChatRequestDTO requestDTO) {
		String response = conversationService.processMessage(requestDTO.getMessage(), sessionId);
		ChatResponseDTO responseDTO = new ChatResponseDTO();
		responseDTO.setResponse(response);
		return responseDTO;
	}

	@PostMapping(API_CHAT_RESET)
	public String resetChat(@RequestParam String sessionId) {
		conversationService.resetChat(sessionId);
		return "Chat session reset successfully.";
	}
}
