package com.boldest.chatbot_lama.controller;

import static com.boldest.chatbot_lama.constant.ApiPath.API;
import static com.boldest.chatbot_lama.constant.ApiPath.API_FEEDBACK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boldest.chatbot_lama.dto.FeedbackDTO;
import com.boldest.chatbot_lama.service.FeedbackService;

@RestController
@RequestMapping(API)
public class FeedbackController {

	@Autowired
	FeedbackService feedbackService;

	@PostMapping(API_FEEDBACK)
	public String submitFeedback(@RequestBody FeedbackDTO feedbackDTO) {
		feedbackService.saveFeedback(feedbackDTO);
		return "Feedback submitted successfully.";
	}
}
