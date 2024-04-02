<template>
  <div>

    <!-- 헤더 -->
    <Wave title="복용 관리" height="30px" />

    <!-- 척척약사의 조언 -->
    <Carousel :autoplay="1500" :itemsToShow="1.25" :wrapAround="true" :transition="500">
      <Slide v-for="(item, index) in advice" :key="index">
        <div class="carousel__item">
          <div class="carousel__img">
            <img :src="item.img" alt="Logo"/>
          </div>
          <div class="carousel__text">
            <div style="font-size: 12px;"><strong>{{ item.title }}</strong></div>
            <div>
              <span style="font-weight: bold; color: blue;">{{ item.pill1 }}</span>
              <span>{{ item.content1 }}</span>
              <span style="font-weight: bold; color: blue;">{{ item.pill2 }}</span>
              <span>{{ item.content2 }}</span>
            </div>
          </div>
        </div>
      </Slide>
      <template #addons>
        <!-- <Navigation /> -->
      </template>
    </Carousel>
    
    <!-- 알람 리스트 -->
    <div class="alarms">

      <!-- 알람 추가 버튼 -->
      <div>
        <button class="rounded-button" @click="showModal = true">
          <span
            ><font-awesome-icon :icon="['fas', 'circle-plus']" size="lg" /></span>
        </button>
        
      </div>
      
      <!-- 등록된 알람 리스트 -->
      <div
        v-for="(alarm, index) in alarmstore.alarmList"
        :key="`pill-date-${index}`"
      >
        <button class="rounded-button">
          <span class="alarm">
            <font-awesome-icon :icon="['fas', 'bell']" size="xs" style="color: #FFD43B;" />
            <span>{{ alarm.takeListName }}</span>
          </span>
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
import { alarmStore } from '@/stores/alarm';
import logo from "@/assests/img/Group.png";
import AlarmModal from '@/views/take_list/components/AlarmModal.vue';

const store = takelistStore();
const alarmstore = alarmStore();

// 현재 복용중인 리스트
const currentTakeList = computed(() =>
  store.takelistpagedatas.filter((item) => !item.isFinished)
);

// 복용 완료한 리스트
const finishedTakeList = computed(() =>
  store.takelistpagedatas.filter((item) => item.isFinished)
);

const showModal = ref(false);

onMounted(async () => {
  await store.getTakeListPageDatas();
  alarmstore.getAlarmList();
});

// 척척약사의 조언 
const advice = ref([
  { img: logo, title: '척척약사의 조언', pill1: '트루포뮬러', pill2: '프라닥사캡슐', content1: '(오메가3)와 ', content2: '(항응고제)은 함께 섭취시 출혈의 위험이 있을 수 있어 주의가 필요합니다.' },
  { img: logo, title: '척척약사의 조언', pill1: '트루포뮬러', pill2: '프라닥사캡슐', content1: '(오메가3)와 ', content2: '(항응고제)은 함께 섭취시 출혈의 위험이 있을 수 있어 주의가 필요합니다.' },
  { img: logo, title: '척척약사의 조언', pill1: '트루포뮬러', pill2: '프라닥사캡슐', content1: '(오메가3)와 ', content2: '(항응고제)은 함께 섭취시 출혈의 위험이 있을 수 있어 주의가 필요합니다.' },
  { img: logo, title: '척척약사의 조언', pill1: '트루포뮬러', pill2: '프라닥사캡슐', content1: '(오메가3)와 ', content2: '(항응고제)은 함께 섭취시 출혈의 위험이 있을 수 있어 주의가 필요합니다.' },
  { img: logo, title: '척척약사의 조언', pill1: '트루포뮬러', pill2: '프라닥사캡슐', content1: '(오메가3)와 ', content2: '(항응고제)은 함께 섭취시 출혈의 위험이 있을 수 있어 주의가 필요합니다.' },
  { img: logo, title: '척척약사의 조언', pill1: '트루포뮬러', pill2: '프라닥사캡슐', content1: '(오메가3)와 ', content2: '(항응고제)은 함께 섭취시 출혈의 위험이 있을 수 있어 주의가 필요합니다.' },
  { img: logo, title: '척척약사의 조언', pill1: '트루포뮬러', pill2: '프라닥사캡슐', content1: '(오메가3)와 ', content2: '(항응고제)은 함께 섭취시 출혈의 위험이 있을 수 있어 주의가 필요합니다.' },
  { img: logo, title: '척척약사의 조언', pill1: '트루포뮬러', pill2: '프라닥사캡슐', content1: '(오메가3)와 ', content2: '(항응고제)은 함께 섭취시 출혈의 위험이 있을 수 있어 주의가 필요합니다.' },
  { img: logo, title: '척척약사의 조언', pill1: '트루포뮬러', pill2: '프라닥사캡슐', content1: '(오메가3)와 ', content2: '(항응고제)은 함께 섭취시 출혈의 위험이 있을 수 있어 주의가 필요합니다.' },

])
</script>

<style scoped>
ol {
  padding-inline-start: 0px;
}

.alarms {
  padding: 15px 10px;
  margin-top: 20px;
  background-color: #c0e6fc;
  display: flex;
  width: 300px;
  height: 35px;
  white-space: nowrap;
  gap: 10px;
  overflow-x: auto; 
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

.alarm > *:first-child {
  margin-right: 8px;
}
.rounded-button {
  border: none;
  background-color: white;
  padding: 5px 15px;
  margin: 0px;
  border-radius: 30px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  cursor: pointer;
  transition: background-color 0.3s;
}

/* 척척약사의 조언 CSS */
.carousel__slide {
  box-shadow: 0 0.1em 0.3em rgba(0, 0, 0, 0.3);
  background-color: #ffffff;
  border-radius: 12px;
  height: 80px;
  margin-bottom: 10px;
}

.carousel__viewport {
  perspective: 2000px;
}

.carousel__track {
  transform-style: preserve-3d;
}

.carousel__slide--sliding {
  transition: 0.5s;
}

.carousel__slide {
  opacity: 0.9;
  transform: rotateY(-20deg) scale(0.9);
}

.carousel__slide--active ~ .carousel__slide {
  transform: rotateY(20deg) scale(0.9);
}

.carousel__slide--prev {
  opacity: 1;
  transform: rotateY(-10deg) scale(0.95);
}

.carousel__slide--next {
  opacity: 1;
  transform: rotateY(10deg) scale(0.95);
}

.carousel__slide--active {
  opacity: 1;
  transform: rotateY(0) scale(1.1);
}
.carousel__item {
  margin-top: 5px;
  display: flex;
  justify-content: center;
  align-items: center;
}
.carousel__item img {
  max-width: 20%;
  margin-left: -60%;
  margin-top: 5px;
  /* height: auto; */
}

.carousel__text {
  margin-top: 0px;
  text-align: left;
  font-size: 10px;
  margin-left: -34%;
  margin-right: 10px;
}
.carousel__icon {
  display: none;
}
</style>
