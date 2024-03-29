<template>
  <div class="list-container">
    <div>{{ takeList.createDate }} [{{ takeList.takeListName }}]</div>
  </div>
</template>

<script setup>
import { defineProps } from "vue";

const props = defineProps({
  takeList: Object,
});
</script>

<style scoped>
/* 스타일 코드 */
</style>
