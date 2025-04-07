<script setup>
import { onMounted, computed } from 'vue';
import { useRoute } from 'vue-router';
import { useItemsStore } from '@/stores/items';
import { useItemCategoriesStore } from '@/stores/itemCategories.js';
import ItemCard from '@/components/item/ItemCard.vue';

const itemsStore = useItemsStore();
const itemsCategoriesStore = useItemCategoriesStore();
const route = useRoute();

onMounted(() => {
  itemsCategoriesStore.fetchItemCategories();
  itemsStore.fetchItems();
});

const categoryTitle = computed(() => route.params.categoryTitle ? route.params.categoryTitle.toLowerCase() : null);
const categoryId = computed(() => {
  if (!categoryTitle.value) return null;
  const category = itemsCategoriesStore.itemCategories.find(category => category.title.toLowerCase() === categoryTitle.value);
  return category ? category.id : null;
});

const filteredItems = computed(() => {
  if (categoryId.value === 0 || categoryTitle.value === null) {
    return itemsStore.items;
  }
  if (categoryId.value !== null) {
    return itemsStore.items.filter(item => item.categoryId === categoryId.value);
  }
  return itemsStore.items;
});
</script>

<template>
  <div class="shop">
    <div class="item-cards">
      <ItemCard
          v-for="item in filteredItems"
          :key="item.id"
          :item="item"
      />
    </div>
  </div>
</template>

<style scoped>
.shop {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 2rem;
}

.item-cards {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(220px, 1fr));
  gap: 1rem;
  width: 100%;
  max-width: 1200px;
  margin: 0 auto;
}
</style>