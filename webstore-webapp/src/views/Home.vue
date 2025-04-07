<script setup>
import { useItemsStore } from '@/stores/items';
import { ref, computed } from 'vue';

const itemsStore = useItemsStore();

const items = computed(() => itemsStore.items);

</script>

<template>
  <div class="container">
    <h1>Home</h1>

  </div>
</template>

<style scoped>
</style>