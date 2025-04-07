<script setup>
import { defineProps, ref, watch } from 'vue';
import { useItemsStore } from '@/stores/items';

const props = defineProps({
  itemId: {
    type: Number,
    required: true
  },
  show: {
    type: Boolean,
    required: true
  },
  onClose: {
    type: Function,
    required: true
  }
});

const itemsStore = useItemsStore();
const item = ref(null);

watch(() => props.itemId, async (newItemId) => {
  if (newItemId) {
    await itemsStore.fetchItemById(newItemId);
    item.value = itemsStore.item;
  }
});

const closeModal = () => {
  props.onClose();
};

</script>

<template>
  <div v-if="show" class="modal-overlay" @click.self="closeModal">
    <div class="modal-content">
      <button class="close-btn" @click="closeModal">&times;</button>
      <div v-if="item" class="item-details">
        <img :src="item.imageurl ? item.imageurl : resolveImage(item.categoryId)" :alt="item.name || 'Default Image'" class="item-image" />
        <h2>{{ item.title }}</h2>
        <p>{{ item.description }}</p>
        <p>Price: {{ item.price.currentPrice }},-</p>
      </div>
      <div v-else>Loading...</div>
    </div>
  </div>
</template>

<style scoped>
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.modal-content {
  background: white;
  padding: 2rem;
  border-radius: 8px;
  width: 90%;
  max-width: 600px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  position: relative;
}

.close-btn {
  position: absolute;
  top: 10px;
  right: 10px;
  background: none;
  border: none;
  font-size: 1.5rem;
  cursor: pointer;
}

.item-details {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.item-image {
  width: 100%;
  max-width: 300px;
}
</style>