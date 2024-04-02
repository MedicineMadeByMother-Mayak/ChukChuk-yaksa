<template>
  <div>
    <!-- 복용관리 -->
    <Wave title="복용 관리" height="30px" />
    <Carousel :autoplay="1500" :wrap-around="true">
      <Slide v-for="slide in 3" :key="slide">
        <div class="carousel__item">{{ slide }}</div>
      </Slide>

      <template #addons>
        <Navigation />
        <Pagination />
      </template>
    </Carousel>

    <div class="alarms">
      <div
        v-for="(takeListData, index) in currentTakeList"
        :key="`pill-date-${index}`"
      >
        <button class="rounded-button">
          <span
            ><i
              class="fa-solid fa-bell"
              style="color: #ffd43b; margin-right: 8px"
            ></i
            >{{ takeListData.takeListName }}</span
          >
        </button>
      </div>
      <div>
        <button class="rounded-button" style="height: 28px">
          <span
            ><i
              class="fa-solid fa-circle-plus fa-lg icon"
              style="color: #1454b5; margin: 2px 15px"
            ></i
          ></span>
        </button>
      </div>
    </div>

    <div class="menu">
      <div class="menu-left">
        <img src="@/assests/icon/pill.png" alt="복용리스트" />
        <div><strong>복용중</strong></div>
      </div>
      <div class="menu-right">
        <button class="gray-button">과거에 먹은 약</button>
        <button class="navy-button">추가</button>
      </div>
    </div>

    <hr style="margin: 3px" />

    <!-- 복용리스트 목록 -->
    <div
      v-for="(takeListData, index) in currentTakeList"
      :key="`pill-date-${index}`"
      class="pill-entry"
    >
      <!-- 복용리스트 날짜와 제목 -->
      <div class="pill-date">
        {{ takeListData.createDate }} [{{ takeListData.takeListName }}]
        <img src="@/assests/icon/edit.png" alt="편집 아이콘" />
      </div>

      <!-- 리스트 별 약 목록 -->
      <ul class="pills-list">
        <li
          v-for="(currentPillData, index) in takeListData.takeListPillInfoList"
          :key="`pill-details-${index}`"
          class="pill-info"
        >
          <!-- 약 카드 -->
          <Content
            :pillId="currentPillData.pillId"
            :pillName="currentPillData.name"
            :imageUrl="currentPillData.imageUrl"
            :type="currentPillData.type"
            :warningPregnant="currentPillData.warningPregnant"
            :warningUseDate="currentPillData.warningUseDate"
            :warningElders="currentPillData.warningElders"
            :warningTogether="currentPillData.warningTogether"
          />
        </li>
      </ul>
    </div>
  </div>
</template>

<script setup>
import Wave from "@/common/Wave.vue";
import { ref, onMounted, computed } from "vue";
import dayjs from "dayjs";
import Content from "./components/Content.vue";
import List from "./components/List.vue";
import { Carousel, Pagination, Slide, Navigation } from "vue3-carousel";
import "vue3-carousel/dist/carousel.css";
import { takelistStore } from "@/stores/takelist";
const store = takelistStore();

// 현재 복용중인 리스트
const currentTakeList = computed(() =>
  store.takelistpagedatas.filter((item) => !item.isFinished)
);

// 복용 완료한 리스트
const finishedTakeList = computed(() =>
  store.takelistpagedatas.filter((item) => item.isFinished)
);

onMounted(async () => {
  await store.getTakeListPageDatas();
});
</script>

<style scoped>
ol {
  padding-inline-start: 0px;
}

.alarms {
  padding: 15px 10px;
  background-color: #c0e6fc;
  display: flex;
  width: 300px;
  flex-wrap: wrap;
  gap: 10px;
}
.menu {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-left: 5px;
  margin-top: 5px;
  position: relative;
  overflow: hidden;
}
.menu-left {
  display: flex;
  align-items: center;
}
.menu-left img {
  width: 80px;
  margin-right: 5px;
}
.menu-right {
  text-align: right;
  margin-bottom: 5px;
}
.menu img {
  width: 10px;
  height: 10px;
}

button {
  display: inline;
  margin-right: 5px;
  margin-top: 10px;
}
.gray-button {
  background-color: #cccccc;
  color: #ffffff;
  font-weight: bold;
  border-radius: 5px;
  padding: 5px 10px;
  font-size: 12px;
  border: none;
  cursor: pointer;
  margin-right: 5px;
}

.navy-button {
  background-color: #242291;
  color: #ffffff;
  font-weight: bold;
  border-radius: 5px;
  padding: 5px 15px;
  font-size: 12px;
  border: none;
  cursor: pointer;
}

.line {
  width: 100%;
  margin-top: 0px;
}

.pill-info {
  font-weight: bold;
  margin: 0px 10px;
  align-items: center;
}

.pills-list {
  margin: 5px 0px;
  list-style: none;
  padding-left: 0;
  position: relative;
}

.pills-list::before {
  content: "";
  position: absolute;
  left: 0;
  top: 0;
  bottom: 0;
  width: 2px;
  background-color: #083688;
}

.pill-info:first-child::before,
.pill-info:last-child::before {
  content: "";
  position: absolute;
  width: 6px;
  height: 6px;
  background-color: #083688; /* 점의 색상, 원하는 색으로 변경 가능 */
  border-radius: 50%; /* 원형 점 */
}

.pill-info:first-child::before {
  top: -2px;
  left: -2px;
}

.pill-info:last-child::before {
  bottom: -2px;
  left: -2px;
}

.pill-entry {
  margin-bottom: 20px;
  padding-left: 5px;
}

.pill-date {
  display: flex;
  align-items: center;
  font-weight: bold;
}

.pill-info + .pill-info {
  margin-top: 10px;
}

.pill-date img {
  margin-left: 3px;
  width: 25px;
  height: 25px;
}
.rounded-button {
  display: flex;
  align-items: center;
  justify-content: center;
  border: none;
  background-color: white;
  padding: 5px 15px;
  margin: 0px;
  border-radius: 30px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  cursor: pointer;
  transition: background-color 0.3s;
}
</style>
