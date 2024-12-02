package com.boldest.chatbot_lama.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "feedback")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Feedback {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "feedback_id", nullable = false, unique = true)
	private Long feedbackId;

	@Column(name = "session_id", nullable = false)
	private String sessionId;

	@Column(name = "response_id")
	private String responseId;

	@Column(name = "feedback_type", nullable = false)
	private String feedbackType;

	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;
}
