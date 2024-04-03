<!-- 사용법 -->
<!-- 
  <Wave :title="원하는 제목" :height="원하는 높이 기본값 148px" :Link="< 아이콘 누르면 가기 원하는 링크" /> 
-->

<template>
<<<<<<< HEAD
  <header>
    <div class="container">
      <img :src="wave" id="pin" />
      <img :src="wave1" id="left" @animationend="handleAnimationEnd" />
      <!-- <img :src="wave2" id="right" @animationend="handleAnimationEnd" /> -->
    </div>
  </header>
</template>

<script setup>
import { ref, onMounted } from "vue";
import wave from "@/assests/img/waves/wave.svg";
import wave1 from "@/assests/img/waves/wave-1.svg";
import wave2 from "@/assests/img/waves/wave-2.svg";

let isLeftAnimation = ref(true);
=======
  <div class="header">
    <div class="inner-header flex">
      <HeaderFormOnlyString :title="title" :Link="Link" />
      <p></p>
    </div>
    <p class="inner-header flex" :style="{ height: headerHeight }"></p>

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
>>>>>>> 626bca03e5d4973cfd50f9a2f8dc11c51770da86

const handleAnimationEnd = () => {
  leftAnimation.value = !leftAnimation.value; // 애니메이션 종료시 상태 변경
};
</script>

<style scoped>
<<<<<<< HEAD
header {
  background-color: #ffffff;
  height: 10vh;
=======
/* @import url(//fonts.googleapis.com/css?family=Lato:300:400); */

* {
  letter-spacing: -0.8px; /* 원하는 자간 값으로 변경하세요 */
  font-size: 10px;
}

body {
  margin: 0;
>>>>>>> 626bca03e5d4973cfd50f9a2f8dc11c51770da86
}

.container {
  max-width: 100%; /* 이미지가 부모 요소에 맞춰질 수 있도록 */
  max-height: 100%;
  position: relative;
<<<<<<< HEAD
}
#pin {
  position: absolute;
  top: -20px;
=======
  right: 11.8px;
  /* letter-spacing: 2px; */
  /* font-size: 1rem; */
  margin: 10px;
>>>>>>> 626bca03e5d4973cfd50f9a2f8dc11c51770da86
}

#left {
  position: absolute;
  bottom: -150px;
  animation: move-1 4s infinite;
}

#right {
  position: absolute;
  bottom: -150px;
  animation: move-2 5s infinite;
}

<<<<<<< HEAD
@keyframes move-1 {
  50% {
    transform: translateY(70px);
=======
.header {
  position: sticky;
  width: 100%;
  top: 0;
  text-align: center;
  background: linear-gradient(to right, #3183ff 0%, #86e7ee 100%);
  color: black;
  margin-bottom: 10px;
  z-index: 999;
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
  /* height: 15vh; */
  height: 15%;
  margin-bottom: -7px; /*Fix for safari gap*/
  min-height: 100px;
  max-height: 150px;
}

.content {
  position: relative;
  height: 20%;
  /* height: 20vh; */
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
>>>>>>> 626bca03e5d4973cfd50f9a2f8dc11c51770da86
  }
}

@keyframes move-2 {
  50% {
    transform: translateY(50px);
  }
<<<<<<< HEAD
=======
  .content {
    /* height: 30vh; */
    height: 30%;
  }
>>>>>>> 626bca03e5d4973cfd50f9a2f8dc11c51770da86
}
</style>
