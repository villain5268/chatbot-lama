package com.boldest.chatbot_lama.constant;

public interface ApiPath {

	// Base path for all APIs
	String API = "/api";

	// Chat-related paths
	String API_CHAT = API + "/chat";
	String API_CHAT_START = "/start";
	String API_CHAT_MESSAGE = "/message";
	String API_CHAT_RESET = "/reset";

	// Feedback-related paths
	public static final String API_FEEDBACK = "/feedback";

}
