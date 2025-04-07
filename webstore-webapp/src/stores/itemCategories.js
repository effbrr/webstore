import { defineStore } from 'pinia';
import apiClient from '@/plugins/axios';

export const useItemCategoriesStore = defineStore('itemCategories', {
    state: () => ({
        itemCategories: [],
        loading: false,
        error: null,
    }),
    actions: {
        async fetchItemCategories() {
            this.loading = true;
            try {
                const response = await apiClient.get('items/categories');
                this.itemCategories = response.data;
            } catch (error) {
                this.error = error;
            } finally {
                this.loading = false;
            }
        },
    },
});