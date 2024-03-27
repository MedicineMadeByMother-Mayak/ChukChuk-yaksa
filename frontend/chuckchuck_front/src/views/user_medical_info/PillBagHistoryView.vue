<template>
  <Wave title="진단내역" height="20px" Link="usermedicalinfo" />

  <div style="margin: 5px 10px">
    총 {{ dumydata.count }}건의 진단내역이 있습니다.
  </div>
  <hr style="margin: 10px" />

  <div v-for="data in dumydata.result">
    <Accordian
      style="margin: 10px"
      :title="formatDate(data.diagnosisDate, 'YYYY/MM/DD 진단내역')"
    >
      <div>
        <TableForm
          :tableData="[
            ['진단일', formatDate(data.diagnosisDate, 'YYYY/MM/DD')],
            ['병원명', data.hospitalName],
            ['질병코드', data.illCode],
            ['질병명', data.illName],
            ['소견', data.opinion],
          ]"
        ></TableForm>
      </div>
    </Accordian>
  </div>
</template>

<script setup>
import Accordian from "@/views/user_medical_info/components/Accordian.vue";
import { ref } from "vue";
import Wave from "@/common/Wave.vue";
import TableForm from "@/common/Form/TableForm.vue";
import dayjs from "dayjs";

const dumydata = ref({
  count: 2, // 건수
  result: [
    {
      diagnosisDate: new Date("2022-05-20 10:30:20"), //진단일
      hospitalName: "나라사랑병원", //병원명
      illCode: "질병코드", //질병코드
      illName: "질병명", //질병명
      opinion: "소견", //소견
    },
    {
      diagnosisDate: new Date("2022-05-21 10:30:20"), //진단일
      hospitalName: "나라사랑병원", //병원명
      illCode: "질병코드", //질병코드
      illName: "질병명", //질병명
      opinion: "소견", //소견
    },
  ],
});

function formatDate(date, format = "YYYY/MM/DD") {
  return dayjs(date).format(format);
}
</script>

<style setup></style>
