<template>
  <div>
    <!-- 헤더 -->
    <Wave title="복용 관리" height="120px" />

    <!-- 척척약사의 조언 -->
    <Carousel
      :autoplay="2000"
      :itemsToShow="1.25"
      :wrapAround="true"
      :transition="1000"
    >
      <Slide v-for="(item, index) in advice" :key="index">
        <div class="carousel__item">
          <div class="carousel__img">
            <img class="logo" :src="logo" alt="chukchuklogo" />
          </div>
          <div class="carousel__text">
            <div style="font-size: 12px; margin-bottom: 3px">
              <strong>{{ item.title }}</strong>
            </div>
            <div>
              <span style="font-weight: bold; color: #145bda">{{
                item.pill1
              }}</span>
              <span>{{ item.content1 }}</span>
              <span style="font-weight: bold; color: #145bda">{{
                item.pill2
              }}</span>
              <span>{{ item.content2 }}</span>
            </div>
          </div>
        </div>
      </Slide>
      <template #addons>
        <Pagination class="page" />
      </template>
    </Carousel>

    <!-- 알람 리스트 -->
    <div class="alarms">
      <!-- 알람 추가 버튼 -->
      <div>
        <button
          v-if="alarmstore.offAlarmList.length"
          class="rounded-button"
          @click="toggleModal"
        >
          <span
            ><font-awesome-icon
              :icon="['fas', 'circle-plus']"
              style="color: #424242"
              size="lg"
          /></span>
        </button>

        <!-- 알람 모달 -->
        <AlarmModal
          v-model="showModal"
          :modalData="modalData"
          @save="saveAlarm"
        />
      </div>

      <!-- 등록된 알람 리스트 -->
      <div
        v-for="(alarm, index) in alarmstore.alarmList"
        :key="`pill-date-${index}`"
      >
        <button class="rounded-button" @click="modifyAlarm(alarm.takeListId)">
          <font-awesome-icon
            class="alarm-icon"
            :icon="['fas', 'bell']"
            style="color: #ffd43b"
          />
          <span
            class="alarm-text"
            style="font-weight: bold; font-size: smaller"
          >
            {{ truncateName(alarm.takeListName, 8, 6) }}
          </span>
        </button>
      </div>
    </div>

    <div class="takelist-container">
      <div class="menu">
        <div class="menu-left">
          <!-- <img src="@/assests/icon/pill.png" alt="복용리스트" /> -->
          <font-awesome-icon
            :icon="['fas', 'paperclip']"
            style="margin-right: 7px"
          />
          <div v-if="!isNow">
            <strong style="color: black">복용중인 </strong>리스트
          </div>
          <div v-if="isNow">
            <strong style="color: black">복용완료한 </strong>리스트
          </div>
        </div>
        <div class="menu-right">
          <button
            v-if="isNow"
            class="gray-button"
            @click="(currentTab = 'current'), toggleIsNow()"
          >
            현재 복용중인 약
          </button>
          <button
            v-if="!isNow"
            class="gray-button"
            @click="(currentTab = 'finished'), toggleIsNow()"
          >
            과거에 먹은 약
          </button>
          <button class="navy-button" @click="openSelectPillModal">추가</button>
        </div>
      </div>
      <hr style="margin: 3px" />

      <!-- 복용리스트 목록 -->

      <!-- 현재 복용중인 약 -->
      <div v-if="currentTab === 'current'">
        <div
          v-for="(takeListData, index) in currentTakeList"
          :key="`pill-date-${index}`"
          class="pill-entry"
        >
          <!-- 복용리스트 날짜와 제목 -->
          <div class="pill-date">
            <div class="pill-date-date">
              {{ formatDate(takeListData.createDate) }}
            </div>
            <span v-if="!takeListData.edit"
              >[ {{ truncateName(takeListData.takeListName, 10, 8) }} ]</span
            >
            <input
              v-else
              v-model="takeListData.takeListName"
              @blur="saveChangeName(takeListData)"
              @keydown.enter="saveChangeName(takeListData)"
            />
            <img
              src="@/assests/icon/edit.png"
              alt="편집 아이콘"
              @click="openTakeListModal(takeListData.takeListId, index)"
            />
          </div>

          <!-- 리스트 별 약 목록 -->
          <ul class="pills-list">
            <li
              v-for="(
                currentPillData, index
              ) in takeListData.takeListPillInfoList"
              :key="`pill-details-${index}`"
              class="pill-info"
            >
              <!-- 약 카드 -->
              <Content
                :pillId="currentPillData.pillId"
                :pillName="currentPillData.name"
                :imageUrl="currentPillData.imageUrl"
                :type="currentPillData.type"
                :takeListName="takeListData.takeListName"
                :warningPregnant="currentPillData.warningPregnant"
                :warningUseDate="currentPillData.warningUseDate"
                :warningElders="currentPillData.warningElders"
                :warningTogether="currentPillData.warningTogether"
              />
            </li>
          </ul>
        </div>
      </div>

      <div v-if="currentTab === 'finished'">
        <div
          v-for="(takeListData, index) in finishedTakeList"
          :key="`pill-date-${index}`"
          class="pill-entry"
        >
          <!-- 복용리스트 날짜와 제목 -->
          <div class="pill-date">
            <div class="pill-date-date">
              {{ formatDate(takeListData.createDate) }}
            </div>
            <span v-if="!takeListData.edit"
              >[{{ takeListData.takeListName }}]</span
            >
            <input
              v-else
              v-model="takeListData.takeListName"
              @blur="saveChangeName(takeListData)"
              @keydown.enter="saveChangeName(takeListData)"
            />
            <img
              src="@/assests/icon/edit.png"
              alt="편집 아이콘"
              @click="openTakeListModal(takeListData.takeListId, index)"
            />
          </div>

          <!-- 리스트 별 약 목록 -->
          <ul class="pills-list">
            <li
              v-for="(
                currentPillData, index
              ) in takeListData.takeListPillInfoList"
              :key="`pill-details-${index}`"
              class="pill-info"
            >
              <!-- 약 카드 -->
              <Content
                :pillId="currentPillData.pillId"
                :pillName="currentPillData.name"
                :imageUrl="currentPillData.imageUrl"
                :type="currentPillData.type"
                :takeListName="takeListData.takeListName"
                :warningPregnant="currentPillData.warningPregnant"
                :warningUseDate="currentPillData.warningUseDate"
                :warningElders="currentPillData.warningElders"
                :warningTogether="currentPillData.warningTogether"
              />
            </li>
          </ul>
        </div>
      </div>

      <ListEditModal
        v-if="isTakeListModalOpen"
        @close="closeTakeListModal"
        @update="handleUpdate"
      />
      <AlarmModalTime
        v-model="showAlarmModalTime"
        :selectTakeList="selectTakeList"
        :createOrModify="createOrModify"
      />
      <SelectPillModal
        v-model="isSelectPillModalOpen"
        :modalData="pillModalData"
        @close="closeSelectPillModal"
      />
    </div>
  </div>

  <Footer></Footer>
