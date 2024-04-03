<template>
  <!-- 약국검색창 -->
  <RouterView />
  <div v-if="$route.path === '/pharmacy'" class="basic-background-color">
    <div class="header-container">
      <HeaderFormOnlyString :title="'약국검색'" />
      <transition class="header-transition" name="slide">
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
      <p class="address">
        <img :src="mileStone" alt="" />대전광역시 유성구 덕명동 124
      </p>
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
        <Observer v-if="showObserver" @show="loadMoreData"> 0"></Observer>
      </div>
    </div>
    <Footer></Footer>
  </div>
</template>

<script setup>
import Footer from "@/common/FooterNoColor.vue";
import mileStone from "@/assests/img/mileStone.png";
import logo from "@/assests/img/logo.png";
import HeaderFormOnlyString from "@/common/Form/HeaderFormOnlyString.vue";
import SearchBar from "@/common/SearchBar.vue";
import PharmacyPill from "@/common/PharmacyPill.vue";
import Observer from "@/views/pharmacy_search/components/Observer.vue";
import _ from "lodash";
import { useRouter } from "vue-router";
import { ref, onMounted, onUnmounted, watch } from "vue";
import { instance } from "@/util/mainAxios";
import { RouterView } from "vue-router";

const router = useRouter();
const keyword = ref("");
const count = ref(0);
const list = ref([]);
const page = ref(1);
const showObserver = ref(false);

onMounted(() => {
  loadMoreData();
});

// 키워드가 변경될 때 실행될 watch
watch(keyword, (newValue, oldValue) => {
  if (newValue.trim() !== "") {
    showObserver.value = false;
    // 0.7초(700ms) 후 showObserver 값을 true로 설정
    setTimeout(() => {
      showObserver.value = true;
    }, 700);
  } else {
    // 키워드가 비어있다면 Observer를 바로 숨김
    showObserver.value = false;
  }
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
.basic-background-color {
  background-color: #f9f9f9;
  height: 100%;
  width: 100%;
  z-index: -1;
}

.header-container {
  position: absolute;
  top: 0;
  overflow: hidden;
  background-color: #f9f9f9;
  height: 100%;
  width: 100%;
}

.header-transition {
  position: absolute;
}

/* 전환 효과 기본 스타일 */
.slide-enter-active,
.slide-leave-active {
  transition: all 0.7s ease;
}

.header-back {
  height: 620px;
  width: 620px;
  top: -60%;
  left: 50%;
  transform: translateX(-50%);
  background: linear-gradient(60deg, #3183ff 0%, #86e7ee 100%);
  border-radius: 100%;
  position: absolute;
  transition: top 0.7s; /* 전환 효과 추가 */
}

.header-back-up {
  top: -80%; /* keyword가 있을 때 위치 */
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
  z-index: 1;
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
  z-index: 1;
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
  z-index: 1;
}
</style>
