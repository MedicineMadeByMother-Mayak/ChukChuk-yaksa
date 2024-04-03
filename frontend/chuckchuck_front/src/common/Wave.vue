<!-- 사용법 -->
<!-- 
  <Wave :title="원하는 제목" :height="원하는 높이 기본값 148px" :Link="< 아이콘 누르면 가기 원하는 링크" /> 
-->

<template>
  <header :style="{ height }">
    <div class="container">
      <img :src="wave" id="pin" />
      <!-- <img :src="wave3" id="wave1" @animationend="handleAnimationEnd" /> -->
      <img :src="wave3" id="wave3" @animationend="handleAnimationEnd" />
    </div>
  </header>
</template>

<script setup>
import { ref, onMounted } from "vue";
import wave from "@/assests/img/waves/wave.svg";
import wave1 from "@/assests/img/waves/wave-1.svg";
import wave2 from "@/assests/img/waves/wave-2.svg";
import wave3 from "@/assests/img/waves/wave-3.svg";

const props = defineProps({
  height: {
    type: Number,
    default: "500px",
  },
});

let isLeftAnimation = ref(true);

const handleAnimationEnd = () => {
  leftAnimation.value = !leftAnimation.value; // 애니메이션 종료시 상태 변경
};
</script>

<style scoped>
header {
  background-color: #f9f9f9;
  height: 10vh;
}

.container {
  max-width: 100%; /* 이미지가 부모 요소에 맞춰질 수 있도록 */
  max-height: 100%;
  position: relative;
}

#pin {
  position: absolute;
  top: -30px;
}

#wave1 {
  position: absolute;
  top: -15px;
  animation: move-3 4s infinite;
}

#wave3 {
  position: absolute;
  top: -30px;
  animation: move-3 4s infinite;
}

@keyframes move-3 {
  50% {
    transform: translateY(15px);
  }
}
</style>
