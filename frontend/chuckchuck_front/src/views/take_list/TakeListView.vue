<template>
  <div>
    <Wave title="복용 관리" height="30px" />
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

    <div class="alarms">
      <div v-for="(pillDatas, index) in dumydata.result" :key="`pill-date-${index}`">
        <button class="rounded-button">
          <span><i class="fa-solid fa-bell" style="color: #FFD43B; margin-right: 8px;"></i>{{ pillDatas.takeListName }}</span>
        </button>
      </div>
      <div>
        <button class="rounded-button" style="height: 28px;"><span><i class="fa-solid fa-circle-plus fa-lg icon" style="color: #1454b5; margin: 2px 15px;"></i></span></button>
      </div>
    </div>
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
    <hr style="margin: 3px;" />
      <div v-for="(pillDatas, index) in dumydata.result" :key="`pill-date-${index}`" class="pill-entry">
        <div class="pill-date">
          {{ pillDatas.createDate }} [{{ pillDatas.takeListName }}]
          <img src="@/assests/icon/edit.png" alt="편집 아이콘" />
        </div>
        <ul class="pills-list">
          <li
            v-for="(pillData, index) in pillDatas.pills"
            :key="`pill-details-${index}`"
            class="pill-info"
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
          </li>
        </ul>
      </div>
  </div>

  <div class="menu">
    <div class="menu-left">
      <img src="@/assests/icon/pill.png" alt="복용리스트">
      <div>복용중</div>
    </div>
    <div class="menu-right">
      <button class="gray-button">과거에먹은약</button>
      <button class="navy-button">추가</button>
    </div>
  </div>
  <hr class="line">
  <div class="pill-info" v-for="(data, index) in dumydata.result" :key="index">
    <div>{{data.createDate}} [{{ data.takeListName }}]</div>
    <img src="@/assests/icon/edit.png" alt="홈">

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
// const server = async () => {
//   const { data } = await instance.get("/pill/search?keyword=활&page=1");
//   console.log(data);
// };
// server();

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
  
<style>

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
  padding: 15px 10px;
  background-color: #c0e6fc;
  display: flex;
  width: 100%;
  flex-wrap: wrap;
  gap: 10px;
}
.menu {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-left: 5px;
  margin-top: 5px;
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
  margin-bottom: 5px;
}
.menu img {
  width: 10px;
  height: 10px;
}

button {
  display: inline;
  margin-right: 5px;
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
  width: 100%;
  margin-top: 0px;
}

.pill-info {
  font-weight: bold;
  margin: 0px 10px;
  align-items: center;
}

.pills-list {
  margin: 5px 0px;
  list-style: none;
  padding-left: 0;
  position: relative;
}

.pills-list::before {
  content: '';
  position: absolute;
  left: 0;
  top: 0;
  bottom: 0;
  width: 2px;
  background-color: #083688;
}

.pill-info:first-child::before,
.pill-info:last-child::before
{
  content: '';
  position: absolute;
  width: 6px;
  height: 6px;
  background-color: #083688; /* 점의 색상, 원하는 색으로 변경 가능 */
  border-radius: 50%; /* 원형 점 */
}

/* 첫 번째 pill-info의 상단 왼쪽 모서리 */
.pill-info:first-child::before {
  top: -2px; /* 점의 위치를 반으로 조정 */
  left: -2px;
}

/* 마지막 pill-info의 하단 왼쪽 모서리 */
.pill-info:last-child::before {
  bottom: -2px;
  left: -2px;
}

.pill-entry {
  margin-bottom: 20px;
  padding-left: 5px;
}

.pill-date {
  display: flex;
  align-items: center;
  font-weight: bold;
}

.pill-info + .pill-info {
  margin-top: 10px;
}

.pill-date img {
  margin-left: 3px;
  width: 25px;
  height: 25px;
}

.rounded-button {
  display: flex;
  align-items: center;
  justify-content: center;
  border: none;
  background-color: white; /* 버튼 배경색 */
  padding: 5px 15px; /* 버튼 내부 패딩 */
  margin: 0px;
  border-radius: 30px; /* 둥근 모서리 */
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* 그림자 효과 */
  cursor: pointer;
  transition: background-color 0.3s; /* 배경색 변경 시 애니메이션 */
}
</style>
