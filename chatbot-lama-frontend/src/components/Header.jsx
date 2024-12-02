import { useContext } from 'react';
import { ChatContext } from '../contexts/ChatContext';

const Header = () => {
  const { resetSession, tone, setTone } = useContext(ChatContext);

  return (
    <header>
      <h1>AI Chatbot Llama Model</h1>
      <div className="flex items-center gap-4">
        <select
          value={tone}
          onChange={(e) => setTone(e.target.value)}
          title="Select tone"
        >
          <option value="neutral">Neutral</option>
          <option value="casual">Casual</option>
          <option value="formal">Formal</option>
        </select>
        <button
          onClick={resetSession}
          title="Reset Chat"
        >
          Reset Chat
        </button>
      </div>
    </header>
  );
};

export default Header;
