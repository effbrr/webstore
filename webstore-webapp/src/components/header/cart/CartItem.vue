<script setup>
import { defineProps, defineEmits } from 'vue';
import { resolveImage } from '@/utils/imageResolver';

const props = defineProps({
  item: Object
});

const emits = defineEmits(['remove']);

const totalItemPrice = (quantity, price) => {
  return (quantity * price).toFixed(2);
}

const removeItem = () => {
  emits('remove', props.item.id);
};
</script>

<template>
  <li class="cart-item" tabindex="0">
    <img
        :src="item.image ? item.image : resolveImage(item.categoryId)"
        :alt="item.name || 'Default Image'"
        class="item-image"
    />
    <div class="item-details">
      <div>
        <label class="item-title">{{ item.title }}</label>
        <p class="item-description">{{ item.shortDescription }}</p>
      </div>
      <div class="flex">
        <p class="item-quantity">{{ item.quantity }} x {{ item.price.currentPrice }}</p>
        <p class="item-total-price">{{totalItemPrice(item.quantity, item.price.currentPrice)}},-</p>
        <button @click="removeItem" class="remove-button" aria-label="Remove item">&times;</button>
      </div>
    </div>
  </li>
</template>

<style scoped>
.cart-item {
  display: flex;
  align-items: center;
  padding: 0.5rem 0;
  border-bottom: 1px solid #ddd;
}

.cart-item:hover {
  background-color: #f0f0f0;
  transform: translateY(-2px);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  cursor: pointer;
}

.cart-item:last-child {
  border-bottom: none;
}

.cart-item:hover .item-image {
  transform: scale(1.1);
}

.item-image {
  width: 50px;
  height: 50px;
  margin-right: 10px;
  border-radius: 5px;
  object-fit: cover;
}

.item-details {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  width: 100%;
}

.item-title {
  font-size: 0.9rem;
  font-weight: bold;
  margin-bottom: 0.2rem;
}

.item-description {
  color: #888;
  font-size: 0.8rem;
}

.item-quantity {
  font-size: 0.9rem;
  margin-right: 1rem;
}

.item-total-price {
  font-size: 0.9rem;
  font-weight: bold;
  width: 3rem;
  text-align: right;
}

.remove-button {
  background: none;
  border: none;
  font-size: 1rem;
  color: black;
  cursor: pointer;
  padding: 0 0.5rem;
  transform: scale(1.3);
  margin-left: 5px;
}

.remove-button:hover {
  transform: scale(1.5);
}
</style>