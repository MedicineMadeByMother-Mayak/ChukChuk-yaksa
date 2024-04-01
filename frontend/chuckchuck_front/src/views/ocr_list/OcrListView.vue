<template>
  <RouterView />
  <section v-if="$route.path === '/ocrlist'">
    <div class="container">
      <!--선택화면 -->
      <OcrSelectView v-if="!isLoading" class="bottom-componenet" />
      <!--로딩화면 -->
      <FilmingView
        v-if="isLoading && analysisType == 'pillBag'"
        :analysis="text"
        class="bottom-componenet"
      />
      <FilmingView
        v-if="isLoading && analysisType == 'diagnosis'"
        :analysis="text"
        class="bottom-componenet"
      />
    </div>
  </section>
  <Footer></Footer>
</template>

<script setup>
import Wave from "@/common/Wave.vue";
import Footer from "@/common/Footer.vue";
import OcrSelectView from "@/views/ocr_list/OcrSelectView.vue";
import FilmingView from "@/views/ocr_list/FilmingView.vue";
import { ref, watch, computed } from "vue";
import { useRouter } from "vue-router";
import { ocrListStore } from "@/stores/ocrList";
import { RouterLink, RouterView } from "vue-router";

const router = useRouter();
const store = ocrListStore();

const isLoading = ref(false);
const analysisType = ref();
const text = ref();

import { getCurrentInstance } from "vue";

const instance = getCurrentInstance();

// 사진이 첨부되면 isLoading=true로 바꿔 로딩화면 띄우기
// 분석이 완료되면 분석화면으로 push하기
watch(
  () => store.pillBagImage,
  async (pillBagImage) => {
    text.value = "약봉투";
    isLoading.value = true;
    analysisType.value = "pillBag";
    await store.analysisPillBagImage();
    router.push({ name: "pillbagresult" });
  }
);
watch(
  () => store.diagnosisImage,
  async (diagnosisImage) => {
    console.log("dd");
    text.value = "진단서";
    isLoading.value = true;
    analysisType.value = "diagnosis";
    await store.analysisDianosisImage();
    router.push({ name: "diagnosisresult" });
  }
);
</script>

<style scoped>
.container {
  background-color: #f9f9f9;
  display: flex;
  flex-direction: column;
  /* gap: 10px; */
}

.text {
  margin-top: 20px;
  height: 5%;
  display: flex;
}
.timeline {
  height: 15%;
}
.bottom-componenet {
  height: 50%;
}
</style>
