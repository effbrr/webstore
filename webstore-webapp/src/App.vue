<script setup>
import Header from '@/components/header/Header.vue';
import Footer from '@/components/Footer.vue';
</script>

<template>
  <div id="app" class="app-container">
    <Header />
    <div class="content">
      <router-view></router-view>
    </div>
    <Footer />
  </div>
</template>

<style scoped>
.app-container {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}

.content {
  background-color: #e9eeff;
  flex: 1;
}
</style>