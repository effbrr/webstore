import { createApp } from 'vue';
import { createPinia } from 'pinia';
import router from './router';
import App from './App.vue';
import { useItemsStore } from '@/stores/items';
import { useItemCategoriesStore } from '@/stores/itemCategories';
import './style.css';

const app = createApp(App);
const pinia = createPinia();

app.use(pinia);
app.use(router);

const itemCategoriesStore = useItemCategoriesStore();
const itemsStore = useItemsStore();

itemCategoriesStore.fetchItemCategories().then(() => {
    itemsStore.fetchItems();
});

app.mount('#app');
