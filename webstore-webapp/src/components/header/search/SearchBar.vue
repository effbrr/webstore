<script setup>
import { ref, onMounted, onUnmounted } from 'vue';
import { useItemsStore } from '@/stores/items';
import SearchResults from './SearchResult.vue';
import searchIcon from '@/assets/search-icon.svg';

const itemsStore = useItemsStore();
const searchQuery = ref(itemsStore.searchQuery);
const isActive = ref(false);

const onInput = () => {
  itemsStore.setSearchQuery(searchQuery.value);
};

const onFocus = () => {
  isActive.value = true;
};

const handleClickOutside = (event) => {
  const searchBar = document.querySelector('.search-bar-container');
  if (searchBar && !searchBar.contains(event.target)) {
    isActive.value = false;
  }
};

onMounted(() => {
  document.addEventListener('click', handleClickOutside);
});

onUnmounted(() => {
  document.removeEventListener('click', handleClickOutside);
});

</script>

<template>
  <div class="search-bar-container">
    <div class="search-bar">
      <input
          type="text"
          placeholder="Search..."
          v-model="searchQuery"
          @input="onInput"
          @focus="onFocus"
      />
      <img :src="searchIcon" alt="Search Icon" class="search-icon" />
    </div>
    <SearchResults v-if="isActive" :searchValue="searchQuery"/>
  </div>
</template>

<style scoped>
.search-bar-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  position: relative;
}

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
  width: 100%;
  height: 40px;
}

.search-icon {
  position: absolute;
  right: 2rem;
  width: 25px;
  height: 25px;
  pointer-events: none; /* Prevent click events */
}

@media (max-width: 768px) {
  .search-bar {
    width: 50vw;
  }
}

@media (max-width: 600px) {
  .search-bar {
    width: 40vw;
  }
}
</style>