<template>
  <!-- 검색바 -->
  <div class="search-bar" :style="{ width: width, height: height }">
    <img :src="searchIcon" :style="{ width: iconWidth }" alt="" />
    <input
      class="input-local"
      placeholder="검색할 약의 이름을 입력하세요."
      type="text"
      :value="keyword"
      :style="{ fontSize, marginLeft, fontWeight }"
    />
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
  keyword: String,
  fontSize: {
    type: String,
    default: "14px",
  },
  marginLeft: {
    type: String,
    default: "20%",
  },
  fontWeight: {
    type: String,
    default: "600",
  },
});
</script>

<style scoped>
.search-bar {
  position: relative;
  display: flex;
  align-items: center;
  background-color: #fff;
  border-radius: 7px;
}

.search-bar::before {
  content: "";
  position: absolute;
  inset: 0;
  border-radius: 6px;
  padding: 2.5px;
  background: linear-gradient(60deg, #3183ff 0%, #86e7ee 100%);
  -webkit-mask: linear-gradient(#fff 0 0) content-box, linear-gradient(#fff 0 0);
  -webkit-mask-composite: xor;
  mask-composite: exclude;
}

.search-bar > img {
  margin: 0;
  position: absolute;
  left: 6%;
}

.input-local {
  width: 100%;
  border: none;
  outline: 0;
  background-color: transparent;
  padding: 0;
  color: #606060;
  z-index: 2;
}
</style>
