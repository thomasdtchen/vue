<template>
  <div class="chat-container">
    <div class="chat-messages" ref="chatMessages">
      <div v-for="(message, index) in messages" :key="index" :class="message.user === 'ai' ? message.class : 'user-bar'">
        <div v-if="message.user === 'ai'" class="ai-response-frame">
          <div class="plain-text-response">{{ message.text }}</div>
          <button v-if="message.user === 'ai'" @click="copyToClipboard(message.text)">Copy</button>
        </div>
        <div v-else v-html="message.text"></div>
      </div>
    </div>

    <div class="chat-input">
      <input v-model="userMessage" @keydown.enter.prevent="sendMessage" v-focus/>
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
      copiedUserMessage: '',
      messages: [],
      apiKey: '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', // Replace with your ChatGPT API key
    };
  },
  directives: {
    focus: {
      // When the bound element is inserted into the DOM...
      inserted: function (el) {
        // Focus the element
        el.focus();
      },
    },
  },
  methods: {
    async sendMessage() {
      if (this.userMessage.trim() === '') return;
      this.copiedUserMessage = this.userMessage
      this.userMessage = '';
      console.log("copiedUserMessage: " + this.copiedUserMessage)
      console.log("userMessage: " + this.userMessage)
      this.messages.push({ text: this.copiedUserMessage, user: 'user' });
      this.scrollToBottom();
      const response = await this.sendRequestToChatGPT(this.copiedUserMessage);
      this.copiedUserMessage = '';
      this.messages.push({ text: this.formatResponse(response.data.choices[0].message.content), user: 'ai', class: 'ai-response' });
      this.scrollToBottom();
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
    // Function to format ChatGPT response
    formatResponse(response) {
      return response;
    },

    copyToClipboard(text) {
      const el = document.createElement('textarea');
      el.value = text;
      document.body.appendChild(el);
      el.select();
      document.execCommand('copy');
      document.body.removeChild(el);
    }
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

.ai-response-frame {
  border: 1px solid #e0e0e0;
  background-color: #f9f9f9;
  padding: 10px;
  border-radius: 5px;
  margin: 10px 0;
  position: relative;
}

.ai-response-frame button {
  position: absolute;
  top: 1px;
  right: 1px;
  background-color: #007AFF;
  color: #fff;
  border: none;
  border-radius: 5px;
  padding: 5px 10px;
  cursor: pointer;
}

.plain-text-response {
  font-family: monospace; /* Use a monospace font for plain text appearance */
  white-space: pre-wrap; /* Preserve line breaks and spaces */
  word-break: break-word; /* Allow long words to break onto the next line */
  font-size: 14px;
  text-align: left; /* Set text alignment to left */
}

.user-bar {
  /* background-color: #e0e0e0; Background color for the user's input bar */
  background-color: #007AFF;
  color: #fff;
  padding: 10px;
  border-radius: 5px;
  margin: 10px 0;
  text-align: left; /* Align text to the left within the user's input bar */
}




</style>
