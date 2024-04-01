<template>
  <div class="container">
    <section><img :src="picUrl" /></section>
    <section class="guide">
      <p>분석된 약들을 복용목록에 추가할 수 있어요.</p>
      <div class="line"></div>
    </section>
    <section class="under">
      <button>모든 분석결과를 복용목록에 추가하기</button>
      <div class="pill-box">
        <PillInfoPlus
          v-for="pillData in data.pills"
          :key="pillData.pillId"
          :pillId="pillData.pillId"
          :pillName="pillData.pillName"
          :imageUrl="pillData.imageUrl"
          :type="pillData.type"
          :warningPregnant="pillData.warningPregnant"
          :warningUseDate="pillData.warningUseDate"
          :warningElders="pillData.warningElders"
          :warningTogether="pillData.warningTogether"
        />
      </div>
    </section>
  </div>
    <!-- Nav-bar용 -->
    <div style="height: 85px; background-color: #f9f9f9;"></div>
</template>
<script setup>
import PillInfoPlus from "@/common/PillInfoPlus.vue";
import { pillPicStore } from "@/stores/pillPic";
import { ref, onMounted, watch } from "vue";

const store = pillPicStore();
const data = store.results;
console.log(data);
const picUrl = ref('');

// store의 data.pic_url이 변경될 때마다 picUrl 업데이트
watch(() => store.results.pic_url, (newPicUrl) => {
  picUrl.value = newPicUrl;
});

// 컴포넌트가 마운트될 때 store 데이터 초기화
onMounted(() => {
  picUrl.value = store.results.pic_url;
});

</script>

<style scoped>
.pill-box {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 2vh;
}
p {
  color: #535353;
}
.container {
  /* background-color: aqua; */
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 5px;
  width: 100%;
}
img {
  width: 280px;
  height: 200px;
  border-radius: 20px;
  object-fit: cover; /* 이미지가 요소에 맞게 잘립니다. */
}
.guide {
  width: 85%;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.line {
  height: 1.5px;
  border-radius: 10px;
  background-color: #bebebe;
}

button {
  margin: 10px 0px 0px 0px;
  padding: 0px;
  border: none;
  border-radius: 0px;
  background: linear-gradient(170deg, rgb(176, 184, 244) 0%, #d2eee8 100%);
  color: #ffffff;
  font-size: small;
  font-weight: bold;
  width: 96%;
  height: 40px;
  border-radius: 8px;
  box-shadow: 0 0.3em 0.3em rgba(119, 119, 119, 0.3);
}

.under {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 15px;
}
</style>
