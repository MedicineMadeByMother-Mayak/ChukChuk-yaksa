<!-- 약효기록 상세 -->
<template>
  <div class="effect-detail-view">
<<<<<<< HEAD
    <HeaderForm :title="'약효기록 작성'" :height="'260px'">
=======
    <HeaderForm :title="'약효기록 작성'" :height="'260px'" :Link="Link">
>>>>>>> 626bca03e5d4973cfd50f9a2f8dc11c51770da86
      <img
        class="pill-img"
        src="../../assests/img/tempPill.png"
        alt="Image description"
      />
      <div class="center-aligned">
        <div class="in-header-pill-name">
          <div>
            <strong>{{ pillName }}</strong>
            <div>{{ company }}</div>
          </div>
        </div>
      </div>
    </HeaderForm>
    <div class="icon-container">
      <div class="icon-container">
        <img
          id="effectFace"
<<<<<<< HEAD
          :src="EffectFace"
          alt="Effect Face Icon"
          @click="toggleOpacity('effectFace')"
        />
        <img
          id="stopFace"
          :src="StopFace"
          alt="StopFace Face Icon"
          @click="toggleOpacity('stopFace')"
        />
        <img
          id="sideEffectFace"
          :src="SideEffectFace"
          alt="SideEffectFace Face Icon"
          @click="toggleOpacity('sideEffectFace')"
=======
          data-value="3"
          :src="EffectFace"
          alt="Effect Face Icon"
          @click="clickFace('effectFace')"
        />
        <img
          id="stopFace"
          data-value="2"
          :src="StopFace"
          alt="StopFace Face Icon"
          @click="clickFace('stopFace')"
        />
        <img
          id="sideEffectFace"
          data-value="1"
          :src="SideEffectFace"
          alt="SideEffectFace Face Icon"
          @click="clickFace('sideEffectFace')"
>>>>>>> 626bca03e5d4973cfd50f9a2f8dc11c51770da86
        />
      </div>
    </div>
    <div class="appointment-section">
      <strong class="title-style">TAG</strong>
      <div class="used-tag-list" @click="activateInput">
        <!-- 추가된태그 -->
        <div class="badge-list">
<<<<<<< HEAD
          <div
            class="badge-custom"
            v-for="(tag, index) in userPillEffectList[0].categories[0].usedTags"
            :key="index"
          >
            <Badge
              :title="tag.tagName"
              :backgroundColor="getBackgroundColor(tag.categoryId)"
              color="white"
              fontSize="12"
              padding="4px 15px 4px 15px"
            />
          </div>
=======
          <Badge
            class="used-custom"
            v-for="(tag, index) in usedTags"
            :key="index"
            :title="tag.tagName"
            backgroundColor="#7fc2ff"
            color="white"
            fontSize="0.9rem"
            padding="4px 15px 4px 15px"
          />
>>>>>>> 626bca03e5d4973cfd50f9a2f8dc11c51770da86
        </div>
        <!-- 직접추가하는 인풋 -->
        <input
          class="tag-input"
          ref="inputField"
          v-model="tagMessage"
          v-on:keyup="onEnter"
        />
      </div>
      <!-- 사용후보태그 -->
      <div class="unused-tag-list">
<<<<<<< HEAD
        <div
          class="badge-custom"
          v-for="(tag, index) in userPillEffectList[0].categories[0].unUsedTags"
          :key="index"
        >
          <Badge
            :title="tag.tagName"
            backgroundColor="#dfdfdf"
            color="white"
            fontSize="12"
            padding="4px"
          />
        </div>
      </div>
      <img
        :src="underDirection"
        style="display: block; margin: 20px auto 12px"
      />
      <div style="border-bottom: 1px solid black"></div>
=======
        <Badge
          class="unused-custom"
          v-for="(tag, index) in unUsedTags"
          :key="index"
          :title="tag.tagName"
          backgroundColor="#dfdfdf"
          color="#5A5A5A"
          fontSize="12"
          padding="3px 10px 3px 10px"
          @click="addUnusedTag(tag.tagId)"
        />
      </div>
      <!-- <img
        v-if="showBadges"
        :src="underDirection"
        style="display: block; margin: 20px auto 12px; cursor: pointer"
        @click="showBadges = false"
      /> -->
      <!-- <div style="border-bottom: 1px solid #bbbbbb"></div> -->
      <br />
>>>>>>> 626bca03e5d4973cfd50f9a2f8dc11c51770da86
      <strong class="title-style">MEMO</strong>

      <div class="memo">
        <textarea
          class="memo-input"
          type="text"
          v-model="memo"
