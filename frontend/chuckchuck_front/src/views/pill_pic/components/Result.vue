<template>
  <div class="container">
    <section class="img-container">
      <div class="scroll-container">
        <img :src="picUrl" />
      </div>
    </section>
    <section class="guide">
      <p>분석된 약들을 복용목록에 추가할 수 있어요.</p>
      <div class="line"></div>
    </section>
    <section class="under">
      <button>모든 분석결과를 복용목록에 추가하기</button>
      <div class="pill-box">
        <PillInfoPlus
          v-for="pill in data.pills"
          :key="pill.pillId"
          :pillId="pill.pillId"
          :pillName="pill.pillName"
          :imageUrl="pill.imageUrl"
          :type="pill.type"
          :warningPregnant="pill.warningPregnant"
          :warningUseDate="pill.warningUseDate"
          :warningElders="pill.warningElders"
          :warningTogether="pill.warningTogether"
          @click-modal="openModal"
        />
      </div>
    </section>
    <ModalForm
      v-model="msg"
      @add-pill-in-take-list="addPill"
      :Link="'pillsearch'"
      :modalData="[
        [
          '복용중인 약 리스트',
          '에 추가하기',
          false,
          { emitName: 'addPillInTakeList' },
        ],
        [
          '나의 약효기록',
          '에 후기 추가하기',
          true,
          {
            params: { pillId: selectPill, Link: 'pillsearch' },
            Link: 'effectdetail',
          },
        ],
      ]"
    />

    <SelectListModalForm
      @save="savePill"
      v-model="showModal"
      :modalData="modalData"
    />

    <AlertModal
      v-if="alertShowModal"
      :text="'복용 리스트에 추가되었습니다.'"
      :showModal="alertShowModal"
    />
  </div>
  <!-- Nav-bar용 -->
  <div style="height: 85px; background-color: #f9f9f9"></div>
</template>
<script setup>
import PillInfoPlus from "@/common/PillInfoPlus.vue";
import { pillPicStore } from "@/stores/pillPic";
import { ref, onMounted, watch } from "vue";
import SelectListModalForm from "@/common/Form/SelectListModalForm.vue";
import AlertModal from "@/common/Form/AlertModal.vue";
import ModalForm from "@/common/Form/AddModalForm.vue";
import { takelistStore } from "@/stores/takelist";

const takeListStore = takelistStore();
const store = pillPicStore();
// const data = store.results;
const picUrl = ref("");
const msg = ref(false);
const showModal = ref(false);
const alertShowModal = ref(false);
const selectPill = ref(0);
const modalData = ref([[1, "새로운 리스트에 추가하기"]]);

async function savePill(selectId) {
  if (selectId === 1) {
    await takeListStore.createAndAddPill([selectPill.value]);
  } else {
    await takeListStore.addPill(selectId, [selectPill.value]);
  }

  showModal.value = false;

  alertShowModal.value = true;
  setTimeout(() => {
    alertShowModal.value = false;
  }, 2500);
}

const data = ref({
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

async function addPill() {
  modalData.value = [[1, "새로운 리스트에 추가하기"]];
  await takeListStore.getTakeListPageDatas();
  takeListStore.takelistpagedatas.forEach((item) => {
    modalData.value.push([item.takeListId, item.takeListName]);
  });

  showModal.value = !showModal.value;
}

function openModal(pillId) {
  selectPill.value = pillId;
  msg.value = !msg.value;
}

// store의 data.pic_url이 변경될 때마다 picUrl 업데이트
// watch(
//   () => store.results.pic_url,
//   (newPicUrl) => {
//     picUrl.value = newPicUrl;
//   }
// );

// 컴포넌트가 마운트될 때 store 데이터 초기화
// onMounted(() => {
//   picUrl.value = store.results.pic_url;
// });
</script>

<style scoped>
.pill-box {
  display: flex;
  flex-direction: column;
  align-items: center;
  /* gap: 2vh; */
  gap: 2%;
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
.img-container {
  width: 280px;
  height: 200px;
  overflow: auto;
  white-space: nowrap; /* 이미지들을 수평으로 나열 */
  -ms-overflow-style: none; /* IE와 Edge에서 스크롤바 숨기기 */
}

.scroll-container img {
  width: 100%; /* 컨테이너의 너비에 맞춤 */
  height: 100%; /* 컨테이너의 높이에 맞춤 */
  object-fit: cover;
  display: inline-block; /* 이미지들을 수평으로 나열 */
}

/* 스크롤바 숨기기 */
::-webkit-scrollbar {
  display: none;
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
