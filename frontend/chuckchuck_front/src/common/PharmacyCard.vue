<template>
  <div :style="{ backgroundImage: `url(${img})` }" class="card-container">
    <div class="card-title">{{ title }}</div>
    <div class="card-info">
      <p class="status" v-if="status">영업 중</p>
      <p class="status end" v-else>영업 종료</p>
      <p>|</p>
      <p v-if="status">{{ newEnd }} 영업</p>
      <p v-else>금일 영업 종료</p>
    </div>
    <div class="address">{{ address }}</div>
  </div>
</template>

<script setup>
import { ref } from "vue";

const props = defineProps({
  title: {
    type: String,
    default: "약국 이름",
  },
  end: {
    type: String,
    default: "영업 시간",
  },
  address: {
    type: String,
    default: "약국 주소",
  },
  img: {
    type: String,
    default: "src/assests/img/testPharmacy.svg",
  },
  status: {
    type: Boolean,
    default: true,
  },
});

const newEnd = ref("22:00");
if (props.status) {
  newEnd.value = `${props.end.substring(0, 2)}:${props.end.substring(2, 4)}`;
}
</script>

<style scoped>
.end {
  color: red !important;
}

.card-container {
  width: 80%;
  color: white;
  background-size: cover;
  position: relative;
  isolation: isolate;
  border-radius: 20px;
  padding: 15px;
}

.card-container::after {
  border-radius: inherit;
  content: "";
  position: absolute;
  background: black;
  z-index: -1;
  inset: 0;
  opacity: 0.4;
}

.card-title {
  font-weight: 1000;
  font-size: 22px;
  margin-bottom: 3px;
}

.card-info {
  display: flex;
  font-size: medium;
  font-weight: 600;
  gap: 7px;
}

.card-info > p {
  margin: 0;
}

.status {
  color: blue;
}

.address {
  font-size: 15px;
}
</style>
