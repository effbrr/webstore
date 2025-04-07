<script setup>
import { defineProps, ref } from 'vue';
import { resolveImage } from '@/utils/imageResolver';
import { useCartStore } from '@/stores/cart';
import ItemModal from './ItemModal.vue';

const props = defineProps({
  item: {
    type: Object,
    required: true
  }
});

const cartStore = useCartStore();

const buttonText = ref('Add to cart');
const addedToCart = ref(false);
const showModal = ref(false);

const buyNow = () => {
  cartStore.addItem(props.item);
  cartStore.openCart();
  addedToCart.value = true;
  buttonText.value = 'Item added';

  setTimeout(() => {
    buttonText.value = 'Add to cart';
    addedToCart.value = false;
  }, 2000); // Change text back after 2 seconds
};

const openModal = () => {
  showModal.value = true;
};

const closeModal = () => {
  showModal.value = false;
};
</script>

<template>
  <div class="item-card" @click="openModal">
    <img
        :src="item.imageurl ? item.imageurl : resolveImage(item.categoryId)"
        :alt="item.name || 'Default Image'"
        class="item-image"
    />
    <label class="item-title">{{ item.title }}</label>
    <div class="item-description">{{ item.shortDescription }}</div>
    <div class="item-footer">
      <div class="item-price">{{ item.price.currentPrice }},-</div>
      <button
          @click.stop="buyNow"
          :class="{ 'added': addedToCart }"
          class="buy-now-btn"
      >{{ buttonText }}</button>
    </div>
    <ItemModal :itemId="item.id" :show="showModal" :onClose="closeModal" />
  </div>
</template>

<style scoped>
.item-card {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
  padding: 1rem;
  border: 2px solid black;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  background: white;
  width: 100%;
  max-width: 300px;
  box-sizing: border-box;
}

.item-image {
  width: 100%;
}

.item-title {
  margin-top: 1rem;
}

.item-description {
  font-size: 1rem;
  text-align: center;
  color: #333;
}

.item-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  margin-top: 1rem;
}

.item-price {
  font-size: 1rem;
}

.buy-now-btn {
  padding: 0.5rem 1rem;
  cursor: pointer;
  width: 120px;
}

.buy-now-btn.added {
  background-color: black; /* Green background when added to cart */
  color: white;
}
</style>