import { defineStore } from 'pinia';

export const useCartStore = defineStore('cart', {
    state: () => ({
        items: [],
        isOpen: false
    }),
    actions: {
        addItem(item) {
            const existingItem = this.items.find(i => i.id === item.id);
            if (existingItem) {
                existingItem.quantity += 1;
            } else {
                this.items.push({ ...item, quantity: 1 });
            }
        },
        removeItem(itemId) {
            this.items = this.items.filter(item => item.id !== itemId);
        },
        clearCart() {
            this.items = [];
        },
        openCart() {
            this.isOpen = true;
        },
        toggleCart() {
            this.isOpen = !this.isOpen;
        },
        closeCart() {
            this.isOpen = false;
        }
    },
    getters: {
        cartItemCount: (state) => {
            return state.items.reduce((total, item) => total + item.quantity, 0);
        },
        cartTotalPrice: (state) => {
            return state.items.reduce((total, item) => total + item.price.currentPrice * item.quantity, 0);
        }
    }
});