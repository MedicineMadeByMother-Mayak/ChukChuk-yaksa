<template>
  <div class="basic-background">
    뭣허냐
    <!-- <ModalForm
      v-model="msg"
      :modalData="[
        ['달디달디달디달디달디달디', true, { params: {}, Link: 'registinfo' }],
        ['단 밤양갱', true, { params: {}, Link: '' }],
        ['밤양갱이얌', true, { params: {}, Link: '' }],
        ['밤양갱밤양갱밤양갱밤양갱', true, { params: {}, Link: '' }],
        ['양갱갱갱갱', true, { params: {}, Link: '' }],
      ]"
    /> -->
    <ModalForm
      v-model="msg"
      :modalData="[
        [1, '달디달디달디달'],
        [2, '단 밤양갱'],
        [3, '단 밤양갱'],
        [4, '밤양갱밤양갱밤갱'],
        [5, '밤양갱이얌'],
      ]"
    />
  </div>
</template>

<script setup>
import ModalForm from "@/common/Form/SelectListModalForm.vue";
import { ref } from "vue";
const msg = ref(true);
</script>

<style scoped>
.basic-background {
  background-color: blueviolet;
  height: 100vh;
}
</style>
