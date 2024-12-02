package com.boldest.chatbot_lama.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boldest.chatbot_lama.dto.FeedbackDTO;
import com.boldest.chatbot_lama.entities.Feedback;
import com.boldest.chatbot_lama.repository.FeedbackRepository;
import com.boldest.chatbot_lama.service.FeedbackService;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	FeedbackRepository feedbackRepository;

	@Override
	public void saveFeedback(FeedbackDTO feedbackDTO) {
		Feedback feedback = Feedback.builder().sessionId(feedbackDTO.getSessionId())
				.responseId(UUID.randomUUID().toString()).feedbackType(feedbackDTO.getFeedbackType())
				.createdAt(LocalDateTime.now()).build();
		feedbackRepository.save(feedback);
	}
}
