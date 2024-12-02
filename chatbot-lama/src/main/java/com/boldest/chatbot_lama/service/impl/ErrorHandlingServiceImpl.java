package com.boldest.chatbot_lama.service.impl;

import org.springframework.stereotype.Service;

import com.boldest.chatbot_lama.service.ErrorHandlingService;

@Service
public class ErrorHandlingServiceImpl implements ErrorHandlingService {

	@Override
	public String getFallbackMessage() {
		return "I'm sorry, I couldn't process that. Could you try rephrasing?";
	}
}
