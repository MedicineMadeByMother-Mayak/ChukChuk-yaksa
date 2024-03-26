<template>
  <div class="pill-card">
    <div class="pill-image">
      <!-- <img src="pill-image.png" alt="약 이미지" /> -->
      <img src="@/assests/img/dumypillimg.jpg" alt="약 이미지" />
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
  width: 80px;
  height: auto;
  margin: 10px;
  border-radius: 8px;
}

.pill-info {
  flex-grow: 1;
}

.pill-type {
  color: #3183ff;
  font-weight: bold;
  font-size: 10px;
  margin-bottom: 5px;
}

.pill-name {
  font-weight: bold;
  margin-bottom: 5px;
}

.pill-capacity {
  margin-left: auto;
  background-color: white;
  border-radius: 10px;
  padding: 5px 10px;
}
</style>
