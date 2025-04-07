<script setup>
import { defineProps } from 'vue';
import { resolveImage } from '@/utils/imageResolver';
import { useCartStore } from '@/stores/cart';
import cartIcon from '@/assets/cart-buy.svg';

const props = defineProps({
  item: Object
});

const cartStore = useCartStore();
const buyNow = () => {
  cartStore.addItem(props.item);
  cartStore.openCart();
};
</script>

<template>
  <li class="result-item" tabindex="0">
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
        <p class="item-price">{{ item.price.currentPrice }},-</p>
        <img
            :src="cartIcon"
            alt="Cart"
            class="icon"
            @click="buyNow"
        />
      </div>
    </div>
  </li>
</template>

<style scoped>
.result-item {
  display: flex;
  align-items: center;
  padding: .3rem;
  border-bottom: 1px solid #ddd;
}

.result-item:hover {
  background-color: #f0f0f0;
  transform: translateY(-2px);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  cursor: pointer;
}

.result-item:last-child {
  border-bottom: none;
}

.result-item:hover .item-image {
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

.item-price {
  align-content: center;
  font-size: 0.9rem;
  font-weight: bold;
}

.icon {
  width: 30px;
  height: 30px;
  margin: auto .2rem;
  cursor: pointer;
  transition: transform 0.3s;
}

.icon:hover {
  transform: scale(1.1);
}
</style>