</template>

<script setup>
import Footer from "@/common/FooterNoColor.vue";
import Wave from "@/common/Wave.vue";
import { ref, onMounted, computed } from "vue";
import Content from "./components/Content.vue";
import ListEditModal from "./components/ListEditModal.vue";
import { Carousel, Pagination, Slide, Navigation } from "vue3-carousel";
import "vue3-carousel/dist/carousel.css";
import "@/assests/css/carousel.css";
import { takelistStore } from "@/stores/takelist";
import { alarmStore } from "@/stores/alarm";
import logo from "@/assests/img/Group.png";
import AlarmModal from "@/views/take_list/components/AlarmModal.vue";
import AlarmModalTime from "@/views/take_list/components/AlarmModalTime.vue";
import SelectPillModal from "./components/SelectPillModal.vue";
const alarmstore = alarmStore();
const store = takelistStore();
// ----------------------------------------
const isTakeListModalOpen = ref(false);
const showAlarmModalTime = ref(false);
const selectTakeList = ref(0);
const showModal = ref(false);
const isSelectPillModalOpen = ref(false);
const currentTab = ref("current");
const isNow = ref(false);

async function toggleIsNow() {
  isNow.value = !isNow.value;
}

// 0 이면 생성 1 이면 수정
const createOrModify = ref(0);

onMounted(async () => {
  await store.getTakeListPageDatas();
  alarmstore.getAlarmList();
});

const truncateName = (name, num1, num2) => {
  if (name.length > num1) {
    return name.slice(0, num2) + " ..";
  } else {
    return name;
  }
};

function modifyAlarm(id) {
  createOrModify.value = 1;
  selectTakeList.value = id;
  showAlarmModalTime.value = true;
}

function saveAlarm(id) {
  createOrModify.value = 0;
  showModal.value = false;
  selectTakeList.value = id;
  showAlarmModalTime.value = true;
}

//리스트 모달창 띄우기
const openTakeListModal = (id, index) => {
  store.currentTakeList = currentTakeList.value[index];
  isTakeListModalOpen.value = true;
};

//리스트 모달창 종료
const closeTakeListModal = () => {
  isTakeListModalOpen.value = false;
};

const openSelectPillModal = () => {
  isSelectPillModalOpen.value = true;
};

const closeSelectPillModal = () => {
  isSelectPillModalOpen.value = false;
};

// 모달에서 전달받은 데이터로 복용 리스트 리로드
const handleUpdate = () => {
  window.location.reload(true);
};

//이름 변경 후 저장
const saveChangeName = async (takeListData) => {
  takeListData.edit = false; // 편집 모드 종료
  //1. alramList에서 takeListId가 takeListData.takeListId와 같은 리스트 찾기
  const index = alarmstore.alarmList.findIndex(
    (item) => item.takeListId == takeListData.takeListId
  );
  //2. alramList.takeListname을 takeListDat.takeListName으로 변경하기
  if (index !== -1) {
    alarmstore.alarmList[index].takeListName = takeListData.takeListName;
  }
  await store.rename(takeListData.takeListId, takeListData.takeListName);
};
// -------------------------------------------------------------

