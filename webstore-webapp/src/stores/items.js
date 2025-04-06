import { defineStore } from 'pinia';

export const useItemsStore = defineStore('items', {
    state: () => ({
        items: [],
        searchQuery: '',
    }),
    actions: {
        setItems(newItems) {
            this.items = newItems;
        },
        setSearchQuery(query) {
            this.searchQuery = query;
        },
        getFilteredItems() {
            if (this.searchQuery === '') {
                return this.items;
            }
            return this.items.filter(item =>
                item.name.toLowerCase().includes(this.searchQuery.toLowerCase())
            );
        },
    },
});