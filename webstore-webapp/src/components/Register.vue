<script setup>
import { ref } from 'vue';
import { useAuthStore } from '@/stores/auth';

const email = ref('');
const password = ref('');
const authStore = useAuthStore();

const emit = defineEmits(['success']);

const register = async () => {
  try {
    await authStore.register(email.value, password.value);
    await authStore.login(email.value, password.value);
    emit('success');
  } catch (error) {
    // Handle error
    alert('Registration failed: ' + error.message);
  }
};
</script>

<template>
  <div class="register-container">
    <h2>Register</h2>
    <form @submit.prevent="register">
      <div class="form-group">
        <label for="email">Email:</label>
        <input type="email" v-model="email" required />
      </div>
      <div class="form-group">
        <label for="password">Password:</label>
        <input type="password" v-model="password" required />
      </div>
      <button type="submit" class="register-button">Register</button>
    </form>
  </div>
</template>

<style scoped>
.register-container {
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
  color: #555;
}

input {
  width: 100%;
  padding: 0.75rem;
  border: 1px solid #ccc;
  border-radius: 5px;
  font-size: 1rem;
}

.register-button {
  display: block;
  width: 100%;
  margin-top: 1rem;
}
</style>