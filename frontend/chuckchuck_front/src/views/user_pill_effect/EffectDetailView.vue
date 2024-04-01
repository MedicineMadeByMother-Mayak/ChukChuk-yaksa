<!-- 약효기록 상세 -->
<template>
  <div class="effect-detail-view">
    <HeaderForm :title="'약효기록 작성'" :height="'260px'">
      <img class="pill-img" src="../../assests/img/tempPill.png" alt="Image description" />
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
        <img id="effectFace" data-value="3" :src="EffectFace" alt="Effect Face Icon" @click="clickFace('effectFace')" />
        <img id="stopFace" data-value="2" :src="StopFace" alt="StopFace Face Icon" @click="clickFace('stopFace')" />
        <img id="sideEffectFace" data-value="1" :src="SideEffectFace" alt="SideEffectFace Face Icon" @click="clickFace('sideEffectFace')" />
      </div>
    </div>
    <div class="appointment-section">
      <strong class="title-style">TAG</strong>
      <div class="used-tag-list">
        <div class="badge-list">
          <div class="badge-custom" v-for="(tag, index) in usedTags" :key="index">
            <Badge
              :title="tag.tagName"
              backgroundColor="#7fc2ff"
              color="white"
              fontSize="12"
              padding="4px 15px 4px 15px"
            />
          </div>
        </div>
      </div>
      <div class="unused-tag-list">
        <div class="badge-custom" v-for="(tag, index) in unUsedTags" :key="index">
          <Badge :title="tag.tagName" backgroundColor="#dfdfdf" color="white" fontSize="12" padding="4px" />
        </div>
      </div>
      <img :src="underDirection" style="display: block; margin: 20px auto 12px;">
      <div style="border-bottom: 1px solid black; "></div>
      <strong class="title-style">MEMO</strong>

      <div class="memo">
        <textarea class="memo-input" type="text" v-model="memo" placeholder="메모를 입력하세요" maxlength="100" />
      </div>
    </div>
  </div>
  <div>
    <EffectFaceIcon />
  </div>
  <!-- Nav-bar용 -->
  <div style="height: 85px; background-color: #f9f9f9;"></div>
</template>

<script setup>
import HeaderForm from "@/common/Form/HeaderForm.vue";
import Badge from "@/common/Badge.vue";
import EffectFace from "@/assests/icon/effectFace.svg";
import StopFace from "@/assests/icon/stopFace.svg";
import SideEffectFace from "@/assests/icon/sideEffectFace.svg";
import underDirection from "@/assests/icon/underDirection.svg";
import { ref, onMounted } from "vue";
import { userPillEffectStore } from '@/stores/userPillEffect';
import { pillSearchStore } from '@/stores/pillSearch';

const userPillEffect = userPillEffectStore();
const pillSearch = pillSearchStore();

const userPillEffectDtoList = ref('');
const sideEffectList = ref([]);
const stopList = ref([]);
const effectList = ref([]);

const pillName = ref('');
const company = ref('');

const usedTags = ref('');
const unUsedTags = ref('');
const memo = ref('');

onMounted(async () => {
  await userPillEffect.getUserPillEffectInfo(4);  // 약효 기록 리스트에서 클릭할 때 해당 pillId로 실행할거라 삭제될 코드
  await pillSearch.getPillInfo(4);  // 이것도 마찬가지

  pillName.value = pillSearch.name;
  company.value = pillSearch.company;

  sideEffectList.value = userPillEffect.sideEffect;
  stopList.value = userPillEffect.stop;
  effectList.value = userPillEffect.effect;
  userPillEffectDtoList.value = userPillEffect.userPillEffectDtoList;

  clickFace('effectFace');  // 시작하면 '효과' 클릭하도록 디폴트 세팅
})


// 이미지 투명도를 토글하는 함수
const clickFace = (imageId) => {
  const images = ["effectFace", "stopFace", "sideEffectFace"];

  images.forEach((img) => {
    const currentImage = document.getElementById(img);
    
    if (img == imageId) {
      if (currentImage) { // 이미지 요소가 존재하는지 확인
        const currentCategoryId = currentImage.dataset.value - 1;
        const userPillEffect = userPillEffectDtoList.value[currentCategoryId];
        
        currentImage.style.opacity = "1.0";
        usedTags.value = userPillEffect.usedTags;
        unUsedTags.value = userPillEffect.unUsedTags;
        memo.value = userPillEffect.memo;
        console.log('============');
      }
    } else { currentImage.style.opacity = "0.5"; }
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

</script>

<style scoped>
.badge-list {
  display: flex;
}

.badge-custom {
  padding: 0 4px;
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
  height: 20vh; /* 전체 화면 높이 설정 */
  width: 100vw; /* 전체 화면 너비 설정 */
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
  padding: 7px 7px;
}

/* 사용중이지 않은 태그 리스트 */
.unused-tag-list {
  display: flex;
  align-items: center;
  background-color: rgb(255, 255, 255);
  border-radius: 5px;
  padding: 12px 7px;
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
