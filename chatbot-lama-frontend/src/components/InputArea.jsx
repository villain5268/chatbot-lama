import { useState, useContext } from 'react';
import { ChatContext } from '../contexts/ChatContext';

const InputArea = () => {
  const [input, setInput] = useState('');
  const { sendMessage, isBotTyping } = useContext(ChatContext);

  const handleSubmit = () => {
    if (input.trim() && !isBotTyping) {
      sendMessage(input);
      setInput('');
    }
  };

  return (
    <div className="input-area">
      <input
        type="text"
        value={input}
        onChange={(e) => setInput(e.target.value)}
        onKeyPress={(e) => e.key === 'Enter' && handleSubmit()}
        placeholder="Type your message..."
        className="input-field"
      />
      <button onClick={handleSubmit} className="input-button">
        Send
      </button>
    </div>
  );
};

export default InputArea;
