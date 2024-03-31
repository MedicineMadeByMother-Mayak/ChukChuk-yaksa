<!-- 사용법 -->
<!-- 
  <Wave :title="원하는 제목" :height="원하는 높이 기본값 148px" :Link="< 아이콘 누르면 가기 원하는 링크" /> 
-->

<template>
  <div class="header">
    <div class="inner-header flex">
      <HeaderFormOnlyString :title="title" :Link="Link" />
      <p></p>
    </div>
    <container class="inner-header flex" :style="{ height: headerHeight }">
    </container>

    <!--Waves Container-->
    <div>
      <svg
        class="waves"
        xmlns="http://www.w3.org/2000/svg"
        xmlns:xlink="http://www.w3.org/1999/xlink"
        viewBox="0 24 150 28"
        preserveAspectRatio="none"
        shape-rendering="auto"
      >
        <defs>
          <path
            id="gentle-wave"
            d="M-160 44c30 0 58-18 88-18s 58 18 88 18 58-18 88-18 58 18 88 18 v44h-352z"
          />
        </defs>
        <g class="parallax">
          <use
            xlink:href="#gentle-wave"
            x="48"
            y="4"
            fill="rgba(255,255,255,0.7"
          />
          <use xlink:href="#gentle-wave" x="48" y="7" fill="#fff" />
          <!-- <use
            xlink:href="#gentle-wave"
            x="48"
            y="5"
            fill="rgba(255,255,255,0.3)"
          />
          <use xlink:href="#gentle-wave" x="48" y="7" fill="#fff" /> -->
        </g>
      </svg>
    </div>
    <!--Waves end-->
  </div>
  <!--Header ends-->
</template>

<script setup>
import { computed } from "vue";
import HeaderFormOnlyString from "@/common/Form/HeaderFormOnlyString.vue";

const props = defineProps({
  title: {
    type: String,
    default: "Simple CSS Waves",
  },
  height: {
    type: String,
    default: "20px",
  },
  Link: {
    type: String,
    default: "home",
  },
  fontSize: {
    type: String,
    default: "16px",
  },
});

const headerHeight = computed(() => props.height);
</script>

<style scoped>
/* @import url(//fonts.googleapis.com/css?family=Lato:300:400); */

* {
  letter-spacing: -0.8px; /* 원하는 자간 값으로 변경하세요 */
  font-size: 10px;
}

body {
  margin: 0;
}

h1 {
  position: relative;
  right: 11.8px;
  /* letter-spacing: 2px; */
  /* font-size: 1rem; */
  margin: 10px;
}

.inner-header h1 {
  flex-grow: 1; /* h1 태그가 가능한 모든 공간을 차지하도록 함 */
  text-align: center; /* h1 내의 텍스트 중앙 정렬 */
}

.arrow.left {
  flex-grow: 0; /* 아이콘이 추가 공간을 차지하지 않도록 함 */
  margin-right: auto; /* 오른쪽에 자동 마진을 줘서 왼쪽으로 밀어냄 */
}

.header {
  position: relative;
  text-align: center;
  background: linear-gradient(to right, #3183ff 0%, #86e7ee 100%);
  color: black;
  margin-bottom: 10px;
}

.inner-header {
  width: 100%;
  margin: 0;
  padding: 0;
}

.flex {
  /*Flexbox for containers*/
  display: flex;
  justify-content: space-between;
  align-items: center;
  text-align: center;
  padding: auto;
}

.inner-header > i {
  margin: 10px;
}

.waves {
  position: relative;
  width: 100%;
  height: 15vh;
  margin-bottom: -7px; /*Fix for safari gap*/
  min-height: 100px;
  max-height: 150px;
}

.content {
  position: relative;
  height: 20vh;
  text-align: center;
  background-color: white;
}

/* Animation */

.parallax > use {
  animation: move-forever 25s cubic-bezier(0.55, 0.5, 0.45, 0.5) infinite;
}
.parallax > use:nth-child(1) {
  animation-delay: -2s;
  animation-duration: 7s;
}
.parallax > use:nth-child(2) {
  animation-delay: -3s;
  animation-duration: 10s;
}
.parallax > use:nth-child(3) {
  animation-delay: -4s;
  animation-duration: 13s;
}
.parallax > use:nth-child(4) {
  animation-delay: -5s;
  animation-duration: 20s;
}
@keyframes move-forever {
  0% {
    transform: translate3d(-90px, 0, 0);
  }
  100% {
    transform: translate3d(85px, 0, 0);
  }
}

/*Shrinking for mobile*/
@media (max-width: 768px) {
  .waves {
    height: 40px;
    min-height: 40px;
  }
  .content {
    height: 30vh;
  }
}
</style>
