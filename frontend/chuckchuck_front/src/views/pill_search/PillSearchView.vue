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
        @click="click(pill.pillId)"
        :key="pill.pillId"
        :pillId="pill.pillId"
        :pillName="pill.pillName"
        :imageUrl="pill.imageUrl"
        :type="pill.type"
        :warningPregnant="pill.warningPregnant"
        :warningUseDate="pill.warningUseDate"
        :warningElders="pill.warningElders"
        :warningTogether="pill.warningTogether"
      />
      <Observer @show="loadMoreData" v-if="isScrolled > 0"></Observer>
    </div>
  </div>

  <ModalForm
    v-model="msg"
    :modalData="[
      [
        '복용중인 약 리스트에 추가하기',
        false,
        { params: { pillId }, Link: '' },
      ],
      [
        '나의 약효기록에 후기 추가 하기',
        false,
        { params: { pillId }, Link: '' },
      ],
    ]"
  />

  <SelectListModalForm
    v-model="showModal"
    :modalData="[
      [1, '달디달디달디달'],
      [2, '단 밤양갱'],
    ]"
  />
</template>

<script setup>
import { ref, onMounted, onUnmounted } from "vue";
import { pillSearchStore } from "@/stores/pillSearch";
import Wave from "@/common/Wave.vue";
import PillInfoPlus from "@/common/PillInfoPlus.vue";
import SearchBar from "@/common/SearchBar.vue";
import Observer from "@/views/pharmacy_search/components/Observer.vue";
import { useRouter } from "vue-router";
import _ from "lodash";

const store = pillSearchStore();

const keyword = ref("");
const page = ref(0);
const count = ref(null);
const pills = ref([]);
const isScrolled = ref(false);

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
  store.backButton = false;
  store.pills = pills.value;
  store.count = count.value;
  store.keyword = keyword.value;
  store.page = page.value;
  await store.getPillInfo(pillId);
  router.push({
    name: "pilldetail",
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
  width: 65%;
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
