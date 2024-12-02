import Header from './components/Header';
import ChatBox from './components/ChatBox';
import InputArea from './components/InputArea';
import ChatProvider from './contexts/ChatContext';

const App = () => {
  return (
    <ChatProvider>
      <div className="chat-container">
        <Header />
        <ChatBox />
        <InputArea />
      </div>
    </ChatProvider>
  );
};

export default App;
