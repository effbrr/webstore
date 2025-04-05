import { defineStore } from 'pinia';
import axios from 'axios';

export const useAuthStore = defineStore('auth', {
    state: () => ({
        token: localStorage.getItem('token') || '',
    }),
    actions: {
        async login(email, password) {
            try {
                const response = await axios.post('/auth/login', { email, password });
                this.token = response.data.token;
                localStorage.setItem('token', this.token);
            } catch (error) {
                throw new Error('Login failed: ' + error.response.data.message);
            }
        },
        logout() {
            this.token = '';
            localStorage.removeItem('token');
        },
        async register(email, password) {
            try {
                await axios.post('/auth/register', { email, password });
            } catch (error) {
                throw new Error('Registration failed: ' + error.response.data.message);
            }
        },
    },
    getters: {
        isAuthenticated: (state) => !!state.token,
    },
});