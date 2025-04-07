<script setup>
import { defineProps} from 'vue';
import { useItemCategoriesStore } from '@/stores/itemCategories';
import CategoryCard from './CategoryCard.vue';

const props = defineProps({
  isOpen: {
    type: Boolean,
    required: true
  },
  closeMenu: {
    type: Function,
    required: true
  }
});

const itemCategoriesStore = useItemCategoriesStore();

</script>

<template>
  <div :class="{ 'menu': true, 'open': isOpen }">
    <div class="container">
      <div class="category-cards">
        <CategoryCard :key="0" :category="{id: 0, title: 'Shop All'}" @click="closeMenu" />
        <CategoryCard v-for="category in itemCategoriesStore.itemCategories" :key="category.id" :category="category" @click="closeMenu" />
      </div>
    </div>
  </div>
</template>

<style scoped>
.menu {
  display: none;
  position: absolute;
  top: 80px;
  left: 0;
  width: 100%;
  background-color: #1d1f21;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  z-index: 1000;
}

.menu.open {
  display: flex;
  justify-content: center;
}

.category-cards {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}

@media (max-width: 768px) {
  .menu {
    display: none;
    flex-direction: column;
  }
  .menu.open {
    display: flex;
    flex-direction: column;
    align-items: center;
  }
}
</style>