import { createContext, useState, useEffect } from 'react';
import apiService from '../services/apiService';

export const ChatContext = createContext();

const ChatProvider = ({ children }) => {
  const [chatHistory, setChatHistory] = useState([]);
  const [sessionId, setSessionId] = useState(null);
  const [tone, setTone] = useState('neutral');
  const [isBotTyping, setIsBotTyping] = useState(false);

  useEffect(() => {
    initializeSession();

    const handleUnload = async () => {
      if (sessionId) {
        try {
          await apiService.resetSession(sessionId);
        } catch (error) {
          console.error('Error resetting session on tab close:', error);
        }
      }
    };

    window.addEventListener('beforeunload', handleUnload);

    return () => {
      window.removeEventListener('beforeunload', handleUnload);
    };
  }, []);

  const initializeSession = async () => {
    const oldSessionId = localStorage.getItem('chatSessionId');
    if (oldSessionId) {
      try {
        await apiService.resetSession(oldSessionId);
      } catch (error) {
        console.error('Failed to clean up old session:', error.message);
      } finally {
        localStorage.removeItem('chatSessionId');
      }
    }

    try {
      const response = await apiService.startSession();
      setSessionId(response.sessionId);
      localStorage.setItem('chatSessionId', response.sessionId);
      setChatHistory([
        { type: 'bot', text: "Hi! I'm here to assist you. How can I help today?", id: `bot-${Date.now()}` },
      ]);
    } catch (error) {
      setChatHistory([
        { type: 'bot', text: 'Failed to initialize session. Please refresh the page or reset the chat.', id: `bot-error` },
      ]);
    }
  };

  const sendMessage = async (message) => {
    setChatHistory((prev) => [...prev, { type: 'user', text: message, id: `user-${Date.now()}` }]);
    setIsBotTyping(true);

    try {
      const response = await apiService.sendMessage(sessionId, message);
      setChatHistory((prev) => [
        ...prev,
        { type: 'bot', text: response.response, id: response.responseId || `bot-${Date.now()}` },
      ]);
    } catch (error) {
      setChatHistory((prev) => [
        ...prev,
        { type: 'bot', text: 'Sorry, something went wrong.', id: `bot-error-${Date.now()}` },
      ]);
    } finally {
      setIsBotTyping(false);
    }
  };

  const resetSession = async () => {
    try {
      await apiService.resetSession(sessionId);
      initializeSession();
    } catch (error) {
      console.error('Error resetting session:', error.message);
    }
  };

  const submitFeedback = async (messageId, feedbackType) => {
    try {
      await apiService.submitFeedback(sessionId, messageId, feedbackType);
      alert('Feedback submitted! Thank you.');
    } catch (error) {
      alert('Failed to submit feedback. Please try again.');
    }
  };

  return (
    <ChatContext.Provider
      value={{
        chatHistory,
        tone,
        setTone,
        sendMessage,
        resetSession,
        isBotTyping,
        submitFeedback,
      }}
    >
      {children}
    </ChatContext.Provider>
  );
};

export default ChatProvider;
