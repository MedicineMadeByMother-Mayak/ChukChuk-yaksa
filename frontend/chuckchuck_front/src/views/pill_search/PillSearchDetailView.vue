<!-- 약 상세 페이지 -->
<template>
  <div class="basic-background-color">
    <Wave :title="waveTitle" height="10px" />
    <div class="container">
      <img :src="imagePath" alt="Image description" />
    </div>

    <div v-for="(item, index) in appointmentDetails" :key="index" class="main-container">
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
          <i class="fa-solid fa-circle-info" style="margin-right: 5px; color: #509af8"></i>
          <span style="vertical-align: middle">{{ item.title }}</span>
        </strong>
        <span style="font-size: 10px"></span>
      </div>
      <div class="details-info">
        <span class="info">{{ item.content }}</span>
      </div>
    </div>
  </div>
  <div class="white-space basic-background-color"></div>
</template>

<script setup>
import Wave from "@/common/Wave.vue";

const waveTitle = "활명수"; // 약 이름 받아오기
const imagePath = "../../assests/img/tempPill.png"; // 이미지 경로 받아오기

const appointmentDetails = [
  { title: "제조사", content: "동화약품 (주)" },
  {
    title: "주의사항",
    content:
      "주의사항 주의사항 주의사항 주의사항 주의사항 주의사항 주의사항 주의사항 주의사항 주의사항 주의사항 주의사항 주의사항 주의사항 주의사항 주의사항 주의사항",
  },
  { title: "종류", content: "진통제 (painkiller)" },
  {
    title: "효능",
    content:
      "기부니가 조음 기부니가 조음 기부니가 조음 기부니가 조음 기부니가 조음 기부니가 조음 기부니가 조음 기부니가 조음 기부니가 조음 기부니가 조음",
  },
  { title: "사용법", content: "동화약품 (주)" },
  { title: "주성분", content: "시나몬, 초코, 얼음" },
  {
    title: "약 사용 전 반드시 알아야 할 내용",
    content:
      "알아야 할 내용 알아야 할 내용 알아야 할 내용 알아야 할 내용 알아야 할 내용 알아야 할 내용 알아야 할 내용 알아야 할 내용 알아야 할 내용 알아야 할 내용 알아야 할 내용 알아야 할 내용 알아야 할 내용",
  },
  {
    title: "보관시 주의사항",
    content:
      "상온에 보관 상온에 보관 상온에 보관 상온에 보관 상온에 보관 상온에 보관 상온에 보관 상온에 보관 상온에 보관 상온에 보관 상온에 보관 상온에 보관 상온에 보관 상온에 보관 상온에 보관 상온에 보관 상온에 보관 상온에 보관 상온에 보관 상온에 보관",
  },
];
</script>

<style>
.basic-background-color {
  background-color: #f9f9f9;
}

/* nav-bar 길이만큼 여백 */
.white-space {
  height: 85px;
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
}
</style>
