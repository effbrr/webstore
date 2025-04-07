<script setup>
import { computed, defineProps } from 'vue';
import { useItemsStore } from '@/stores/items';
import SearchResultItem from './SearchResultItem.vue';

const props = defineProps({
  searchValue: String,
});

const itemsStore = useItemsStore();

const filteredItems = computed(() => {
  return itemsStore.getFilteredItems();
});
</script>

<template>
  <div class="search-results">
    <template v-if="filteredItems.length > 0">
      <ul class="results-list">
        <SearchResultItem v-for="item in filteredItems" :key="item.id" :item="item" />
      </ul>
    </template>
    <template v-else-if="searchValue">
      <div class="no-results">No results found</div>
    </template>
  </div>
</template>

<style scoped>
.search-results {
  position: absolute;
  top: 100%;
  left: 0;
  right: 0;
  z-index: 10;
  background: white;
  border: 1px solid #ddd;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  max-width: 600px;
  margin: 0 1.5rem;
  max-height: 304px; /* Adjust height to show max 5 items */
  overflow-y: auto; /* Enable vertical scrolling */
}

.results-list {
  list-style: none;
  padding: 0;
  margin: 0;
}

.no-results {
  padding: 1rem;
  text-align: center;
  color: #777;
}
</style>