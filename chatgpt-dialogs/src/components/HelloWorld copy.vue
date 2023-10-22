<template>
  <div class="chat-container">
    <div class="chat-messages" ref="chatMessages">
      <div v-for="(message, index) in messages" :key="index" class="chat-message" :class="{ 'user-message': message.user === 'user', 'ai-message': message.user === 'ai' }">
        <div v-html="message.text"></div>
      </div>
    </div>
    <div class="chat-input">
      <input v-model="userMessage" @keydown.enter.prevent="sendMessage" placeholder="Type your message..." />
      <button @click="sendMessage">Send</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      userMessage: '',
      messages: [],
      apiKey: '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', // Replace with your ChatGPT API key
    };
  },
  methods: {
    async sendMessage() {
      if (this.userMessage.trim() === '') return;
      this.messages.push({ text: this.userMessage, user: 'user' });
      this.scrollToBottom();

      const response = await this.sendRequestToChatGPT(this.userMessage);
      this.messages.push({ text: response.data.choices[0].text, user: 'ai' });
      this.scrollToBottom();

      this.userMessage = '';
    },
    sendRequestToChatGPT(message) {
      return axios.post('https://api.openai.com/v1/chat/completions', {
        model: 'gpt-3.5-turbo',
        messages: [
          { role: 'system', content: 'You are a helpful assistant.' },
          { role: 'user', content: message },
        ],
      }, {
        headers: {
          Authorization: `Bearer ${this.apiKey}`,
        },
      });
    },
    scrollToBottom() {
      this.$nextTick(() => {
        const chatMessages = this.$refs.chatMessages;
        chatMessages.scrollTop = chatMessages.scrollHeight;
      });
    },
  },
};
</script>

<style scoped>
.chat-container {
  display: flex;
  flex-direction: column;
  height: 100vh;
  padding: 10px;
}

.chat-messages {
  flex: 1;
  overflow-y: auto;
  padding: 10px;
}

.chat-message {
  max-width: 70%;
  padding: 8px;
  margin: 8px;
  border-radius: 8px;
  word-break: break-word;
}

.user-message {
  background-color: #007AFF;
  color: #fff;
  align-self: flex-end;
}

.ai-message {
  background-color: #E1E1E1;
  color: #000;
  align-self: flex-start;
}

.chat-input {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px;
  background-color: #f2f2f2;
}

input {
  flex: 1;
  padding: 10px;
  border: none;
  border-radius: 20px;
  margin-right: 10px;
}

button {
  padding: 10px 20px;
  background-color: #007AFF;
  color: #fff;
  border: none;
  border-radius: 20px;
  cursor: pointer;
}
</style>
