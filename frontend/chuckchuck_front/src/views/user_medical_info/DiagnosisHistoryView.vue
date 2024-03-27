<template>
  <Wave title="처방내역" height="60px" Link="usermedicalinfo" />

  <div style="margin: 5px 10px">
    총 {{ dumydata.count }}건의 처방내역이 있습니다.
  </div>
  <hr style="margin: 10px" />

  <div v-for="data in dumydata.result">
    <Accordian
      style="margin: 10px"
      :title="formatDate(data.receipt.buildDate, 'YYYY/MM/DD 처방내역')"
    >
      <li>
        <strong>영수증</strong>
        <TableForm
          style="margin-top: 10px"
          :tableData="[
            ['약국정보', data.receipt.pharmacyName],
            ['조제일자', formatDate(data.receipt.buildDate, 'YYYY/MM/DD')],
            ['수납금액', data.receipt.cost],
          ]"
        ></TableForm>
      </li>
      <li style="margin: 10px 0px">
        <strong>복약안내</strong>
        <div v-for="pillData in data.guide" style="margin-top: 10px">
          <PillBagContent
            :pillName="pillData.pillName"
            :type="pillData.type"
            :capacity="pillData.capacity"
          />
        </div>
      </li>
    </Accordian>
  </div>
</template>

<script setup>
import Accordian from "@/views/user_medical_info/components/Accordian.vue";
import PillBagContent from "@/common/PillInfo.vue";
import { ref } from "vue";
import Wave from "@/common/Wave.vue";
import TableForm from "@/common/Form/TableForm.vue";
import dayjs from "dayjs";

const dumydata = ref({
  count: 1, // 건수
  result: [
    {
      receipt: {
        pharmacyName: "늘 푸른 약국", //약국명
        buildDate: new Date("2022-05-21 10:30:20"), //조제일자
        cost: 8600,
      },
      guide: [
        {
          pillName: "프라닥사캡슐", //약 이름
          type: "항히스타민제", //약 분류(타입)
          capacity: "1정씩 2회, 14일분", //용법용량(복약안내)
        },
      ],
    },
    {
      receipt: {
        pharmacyName: "에잇 퍼런 약국", //약국명
        buildDate: new Date("2011-11-30 10:30:20"), //조제일자
        cost: 8600,
      },
      guide: [
        {
          pillName: "프라닥사캡슐", //약 이름
          type: "항히스타민제", //약 분류(타입)
          capacity: "1정씩 2회, 14일분", //용법용량(복약안내)
        },
        {
          pillName: "프라닥사캡슐", //약 이름
          type: "항히스타민제", //약 분류(타입)
          capacity: "1정씩 2회, 14일분", //용법용량(복약안내)
        },
      ],
    },
  ],
});

function formatDate(date, format = "YYYY/MM/DD") {
  return dayjs(date).format(format);
}
</script>

<style setup></style>
