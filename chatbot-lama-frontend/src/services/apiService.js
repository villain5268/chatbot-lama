import axios from 'axios';

const BASE_URL = 'http://localhost:8090/api/chat';

const apiService = {
  startSession: async () => {
    const response = await axios.post(`${BASE_URL}/start`);
    return response.data;
  },
  sendMessage: async (sessionId, message) => {
    const response = await axios.post(
      `${BASE_URL}/message?sessionId=${sessionId}`,
      { message }
    );
    return response.data;
  },
  resetSession: async (sessionId) => {
    await axios.post(`${BASE_URL}/reset?sessionId=${sessionId}`);
  },
  submitFeedback: async (sessionId, messageId, feedbackType) => {
    await axios.post(`http://localhost:8090/api/feedback`, {
      sessionId,
      responseId: messageId,
      feedbackType,
    });
  },
};

export default apiService;
