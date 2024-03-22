<template>
  <div class="modie_size">
    <RouterView />
  </div>
</template>

<script setup></script>

<style>
/* 모바일 환경으로 사이즈 고정 */
.modie_size {
  width: 360px;
  height: 740px;
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
}

html,
body {
  height: 100%;
  margin: 0;
}

body {
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
