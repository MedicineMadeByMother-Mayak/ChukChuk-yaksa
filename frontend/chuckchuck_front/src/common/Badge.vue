<!-- 사용법
    <Badge
      title="뱃지 내용"
      backgroundColor="뱃지 색"
      color="글씨 색"
      fontSize="글씨 크기"
    /> -->

<template>
  <div class="badge-container" :style="{ backgroundColor }">
    <span class="badge-text" :style="{ color, fontSize }">{{ title }}</span>
  </div>
</template>

<script setup>
const props = defineProps({
  title: {
    type: String,
    default:
      "title : 뱃지 내용, backgroundColor : 뱃지 색, color : 글씨 색, size : 글씨 크기",
  },
  backgroundColor: {
    type: String,
    default: "#34c759",
  },
  color: {
    type: String,
    default: "white",
  },
  fontSize: {
    type: String,
    default: "14px",
  },
});
</script>

<style scoped>
.badge-container {
  background-color: #34c759; /* 배경색 */
  padding: 8px 16px; /* 내부 여백 */
  border-radius: 5px; /* 둥근 모서리 */
  display: inline-block; /* 필요한 만큼의 공간만 차지 */
}

.badge-text {
  color: white; /* 글자색 */
  font-weight: bold; /* 글자 두껍게 */
  font-size: 14px; /* 글자 크기 */
  white-space: nowrap; /* 텍스트가 한 줄로 나타나도록 설정 */
}
</style>
