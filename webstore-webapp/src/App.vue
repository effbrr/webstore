<script setup>
import Header from './components/Header.vue';
</script>

<template>
  <div id="app">
    <Header />
    <div class="content">
      <router-view></router-view>
    </div>
  </div>
</template>

<style scoped>

</style>