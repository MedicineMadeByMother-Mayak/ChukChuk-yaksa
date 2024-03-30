<template>
  <div class="base-backgound-color">
    <Wave title="사진으로 약찾기" height="10px" Link="usermedicalinfo" />

    <div class="container">
      <!-- Text -->
      <Text class="text"></Text>
      <!-- Timeline -->
      <Timeline class="timeline" :currentStep="currentStep" />
      <!-- selectPic, Loading, Result -->
      <SelectPic v-if="currentStep === 1" class="bottom-componenet" />
      <Loading v-if="currentStep === 2" class="bottom-componenet" />
      <Result v-if="currentStep === 3" class="bottom-componenet" />
    </div>
  </div>
  <div
    style="
      position: relative;
      text-align: center;
      background: linear-gradient(60deg, #3183ff 0%, #86e7ee 100%);
      color: black;
      margin-bottom: 10px;
      height: 117px;
    "
  ></div>
</template>

<script setup>
import Wave from "@/common/Wave.vue";
import Text from "@/views/pill_pic/components/Text.vue";
import Timeline from "@/views/pill_pic/components/Timeline.vue";
import SelectPic from "@/views/pill_pic/components/SelectPic.vue";
import Loading from "@/views/pill_pic/components/Loading.vue";
import Result from "@/views/pill_pic/components/Result.vue";
import { ref, watch } from "vue";
import { useRouter } from "vue-router";
import { pillPicStore } from "@/stores/pillPic";

const router = useRouter();
const store = pillPicStore();
const currentStep = ref(3);
//selectPic에서 사진첨부 이벤트 발생시(currentStep 변화시) 실행
watch(
  () => store.currentStep,
  (nextStep) => {
    console.log("currentStep 값이 변경되었습니다:", nextStep);
    currentStep.value = nextStep;
  }
);
</script>

<style scoped>
.container {
  background-color: #f9f9f9;
  height: 445px;
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
