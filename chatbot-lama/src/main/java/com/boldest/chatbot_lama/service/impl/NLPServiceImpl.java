package com.boldest.chatbot_lama.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.boldest.chatbot_lama.dto.ChatRequestDTO;
import com.boldest.chatbot_lama.dto.ChatResponseDTO;
import com.boldest.chatbot_lama.service.NLPService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class NLPServiceImpl implements NLPService {

	private final WebClient groqApiClient;

	@Autowired
	public NLPServiceImpl(WebClient groqApiClient) {
		this.groqApiClient = groqApiClient;
	}

	@Override
	public ChatResponseDTO processMessage(ChatRequestDTO requestDTO) {
		String payload = buildRequestPayload(requestDTO.getMessage());

		String apiResponse = groqApiClient.post().bodyValue(payload).retrieve().bodyToMono(String.class).block();

		String parsedResponse = parseApiResponse(apiResponse);

		ChatResponseDTO responseDTO = new ChatResponseDTO();
		responseDTO.setResponse(parsedResponse);

		return responseDTO;
	}

	private String buildRequestPayload(String userMessage) {
		return String.format(
				"{\"model\": \"llama3-8b-8192\", \"messages\": [{\"role\": \"user\", \"content\": \"%s\"}]}",
				userMessage);
	}

	private String parseApiResponse(String apiResponse) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode root = objectMapper.readTree(apiResponse);

			JsonNode choices = root.path("choices");
			if (choices.isArray() && choices.size() > 0) {
				JsonNode messageContent = choices.get(0).path("message").path("content");
				return messageContent.asText();
			}

			return "Unable to parse the response from the chatbot.";
		} catch (Exception e) {
			// Handle JSON parsing exceptions
			e.printStackTrace();
			return "An error occurred while processing the chatbot's response.";
		}
	}
}
