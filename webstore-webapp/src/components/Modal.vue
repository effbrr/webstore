<script setup>
import { ref, defineProps, defineEmits, watch } from 'vue';

const props = defineProps({
  modelValue: Boolean,
});

const emit = defineEmits(['update:modelValue']);

watch(() => props.modelValue, (newValue) => {
  showModal.value = newValue;
});

const showModal = ref(props.modelValue);

const closeModal = () => {
  emit('update:modelValue', false);
};
</script>

<template>
  <div v-if="showModal" class="modal-overlay" @click="closeModal">
    <div class="modal-content" @click.stop>
      <button class="modal-close" @click="closeModal">&times;</button>
      <slot></slot>
    </div>
  </div>
</template>

<style scoped>
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.modal-content {
  background: white;
  padding: 1rem;
  border-radius: 8px;
  position: relative;
}

.modal-close {
  position: absolute;
  top: 10px;
  right: 10px;
  background: none;
  border: none;
  font-size: 1.5rem;
  cursor: pointer;
}
</style>