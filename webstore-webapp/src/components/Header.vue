<script setup>
import { ref } from 'vue';
import { useAuthStore } from '@/stores/auth';
import Modal from './Modal.vue';
import Login from './Login.vue';
import Register from './Register.vue';
import HeaderMenu from './HeaderMenu.vue';

const isMenuOpen = ref(false);
const showLoginModal = ref(false);
const showRegisterModal = ref(false);

const authStore = useAuthStore();

const toggleMenu = () => {
  isMenuOpen.value = !isMenuOpen.value;
};

const closeMenu = () => {
  isMenuOpen.value = false;
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
</script>

<template>
  <header class="header">
    <nav class="nav">
      <div class="container">
        <div class="left flex">
          <div class="burger" @click="toggleMenu" @keydown.enter="toggleMenu" tabindex="0" aria-label="Toggle menu" role="button">
            <span :class="{ 'burger-line': true, 'open': isMenuOpen }"></span>
            <span :class="{ 'burger-line': true, 'open': isMenuOpen }"></span>
            <span :class="{ 'burger-line': true, 'open': isMenuOpen }"></span>
          </div>
          <router-link to="/" class="logo-link">
            <img src="@/assets/webstore-logo.svg" alt="Webstore Logo" class="logo" />
          </router-link>
        </div>
        <div class="center"></div>
        <div class="right flex">
          <div v-if="!authStore.isAuthenticated">
            <button class="login-btn" @click="openLoginModal">Login</button>
          </div>
          <div v-else>
            <router-link to="/dashboard">
              <button class="user-btn">User Icon</button>
            </router-link>
            <button class="logout-btn" @click="logout">Logout</button>
          </div>
        </div>
      </div>
    </nav>
    <HeaderMenu :isOpen="isMenuOpen" :closeMenu="closeMenu" />
    <Modal v-model="showLoginModal">
      <Login @success="handleSuccess" @register="openRegisterModal" />
    </Modal>
    <Modal v-model="showRegisterModal">
      <Register @success="handleSuccess" />
    </Modal>
  </header>
</template>

<style scoped>
.header {
  background-color: #0d7bf2; /* Light blue color */
  height: 80px; /* Fixed height for the header */
  box-sizing: border-box; /* Ensure padding does not affect the width and height */
  display: flex;
  align-items: center; /* Center content vertically */
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
  margin-left: 1rem;
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
  background-color: #b3e5fc; /* Lighter blue color for hover effect */
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
button {
  border: 2px solid white;
  padding: 0.5rem 1rem;
  margin-left: 0.5rem;
  transition: background-color 0.3s, color 0.3s, border-color 0.3s;
}
</style>