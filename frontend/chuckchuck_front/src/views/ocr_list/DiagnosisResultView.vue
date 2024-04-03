<template>
  <HeaderForm title="진단서 촬영" height="300px">
    <div class="white-box" style="margin-top: 73px">
      <img class="receipt-image" :src="diagnosisImageSrc" alt="진단서 이미지" />
    </div>
    <button @click="saveDiagnosis" class="custom-button">
      <div class="button-icon"></div>
      병력 저장하기
    </button>
    <div
      style="
        position: relative;
        top: 19px;
        height: 10px;
        background-color: white;
        border-top-left-radius: 10px;
        border-top-right-radius: 10px;
      "
    ></div>
  </HeaderForm>
  <div style="margin: 0px 20px">
    <div style="text-align: center">진단서 분석 결과입니다.</div>
    <hr />
    <li>
      <strong>영수증</strong>
      <TableForm
        style="margin-top: 10px"
        :tableData="[
          ['진단일', formatDate(data.buildDate, 'YYYY/MM/DD')],
          ['병원명', data.hospitalName],
          ['질병코드', data.illCode],
          ['질병명', data.illName],
          ['소견', data.opinion],
        ]"
      ></TableForm>
    </li>
  </div>
  <div style="margin-bottom: 10px"></div>
  <AlertModal
    v-if="showModal"
    :text="'진단서가 저장 되었습니다.'"
    :showModal="showModal"
  />
</template>

<script setup>
import HeaderForm from "@/common/Form/HeaderForm.vue";
import { ref, computed } from "vue";
import TableForm from "@/common/Form/TableForm.vue";
import dayjs from "dayjs";
import { ocrListStore } from "@/stores/ocrList";
import AlertModal from "@/common/Form/AlertModal.vue";
const showModal = ref(false);

const store = ocrListStore();

function formatDate(date, format = "YYYY/MM/DD") {
  return dayjs(date).format(format);
}
const diagnosisImageSrc = computed(() => store.diagnosisImageSrc);
const data = ref(store.diagnosisResult);

const saveDiagnosis = async () => {
  try {
    await store.saveDiagnosis();
    showModal.value = true;
    setTimeout(() => {
      showModal.value = false;
    }, 2500);
  } catch (error) {
    alert("진단서 저장중 오류가 발생하였습니다.");
  }
};
</script>

<style scoped>
.white-box {
  width: auto;
  height: 150px;
  border-radius: 5px;
  border: solid 2px white;
  background-clip: padding-box;
  margin: 0px 20px;
  padding: 4px;
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
  overflow: hidden;
}

.receipt-image {
  width: 400px;
  height: 140px;
  margin: 4px;
  border-radius: 5px;
  object-fit: cover;
}

.white-box::before {
  content: "";
  position: absolute;
  top: -2px;
  left: -2px;
  right: -2px;
  bottom: -2px;
  z-index: -1;
  border-radius: inherit;
  box-shadow: inherit;
  overflow: hidden;
  padding: 0;
}

.custom-button {
  margin-top: 10px;
  border: 0px;
  background-color: white;
  display: inline-flex;
  align-items: center;
  justify-content: center;
  padding: 4px 8px;
  border-radius: 5px;
  cursor: pointer;
  outline: none;
  position: relative;
  overflow: hidden;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.button-icon {
  background-image: url("@/assests/img/Group.png");
  background-size: cover;
  width: 20px;
  height: 20px;
  margin-right: 8px;
}
</style>
