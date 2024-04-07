<template>
  <div class="modal-overlay" v-if="showModal" @click="closeModal">
    <div class="modal">
      <ul class="modal-menu">
        <div class="close-button">
          <div class="close" @click="selectXButton">&times;</div>
        </div>
        <li v-for="(item, index) in modalData" :key="index">
          <RouterLink
            v-if="item[1]"
            :to="{ name: item[2].Link, params: item[2].params }"
            class="router-link-item"
          >
            <div style="margin: 15px; font-size: 13px">{{ item[0] }}</div>
          </RouterLink>
          <hr style="margin: 0px 10px" v-if="index < modalData.length - 1" />
        </li>
      </ul>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";

const showModal = defineModel();
const props = defineProps({
  modalData: {
    type: Array,
    default: [["원하는 텍스트 넣으세요", true, { params: {}, Link: "home" }]],
  },
});

const closeModal = () => {
  showModal.value = !showModal.value;
};

const selectXButton = () => {
  showModal.value = true;
};
</script>

<style scoped>
@keyframes slideIn {
  from {
    transform: translateY(100%);
  }
  to {
    transform: translateY(0%);
  }
}
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.6);
  display: flex;
  align-items: center;
  justify-content: center;
  caret-color: transparent;
  z-index: 9999;
  backdrop-filter: blur(1px);
}

.modal {
  background: white;
  width: 320px;
  height: 30%;
  border-top-left-radius: 15px;
  border-top-right-radius: 15px;
  overflow: hidden;
  bottom: 0px;
  position: absolute;
  animation: slideIn 0.7s ease-in-out;
}

.modal-menu {
  list-style: none;
  padding: 0;
  margin: 0;
}

.modal-menu li {
  text-align: left;
  margin-left: 5px;
  background: white;
  color: #565656;
  cursor: pointer;
}

.modal-menu hr {
  margin: 0;
  border: none;
  border-top: 1px solid #ccc;
}

.modal-menu li:hover {
  background-color: #f2f2f2;
}

.modal-menu li:not(hr):hover {
  background-color: #f7f7f7;
}

.router-link-item {
  text-decoration: none;
  color: inherit;
}

.close-button {
  position: relative;
  margin-left: auto;
}

.close {
  color: #aaaaaa;
  font-size: 28px;
  font-weight: bold;
  text-align: right;
  margin-right: 14px;
}
</style>
