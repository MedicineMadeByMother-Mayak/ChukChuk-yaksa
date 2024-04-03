<template>
  <div class="header-background-color">
    <header class="heads">
      <RouterLink :to="{ name: Link }">
        <img class="backIcon" :src="backIcon" alt="" />
      </RouterLink>
      <h1>{{ title }}</h1>
    </header>
  </div>
</template>

<script setup>
import backIcon from "@/assests/img/backIcon.svg";
const props = defineProps({
  title: {
    type: String,
    default: "title : 제목",
  },
  Link: {
    type: String,
    default: "home",
  },
});
</script>

<style scoped>
.header-background-color {
  width: 100%;
  background-color: transparent;
  position: sticky;
  top: 0;
  z-index: 999;
}

.heads {
  width: 100%;
  height: 21px;
  margin-top: 11px;
  display: flex;
  align-items: center;
  justify-content: center;
  position: relative;
}

.backIcon {
  height: 100%;
  position: absolute;
  left: 0;
  top: 0;
  margin-left: 14px;
}

.heads > h1 {
  font-size: medium;
  color: #2d2d2d !important;
}
</style>
