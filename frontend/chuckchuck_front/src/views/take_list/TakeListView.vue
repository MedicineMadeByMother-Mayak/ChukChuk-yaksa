<template>
  <div>
    <Wave title="복용 관리" height="60px" />
    <!-- <vueper-slides
      autoplay
      class="no-shadow ex--center-mode"
      :arrows-outside="false"
      bullets-outside
      transition-speed="250"
      :interval="2000"
    >
      <vueper-slide
        class="vueper-slide"
        v-for="i in 3"
        :key="i"
        :title="i.toString()"
      />
    </vueper-slides> -->

    <div class="alarms"></div>
    <div class="menu">
      <div class="menu-left">
        <img src="@/assests/icon/pill.png" alt="복용리스트" />
        <div><strong>복용중</strong></div>
      </div>
      <div class="menu-right">
        <button class="gray-button">과거에 먹은 약</button>
        <button class="navy-button">추가</button>
      </div>
    </div>
    <hr class="line" />

    <div
      class="pill-info"
      v-for="(data, index) in dumydata.result"
      :key="index"
    >
      <div>{{ data.createDate }} [{{ data.takeListName }}]</div>

      <img src="@/assests/icon/edit.png" alt="홈" />
    </div>
    <div v-for="(pillDatas, index) in dumydata.result" :key="index">
      <div
        v-for="(pillData, index) in pillDatas.pills"
        style="margin-top: 10px"
      >
        <Content
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
</template>

<script setup>
import Wave from "@/common/Wave.vue";
// // import { VueperSlides, VueperSlide } from "vueperslides";
import { ref } from "vue";
// // import "vueperslides/dist/vueperslides.css";
import Content from "./components/Content.vue";
import List from "./components/List.vue";

// import { instance } from "@/util/mainAxios";
//요청 test
const server = async () => {
  const { data } = await instance.get("/pill/search?keyword=활&page=1");
  console.log(data);
};
server();

const dumydata = ref({
  count: 5,
  result: [
    {
      createDate: "2024-03-04",
      takeListName: "위염약",
      pills: [
        {
          pillId: 1,
          pillName:
            "중외5%포도당생리식염액(수출명:5%DextroseinnormalsalineInj.)",
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
      ],
    },
    {
      createDate: "2024-03-05",
      takeListName: "감기약",
      pills: [
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
      ],
    },
    {
      createDate: "2024-03-06",
      takeListName: "해열제",
      pills: [
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
    },
  ],
});
</script>

<style scoped>
/* .ex--center-mode {
  margin-top: 0px;
  left: 50%;
  transform: translateX(-50%);
  width: 100%;
} */
/* .vueper-slide {
  position: fixed;
  left: 50%;
  transform: translateX(-50%);
  width: 90%;
  height: 80px;
  display: flex;
  align-items: center;
  box-shadow: 0.1em 0.1em 0.1em rgba(0, 0, 0, 0.1);
  background-color: #ffffff;
  border-radius: 10px;
  z-index: 1000;
  transition: top 0.3s ease;
}
.vueperslides__bullet .default {
  background-color: rgba(0, 0, 0, 0.3);
  border: none;
  box-shadow: none;
  transition: 0.3s;
  width: 10px;
  height: 10px;
  margin-left: -10px;
  margin-top: 0px;
}
.vueperslides__bullet--active .default {
  background-color: rgb(66, 66, 66);
} */

.alarms {
  background-color: lightskyblue;
  height: 50px;
}
.menu {
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 90%;
  margin-left: 20px;
  margin-top: 10px;
  position: relative;
  overflow: hidden;
}
.menu-left {
  display: flex;
  align-items: center;
}
.menu-left img {
  width: 80px;
  margin-right: 5px;
}
.menu-right {
  text-align: right;
  margin-bottom: 10px;
}
.menu img {
  width: 10px;
  height: 10px;
}

button {
  display: inline;
  margin-right: 10px;
  margin-top: 10px;
}
.gray-button {
  background-color: #cccccc;
  color: #ffffff;
  font-weight: bold;
  border-radius: 5px;
  padding: 5px 10px;
  font-size: 12px;
  border: none;
  cursor: pointer;
  margin-right: 5px;
}
.navy-button {
  background-color: #242291;
  color: #ffffff;
  font-weight: bold;
  border-radius: 5px;
  padding: 5px 15px;
  font-size: 12px;
  border: none;
  cursor: pointer;
}

.line {
  width: 90%;
  margin-top: 0px;
}
.pill-info {
  margin-left: 20px;
  font-weight: bold;
  display: flex;
  align-items: center;
}
.pill-info p {
  flex: 1;
}
.pill-info img {
  width: 20px;
  height: 20px;
}
</style>
