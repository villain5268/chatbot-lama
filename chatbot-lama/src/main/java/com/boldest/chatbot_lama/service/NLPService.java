package com.boldest.chatbot_lama.service;

import com.boldest.chatbot_lama.dto.ChatRequestDTO;
import com.boldest.chatbot_lama.dto.ChatResponseDTO;

public interface NLPService {
	ChatResponseDTO processMessage(ChatRequestDTO requestDTO);
}