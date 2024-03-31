<!-- 약 상세 페이지 -->
<template>
  <div class="basic-background-color">
    <Wave :title="waveTitle" height="10px" />
    <div class="container">
      <img :src="imagePath" alt="Image description" />
    </div>

    <div
      v-for="(item, index) in appointmentDetails"
      :key="index"
      class="main-container"
    >
      <div style="margin: 5px 0px; display: flex; align-items: center">
        <strong
          style="
            font-size: 15px;
            font-family: Arial, Helvetica, sans-serif;
            margin: 5px 0px;
            display: flex;
            align-items: center;
          "
        >
          <i
            class="fa-solid fa-circle-info"
            style="margin-right: 5px; color: #509af8"
          ></i>
          <font-awesome-icon
            :icon="['fas', 'circle-info']"
            style="color: #509af8; margin-right: 5px"
          />
          <span class="info-title" style="vertical-align: middle">{{ item.title }}</span>
        </strong>
        <span style="font-size: 10px"></span>
      </div>
      <div class="details-info">
        <span class="info">{{ item.content }}</span>
      </div>
    </div>
  </div>
  <!-- Nav-bar용 -->
  <div style="height: 85px; background-color: #f9f9f9"></div>
</template>

<script setup>
import Wave from "@/common/Wave.vue";

import { ref, onMounted } from "vue";
import { pillSearchStore } from "@/stores/pillSearch"

const waveTitle = ref('');
const imagePath = ref('');
const appointmentDetails = ref([]);

onMounted(async () => {
  const store = pillSearchStore();
  const temp = await store.getPillInfo(10); // getPillInfo를 비동기로 호출

  console.log(temp);
  waveTitle.value = store.name;

  console.log(waveTitle);
  imagePath.value = store.imageUrl; // 이미지 경로 받아오기
  console.log(imagePath.value);
  appointmentDetails.value = [
    { title: "제조사", content: store.company },
    { title: "주의사항", content: store.caution },
    { title: "종류", content: store.type },
    { title: "효능", content: store.effect },
    { title: "사용법", content: store.capacity },
    { title: "주성분", content: store.basis },
    // { title: "약 사용 전 반드시 알아야 할 내용", content: store. },
    // { title: "보관시 주의사항", content:  },
  ];
})
</script>

<style scoped>
.basic-background-color {
  background-color: #f9f9f9;
}

.main-container {
  padding: 7px 20px;
}

.details-info {
  display: flex;
  align-items: center;
  background-color: rgb(255, 255, 255);
  border-radius: 5px;
  padding: 4px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
}

.img-section {
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: rgb(0, 119, 255);
}

.container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 25vh; /* 전체 화면 높이 설정 */
  width: 100vw; /* 전체 화면 너비 설정 */
}

img {
  max-width: 80%; /* 이미지 최대 너비 설정 */
  max-height: 80%; /* 이미지 최대 높이 설정 */
  margin: 10px; /* 이미지 여백 설정 */
}

.info {
  padding: 4px;
  /* color: rgb(86, 86, 86); */
}

.info-title { 
  /* color: rgb(86, 86, 86); */
}
</style>
