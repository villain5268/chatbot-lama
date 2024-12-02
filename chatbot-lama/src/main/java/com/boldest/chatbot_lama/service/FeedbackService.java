package com.boldest.chatbot_lama.service;

import com.boldest.chatbot_lama.dto.FeedbackDTO;

public interface FeedbackService {
    void saveFeedback(FeedbackDTO feedbackDTO);
}
