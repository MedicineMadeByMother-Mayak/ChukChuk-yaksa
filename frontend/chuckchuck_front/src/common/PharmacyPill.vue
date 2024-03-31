<template>
  <div class="pill-card">
    <img :src="dumypillimg" alt="약 이미지" />
    <div class="pill-info">
      <div class="pill-type">{{ type }}</div>
      <div class="pill-name">{{ pillName }}</div>
    </div>

    <button>판매약국</button>
  </div>
</template>

<script setup>
import dumypillimg from "@/assests/img/dumypillimg.jpg";
const props = defineProps({
  pillName: {
    type: String,
    default: "약 이름",
  },
  type: {
    type: String,
    default: "약 타입",
  },
  imageUrl: {
    type: String,
    default: dumypillimg,
  },
});
</script>

<style scoped>
.pill-card {
  height: 60px;
  font-size: 12px;
  display: flex;
  align-items: center;
  background-color: #f9f9f9;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
}

.pill-card > img {
  width: 80px;
  height: 40px;
  margin: 10px;
  object-fit: cover;
  border-radius: 8px;
}

.pill-card > button {
  margin: 0;
  padding: 3px;
  color: white;
  background-color: #1d1c7a;
  border-radius: 5px;
  font-family: "Cafe24SsurroundAir", sans-serif;
  font-size: 8px;
  font-weight: 600;
  width: 45px;
  margin-right: 8px;
}

.pill-info {
  display: flex;
  flex-direction: column;
  align-items: start;
  margin: 0;
}

.pill-info > div {
  margin: 0;
  padding: 0;
  width: 115px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.pill-type {
  color: #3183ff;
  font-weight: bold;
  font-size: 10px;
  margin-bottom: 2px;
}

.pill-name {
  font-weight: bold;
  margin-bottom: 5px;
  font-size: 12px;
}

.pill-capacity {
  margin-left: auto;
  border-radius: 10px;
}
</style>
