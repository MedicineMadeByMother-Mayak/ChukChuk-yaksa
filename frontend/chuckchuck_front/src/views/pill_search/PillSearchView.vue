<template>
  이거 약 리스트 하나하나의 컴포넌트 따로 짜야함
  근데 태완이꺼 복붙할거라 대기 중
  <div>
    <Wave title="약 검색" height="60px" />
    <div>PillSearchView(약검색)</div>
    <li style="margin: 10px 0px">
      <strong>복약안내</strong>
      <div v-for="pillData in dumydata.pills" :key="pillData.pillId" style="margin-top: 10px">
        <PillBagContent
          :pillName="pillData.pillName"
          :type="pillData.type"
        />
      </div>
    </li>
  </div>
</template>

<script setup>
import Wave from "@/common/Wave.vue";
import PillBagContent from "@/common/PillInfo.vue";
import { ref } from 'vue';

const dumydata = ref({
  count: 5,
  pills: [
    {
      pillId: 1,
      pillName: "중외5%포도당생리식염액(수출명:5%DextroseinnormalsalineInj.)",
      imageUrl: "../../assests/img/tempPill.png",
      type: "진통제 (painkiller)",
      warningPregnant: true,
      warningUseDate: true,
      warningElders: true,
      warningTogether: true
    },
    {
      pillId: 2,
      pillName: "디고신정(디곡신)",
      imageUrl: "../../assests/img/tempPill.png",
      type: "진통제 (painkiller)",
      warningPregnant: true,
      warningUseDate: true,
      warningElders: true,
      warningTogether: true
    },
    {
      pillId: 3,
      pillName: "옥시톤주사액5아이유(옥시토신)",
      imageUrl: "../../assests/img/tempPill.png",
      type: "진통제 (painkiller)",
      warningPregnant: true,
      warningUseDate: true,
      warningElders: true,
      warningTogether: true
    },
    {
      pillId: 4,
      pillName: "아주디곡신주사액",
      imageUrl: "../../assests/img/tempPill.png",
      type: "진통제 (painkiller)",
      warningPregnant: true,
      warningUseDate: true,
      warningElders: true,
      warningTogether: true
    },
    {
      pillId: 5,
      pillName: "삐콤정",
      imageUrl: "../../assests/img/tempPill.png",
      type: "진통제 (painkiller)",
      warningPregnant: true,
      warningUseDate: true,
      warningElders: true,
      warningTogether: true
    }
  ]
});
</script>

<style scoped></style>
