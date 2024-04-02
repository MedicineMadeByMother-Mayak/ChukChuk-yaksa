<template>
  <!-- PillSearchView(약검색) -->
  <div class="basic-background">
    <Wave title="약 검색" height="1px" />
    <div style="padding: 8%; padding-bottom: 0">
      <SearchBar
        class="searchBar"
        @input="input"
        :value="keyword"
        :keyword="keyword"
        :width="'100%'"
        :height="'33px'"
        :iconWidth="'15px'"
        :font-size="'15px'"
        :fontWeight="'400'"
        :marginLeft="'15%'"
      />

      <div class="count-container">
        <div class="count-box">
          <p class="keyword">{{ keyword ? keyword : "전체" }}</p>
          <p class="count">
            검색결과:
            <strong>{{ count }}</strong
            >건
          </p>
        </div>
        <hr />
      </div>
    </div>

    <div class="search-result-container">
      <PillInfoPlus
        v-for="pill in pills"
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
      <Observer @show="loadMoreData" v-if="isScrolled > 0"></Observer>
    </div>
  </div>
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
</template>

<script setup>
import Wave from "@/common/Wave.vue";
import PillInfoPlus from "@/common/PillInfoPlus.vue";
import SearchBar from "@/common/SearchBar.vue";
import Observer from "@/views/pharmacy_search/components/Observer.vue";
import _ from "lodash";
import { ref, onMounted, onUnmounted } from "vue";
import { useRouter } from "vue-router";
import { pillSearchStore } from "@/stores/pillSearch";
import ModalForm from "@/common/Form/AddModalForm.vue";
import SelectListModalForm from "@/common/Form/SelectListModalForm.vue";
import { takelistStore } from "@/stores/takelist";
import AlertModal from "@/common/Form/AlertModal.vue";

const takeListStore = takelistStore();
const store = pillSearchStore();
const router = useRouter();

const keyword = ref("");
const page = ref(0);
const count = ref(null);
const pills = ref([]);
const isScrolled = ref(false);
const msg = ref(false);
const showModal = ref(false);
const modalData = ref([[1, "새로운 리스트에 추가하기"]]);
const selectPill = ref(0);
const alertShowModal = ref(false);

async function savePill(selectId) {
  if (selectId === 1) {
    await takeListStore.createAndAddPill([selectPill.value]);
  } else {
    await takeListStore.addPill(selectId, [selectPill.value]);
  }

  alertShowModal.value = true;
  setTimeout(() => {
    alertShowModal.value = false;
  }, 2500);
}

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

onMounted(() => {
  window.addEventListener("scroll", handleScroll);
  if (store.backButton) {
    loadMoreData();
  } else {
    keyword.value = store.keyword;
    page.value = store.page;
    count.value = store.count;
    pills.value = store.pills;
  }
  store.backButton = true;
});

onUnmounted(() => {
  window.removeEventListener("scroll", handleScroll);
});

async function loadMoreData() {
  if (count.value && count.value <= pills.value.length) return;
  page.value++;
  const data = await store.input(keyword.value, page.value);
  count.value = data.count;
  pills.value = [...pills.value, ...data.pills];
}

function handleScroll() {
  isScrolled.value = window.scrollY > 0;
}

async function click(pillId) {
  // await store.getPillInfo(pillId);
  router.push({
    name: "pilldetail",
    params: { id: pillId },
  });
}

// 디바운스 함수 정의
const debouncedInput = _.debounce(async (value) => {
  keyword.value = value;
  pills.value = [];
  page.value = 1;
  const data = await store.input(keyword.value, page.value);
  if (data) {
    count.value = data.count;
    pills.value = data.pills;
  }
}, 200);

// input 이벤트 핸들러
function input(event) {
  debouncedInput(event.target.value);
}
</script>

<style scoped>
.basic-background {
  background-color: #f9f9f9;
  height: 100%;
}

.count-container {
  margin: 0;
  width: 100%;
}

.count-container > hr {
  margin-bottom: 13px;
}

.count-box {
  display: flex;
  justify-content: space-between;
}

.count-box > p {
  margin: 0;
  margin-top: 22px;
}

.keyword {
  font-weight: 600;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.search-result-container {
  margin: 0 27px;
  gap: 17px;
  display: flex;
  flex-direction: column;
  padding-bottom: 28%;
}
</style>
