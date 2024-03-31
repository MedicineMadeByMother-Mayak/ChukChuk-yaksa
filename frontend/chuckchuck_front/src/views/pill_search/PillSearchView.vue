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
          @click="click"
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
      </div>
    </div>
  </div>
  <!-- Nav-bar용 -->
  <div style="height: 85px; background-color: #ffffff"></div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { pillSearchStore } from "@/stores/pillSearch";
import Wave from "@/common/Wave.vue";
import PillInfoPlus from "@/common/PillInfoPlus.vue";
import SearchBar from "@/common/SearchBar.vue";

const store = pillSearchStore();

const keyword = ref(store.keyword);
const page = ref(store.page);
const count = ref(0);
const pills = ref([]);

onMounted(() => {
  input();
});

async function input(event) {
  if (event !== undefined) {
    keyword.value = event.target.value;
  }
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
  margin: 0;
  gap: 17px;
  display: flex;
  flex-direction: column;
}
</style>
