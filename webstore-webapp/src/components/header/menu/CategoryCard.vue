<script setup>
import { defineProps } from 'vue';
import { useRouter } from 'vue-router';
import { resolveImage } from '@/utils/imageResolver';

const props = defineProps({
  category: {
    type: Object,
    required: true
  }
});

const router = useRouter();

const shopNow = (category) => {
  if (category.id === 0) {
    router.push('/shop');
  } else {
    router.push(`/shop/${category.title}`);
  }
};
</script>

<template>
  <div class="category-card">
    <img :src="resolveImage(category.id)" alt="Category Image" class="category-image" />
    <label class="category-description">{{ category.title }}</label>
    <button @click="shopNow(category)" class="shop-now-btn">Shop Now</button>
  </div>
</template>

<style scoped>
.category-card {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 1rem;
  border: 4px solid black;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  background: white;
  margin: 1rem;
  width: 200px;
}

.category-image {
  width: 100%;
  height: auto;
  border-radius: 8px;
}

.category-description {
  margin: 1rem 0;
  text-align: center;
}

</style>