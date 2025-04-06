<script setup>
import { defineProps, onMounted, onBeforeUnmount } from 'vue';

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

const handleClickOutside = (event) => {
  const menuElement = document.querySelector('.menu');
  const burgerElement = document.querySelector('.burger');
  if (
      menuElement &&
      !menuElement.contains(event.target) &&
      burgerElement &&
      !burgerElement.contains(event.target)
  ) {
    props.closeMenu();
  }
};

onMounted(() => {
  document.addEventListener('mousedown', handleClickOutside);
});

onBeforeUnmount(() => {
  document.removeEventListener('mousedown', handleClickOutside);
});
</script>

<template>
  <div :class="{ 'menu': true, 'open': isOpen }">
    <ul>
      <li><router-link to="/" @click="closeMenu">Home</router-link></li>
      <li><router-link to="/about" @click="closeMenu">About</router-link></li>
      <li><router-link to="/contact" @click="closeMenu">Contact</router-link></li>
    </ul>
  </div>
</template>

<style scoped>
.menu {
  display: none;
  position: absolute;
  top: 80px;
  left: 0;
  width: 100%;
  background-color: white;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  z-index: 1000;
}
.menu.open {
  display: block;
}
.menu ul {
  list-style: none;
  padding: 1rem;
  margin: 0;
}
.menu li {
  margin: 0;
  padding: 0.5rem 0;
}
.menu a {
  color: #333;
  text-decoration: none;
  font-weight: bold;
}
.menu a:hover {
  color: #0d7bf2;
}

@media (max-width: 768px) {
  .menu {
    display: none;
    flex-direction: column;
  }
  .menu.open {
    display: block;
  }
}
</style>