<script>
import { ref } from 'vue';
import { useAuthStore } from '@/stores/auth';

export default {
  setup() {
    const email = ref('');
    const password = ref('');
    const authStore = useAuthStore();

    const register = async () => {
      try {
        await authStore.register(email.value, password.value);
        alert('Registration successful!');
      } catch (error) {
        alert(error.message);
      }
    };

    return {
      email,
      password,
      register,
    };
  },
};
</script>

<template>
  <div>
    <h2>Register</h2>
    <form @submit.prevent="register">
      <div>
        <label for="email">Email:</label>
        <input type="email" v-model="email" required>
      </div>
      <div>
        <label for="password">Password:</label>
        <input type="password" v-model="password" required>
      </div>
      <button type="submit">Register</button>
    </form>
  </div>
</template>

<style scoped>

</style>