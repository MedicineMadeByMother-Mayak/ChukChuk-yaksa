<!-- 약효기록 상세 -->
<template>
  <div class="effect-detail-view">
    <HeaderForm :title="'약효기록 작성'" :height="'260px'">
      <img class="img" src="../../assests/img/tempPill.png" alt="Image description" />
      <div class="center-aligned">
        <div class="in-header-pill-name">
          <div>
            <strong>{{ pillName }}</strong>
            <div>{{ companyName }}</div>
          </div>
        </div>
      </div>
    </HeaderForm>
    <div class="icon-container">
      <div class="icon-and-text">
        <span class="icon"><i class="fa-regular fa-face-kiss-beam fa-2xl"></i></span>
        <span>효과</span>
      </div>
      <div class="icon-and-text">
        <span class="icon"><i class="fa-regular fa-face-kiss-beam fa-2xl"></i></span>
        <span>중단</span>
      </div>
      <div class="icon-and-text">
        <span class="icon"><i class="fa-regular fa-face-kiss-beam fa-2xl"></i></span>
        <span>부작용</span>
      </div>
    </div>
    <div class="appointment-section">
      <strong class="title-style">TAG</strong>
      <div class="used-tag-list">
        <div class="badge-list">
          <div class="badge-custom" v-for="(tag, index) in userPillEffectList[0].categories[0].usedTags" :key="index">
            <Badge :title="tag.tagName" :backgroundColor="getBackgroundColor(tag.categoryId)" color="white" fontSize="12" padding="4px" />
          </div>
        </div>
      </div>
      <div class="unused-tag-list">
        <div class="badge-custom" v-for="(tag, index) in userPillEffectList[0].categories[0].unUsedTags" :key="index">
          <Badge :title="tag.tagName" backgroundColor="#dfdfdf" color="white" fontSize="12" padding="4px" />
        </div>
      </div>
      <strong class="title-style">MEMO</strong>
      <!-- 수정부분 start -->
      <div class="memo">
        <input type="text" v-model="memo" placeholder="메모를 입력하세요" class="memo-input" maxlength="100" />
      </div>
      <!-- 수정부분 end -->
    </div>
  </div>
</template>

<script setup>
import HeaderForm from "@/common/Form/HeaderForm.vue";
import Badge from "@/common/Badge.vue";

const pillName = "프라닥사캡슐"; // 약 이름 받아오기
const companyName = "메딕스제약";

const userPillEffectList = [
  {
    pillId : 1,
    name : "프라닥사캡슐",
    company : "메딕스제약",
    imageUrl : "../../assests/img/tempPill.png",
    categories : [
        {
          userPillEffectId : 32,
          categoryId : 1,
          categoryName : "부작용",
          usedTags : [  // 사용 중인 태그
              {
                  categoryId : 1,
                  tagId : 1,
                  tagName : "졸림"
              },
              {
                  categoryId : 1,
                  tagId : 1,
                  tagName : "피곤"
              },
              {
                  categoryId : 2,
                  tagId : 1,
                  tagName : "물려"
              },
              {
                  categoryId : 3,
                  tagId : 1,
                  tagName : "시력강화"
              }


          ],
          unUsedTags : [  // 사용하지 않은 태그
              {
                  categoryId : 1,
                  tagId : 10,
                  tagName : "안씀1"
              },
              {
                  categoryId : 1,
                  tagId : 11,
                  tagName : "안씀2"
              },
              {
                  categoryId : 1,
                  tagId : 12,
                  tagName : "안씀3"
              },
              {
                  categoryId : 1,
                  tagId : 13,
                  tagName : "안씀4"
              },
              {
                  categoryId : 1,
                  tagId : 14,
                  tagName : "안씀5"
              },
              {
                  categoryId : 1,
                  tagId : 15,
                  tagName : "안씀6"
              },
          ],
          memo : "부작용 메모"
        }
    ]
  }
]

// categoryId에 따라 다른 backgroundColor를 반환하는 함수
const getBackgroundColor = (categoryId) => {
  switch (categoryId) {
    case 1:
      return "#ff4343"; // categoryId가 1인 경우
    case 2:
      return "#ffbb7f"; // categoryId가 2인 경우
    case 3:
      return "#7fc2ff"; // categoryId가 3인 경우
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
  padding: 0 2px;
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

.icon-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 20vh; /* 전체 화면 높이 설정 */
  width: 100vw; /* 전체 화면 너비 설정 */
}

.icon-container i {
  font-size: 4em; /* 현재 사이즈의 2배 */
  padding: 0 15px; /* 좌우로 20px의 패딩 */
  height: 10px;
}

.icon {
  color: #ffd43b;
  padding-bottom: 15px;
}

.icon-and-text {
  display: flex;
  flex-direction: column;
  align-items: center;
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
  padding: 15px 0 5px 5px;
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
  width: 100%; /* 부모 요소의 가로폭을 최대한 활용 */
  padding: 0; /* 내부 패딩 제거 */
  font-size: 14px; /* 글꼴 크기 설정 */
  box-sizing: border-box; /* 너비와 높이에 패딩과 테두리를 포함하도록 설정 */
  resize: none; /* 크기 조절 비활성화 */
  word-wrap: break-word; /* 긴 텍스트가 영역을 벗어날 때 줄 바꿈 설정 */
}
</style>
