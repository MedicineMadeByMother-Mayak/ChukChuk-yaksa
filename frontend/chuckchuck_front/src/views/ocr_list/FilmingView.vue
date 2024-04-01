<template>
  <div class="container"></div>
  <HeaderForm :title="type" />
  <p>척척약사가 {{ analysis }}를 분석중이에요.</p>
  <p>잠시만 기다려주세요.</p>
  <img :src="LoadImage" />
</template>

<script setup>
import HeaderForm from "@/common/Form/HeaderForm.vue";
import { ref } from "vue";
import LoadImage from "@/assests/img/loadbag.svg";
import EffectFace from "@/assests/icon/effectFace.svg";
import { useRoute } from "vue-router";
import { defineProps } from "vue";

import { computed } from "vue";

const props = defineProps({
  analysis: {
    type: String,
    default: "-",
  },
});
</script>

<style scoped>
.container {
  background-color: beige;
}
</style>
