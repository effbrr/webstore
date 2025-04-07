<script setup>
import { defineEmits, defineProps, ref, watch } from 'vue';
import { useCartStore } from '@/stores/cart';
import CartItem from './CartItem.vue';

const props = defineProps({
  modelValue: Boolean,
});

const cartStore = useCartStore();

const emit = defineEmits(['update:modelValue']);

const showCart = ref(props.modelValue);

watch(() => props.modelValue, (newValue) => {
  showCart.value = newValue;
});

const closeCart = () => {
  emit('update:modelValue', false);
};

const removeItemFromCart = (itemId) => {
  cartStore.removeItem(itemId);
};
</script>

<template>
  <div v-if="showCart" class="cart-container">
    <div class="cart-content">
      <div class="cart top">
        <div class="cart-header">
          <h2>Your Cart</h2>
          <button class="close-btn" @click="closeCart">&times;</button>
        </div>
        <ul>
          <CartItem
              v-for="item in cartStore.items"
              :key="item.id"
              :item="item"
              @remove="removeItemFromCart"
          />
        </ul>
      </div>
      <div class="cart-summary">
        <label>Total Price: {{ cartStore.cartTotalPrice.toFixed(2) }},-</label>
        <button class="complete-purchase-btn" disabled>Complete purchase</button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.cart-container {
  position: fixed;
  top: 80px; /* 80px from the top to keep the header visible */
  right: 0;
  width: 500px;
  height: calc(100% - 80px); /* Adjust height to account for the header */
  background: white;
  border-left: 1px solid #ddd; /* Add a border instead of overlay */
  box-shadow: -2px 0 5px rgba(0, 0, 0, 0.5);
  z-index: 1000;
}

.cart-header {
  margin-bottom: 1rem;
}

.cart-content {
  height: 100%;
  padding: 2rem 1rem;
  overflow-y: auto;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.cart-items {

}

.close-btn {
  position: absolute;
  top: 10px;
  right: 20px;
  background: none;
  color: black;
  border: none;
  font-size: 3rem;
  cursor: pointer;
  padding: 0;
}

.cart-summary {
  margin-top: 1rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.complete-purchase-btn {
  cursor: not-allowed;
  margin-left: auto;
}
</style>