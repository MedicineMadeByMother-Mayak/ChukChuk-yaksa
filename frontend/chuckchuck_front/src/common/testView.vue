<template>
  <div class="what-container">
    <div class="white-background">
      <div class="white-box"></div>
    </div>
    <div class="blue-rounded"></div>
  </div>
</template>

<script setup>
</script>

<style scoped>
.what-container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.blue-rounded {
  width: 200px;
  height: 100px;
  background-color: blue;
  border-radius: 20px;
}

.white-background {
  width: 200px;
  height: 150px; /* 높이를 조정하여 파란색 상자와 겹치도록 설정 */
  background-color: white;
  position: relative;
}

.white-box {
  width: 100px;
  height: 100px;
  background-color: rgb(255, 0, 0);
  position: absolute;
  top: 50%;
  left: 50%;
}
</style>
