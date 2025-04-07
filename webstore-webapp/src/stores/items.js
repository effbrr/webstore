import { defineStore } from 'pinia';
import apiClient from '@/plugins/axios';

export const useItemsStore = defineStore('items', {
    state: () => ({
        items: [],
        item: null,
        searchQuery: '',
        loading: false,
        error: null,
    }),
    actions: {
        setItems(newItems) {
            this.items = newItems;
            this.sortItemsByTitle();
        },
        setSearchQuery(query) {
            this.searchQuery = query;
        },
        getFilteredItems() {
            if (this.searchQuery === '') {
                return [];
            }
            return this.items.filter(item =>
                item.title.toLowerCase().includes(this.searchQuery.toLowerCase())
            );
        },
        async fetchItems() {
            this.loading = true;
            try {
                const response = await apiClient.get('/items');
                this.setItems(response.data);
            } catch (error) {
                this.error = error;
            } finally {
                this.loading = false;
            }
        },
        async fetchItemById(itemId) {
            this.loading = true;
            try {
                const response = await apiClient.get(`/items/${itemId}`);
                this.item = response.data;
            } catch (error) {
                this.error = error;
            } finally {
                this.loading = false;
            }
        },
        async createItem(itemData) {
            this.loading = true;
            try {
                const response = await apiClient.post('/items', itemData);
                this.items.push(response.data);
            } catch (error) {
                this.error = error;
            } finally {
                this.loading = false;
            }
        },
        async updateItem(itemId, itemData) {
            this.loading = true;
            try {
                const response = await apiClient.put(`/items/${itemId}`, itemData);
                const index = this.items.findIndex((item) => item.id === itemId);
                if (index !== -1) {
                    this.items[index] = response.data;
                }
            } catch (error) {
                this.error = error;
            } finally {
                this.loading = false;
            }
        },
        sortItemsByTitle() {
            this.items.sort((a, b) => a.title.localeCompare(b.title));
        },
    },
});