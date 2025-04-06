<script setup>
import { ref } from 'vue';
import { useItemsStore } from '@/stores/items';

const itemsStore = useItemsStore();
const searchQuery = ref(itemsStore.searchQuery);

const onInput = () => {
  itemsStore.setSearchQuery(searchQuery.value);
};
</script>

<template>
  <div class="search-bar">
    <input type="text" placeholder="Search..." v-model="searchQuery" @input="onInput" />
  </div>
</template>

<style scoped>
.search-bar {
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 0 1.5rem;
  width: 40vw;
  max-width: 500px;
}

.search-bar input {
  padding: 0.5rem;
  border: 1px solid #ccc;
  border-radius: 5px;
  width: 100%;
  height: 40px;
}
</style>