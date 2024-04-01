<template>
  <!-- PillSearchView(약검색) -->
  <div class="basic-background">
    <Wave title="약 검색" height="1px" />
    <div style="padding: 8%; height: 100%">
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
          @click-modal="clickModal(pill.pillId)"
        />
      </div>
    </div>
    <!-- Nav-bar용 -->
    <!-- <div class="save-nav-bar"></div> -->
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
import SelectListModalForm from "@/common/Form/SelectListModalForm.vue";
import ModalForm from "@/common/Form/AddModalForm.vue";

const store = pillSearchStore();

const keyword = ref(store.keyword);
const page = ref(store.page);
const count = ref(0);
const pills = ref([]);
const isLoading = ref(false);
const pillId = ref(null);
const msg = ref(false);
const showModal = ref(false);

onMounted(() => {
  input();
  window.addEventListener("scroll", handleScroll);
});

onUnmounted(() => {
  window.removeEventListener("scroll", handleScroll);
});

function clickModal(currentPillId) {
  pillId.value = currentPillId;
  msg.value = true;
}

// 스크롤 이벤트 핸들러
function handleScroll() {
  const nearBottom =
    window.innerHeight + window.scrollY + 100 >= document.body.offsetHeight;
  if (nearBottom && count.value > pills.value.length) {
    // 페이지 끝에 근접했을 때 실행할 로직
    loadMoreData();
  }
}

// 더 많은 데이터 로딩
async function loadMoreData() {
  if (isLoading.value) return;
  isLoading.value = true;
  // 페이지 번호 증가
  page.value++;
  const data = await store.input(keyword.value, page.value);
  count.value = data.count;
  pills.value = [...pills.value, ...data.pills];
  isLoading.value = false;
}

async function input(event) {
  if (event !== undefined) {
    keyword.value = event.target.value;
  }
  page.value = 1;
  const data = await store.input(keyword.value, page.value);
  count.value = data.count;
  pills.value = data.pills;
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
  height: 100%;
  margin: 0;
  gap: 17px;
  display: flex;
  flex-direction: column;
}

.save-nav-bar {
  content: "";
  height: 85px;
}
</style>
