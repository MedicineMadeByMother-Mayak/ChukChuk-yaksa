<template>
  <div class="modie_size">
    <!-- <RouterView /> -->
    <nav-bar></nav-bar>
    <router-view></router-view>

  </div>
</template>

<script>
import NavBar from './common/NavBar.vue';
export default {
  components: {
    NavBar,
  }
}
</script>

<style>
/* 모바일 환경으로 사이즈 고정 */
.modie_size {
  width: 320px;
  height: 658px;
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
