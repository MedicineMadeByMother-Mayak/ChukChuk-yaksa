<template>
  <HeaderForm title="약봉투 촬영" height="300px">
    <div class="white-box" style="margin-top: 73px">
      <img class="receipt-image" :src="pillBagImageSrc" alt="약 봉투 이미지" />
    </div>
    <button @click="savePillBag" class="custom-button">
      <div class="button-icon"></div>
      <strong>복용관리</strong>에 추가하기
    </button>
    <div class="grey-oval"></div>
  </HeaderForm>
  <div style="margin: 20px">
    <div style="text-align: center; margin: 20px 0px" class="bold">
      처방 받으신 약 봉투 분석 결과입니다.
    </div>
    <hr style="margin-bottom: 20px" />
    <li>
      <strong>영수증</strong>
      <TableForm
        style="margin-top: 10px"
        :tableData="[
          ['약국정보', data.pharmacyName],
          ['조제일자', formatDate(data.buildDate, 'YYYY/MM/DD')],
          ['수납금액', data.cost],
        ]"
      ></TableForm>
    </li>
    <li style="margin: 30px 0px">
      <strong>복약안내</strong>
      <div v-for="pillData in data.pills" style="margin-top: 10px">
        <PillBagContent
          :pillName="pillData.pillName"
          :type="pillData.type"
          :capacity="pillData.guide"
        />
      </div>
    </li>
  </div>
  <div style="height: 50px"></div>
  <AlertModal
    v-if="showModal"
    :text="'복용관리에 추가 되었습니다.'"
    :showModal="showModal"
  />
</template>

<script setup>
import HeaderForm from "@/common/Form/HeaderForm.vue";
import { ref, onMounted, computed } from "vue";
import TableForm from "@/common/Form/TableForm.vue";
import PillBagContent from "@/common/PillInfo.vue";
import dayjs from "dayjs";
import { ocrListStore } from "@/stores/ocrList";
import AlertModal from "@/common/Form/AlertModal.vue";
const showModal = ref(false);

const store = ocrListStore();

const pillBagImageSrc = computed(() => store.pillBagImageSrc);

function formatDate(date, format = "YYYY/MM/DD") {
  return dayjs(date).format(format);
}

const data = ref(store.pillBagResult);

const savePillBag = async () => {
  try {
    await store.savePillBag();
    showModal.value = true;
    setTimeout(() => {
      showModal.value = false;
    }, 2500);
  } catch (error) {
    alert("약봉투 저장중 오류가 발생하였습니다.");
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
  background-image: url("../../assests/img/Group.png");
  background-size: cover;
  width: 20px;
  height: 20px;
  margin-right: 8px;
}

.grey-oval {
  caret-color: transparent;
  position: relative;
  top: 18px;
  width: 320px;
  height: 20px;
  background-color: #e7e7e7;
  border-radius: 50%;
  caret-color: transparent;
}
</style>
