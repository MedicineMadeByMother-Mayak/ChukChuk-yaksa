<template>
  <!-- 약국검색창 -->
  <RouterView />
  <div v-if="$route.path === '/pharmacy'" class="basic-background-color">
    <div class="header-container">
      <HeaderFormOnlyString :title="'약국검색'" />
      <transition name="slide">
        <div class="header-back" :class="{ 'header-back-up': keyword }"></div>
      </transition>
    </div>

    <div v-if="!keyword" class="header-string">
      <img class="logo" :src="logo" alt="" />
      <div>
        <p>찾는 약이 <strong>어떤 약국에 있는지</strong> 궁금하신가요?</p>
        <p>척척약사가 찾아드릴게요.</p>
      </div>
    </div>

    <div class="address-search-container" :class="{ 'move-up': keyword }">
      <div>
        <p class="address">
          <img :src="mileStone" alt="" />대전광역시 서구 대덕대로150
        </p>
      </div>
      <SearchBar
        :value="keyword"
        @keyup="input"
        :width="'90%'"
        :height="'55px'"
        :iconWidth="'30px'"
        :keyword="keyword"
      />
      <p class="search-update-info" v-if="!keyword">
        판매 약국정보는 매월 1일 업데이트 됩니다.
      </p>

      <div v-if="keyword" class="count-container">
        <div class="count-box">
          <p class="keyword">{{ keyword }}</p>
          <p class="count">
            검색결과:
            <strong>{{ count }}</strong
            >건
          </p>
        </div>
        <hr />
      </div>

      <div v-if="keyword" class="search-result-container">
        <PharmacyPill
          v-for="pill in list"
          :key="pill.pillId"
          :pillName="pill.pillName"
          @click="click(pill.pillId)"
          :type="pill.type"
          :imageUrl="pill.imageUrl"
        />
        <Observer @show="loadMoreData" v-if="isScrolled > 0"></Observer>
      </div>
    </div>
  </div>
</template>

<script setup>
import mileStone from "@/assests/img/mileStone.png";
import logo from "@/assests/img/logo.png";
import HeaderFormOnlyString from "@/common/Form/HeaderFormOnlyString.vue";
import SearchBar from "@/common/SearchBar.vue";
import PharmacyPill from "@/common/PharmacyPill.vue";
import Observer from "@/views/pharmacy_search/components/Observer.vue";
import _ from "lodash";
import { useRouter } from "vue-router";
import { ref, onMounted, onUnmounted } from "vue";
import { instance } from "@/util/mainAxios";
import { RouterLink, RouterView } from "vue-router";

const router = useRouter();
const keyword = ref("");
const count = ref(0);
const list = ref([]);
const page = ref(1);

onMounted(() => {
  window.addEventListener("scroll", handleScroll);
  // 초기 로드 시 스크롤 위치 확인
  loadMoreData;
});

onUnmounted(() => {
  window.removeEventListener("scroll", handleScroll);
});

// 더 많은 데이터 로딩
async function loadMoreData() {
  if (count.value <= list.value.length) return;
  // 페이지 번호 증가
  page.value++;
  const { data } = await instance.get("/pill/search", {
    params: {
      keyword: keyword.value,
      page: page.value,
    },
  });
  count.value = data.count;
  list.value = [...list.value, ...data.pills];
}

// 스크롤 위치를 저장할 반응형 참조
const isScrolled = ref(false);

// 스크롤 이벤트 핸들러
function handleScroll() {
  isScrolled.value = window.scrollY > 0;
}

async function input(event) {
  if (keyword.value) return debouncedInput(event.target.value);
  list.value = [];
  keyword.value = event.target.value;
  page.value = 1;
  const { data } = await instance.get("/pill/search", {
    params: {
      keyword: keyword.value,
      page: page.value,
    },
  });
  list.value = data.pills;
  count.value = data.count;
  return data;
}

// 디바운스 함수 정의
const debouncedInput = _.debounce(async (value) => {
  list.value = [];
  keyword.value = value;
  page.value = 1;
  const { data } = await instance.get("/pill/search", {
    params: {
      keyword: keyword.value,
      page: page.value,
    },
  });
  list.value = data.pills;
  count.value = data.count;
  return data;
}, 200);

const click = (pillId) => {
  sessionStorage.setItem("pillId", pillId);
  router.push({
    name: "map",
  });
};
</script>

<style scoped>
.header-container {
  position: sticky;
  top: 0;
  z-index: 999;
}

.basic-background-color {
  background-color: #f9f9f9;
  height: 100%;
  width: 100%;
  z-index: -1;
}

/* 전환 효과 기본 스타일 */
.slide-enter-active,
.slide-leave-active {
  transition: all 0.7s ease;
}

.header-back {
  height: 620px;
  width: 620px;
  top: -60vh;
  left: 50%;
  transform: translateX(-50%);
  background: linear-gradient(60deg, #3183ff 0%, #86e7ee 100%);
  border-radius: 100%;
  overflow: hidden;
  position: fixed;
  transition: top 0.7s; /* 전환 효과 추가 */
}

.header-back-up {
  top: -80vh; /* keyword가 있을 때 위치 */
  background: linear-gradient(100deg, #3183ff 0%, #86e7ee 100%);
}

.header-string {
  display: flex;
  justify-content: center;
  flex-direction: column;
  align-items: center;
  color: white;
  position: relative;
  top: 8%;
  z-index: 9999;
}

.header-string > div > p {
  text-align: center;
  margin: 0;
  font-size: 14px;
}

.logo {
  width: 39px;
  height: 39px;
  margin-bottom: 15px;
}

.address-search-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 50%;
  transition: margin-top 0.7s; /* 전환 효과 추가 */
}

.address-search-container img {
  width: 14px;
  margin: 0;
  margin-right: 8px;
}

.move-up {
  margin-top: 35%; /* keyword가 있을 때의 상단 여백 조정 */
}

.address {
  display: flex;
  align-items: center;
  justify-content: center;
  font-weight: 600;
}

.search-update-info {
  text-align: center;
  color: #666;
  font-size: 12px;
  margin-top: 20px;
}

.count-container {
  margin: 0 33px;
  width: 80%;
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
  gap: 13px;
  display: flex;
  flex-direction: column;
  padding-bottom: 28%;
}
</style>
