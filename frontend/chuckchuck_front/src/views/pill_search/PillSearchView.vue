<template>
  <!-- PillSearchView(약검색) -->
  <div class="basic-background">
    <Wave title="약 검색" height="1px" />
    <div style="padding: 15px">
      <div style="padding: 0 0 20px 0">
        검색 상자 추후에 추가 예정
        <input />
      </div>
      <div style="font-size: 10px">
        전체 | <strong>{{ dumydata.count }}</strong> 건
      </div>
      <div style="border-bottom: 1px solid lightgray"></div>
      <div
        v-for="pillData in dumydata.pills"
        :key="pillData.pillId"
        style="margin-top: 10px"
      >
        <PillBagContent
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
    </div>
  </div>
  <!-- Nav-bar용 -->
  <div style="height: 85px; background-color: #ffffff"></div>
</template>

<script setup>
import Wave from "@/common/Wave.vue";
import PillBagContent from "@/common/PillInfoPlus.vue";
import { ref } from "vue";

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
      warningTogether: true,
    },
    {
      pillId: 2,
      pillName: "디고신정(디곡신)",
      imageUrl: "../../assests/img/tempPill.png",
      type: "진통제 (painkiller)",
      warningPregnant: true,
      warningUseDate: false,
      warningElders: true,
      warningTogether: false,
    },
    {
      pillId: 3,
      pillName: "옥시톤주사액5아이유(옥시토신)",
      imageUrl: "../../assests/img/tempPill.png",
      type: "진통제 (painkiller)",
      warningPregnant: false,
      warningUseDate: false,
      warningElders: true,
      warningTogether: true,
    },
    {
      pillId: 4,
      pillName: "아주디곡신주사액",
      imageUrl: "../../assests/img/tempPill.png",
      type: "진통제 (painkiller)",
      warningPregnant: false,
      warningUseDate: true,
      warningElders: true,
      warningTogether: false,
    },
    {
      pillId: 5,
      pillName: "삐콤정",
      imageUrl: "../../assests/img/tempPill.png",
      type: "진통제 (painkiller)",
      warningPregnant: true,
      warningUseDate: true,
      warningElders: true,
      warningTogether: false,
    },
    {
      pillId: 2,
      pillName: "디고신정(디곡신)",
      imageUrl: "../../assests/img/tempPill.png",
      type: "진통제 (painkiller)",
      warningPregnant: true,
      warningUseDate: false,
      warningElders: true,
      warningTogether: false,
    },
    {
      pillId: 3,
      pillName: "옥시톤주사액5아이유(옥시토신)",
      imageUrl: "../../assests/img/tempPill.png",
      type: "진통제 (painkiller)",
      warningPregnant: false,
      warningUseDate: false,
      warningElders: true,
      warningTogether: true,
    },
    {
      pillId: 4,
      pillName: "아주디곡신주사액",
      imageUrl: "../../assests/img/tempPill.png",
      type: "진통제 (painkiller)",
      warningPregnant: false,
      warningUseDate: true,
      warningElders: true,
      warningTogether: false,
    },
    {
      pillId: 5,
      pillName: "삐콤정",
      imageUrl: "../../assests/img/tempPill.png",
      type: "진통제 (painkiller)",
      warningPregnant: true,
      warningUseDate: true,
      warningElders: true,
      warningTogether: false,
    },
  ],
});
</script>

<style scoped>
.basic-background {
  background-color: #F9F9F9;
}
</style>