<<<<<<< HEAD
=======
          @change="memoUpdate"
>>>>>>> 626bca03e5d4973cfd50f9a2f8dc11c51770da86
          placeholder="메모를 입력하세요"
          maxlength="100"
        />
      </div>
    </div>
  </div>
  <div>
    <EffectFaceIcon />
  </div>
  <!-- Nav-bar용 -->
  <div style="height: 85px; background-color: #f9f9f9"></div>
</template>

<script setup>
import HeaderForm from "@/common/Form/HeaderForm.vue";
import Badge from "@/common/Badge.vue";
import EffectFace from "@/assests/icon/effectFace.svg";
import StopFace from "@/assests/icon/stopFace.svg";
import SideEffectFace from "@/assests/icon/sideEffectFace.svg";
import underDirection from "@/assests/icon/underDirection.svg";
import { ref, onMounted } from "vue";
import { userPillEffectStore } from "@/stores/userPillEffect";
import { pillSearchStore } from "@/stores/pillSearch";
import { useRoute } from "vue-router";

const route = useRoute();

const userPillEffect = userPillEffectStore();
const pillSearch = pillSearchStore();

const userPillEffectDtoList = ref("");
const sideEffectList = ref([]);
const stopList = ref([]);
const effectList = ref([]);
const pillName = ref("");
const company = ref("");
const usedTags = ref("");
const unUsedTags = ref("");
const userPillEffectId = ref();
const memo = ref("");
const isVisibleUnUsed = ref(false);
const tagMessage = ref(null);
const tabId = ref(3); //현재 탭 어딘지 저장 효과:3, 중단:2, 부작용:1
const pillId = ref();
const inputField = ref(null);

const activateInput = () => {
  inputField.value.focus();
};

const addUnusedTag = async (tagId) => {
  await userPillEffect.createUnUsedTag(tagId, pillId.value);
  //동기화
  await syncroData(pillId.value);
};

//input창에 새로운 태그 작성시 동작
async function onEnter(event) {
  if (event.keyCode === 13) {
    // 새로운 태그를 바로 저장 및 동기화(param:카테고리ID, 태그Name)
    const savedTagId = await userPillEffect.createTag(
      tabId.value,
      tagMessage.value
    );
    tagMessage.value = null;

    // 사용중인 태그로 연결
    await userPillEffect.createUnUsedTag(savedTagId, pillId.value);

    await syncroData(pillId.value);
  }
}

//약효기록 데이터 동기화
async function syncroData(pillId) {
  await userPillEffect.getUserPillEffectInfo(pillId);
  pillName.value = pillSearch.name;
  company.value = pillSearch.company;
  sideEffectList.value = userPillEffect.sideEffect;
  stopList.value = userPillEffect.stop;
  effectList.value = userPillEffect.effect;
  userPillEffectDtoList.value = userPillEffect.userPillEffectDtoList;

  if (tabId.value == 3) {
    clickFace("effectFace");
  } else if (tabId.value == 2) {
    clickFace("stopFace");
  } else if (tabId.value == 1) {
    clickFace("sideEffectFace");
  }
}

onMounted(async () => {
  pillId.value = route.params.pillId;
  await pillSearch.getPillInfo(pillId.value);
  await syncroData(pillId.value);
});

