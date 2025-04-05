<script setup>
import { ref } from 'vue';

const welcomeMessage = ref('Welcome to your Dashboard!');
</script>

<template>
  <div class="dashboard">
    <h1>{{ welcomeMessage }}</h1>
    <!-- Add more dashboard content here -->
  </div>
</template>

<style scoped>
.dashboard {
  padding: 2rem;
  text-align: center;
}
</style>