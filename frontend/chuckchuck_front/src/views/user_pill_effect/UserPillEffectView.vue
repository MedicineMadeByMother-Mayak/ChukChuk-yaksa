<template>
  <HeaderForm class="header-form" title="약효기록" height="120px">
    <div class="header-content">
      <SearchBar
        class="SearchBar"
        height="30px"
        width="300px"
        iconWidth="15px"
        fontSize="12px"
        marginLeft="15%"
      ></SearchBar>
    </div>
    <div class="buttons">
      <div>
        <button class="tab-button" @click="setCategoryflagData(0)">전체</button
        ><button class="tab-button" @click="setCategoryflagData(1)">효과</button
        ><button class="tab-button" @click="setCategoryflagData(2)">중단</button
        ><button class="tab-button" @click="setCategoryflagData(3)">
          부작용
        </button>
      </div>
      <div>
        <div>
          <button class="navy-button" @click="openModal">
            <font-awesome-icon
              :icon="['fas', 'circle-plus']"
              style="color: #ffffff"
            />
            기록추가
          </button>
        </div>
      </div>
    </div>
  </HeaderForm>
  <!-- {{ pilldatas }} -->
  <div class="pill-content">
    <div style="margin: 5px 10px; caret-color: transparent">
      전체 : {{ counts }}
    </div>
    <div
      v-for="(pillData, index) in pilldatas"
      :key="`pill-data-${index}`"
      class="pill-info"
    >
      <PillContent
        v-model="categoryflag"
        :pillId="pillData.pill_id"
        :pillName="pillData.name"
        :imageURL="pillData.image_url"
        :company="pillData.company"
        :categories="pillData.categories"
        :tags="pillData.usedTags"
      />
    </div>
  </div>
  <AddModalForm
    v-model="msg"
    :modalData="[
      [
        '이름으로 검색',
        '해서 약 추가하기',
        true,
        { params: {}, Link: 'pillsearch' },
      ],
      ['약 사진', '을 찍어 약 선택하기', true, { params: {}, Link: 'pillpic' }],
    ]"
  />
</template>

<script setup>
import HeaderForm from "@/common/Form/HeaderForm.vue";
import PillContent from "./components/Content.vue";
import SearchBar from "@/common/SearchBar.vue";
import { ref, computed, onMounted, watchEffect } from "vue";
import AddModalForm from "@/common/Form/AddModalForm.vue";
import { pillEffectStore } from "@/stores/pilleffect";

const counts = ref(0);
const msg = ref(false); //모달창 관리하는 변수 ref로 반드시 설정해주세요
const categoryflag = ref(true);
const tabnum = ref(0);
const pilleffectstore = pillEffectStore();
const pilldatas = ref({});
const page = ref(1);

function setCategoryflagData(num) {
  categoryflag.value = 0 == num;
  tabnum.value = num;
  if (num === 0 && pilleffectstore.pillEffectDatas.totalPillDtoList) {
    pilldatas.value = pilleffectstore.pillEffectDatas.totalPillDtoList.sort(
      (a, b) => {
        return a.name.localeCompare(b.name); // pill_name을 기준으로 정렬
      }
    );
    counts.value = pilleffectstore.pillEffectDatas.totalPillDtoListCount;
    pilldatas.value.forEach((pilldata, pillindex) => {
      pilldata.categories = [];
      pilleffectstore.pillEffectDatas.siedEffectPillDtoList.forEach(
        (flagdata, flagindex) => {
          if (pilldata.pill_id === flagdata.pill_id) {
            pilldata.categories.push("부작용");
          }
        }
      );
      pilleffectstore.pillEffectDatas.stopPillDtoList.forEach(
        (flagdata, flagindex) => {
          if (pilldata.pill_id === flagdata.pill_id) {
            pilldata.categories.push("중단");
          }
        }
      );
      pilleffectstore.pillEffectDatas.effectPillDtoList.forEach(
        (flagdata, flagindex) => {
          if (pilldata.pill_id === flagdata.pill_id) {
            pilldata.categories.push("효과");
          }
        }
      );
    });
  } else if (num === 3) {
    pilldatas.value = pilleffectstore.pillEffectDatas.siedEffectPillDtoList;
    counts.value = pilleffectstore.pillEffectDatas.siedEffectPillDtoListCount;
  } else if (num === 2) {
    pilldatas.value = pilleffectstore.pillEffectDatas.stopPillDtoList;
    counts.value = pilleffectstore.pillEffectDatas.stopPillDtoListCount;
  } else {
    pilldatas.value = pilleffectstore.pillEffectDatas.effectPillDtoList;
    counts.value = pilleffectstore.pillEffectDatas.effectPillDtoListCount;
  }
}

const effectData = computed(() => pilleffectstore.pillEffectDatas);

watchEffect(() => {
  if (effectData.value) {
    setCategoryflagData(tabnum.value);
  }
});

function openModal() {
  msg.value = true;
}

onMounted(async () => {
  await pilleffectstore.getpillEffectDatas("", page.value);
  counts.value = pilleffectstore.pillEffectDatas.totalPillDtoListCount;
});
</script>

<style scoped>
.pill-info {
  margin: 5px 10px;
  align-items: center;
}

.header-form {
  position: sticky;
  top: 0;
  z-index: 999;
}

.header-content {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 8%;
  z-index: 999;
}

.tab-button {
  font-family: Cafe24SsurroundAir;
  font-size: 11px;
  border: none;
  background-color: white;
  width: 55px;
  height: 21px;
  padding: 3px 10px;
  margin: 0px;
  cursor: pointer;
  clip-path: polygon(10% 0, 90% 0, 100% 100%, 0% 100%);
  caret-color: transparent;
  border-top-left-radius: 5%;
  position: relative;
  overflow: hidden;
}

.buttons {
  width: 320px;
  display: flex;
  justify-content: space-between;
  margin: 0px 0px;
  position: absolute;
  left: 0;
  bottom: 0;
}

.navy-button {
  background-color: #242291;
  color: #ffffff;
  font-weight: bold;
  width: 65px;
  height: 21px;
  border-radius: 5px;
  padding: 3px;
  font-size: 11px;
  border: none;
  cursor: pointer;
}

.icon {
  display: flex;
  margin-left: auto;
  padding: 0 15px 0 0;
}
</style>