// 이미지 투명도를 토글하는 함수
const clickFace = (imageId) => {
  const images = ["effectFace", "stopFace", "sideEffectFace"];

  images.forEach((img) => {
    const currentImage = document.getElementById(img);

<<<<<<< HEAD
    if (img !== imageId) {
      if (currentImage) {
        // 이미지 요소가 존재하는지 확인
        currentImage.style.opacity = "0.5";
      }
    } else {
      currentImage.style.opacity = "1.0";
=======
    if (img == imageId) {
      if (currentImage) {
        // 이미지 요소가 존재하는지 확인
        const currentCategoryId = currentImage.dataset.value - 1;
        const userPillEffect = userPillEffectDtoList.value[currentCategoryId];

        userPillEffectId.value = userPillEffect.userPillEffectId;
        currentImage.style.opacity = "1.0";
        usedTags.value = userPillEffect.usedTags;
        unUsedTags.value = userPillEffect.unUsedTags;
        memo.value = userPillEffect.memo;

        tabId.value = currentImage.dataset.value;
      }
    } else {
      currentImage.style.opacity = "0.5";
>>>>>>> 626bca03e5d4973cfd50f9a2f8dc11c51770da86
    }
  });
};

// categoryId에 따라 다른 backgroundColor를 반환하는 함수
const getBackgroundColor = (categoryId) => {
  switch (categoryId) {
    case 1:
      return "#7fc2ff"; // categoryId가 1인 경우 (적당한 빨강 : #ff4343)
    case 2:
      return "#ffbb7f"; // categoryId가 2인 경우
    case 3:
      return "#ffbb7f"; // categoryId가 3인 경우
    default:
      return "#ffffff"; // 기본값은 흰색
  }
};

// 메모 업데이트
const memoUpdate = (event) => {
  console.log("입력 값이 변경되었습니다:", event.target.value);
  userPillEffect.updateMemo(userPillEffectId.value, memo.value);
};
</script>

<style scoped>
.used-custom {
  padding: 0 4px;
  margin-right: 7px;
  font-weight: bold;
}
.unused-custom {
  /* margin-right: 7px; */
  /* color: black; */
  font-weight: bold;
}
.tag-input {
  width: 100px;
  border: none;
  padding: 0;
  /* margin: 0px 0px 0px 10px; */
  outline: none;
  box-sizing: border-box;
}
.badge-list {
  display: flex;
}

.appointment-section {
  padding: 7px 20px;
}

.center-aligned {
  display: flex;
  justify-content: center;
}

.in-header-pill-name {
  text-align: center;
  background-color: white;
  border-radius: 39px 39px 0 0;
  padding: 10px;
  margin: 10px; /* 양쪽에 10px 여백 설정 */
  width: 120px;
}

.img {
  border-radius: 10px;
}

.pill-img {
  border-radius: 10px;
  max-height: 150px;
}

.icon-container {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%; /* 전체 화면 너비 설정 */
  margin-top: 15px;
}

.icon-container img {
  padding: 0 5px;
}

/* 사용중인 태그 리스트 */
.used-tag-list {
  display: flex;
  align-items: center;
  background-color: rgb(255, 255, 255);
  border-radius: 5px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
  padding: 10px 7px;
  overflow-x: auto; /* 수평 스크롤바 추가 */
}

/* 사용중이지 않은 태그 리스트 */
.unused-tag-list {
  display: flex;
  flex-wrap: wrap; /* 요소들이 넘칠 때 다음 줄로 이동하도록 설정 */
  border-radius: 5px;
  padding: 12px 0px;
  gap: 7px;
}

/* 태그 추가 div */
/* .add-tag {
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 10px 0 0 0;
} */

/* 사용중 태그 추가 input */
.add-tag-used-input {
  display: flex;
  width: 30%;
  height: 1.2rem;
  align-items: center;
  margin: 0 10px 0 0;
  border-radius: 5px;
  border: 1px solid lightblue;
}

/* 사용중 태그 추가 버튼 */
.add-tag-used-button {
  height: 1.5rem;
  background-color: rgb(105, 190, 255);
  border-radius: 5px;
  border: none;
  color: white;
  padding: 3px 6px;
}

/* 사용중 태그 추가 input */
.add-tag-unused-input {
  display: flex;
  width: 30%;
  height: 1.2rem;
  align-items: center;
  margin: 0 10px 0 0;
  border-radius: 5px;
  border: 1px solid lightgray;
}

/* 사용중 태그 추가 버튼 */
.add-tag-unused-button {
  height: 1.5rem;
  background-color: lightgray;
  border-radius: 5px;
  border: none;
  color: white;
  padding: 3px 6px;
}

/* 제목 스타일 */
.title-style {
  font-size: 15px;
  font-family: Arial, Helvetica, sans-serif;
  margin: 5px 0px;
  display: flex;
  align-items: center;
  padding: 7px 0 5px 5px;
}

/* 메모 */
.memo {
  display: flex;
  background-color: rgb(255, 255, 255);
  border-radius: 5px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
  padding: 7px 7px;
  min-height: 100px;
  align-items: flex-start; /* 자식 요소를 열의 시작 부분에 정렬 */
}

/* 메모 input box */
.memo-input {
  outline: none; /* 포커스 테두리 제거 */
  border: none; /* 테두리 제거 */
  background-color: transparent; /* 배경색 투명하게 설정 */
  height: 100px;
  width: 100%; /* 부모 요소의 가로폭을 최대한 활용 */
  padding: 0; /* 내부 패딩 제거 */
  box-sizing: border-box; /* 너비와 높이에 패딩과 테두리를 포함하도록 설정 */
  resize: none; /* 크기 조절 비활성화 */
  word-wrap: break-word; /* 긴 텍스트가 영역을 벗어날 때 줄 바꿈 설정 */
}

.icon-container img {
  padding: 0 5px;
}
</style>
