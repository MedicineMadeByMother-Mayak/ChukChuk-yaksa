<template>
  <div class="header-background-color">
    <header class="heads">
      <img :src="backIcon" alt="" />
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
});
</script>

<style scoped>
.header-background-color {
  width: 100%;
  background-color: transparent;
  position: fixed;
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

.heads > img {
  height: 100%;
  position: absolute;
  left: 0;
  margin-left: 14px;
}

.heads > h1 {
  font-size: small;
}
</style>
