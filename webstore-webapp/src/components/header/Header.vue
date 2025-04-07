<script setup>
import { ref } from 'vue';
import { useAuthStore } from '@/stores/auth.js';
import { useCartStore } from '@/stores/cart';
import { useRouter} from "vue-router";
import Modal from '../Modal.vue';
import Login from './Login.vue';
import Register from './Register.vue';
import HeaderMenu from './menu/HeaderMenu.vue';
import SearchBar from './search/SearchBar.vue';
import Cart from './cart/Cart.vue';
import profileIcon from '@/assets/profile.svg';
import cartIcon from '@/assets/cart.svg';
import gymstoreLogo from '@/assets/gymstore-logo.svg';

const isMenuOpen = ref(false);
const showLoginModal = ref(false);
const showRegisterModal = ref(false);
const showCart = ref(false);

const authStore = useAuthStore();
const cartStore = useCartStore();
const router = useRouter();

const toggleMenu = () => {
  isMenuOpen.value = !isMenuOpen.value;
};

const closeMenu = () => {
  isMenuOpen.value = false;
};

const openMenu = () => {
  isMenuOpen.value = true;
};

const openLoginModal = () => {
  showLoginModal.value = true;
};

const openRegisterModal = () => {
  showRegisterModal.value = true;
  showLoginModal.value = false;
};

const closeModals = () => {
  showLoginModal.value = false;
  showRegisterModal.value = false;
};

const handleSuccess = () => {
  closeModals();
};

const logout = () => {
  authStore.logout();
};

const handleProfileClick = () => {
  closeMenu();
  if (!authStore.isAuthenticated) {
    openLoginModal();
  } else {
    router.push("/dashboard");
  }
};

const closeCart = () => {
  showCart.value = false;
}

const toggleCart = () => {
  closeMenu();
  cartStore.toggleCart()
};

const onItemAddedToCart = () => {
  showCart.value = true;
};
</script>

<template>
  <header class="header" @mouseleave="closeMenu">
    <nav class="nav">
      <div class="container">
        <div class="left flex">
          <div class="burger" @mouseover="openMenu" @click="toggleMenu" @keydown.enter="toggleMenu" tabindex="0" aria-label="Toggle menu" role="button">
            <span :class="{ 'burger-line': true, 'open': isMenuOpen }"></span>
            <span :class="{ 'burger-line': true, 'open': isMenuOpen }"></span>
            <span :class="{ 'burger-line': true, 'open': isMenuOpen }"></span>
          </div>
          <router-link to="/" class="logo-link" @click="closeMenu">
            <img :src=gymstoreLogo alt="Webstore Logo" class="logo" />
          </router-link>
          <SearchBar @click="closeMenu" />
        </div>
        <div class="right flex">
          <img
              :src="profileIcon"
              alt="Profile"
              class="icon"
              @click="handleProfileClick"

          />
          <img
              :src="cartIcon"
              alt="Cart"
              class="icon"
              @click="toggleCart"
          />
        </div>
      </div>
    </nav>
    <HeaderMenu :isOpen="isMenuOpen" :closeMenu="closeMenu" />
    <Modal v-model="showLoginModal">
      <Login @success="handleSuccess" @register="openRegisterModal" />
    </Modal>
    <Modal v-model="showRegisterModal" @click="closeMenu">
      <Register @success="handleSuccess" />
    </Modal>
    <Cart v-model="cartStore.isOpen" @click="closeMenu"/>
  </header>
</template>

<style scoped>
.header {
  background-color: #1d1f21;
  height: 80px;
  box-sizing: border-box;
  display: flex;
  align-items: center;
  position: relative;
}
.nav {
  display: flex;
  justify-content: space-between;
  align-items: center;
  position: relative;
  width: 100%;
}
.container {
  flex-direction: row;
  justify-content: space-between;
}

.logo {
  height: 40px;
  margin-left: 1.5rem;
}

.logo-link {
  height: 40px;
}
.burger {
  display: flex;
  flex-direction: column;
  cursor: pointer;
  align-items: flex-end;
  margin: auto 0;
  width: 30px;
}
.burger:hover .burger-line {
  transform: scale(1.1);
}
.burger-line {
  width: 30px;
  height: 4px;
  background-color: white;
  margin: 4px 0;
  transition: transform 0.4s, opacity 0.4s, background-color 0.2s;
}

.burger-line.open {
  width: 37px;
  margin-left: -3px;
}

.burger-line.open:nth-child(1) {
  transform: translateY(12px) rotate(45deg);
}
.burger-line.open:nth-child(2) {
  opacity: 0;
}
.burger-line.open:nth-child(3) {
  transform: translateY(-12px) rotate(-45deg);
}

.icon {
  width: 40px;
  height: 40px;
  margin-left: 1rem;
  cursor: pointer;
  transition: transform 0.3s;
}

.icon:hover {
  transform: scale(1.1);
}

@media (max-width: 768px) {
  .logo {
    content: url('@/assets/gymstore-logo-mobile.svg');
  }
}
</style>