<template>
  <div class="user-madical-info">
    <div class="header">
      <h3>문진표</h3>
      <p>본인의 약국에 프로필을 보여주고 더 정확한 진단을 받아보세요.</p>
      <div class="profile-picture">
        <img src="../../assests/img/woman.jpg" alt="프로필 사진" />
      </div>
    </div>

    <div style="height: 42px"></div>

    <div class="profile-container">
      <h3>{{ userdata.userName }}</h3>
      <hr style="margin: 8px 18px" />
    </div>

    <div class="container">
      <h3 style="margin: 5px 0px">개인정보</h3>
      <div class="content">
        <div class="info-card">
          <div class="info-item">
            <p class="info-title">생년월일</p>
            <p class="info-content">{{ formatDate(userdata.birth) }}</p>
          </div>
          <hr />
          <div class="info-item">
            <p class="info-title">혈액형</p>
            <p class="info-content">{{ userdata.bloodType }}</p>
          </div>
          <hr />
          <div class="info-item">
            <p class="info-title">키/몸무게</p>
            <p class="info-content">
              {{ userdata.height }}cm/ {{ userdata.weight }}kg
            </p>
          </div>
          <hr />
          <div class="info-item">
            <p class="info-title">BMI</p>
            <p class="info-content">
              {{ calculateBMI(userdata.weight, userdata.height) }}
            </p>
          </div>
        </div>
        <div class="buttons-container">
          <RouterLink :to="{ name: 'pillbaghistory' }">
            <button class="button gradient-blue">
              <strong>처방내역 확인</strong>
              <p><strong>모든 약봉투를 확인</strong></p>
            </button>
          </RouterLink>
          <RouterLink :to="{ name: 'diagnosishistory' }">
            <button class="button gradient-purple">
              <strong>진단내역 확인</strong>
              <p><strong>모든 진단서를 확인</strong></p>
            </button>
          </RouterLink>
        </div>
      </div>
    </div>

    <div class="appointment-section">
      <div style="margin: 5px 0px">
        <strong style="font-size: 18.72px; margin: 5px 0px"> 병력 </strong
        ><span style="font-size: 10px"
          >(다음과 같은 질병으로 진단을 받았어요.)</span
        >
      </div>
      <div class="diseas-list">
        <div
          v-for="(diseasedata, index) in diseasedatas.results"
          :key="`disease-${index}`"
          class="disease-detail"
        >
          <span>{{ formatDate(diseasedata.Date) }}</span>
          <Badge
            :title="diseasedata.Code"
            background-color="#d3e0f8"
            color="black"
          />
          <span>{{ diseasedata.Name }}</span>
        </div>
      </div>
    </div>

    <div class="appointment-section">
      <div style="margin: 5px 0px">
        <strong style="font-size: 18.72px; margin: 5px 0px"> 약력 </strong
        ><span style="font-size: 10px"
          >(다음과 같은 질병으로 진단을 받았어요.)</span
        >
      </div>
      <div class="diseas-list">
        <div style="display: flex">
          <div class="navy-button">현재 복용중인 약</div>
        </div>
        <div
          v-for="(diseasedata, index) in diseasedatas.results"
          :key="`disease-${index}`"
          class="disease-detail"
        >
          <span>{{ formatDate(diseasedata.Date) }}</span>
          <Badge
            :title="diseasedata.Code"
            background-color="#d3e0f8"
            color="black"
          />
          <span>{{ diseasedata.Name }}</span>
        </div>
      </div>
      <div class="diseas-list">
        <div style="display: flex">
          <div class="gray-button">한달 이내에 복용한 약</div>
        </div>
        <div
          v-for="(diseasedata, index) in diseasedatas.results"
          :key="`disease-${index}`"
          class="disease-detail"
        >
          <span>{{ formatDate(diseasedata.Date) }}</span>
          <Badge
            :title="diseasedata.Code"
            background-color="#d3e0f8"
            color="black"
          />
          <span>{{ diseasedata.Name }}</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import Badge from "@/common/Badge.vue";
import { instance } from "@/util/mainAxios";
import { ref, onMounted } from "vue";
import dayjs from "dayjs";

const userdata = ref({});
const diseasedatas = ref({});
const takelistdatas = ref({});

async function fetchData() {
  try {
    userdata.value = (await instance.get("/profile")).data;
    diseasedatas.value = (await instance.get("/record/disease")).data;
    takelistdatas.value = (
      await instance.get("/take-list", {
        params: { period: true },
      })
    ).data.results;
    // console.log(takelistdatas.value);
  } catch (error) {
    console.error("API 데이터를 불러오는데 실패했습니다.", error);
  }
}

function formatDate(date, format = "YYYY.MM.DD") {
  return dayjs(date).format(format);
}

function calculateBMI(weight, height) {
  if (!weight || !height) return ""; // 무게나 키가 없으면 빈 문자열 반환
  const bmi = (weight / (height * height)) * 10000;
  return bmi.toFixed(2); // 소수점 두 자리까지의 문자열 반환
}

onMounted(() => {
  fetchData();
});
</script>

<style scoped>
/* 그라데이션 */
.header {
  color: black;
  text-align: center;
  background: linear-gradient(to left, #86e7ee, #3183ff);
  height: 148px;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.header > * {
  position: relative;
  top: 33px;
}

div p {
  font-size: 10px;
  margin: 5px;
}

.profile-container {
  text-align: center;
  position: relative;
  padding-top: 50px;
  margin-top: -50px;
}

.profile-picture {
  position: relative;
}

.profile-picture img {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  border: 4px solid white;
  position: relative;
  z-index: 2;
  background-color: white;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.container {
  width: 100%;
  padding: 18px;
  box-sizing: border-box;
}

.content {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
}

.info-card,
.buttons-container {
  width: 48%;
}

.info-card {
  display: flex;
  justify-content: center;
  flex-direction: column;
  background-color: #ffffff;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  padding: 10px;
  box-sizing: border-box;
}

.info-card hr {
  width: 100%;
  border: 0;
  height: 1px;
  background-color: #eaeaea;
  margin: 3px 0;
}

.info-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 5px;
}

.info-title {
  font-weight: bold;
  font-size: 8px;
  color: #666;
}

.info-content {
  font-size: 8px;
  color: #333;
}

.buttons-container {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  gap: 5px;
}

.button {
  width: 100%;
  min-height: 70px;
  height: 100%;
  padding: calc(50% - 44px);

  margin-bottom: 0px;
  border-radius: 8px;
  border: none;
  color: white;
  cursor: pointer;
  font-size: 14px;
}

.gradient-blue {
  background: linear-gradient(145deg, #a0b7ef, #d5f2e8);
}

.gradient-purple {
  background: linear-gradient(145deg, #a0b7ef, #e3b8fb);
}

@media (max-width: 768px) {
  .button {
    padding: 10px;
  }
}

.appointment-section {
  padding: 18px;
}

.diseas-list {
  align-items: center;
  background-color: white;
  border-radius: 15px;
  padding: 1px 18px;
  margin: 10px 0px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
}

.disease-detail {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 10px 0px;
}

.navy-button {
  background-color: #242291;
  color: #ffffff;
  font-weight: bold;
  border-radius: 5px;
  padding: 5px 15px;
  margin-top: 10px;
  font-size: 12px;
  border: none;
  cursor: pointer;
}

.gray-button {
  background-color: #cccccc;
  color: #ffffff;
  font-weight: bold;
  border-radius: 5px;
  padding: 5px 15px;
  margin-top: 10px;
  font-size: 12px;
  border: none;
  cursor: pointer;
}
</style>
