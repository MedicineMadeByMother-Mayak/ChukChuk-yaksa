<template>
  <div class="header-background">
    <!-- <img src="@/assests/img/main_page_background.svg" /> -->
    <img src="@/assests/img/Rectangle.svg" />
    <!-- <img src="@/assests/img/Pil.png" /> -->
  </div>
  <div class="basic-background">
    <div class="basic-content">
      <div class="hello-section">
        <img
          src="@/assests/icon/main_page/main_icon.svg"
          style="border-radius: 30px"
        />
        <div class="hello-text">안녕하세요, {{ store.userName }}님!</div>
      </div>
      <img class="hello-pill" src="@/assests/img/Pil.png" />
      <div class="temp">
        <div class="icon-container">
          <div class="box">
            <div
              class="row"
              style="border-bottom: 0.5px solid rgb(231, 231, 231)"
            >
              <div
                class="column"
                style="border-right: 0.5px solid rgb(231, 231, 231)"
              >
                <a href="/pillpic"
                  ><img src="@/assests/icon/main_page/find_pill_by_pic.svg"
                /></a>
              </div>
              <div
                class="column"
                style="
                  border-left: 0.5px solid rgb(231, 231, 231);
                  border-right: 0.5px solid rgb(231, 231, 231);
                "
              >
                <a href="/ocrlist"
                  ><img src="@/assests/icon/main_page/take_pic.svg"
                /></a>
              </div>
              <div
                class="column"
                style="border-left: 0.5px solid rgb(231, 231, 231)"
              >
                <a href="/pill"
                  ><img src="@/assests/icon/main_page/pill_search.svg"
                /></a>
              </div>
            </div>
            <div class="row" style="border-top: 0.5px solid rgb(231, 231, 231)">
              <div
                class="column"
                style="border-right: 0.5px solid rgb(231, 231, 231)"
              >
                <a href="/pharmacy"
                  ><img src="@/assests/icon/main_page/pharmacy_search.svg"
                /></a>
              </div>
              <div
                class="column"
                style="
                  border-left: 0.5px solid rgb(231, 231, 231);
                  border-right: 0.5px solid rgb(231, 231, 231);
                "
              >
                <a href="/user-pill-effect"
                  ><img src="@/assests/icon/main_page/effect_record.svg"
                /></a>
              </div>
              <div
                class="column"
                style="border-left: 0.5px solid rgb(231, 231, 231)"
              >
                <a href="/take-list"
                  ><img src="@/assests/icon/main_page/take_pill_management.svg"
                /></a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 수정 start -->
    <div class="middle-content">
      <carousel
        autoplay="true"
        wrapAround="true"
        transition="3000"
        :items-to-show="1"
      >
        <slide v-for="(slide, index) in slides" :key="index">
          <img :src="slide" alt="안내배너" />
        </slide>
        <template #addons>
          <pagination />
        </template>
      </carousel>
    </div>
    <!-- 수정 end -->
  </div>
  <!-- Nav-bar용 -->
  <!-- <div style="height: 85px; background-color: #f9f9f9"></div> -->
</template>

<script setup>
import { userStore } from "@/stores/user";
import { ref } from "vue";
import { Carousel, Slide, Pagination, Navigation } from "vue3-carousel";
import Baner from "@/assests/icon/main_page/banner/banner1.svg";
import Baner1 from "@/assests/icon/main_page/banner/banner1.svg";
import Baner2 from "@/assests/icon/main_page/banner/banner2.svg";
import Baner3 from "@/assests/icon/main_page/banner/banner3.svg";
const slides = ref([Baner1, Baner2, Baner3]);

const store = userStore();
store.getUserName(); // 유저 이름 가져오기 위함
</script>

<style scoped>
/* 베경 영역 */
.header-background {
  height: 400px;
  width: 320px;
  margin-top: -110px; /*위로 올려 원 일부를 가리는 용도 */
  margin-bottom: -257px; /*밑에 사진 삼키는 용도 */
  display: flex;
  justify-items: center;
  align-items: center;
}
/* 배경사진 */
.header-background img {
  width: 320px;
  height: 600px;
  margin-top: 100px;
  border-radius: 100px;
}
/* 본문영역 */
.basic-background {
  background-color: #f9f9f9;
  /* height: 100%; */
  display: flex;
  justify-content: top; /* 가로 가운데 정렬 */
  flex-direction: column; /* 요소들을 세로로 정렬 */
}

/* 상단마진 */
.basic-content {
  margin: 90px 0 0 0;
  position: relative;
}

/* 아이콘+이름 */
.hello-section {
  display: flex;
  align-items: center;
  position: absolute;
  z-index: 3; /* 다른 요소 위에 표시되도록 설정 */
  left: 10px;
  top: -10px;
}

.hello-text {
  color: white;
  margin: 0 0 0 5px;
  font-size: 1rem;
  font-weight: 600;
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3); /* 그림자 설정 */
}

/* 알약그림 */
.hello-pill {
  width: 150px;
  z-index: 2;
  position: absolute;
  left: 170px;
  bottom: 125px;
  animation: floatUpDown 3s ease-in-out infinite; /* 애니메이션을 3초간 재생하고, ease-in-out 함수로 부드럽게 변화시키며, 무한 반복합니다. */
}

@keyframes floatUpDown {
  0% {
    transform: translateY(0);
  }
  50% {
    transform: translateY(-10px);
  }
  100% {
    transform: translateY(0);
  }
}

.icon-container {
  width: 270px; /* 가로 50px */
  height: 180px; /* 높이 80px */
}

/* 메뉴박스 */
.box {
  margin-top: 40px;
  width: 100%;
  height: 100%;
  display: flex;
  flex-wrap: wrap;
  border-radius: 10px; /* 바깥 모서리에 border-radius 적용 */
  border: 1px solid rgb(231, 231, 231);
  position: relative; /* 상대 위치 설정 */
  z-index: 3; /* 다른 요소 위에 표시되도록 설정 */
  background-color: white;
  box-shadow: 0px 5px 5px 0px rgba(0, 0, 0, 0.4); /* 아래쪽에만 그림자 추가 */
  /* margin: 43px 0 50px 0; */
}

.row {
  width: 100%;
  height: 50%;
  display: flex;
}

.column {
  width: 33.333%;
  height: 100%;

  display: flex;
  justify-content: center;
  align-items: center;
}

.temp {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.middle-content {
  width: 100%;
  margin-top: 80px; /* 상단 여백을 조절하기 위해 auto로 수정 */
}

.middle-content img {
  width: 300px;
}

.carousel__pagination {
  margin-left: -35px;
}
</style>
