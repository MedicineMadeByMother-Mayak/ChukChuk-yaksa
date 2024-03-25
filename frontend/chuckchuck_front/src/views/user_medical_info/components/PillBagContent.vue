<template>
  <div class="pill-card">
    <div class="pill-image">
      <!-- 이미지를 배치합니다; 실제 이미지 경로로 대체해야 합니다 -->
      <!-- <img src="pill-image.png" alt="약 이미지" /> -->
      <img src="../../../assests/img/dumypillimg.jpg" alt="약 이미지" />
    </div>
    <div class="pill-info">
      <div class="pill-type">{{ type }}</div>
      <div class="pill-name">{{ pillName }}</div>
    </div>
    <div class="pill-capacity">
      <div>{{ capacity }}</div>
    </div>
  </div>
</template>

<script setup>
const props = defineProps({
  pillName: {
    type: String,
    default: "프로다나서캡슐",
  },
  type: {
    type: String,
    default: "항히스타민제",
  },
  capacity: {
    type: String,
    default: "1정씩 2회, 14일분",
  },
});
</script>

<style scoped>
.pill-card {
  font-size: 12px;
  display: flex;
  align-items: center;
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
}

.pill-image img {
  width: 80px; /* 실제 이미지 크기에 맞게 조정 */
  height: auto; /* 이미지의 비율을 유지 */
  margin: 10px; /* 이미지와 정보 사이의 여백 */
  border-radius: 8px;
}

.pill-info {
  flex-grow: 1; /* 남은 공간을 모두 차지하도록 함 */
}

.pill-type {
  color: #3183ff;
  font-weight: bold;
  font-size: 10px;
  margin-bottom: 5px; /* 이름과 상세 정보 사이의 여백 */
}

.pill-name {
  font-weight: bold;
  margin-bottom: 5px; /* 상세 정보와 복용 정보 사이의 여백 */
}

.pill-capacity {
  margin-left: auto; /* 컨테이너 오른쪽에 자리잡게 함 */
  background-color: white;
  border-radius: 10px;
  padding: 5px 10px; /* 복용 정보 주위의 여백 */
}
</style>
