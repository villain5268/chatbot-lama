import React from 'react';
import { ChatContext } from '../contexts/ChatContext';
import PropTypes from 'prop-types';


const Message = ({ message }) => {
  const { submitFeedback } = React.useContext(ChatContext);

  const isUser = message.type === 'user';

  return (
    <div
      className={`message ${isUser ? 'user' : 'bot'}`}
      style={{ position: 'relative' }}
    >
      {message.text}
      {!isUser && (
        <div className="feedback-buttons" style={{ marginTop: '0.5rem' }}>
          <button
            onClick={() => submitFeedback(message.id, 'THUMBS_UP')}
            className="feedback-button"
            style={{
              color: 'green',
              background: 'transparent',
              border: 'none',
              cursor: 'pointer',
              marginRight: '0.5rem',
            }}
          >
            👍
          </button>
          <button
            onClick={() => submitFeedback(message.id, 'THUMBS_DOWN')}
            className="feedback-button"
            style={{
              color: 'red',
              background: 'transparent',
              border: 'none',
              cursor: 'pointer',
            }}
          >
            👎
          </button>
        </div>
      )}
    </div>
  );
};

Message.propTypes = {
  message: PropTypes.shape({
    type: PropTypes.string.isRequired,
    text: PropTypes.string.isRequired,
  }).isRequired,
};

export default Message;
