import { useContext } from 'react';
import Message from './Message';
import { ChatContext } from '../contexts/ChatContext';

const ChatBox = () => {
  const { chatHistory, isBotTyping } = useContext(ChatContext);

  return (
    <div className="chat-history">
      {chatHistory.map((msg) => (
        <Message key={msg.id} message={msg} />
      ))}
      {isBotTyping && (
        <div className="message bot">
          Typing...
        </div>
      )}
    </div>
  );
};

export default ChatBox;
