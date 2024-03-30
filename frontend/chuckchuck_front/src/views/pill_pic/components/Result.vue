<template>
  <div class="container">
    <section><img src="@/assests/img/pillPicResult.png" /></section>
    <section class="guide">
      <p>분석된 약들을 복용목록에 추가할 수 있어요.</p>
      <div class="line"></div>
    </section>
    <section class="under">
      <button>모든 분석결과를 복용목록에 추가하기</button>
      <div class="pill-box">
        <PillInfoPlus
          v-for="pillData in dumydata.pills"
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
</template>
<script setup>
import PillInfoPlus from "@/common/PillInfoPlus.vue";
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
