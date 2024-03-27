<template>
  <HeaderForm title="진단서 촬영" height="275px">
    <div class="image-container"></div>
    <div class="white-box">
      <img
        class="receipt-image"
        src="../../assests/img/image 16.png"
        alt="약 봉투 이미지"
      />
    </div>
    <button class="custom-button">
      <div class="button-icon"></div>
      병력 저장하기
    </button>
    <div class="grey-oval"></div>
  </HeaderForm>
  <div style="margin: 20px">
    <div style="text-align: center">진단서 분석 결과입니다.</div>
    <hr />
    <li>
      <strong>영수증</strong>
      <TableForm
        style="margin-top: 10px"
        :tableData="[
          ['진단일', formatDate(dumydata.buildDate, 'YYYY/MM/DD')],
          ['병원명', dumydata.hospitalName],
          ['질병코드', dumydata.illCode],
          ['질병명', dumydata.illName],
          ['소견', dumydata.opinion],
        ]"
      ></TableForm>
    </li>
  </div>
</template>

<script setup>
import HeaderForm from "@/common/Form/HeaderForm.vue";
import { ref } from "vue";
import TableForm from "@/common/Form/TableForm.vue";
import dayjs from "dayjs";

function formatDate(date, format = "YYYY/MM/DD") {
  return dayjs(date).format(format);
}

const dumydata = ref({
  diagnosisDate: new Date("2022-05-21 10:30:20"),
  hospitalName: "싸피사랑병원",
  illCode: "Joo",
  illName: "코로나",
  opinion: "널옵션",
});
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
  max-width: 100%;
  height: 150px;
  max-height: 100%;
  object-fit: cover;
  margin: 4px;
  border-radius: 5px;
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

.grey-oval {
  position: relative;
  top: 18px;
  width: 320px;
  height: 20px;
  background-color: #e7e7e7;
  border-radius: 50%;
}
</style>