// 현재 복용중인 리스트
const currentTakeList = computed(() =>
  store.takelistpagedatas.filter((item) => !item.isFinished)
);

// 복용 완료한 리스트
const finishedTakeList = computed(() =>
  store.takelistpagedatas.filter((item) => item.isFinished)
);

const modalData = [["어떤 약에 대한 알람을 등록하시겠어요?", true, {}, {}]];
const pillModalData = [
  ["약 사진을 찍어서 등록하기", true, { params: {}, Link: "pillpic" }],
  ["약 검색해서 등록하기", true, { params: {}, Link: "pillsearch" }],
  ["약봉투 찍어서 등록하기", true, { params: {}, Link: "ocrlist" }],
];
const toggleModal = async () => {
  await alarmstore.getOffAlarmList();
  showModal.value = !showModal.value;
};

const formatDate = (dateString) => {
  const date = new Date(dateString);
  const year = date.getFullYear();
  const month = ("0" + (date.getMonth() + 1)).slice(-2);
  const day = ("0" + date.getDate()).slice(-2);
  return `${year}. ${month}. ${day}`;
};

// 척척약사의 조언
const advice = ref([
  {
    img: logo,
    title: "척척약사의 조언",
    pill1: "피콤씨에프정",
    pill2: "비타민 중독",
    content1: "은 과량복용 시 ",
    content2:
      "의 위험이 있고 속쓰림, 두드러기, 어지럼증 증상이 나타날 경우 즉시 의사나 약사와 상의해야 합니다.",
  },
  {
    img: logo,
    title: "척척약사의 조언",
    pill1: "게보린정",
    pill2: "아세트아미노펜",
    content1: "은 ",
    content2:
      "을 포함하는 다른 제품과 복용하면 간손상을 일으킬 수 있습니다. 일일 최대 4,000 mg을 초과하여 복용하지 마세요.",
  },
  {
    img: logo,
    title: "척척약사의 조언",
    pill1: "트루포뮬러",
    pill2: "프라닥사캡슐",
    content1: "(오메가3)와 ",
    content2:
      "(항응고제)은 함께 섭취시 출혈의 위험이 있을 수 있어 주의가 필요합니다.",
  },
]);
</script>

<style scoped>
@keyframes rotateAndPause {
  0% {
    transform: rotate(0deg);
  }

  55% {
    transform: rotate(360deg);
  }

  100% {
    transform: rotate(360deg);
  }
}

.alarms {
  padding: 15px 10px;
  margin-top: 10px;
  background-color: #c0e6fc;
  display: flex;
  width: 300px;
  height: 30px;
  white-space: nowrap;
  gap: 10px;
  overflow-x: auto;
}

.takelist-container {
  margin: 0px 12px 0px 12px;
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
  margin-left: 5px;
  margin-bottom: -17px;
  font-weight: bolder;
  font-size: small;
}
.menu-left img {
  width: 100px;
  margin-right: 5px;
  margin-left: -4px;
}
.menu-right {
  text-align: right;
  margin-bottom: 5px;
}
.menu img {
  width: 15px;
  height: 15px;
}

button {
  display: inline;
  margin-right: 5px;
  margin-top: 20px;
  margin-bottom: -10px;
}
.gray-button {
  background-color: #cccccc;
  color: #ffffff;
  font-weight: bold;
  border-radius: 5px;
  padding: 5px 10px;
  font-size: 10px;
  font-weight: 200;
  border: none;
  cursor: pointer;
  margin-right: 5px;
}

.navy-button {
  background-color: #242291;
  color: #ffffff;
  font-weight: bold;
  border-radius: 5px;
  font-weight: 200;
  padding: 5px 15px;
  font-size: 10px;
  border: none;
  cursor: pointer;
}

ol {
  padding-inline-start: 0px;
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
.pill-info:first-child::after {
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

.pill-info:first-child::after {
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
  margin-top: 10px;
}
.pill-date-date {
  color: #083688;
  margin-right: 3px;
  margin-bottom: -1px;
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
  width: 95px;
  border: none;
  background-color: white;
  padding: 5px 8px;
  margin: 2.5px;
  border-radius: 10px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.05);
  cursor: pointer;
  position: relative;
}

.alarm-icon {
  position: absolute;
  top: 6px;
  left: 10px;
}

.alarm-text {
  margin-left: 10px;
}

/* 척척약사의 조언 CSS */

.logo {
  animation: rotateAndPause 3s ease-in-out infinite;
}

.carousel__slide {
  box-shadow: 0 0.1em 0.3em rgba(0, 0, 0, 0.1);
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
  margin-top: -5px;
  text-align: left;
  font-size: 10px;
  margin-left: -34%;
  margin-right: 10px;
}
.carousel__icon {
  display: none;
}
</style>
