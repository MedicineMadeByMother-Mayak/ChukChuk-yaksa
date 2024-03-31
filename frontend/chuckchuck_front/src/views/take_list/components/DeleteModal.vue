<template>
  <div>
    <button @click="deleteItem">Delete</button>
    <div v-if="showMessage" class="modal">
      <p>복용리스트가 삭제되었습니다.</p>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';

const showMessage = ref(false);

const deleteItem = () => {
  // 아이템 삭제 로직 실행

  // 삭제됨을 나타내는 메시지 표시
  showMessage.value = true;

  // 일정 시간 후에 메시지 숨기기
  setTimeout(() => {
    showMessage.value = false;
  }, 2000); // 2초 후에 메시지를 숨김
};
</script>

<style scoped>
.modal {
  position: fixed;
  top: 80%;
  left: 50%;
  height: 30px;
  width: 58%;
  transform: translate(-50%, -50%);
  background-color: rgba(0, 0, 0, 0.8);
  color: white;
  border-radius: 5px;
  animation: fadeInOut 2s ease-in-out; 
  display: flex;
  align-items: center;
}
.modal p {
  display: flex;
  justify-content: center;
  align-items: center;
  margin: auto;
}

@keyframes fadeInOut {
  0% {
    opacity: 0;
  }
  50% {
    opacity: 1;
  }
  100% {
    opacity: 0;
  }
}
</style>
