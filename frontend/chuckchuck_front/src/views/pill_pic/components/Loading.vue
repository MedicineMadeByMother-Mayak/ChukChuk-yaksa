<template>
  <div class="container">
    <img src="@/assests/img/ai.gif" />
    <p class="bold">{{ analyzingText }}</p>
  </div>
</template>
<script setup>
import { ref, watchEffect, onUnmounted, onMounted } from "vue";
import { pillPicStore } from "@/stores/pillPic";
import { instance } from "@/util/mainAxios";
const store = pillPicStore();
const dots = ref(0);
const analyzingText = ref("분석중" + ".".repeat(dots.value));

const intervalId = setInterval(() => {
  dots.value = (dots.value + 1) % 4; // 온점이 0, 1, 2, 3으로 반복되도록 설정
}, 500);

watchEffect(() => {
  analyzingText.value = "분석중" + ".".repeat(dots.value);
});

onUnmounted(() => {
  clearInterval(intervalId);
});

onMounted(async () => {
  await store.photoSearch();
  store.endLoading(3);
});
</script>
<style scoped>
img {
  display: block; /* 이미지를 블록 요소로 설정하여 다른 요소와 세로로 정렬되도록 함 */
  margin: 0 auto; /* 이미지를 가운데 정렬 */
  opacity: 0.5;
}
p {
  font-size: large;
  color: rgb(60, 60, 60);
  margin-top: 10px;
}
.container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  /* margin-top: 20px; */
}
</style>
