<script setup>
import { ref } from 'vue';
import { useAuthStore } from '@/stores/auth.js';

const email = ref('');
const password = ref('');
const authStore = useAuthStore();

const emit = defineEmits(['success', 'register']);

const login = async () => {
  try {
    await authStore.login(email.value, password.value);
    emit('success');
  } catch (error) {
    // Handle error
    alert('Login failed: ' + error.message);
  }
};
</script>

<template>
  <div class="login-container">
    <h2>Login</h2>
    <form @submit.prevent="login">
      <div class="form-group">
        <label for="email">Email:</label>
        <input type="email" v-model="email" required />
      </div>
      <div class="form-group">
        <label for="password">Password:</label>
        <input type="password" v-model="password" required />
      </div>
      <button type="submit" class="login-button">Login</button>
    </form>
    <p class="register-text">Don't have an account? <button class="register-link" @click="$emit('register')">Register</button></p>
  </div>
</template>

<style scoped>
.login-container {
  background-color: #fff;
  padding: 2rem;
  border-radius: 10px;
  width: 300px;
  margin: 0 auto;
  text-align: left;
}

h2 {
  margin-bottom: 1.5rem;
  font-size: 1.5rem;
  color: #333;
}

.form-group {
  margin-bottom: 1.5rem;
}

label {
  display: block;
  margin-bottom: 0.5rem;
  font-weight: bold;
}

.login-button {
  display: block;
  width: 100%;
  margin-top: 1rem;
}

.register-text {
  margin-top: 1rem;
  font-size: 0.9rem;
  color: #555;
  text-align: center;
}

.register-link {
  background: none;
  border: none;
  color: #03a9f4;
  font-size: 0.9rem;
  cursor: pointer;
  text-decoration: underline;
  padding: 0;
}

.register-link:hover {
  color: #0288d1;
}
</style>