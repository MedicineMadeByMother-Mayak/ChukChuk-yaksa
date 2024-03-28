<template>
  <div class="accordion">
    <div
      class="accordion-header"
      :style="
        isOpen
          ? 'background : linear-gradient(to left, #86e7ee, #3183ff)'
          : 'background : #ccc'
      "
      @click="toggleAccordion"
    >
      <h2>{{ title }}</h2>
      <span class="icon"
        ><i :class="isOpen ? 'arrow up' : 'arrow down'"></i
      ></span>
    </div>
    <div v-if="isOpen" class="accordion-content">
      <slot></slot>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";

const porps = defineProps({
  title: {
    type: String,
    default: "아코디언 제목",
  },
});

const isOpen = ref(false);

function toggleAccordion() {
  isOpen.value = !isOpen.value;
}
</script>

<style scoped>
.accordion {
  border-radius: 2px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.accordion-header {
  background: linear-gradient(to left, #86e7ee, #3183ff);
  border-radius: 2px;
  padding: 10px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  cursor: pointer;
}

.accordion-header h2 {
  margin: 0;
  font-size: 1rem;
}

.icon {
  font-size: 14px;
}

.accordion-content {
  padding: 10px;
}

table {
  width: 100%;
  border-collapse: collapse;
}

td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: left;
}

tr:nth-child(odd) {
  background-color: #f9f9f9;
}

.arrow {
  border: solid black;
  border-width: 0 3px 3px 0;
  display: inline-block;
  padding: 3px;
  caret-color: transparent;
}

.up {
  transform: rotate(-135deg);
  -webkit-transform: rotate(-135deg);
}

.down {
  transform: rotate(45deg);
  -webkit-transform: rotate(45deg);
}
</style>
