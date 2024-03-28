<template>
  <div class="search-bar" :style="{ width: width, height: height }">
    <img :src="searchIcon" :style="{ width: iconWidth }" alt="" />
    <span> 검색할 약의 이름을 입력하세요. </span>
  </div>
</template>

<script setup>
import searchIcon from "@/assests/img/searchIcon.svg";
const props = defineProps({
  width: {
    type: String,
    default: "80%",
  },
  height: {
    type: String,
    default: "55px",
  },
  iconWidth: {
    type: String,
    default: "30px",
  },
});
</script>

<style scoped>
.search-bar {
  position: relative;
  display: flex;
  align-items: center;
  justify-content: space-evenly;
}

.search-bar::before {
  content: "";
  position: absolute;
  inset: 0;
  border-radius: 6px;
  padding: 3px;
  background: linear-gradient(60deg, #3183ff 0%, #86e7ee 100%);
  -webkit-mask: linear-gradient(#fff 0 0) content-box, linear-gradient(#fff 0 0);
  -webkit-mask-composite: xor;
  mask-composite: exclude;
}

.search-bar > img {
  margin: 0;
}

.search-bar > span {
  font-size: 115%;
}
</style>
