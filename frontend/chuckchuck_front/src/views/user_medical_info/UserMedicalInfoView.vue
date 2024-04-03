<template>
  <div class="user-madical-info">
    <div class="header">
      <h3>문진표</h3>
      <p style="margin-bottom: 20px">
        병원과 약국에 프로필을 보여주고 더 정확한 진단을 받아보세요.
      </p>
      <div class="profile-picture">
        <img :src="imgUrl" alt="프로필 사진" />
      </div>
    </div>

    <div style="height: 42px"></div>

    <div class="profile-container">
      <h3>{{ userstore.userName }}({{ sex }})</h3>
      <hr style="margin: 8px 18px" />
    </div>

    <div class="container">
      <h3 style="margin: 5px 0px">개인정보</h3>
      <div class="content">
        <div class="info-card">
          <div class="info-item">
            <p class="info-title">생년월일</p>
            <p class="info-content">{{ formatDate(userstore.birth) }}</p>
          </div>
          <hr />
          <div class="info-item">
            <p class="info-title">혈액형</p>
            <p class="info-content">
              <strong>{{ userstore.bloodType }}</strong>
              <span style="font-size: 8px">RH+</span>
            </p>
          </div>
          <hr />
          <div class="info-item">
            <p class="info-title">키/몸무게</p>
            <p class="info-content">
              {{ userstore.height }}cm/ {{ userstore.weight }}kg
            </p>
          </div>
          <hr />
          <div class="info-item">
            <p class="info-title">BMI</p>
            <p class="info-content">
              {{ calculateBMI(userstore.weight, userstore.height) }}
            </p>
          </div>
        </div>
        <div class="buttons-container">
          <RouterLink :to="{ name: 'pillbaghistory' }">
            <button class="button gradient-blue">
              <strong>처방내역 확인</strong>
              <p>모든 약봉투를 확인</p>
            </button>
          </RouterLink>
          <RouterLink :to="{ name: 'diagnosishistory' }">
            <button class="button gradient-purple">
              <strong>진단내역 확인</strong>
              <p>모든 진단서를 확인</p>
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
          v-for="(diseasedata, index) in diseasestore.diseaseInfos"
          :key="`disease-${index}`"
          class="disease-detail"
        >
          <span>{{ formatDate(diseasedata.Date) }}</span>
          <Badge
            :title="diseasedata.Code"
            background-color="#d3e0f8"
            color="black"
          />
          <span class="txt_line">{{ diseasedata.Name }}</span>
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
          v-for="(takelist, index) in currentMedications"
          :key="`current-${index}`"
        >
          <div
            v-for="(curpillinfo, index) in takelist.takeListPillInfoList"
            :key="`cur-pillinfo-${index}`"
            class="current-detail"
          >
            {{ curpillinfo.name }}
          </div>
        </div>
      </div>
      <div class="diseas-list">
        <div style="display: flex">
          <div class="gray-button">한달 이내에 복용한 약</div>
        </div>
        <div
          v-for="(takelist, index) in recentMedications"
          :key="`disease-${index}`"
        >
          <div
            v-for="(pastpillinfo, index) in takelist.takeListPillInfoList"
            :key="`past-pillinfo-${index}`"
            class="disease-detail"
          >
            {{ pastpillinfo.name }}
          </div>
        </div>
      </div>
    </div>
  </div>
  <Footer></Footer>
</template>

<script setup>
import Footer from "@/common/FooterNoColor.vue";
import Female from "@/assests/img/Female.svg";
import Male from "@/assests/img/Male.svg";
import Badge from "@/common/Badge.vue";
import { ref, onMounted, computed } from "vue";
import dayjs from "dayjs";
import { userStore } from "@/stores/user";
import { diseaseStore } from "@/stores/disease";
import { takelistStore } from "@/stores/takelist";

const userstore = userStore();
const diseasestore = diseaseStore();
const takeliststore = takelistStore();
const imgUrl = ref("");
let sex = ref("");

function kotextsex(en_sex) {
  if (en_sex === "MALE") {
    sex.value = "남";
    imgUrl.value = Male;
  } else {
    sex.value = "여";
    imgUrl.value = Female;
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

// 현재 복용 중인 약 (isFinished: false)
const currentMedications = computed(() =>
  takeliststore.takelistdatas.filter((item) => !item.isFinished)
);

// 한 달 이내에 복용한 약 (isFinished: true)
const recentMedications = computed(() =>
  takeliststore.takelistdatas.filter((item) => item.isFinished)
);

onMounted(async () => {
  // fetchData();
  await userstore.getUserInfo();
  diseasestore.getDiseaseInfo();
  await takeliststore.getUserMedicalInfoTakelist();

  kotextsex(userstore.sex);
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
  padding: 5px 3px;
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
  font-size: 10px;
  color: #666;
  width: 50%;
  text-align: center;
  margin: 4px;
}

.info-content {
  font-size: 10px;
  color: #333;
  width: 50%;
  text-align: center;
  margin: 4px;
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
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
}

.gradient-purple {
  background: linear-gradient(145deg, #a0b7ef, #e3b8fb);
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
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

.txt_line {
  width: 80px;
  padding: 0 5px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.disease-detail {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 10px 0px;
}

.navy-button {
  background-color: #30579f;
  color: #ffffff;
  font-weight: bold;
  border-radius: 2px;
  padding: 2px 20px;
  margin-top: 10px;
  font-size: 12px;
  border: none;
  cursor: pointer;
}

.gray-button {
  background-color: #cccccc;
  color: #ffffff;
  font-weight: bold;
  border-radius: 2px;
  padding: 2px 15px;
  margin-top: 10px;
  font-size: 12px;
  border: none;
  cursor: pointer;
}

.current-detail {
  margin: 8px 0px;
}

.disease-detail {
  margin: 8px 0px;
}
</style>
