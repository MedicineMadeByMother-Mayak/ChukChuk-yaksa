<!-- 
사용예시 By.서현
알림 애니메이션이 있어 setTimeOut써야함 다른방법 찾지못했음
    

<AlertModal
      v-if="showModal"
      :text="'삭제 되었습니다.'"
      :showModal="showModal"
    />
import AlertModal from "@/common/Form/AlertModal.vue";
const showModal = ref(false);
const click = () => {
  showModal.value = true;
  setTimeout(() => {
    showModal.value = false;
  }, 2500);
};


-->

<template>
  <div v-if="showModal">
    <div class="modal">
      <p>{{ text }}</p>
    </div>
  </div>
</template>

<script setup>
const props = defineProps({
  text: {
    type: String,
    default: "알림내용",
  },
  showModal: {
    type: Boolean,
    default: false,
  },
});
</script>

<style scoped>
.modal {
  position: fixed;
  top: 80%;
  left: 50%;
  height: 30px;
  width: 58%;
  transform: translate(-50%, -50%);
  background-color: rgba(0, 0, 0, 0.9);
  color: white;
  border-radius: 5px;
  animation: fadeInOut 2.5s ease-in-out;
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
  10% {
    opacity: 1;
  }
  100% {
    opacity: 0;
  }
}
</style>
