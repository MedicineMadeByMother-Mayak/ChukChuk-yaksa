<template>
  <div class="modie_size">
    <NavBar id="nav" v-if="!$route.meta.hideNavBar" />
    <router-view></router-view>
    <!-- meta: { hideNavBar: true } index.js의 navbar 필요없는 페이지에 추가 -->
  </div>
</template>

<script>
import NavBar from "./common/NavBar.vue";
export default {
  components: {
    NavBar,
  },
};
</script>

<style>
/* 모바일 환경으로 사이즈 고정 */
.modie_size {
  overflow-x: hidden;
  width: 320px;
  height: 658px;
  background-color: #f9f9f9;
  position: relative;
}

#nav {
  position: sticky;
  top: 580px;
  left: 20px;
  margin-top: -55px; /* 네비게이션 바의 높이만큼 음수 margin을 줌 */
}

html,
body {
  height: 100%;
  margin: 0;
}

body {
  background-color: black;
  display: flex;
  justify-content: center;
  align-items: center;
}

/* 스크롤바 전역 커스텀 */
.modie_size::-webkit-scrollbar {
  display: none;
}
</style>
