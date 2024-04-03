<template>
  <HeaderForm class="header-form" title="약효기록" height="200px">
    <div class="header-content" style="margin-top: 90px">
      <SearchBar
        @input="input"
        :value="keyword"
        :keyword="keyword"
        class="SearchBar"
        height="42px"
        width="85%"
        iconWidth="15px"
        fontSize="13px"
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
  <!-- {{ pilleffectstore.pillEffectDatas.totalPillDtoList }} -->
  <!-- {{ pilldatas }} -->
  <div class="pill-content-container">
    <div style="margin: 12px 10px 18px 10px; caret-color: transparent">
      전체 <span class="bold">{{ counts }}</span
      >건
    </div>
    <div class="pill-content">
      <PillContent
        v-for="(pillData, index) in pilldatas"
        :key="`pill-data-${index}`"
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
  <Footer></Footer>
</template>

<script setup>
import Footer from "@/common/FooterNoColor.vue";
import HeaderForm from "@/common/Form/HeaderForm.vue";
import PillContent from "./components/Content.vue";
import { ref, onMounted, nextTick, onUnmounted } from "vue";
import AddModalForm from "@/common/Form/AddModalForm.vue";
import { pillEffectStore } from "@/stores/pilleffect";
import Observer from "@/views/pharmacy_search/components/Observer.vue";
import SearchBar from "@/common/SearchBar.vue";
import _ from "lodash";

const counts = ref(30);
const msg = ref(false); //모달창 관리하는 변수 ref로 반드시 설정해주세요
const categoryflag = ref(true);
const tabnum = ref(0);
const pilleffectstore = pillEffectStore();
const pilldatas = ref([]);
const page = ref(1);
const hasNext = ref(true);
const keyword = ref("");

async function addData() {
  await pilleffectstore.getpillEffectDatas(keyword.value, page.value);
  if (tabnum.value === 0) {
    pilleffectstore.pillEffectDatas.totalPillDtoList.forEach(
      (pilldata, pillindex) => {
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
      }
    );
    pilldatas.value.push(...pilleffectstore.pillEffectDatas.totalPillDtoList);
  } else if (tabnum.value === 3) {
    pilldatas.value.push(
      ...pilleffectstore.pillEffectDatas.siedEffectPillDtoList
    );
  } else if (tabnum.value === 2) {
    pilldatas.value.push(...pilleffectstore.pillEffectDatas.stopPillDtoList);
  } else {
    pilldatas.value.push(...pilleffectstore.pillEffectDatas.effectPillDtoList);
  }
  const totalLoadedItems = page.value * 5;
  hasNext.value = totalLoadedItems < counts.value;
  page.value++;
}

async function setCategoryflagData(num) {
  categoryflag.value = 0 == num;
  tabnum.value = num;
  page.value = 1;
  await pilleffectstore.getpillEffectDatas(keyword.value, page.value);
  if (tabnum.value === 0) {
    pilldatas.value = pilleffectstore.pillEffectDatas.totalPillDtoList;
    // counts.value = pilleffectstore.pillEffectDatas.totalPillDtoListCount;
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
  } else if (tabnum.value === 3) {
    pilldatas.value = pilleffectstore.pillEffectDatas.siedEffectPillDtoList;
    // counts.value = pilleffectstore.pillEffectDatas.siedEffectPillDtoListCount;
  } else if (tabnum.value === 2) {
    pilldatas.value = pilleffectstore.pillEffectDatas.stopPillDtoList;
    // counts.value = pilleffectstore.pillEffectDatas.stopPillDtoListCount;
  } else {
    pilldatas.value = pilleffectstore.pillEffectDatas.effectPillDtoList;
    // counts.value = pilleffectstore.pillEffectDatas.effectPillDtoListCount;
  }
  const totalLoadedItems = page.value * 5;
  hasNext.value = totalLoadedItems < counts.value;
  page.value++;
  checkAndFetchData();
}

function openModal() {
  msg.value = true;
}

// fetchData 호출 후 문서 높이 체크 및 추가 로드 처리
async function checkAndFetchData() {
  await addData();

  await nextTick(); // DOM 업데이트 대기

  const { clientHeight, scrollHeight } = document.documentElement;
  if (scrollHeight <= clientHeight && hasNext.value) {
    // 문서 전체 높이가 뷰포트 높이 이하이고, 추가 데이터가 있으면 재귀적으로 호출
    checkAndFetchData();
  }
}

// 스크롤 이벤트 핸들러
function handleScroll() {
  const { scrollTop, scrollHeight, clientHeight } = document.documentElement;
  if (scrollHeight - (scrollTop + clientHeight) < 1 && hasNext.value) {
    checkAndFetchData();
  }
}

onMounted(async () => {
  window.addEventListener("scroll", handleScroll);
  await setCategoryflagData(0);
});

onUnmounted(() => {
  window.removeEventListener("scroll", handleScroll);
});

// input 이벤트 핸들러
function input(event) {
  debouncedInput(event.target.value);
}

// 디바운스 함수 정의
const debouncedInput = _.debounce(async (value) => {
  keyword.value = value;
  setCategoryflagData(tabnum.value);
}, 200);
</script>

<style scoped>
/* .pill-info {
  margin: 5px 10px;
  align-items: center;
} */
.pill-content-container {
  background-color: #f9f9f9;
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
  height: 100%;
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
  height: 100%;
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

.pill-content {
  width: 100%;
  gap: 10px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
</style>
