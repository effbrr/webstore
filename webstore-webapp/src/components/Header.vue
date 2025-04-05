<script setup>
import { ref } from 'vue';
import { useAuthStore } from '@/stores/auth';
import Modal from './Modal.vue';
import Login from './Login.vue';
import Register from './Register.vue';

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

const logout = () => {
  authStore.logout();
};
</script>

<template>
  <header class="header">
    <nav class="nav">
      <div class="container">
        <div class="burger" @click="toggleMenu">
          <span :class="{ 'burger-line': true, 'open': isMenuOpen }"></span>
          <span :class="{ 'burger-line': true, 'open': isMenuOpen }"></span>
          <span :class="{ 'burger-line': true, 'open': isMenuOpen }"></span>
        </div>
        <ul :class="{ 'menu': true, 'open': isMenuOpen }">
          <li><router-link to="/" @click="closeMenu">Home</router-link></li>
          <li><router-link to="/about" @click="closeMenu">About</router-link></li>
          <li><router-link to="/contact" @click="closeMenu">Contact</router-link></li>
        </ul>
        <div v-if="!authStore.isAuthenticated">
          <button class="login-btn" @click="openLoginModal">Login</button>
        </div>
        <div v-else>
          <router-link to="/dashboard">
            <button class="user-btn">User Icon</button>
          </router-link>
          <button class="logout-btn" @click="logout">Logout</button>
        </div>
        <Modal v-model="showLoginModal">
          <Login />
          <p>Don't have an account? <button class="register-link" @click="openRegisterModal">Register</button></p>
        </Modal>
        <Modal v-model="showRegisterModal">
          <Register />
        </Modal>
      </div>
    </nav>
  </header>
</template>

<style scoped>
.header {
  background-color: #03a9f4; /* Light blue color */
  height: 60px; /* Fixed height for the header */
  box-sizing: border-box; /* Ensure padding does not affect the width and height */
  display: flex;
  align-items: center; /* Center content vertically */
}
.nav {
  display: flex;
  justify-content: space-between;
  align-items: center;
  position: relative;
  width: 100%;
}
.burger {
  display: none;
  flex-direction: column;
  cursor: pointer;
  align-items: flex-end;
}
.burger:hover .burger-line {
  background-color: #b3e5fc; /* Lighter blue color for hover effect */
}
.burger-line {
  width: 25px;
  height: 3px;
  background-color: white;
  margin: 4px 0;
  transition: transform 0.4s, opacity 0.4s, background-color 0.2s;
}
.burger-line.open:nth-child(1) {
  transform: translateY(7px) rotate(45deg);
}
.burger-line.open:nth-child(2) {
  opacity: 0;
}
.burger-line.open:nth-child(3) {
  transform: translateY(-7px) rotate(-45deg);
}
.menu {
  list-style: none;
  display: flex;
  gap: 2rem;
}
.menu li {
  margin: 0;
}
.menu a {
  color: white;
  text-decoration: none;
  font-weight: bold;
}
.menu a:hover {
  color: #b3e5fc; /* Lighter blue color for hover effect */
}
.login-btn {
  background: none;
  border: none;
  color: white;
  font-size: 1rem;
  cursor: pointer;
}
.login-btn:hover {
  color: #b3e5fc; /* Lighter blue color for hover effect */
}
.register-link {
  background: none;
  border: none;
  color: #03a9f4;
  font-size: 1rem;
  cursor: pointer;
  text-decoration: underline;
}
.register-link:hover {
  color: #0288d1; /* Darker blue color for hover effect */
}
/* Media queries for responsiveness */
@media (max-width: 768px) {
  .burger {
    display: flex;
  }
  .menu {
    display: none;
    flex-direction: column;
    gap: 1rem;
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: #03a9f4; /* Light blue color */
    padding: 2rem;
    justify-content: center;
    align-items: center;
    z-index: 1000;
  }
  .menu.open {
    display: flex;
  }
  .login-btn {
    display: none;
  }
}
</style>