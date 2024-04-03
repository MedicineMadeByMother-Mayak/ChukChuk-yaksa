<!-- 사용법 -->
<!-- 
  <Wave :title="원하는 제목" :height="원하는 높이 기본값 148px" :Link="< 아이콘 누르면 가기 원하는 링크" /> 
-->

<template>
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

const handleAnimationEnd = () => {
  leftAnimation.value = !leftAnimation.value; // 애니메이션 종료시 상태 변경
};
</script>

<style scoped>
header {
  background-color: #ffffff;
  height: 10vh;
}

.container {
  max-width: 100%; /* 이미지가 부모 요소에 맞춰질 수 있도록 */
  max-height: 100%;
  position: relative;
}
#pin {
  position: absolute;
  top: -20px;
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

@keyframes move-1 {
  50% {
    transform: translateY(70px);
  }
}

@keyframes move-2 {
  50% {
    transform: translateY(50px);
  }
}
</style>
