<template>
  <footer class="footer">
    <div class="footer-container">
      <div class="footer-content">
        <p>&copy; 2025 Gymstore. All rights reserved.</p>
        <nav class="footer-nav">
          <router-link to="/">About Us</router-link>
          <router-link to="/">Contact</router-link>
          <router-link to="/">Privacy Policy</router-link>
          <router-link to="/">Terms of Service</router-link>
        </nav>
      </div>
    </div>
  </footer>
</template>

<style scoped>
.footer {
  background-color: #1d1f21;
  color: white;
  padding: 1rem 0;
  text-align: center;
}

.footer-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 1rem;
}

.footer-content {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.footer-nav {
  margin-top: 0.5rem;
}

.footer-nav a {
  color: white;
  margin: 0 0.5rem;
  text-decoration: none;
}

.footer-nav a:hover {
  text-decoration: underline;
}
</style>