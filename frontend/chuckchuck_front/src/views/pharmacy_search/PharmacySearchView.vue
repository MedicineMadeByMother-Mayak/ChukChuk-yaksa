<template>
  <!-- 약국검색창 -->
  <div class="basic-background-color">
    <HeaderFormOnlyString :title="'약국검색'" />

    <transition name="slide">
      <div class="header-back" :class="{ 'header-back-up': keyword }"></div>
    </transition>
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
        @input="input"
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
import { useRouter } from "vue-router";
import { ref } from "vue";
import { instance } from "@/util/mainAxios";

const router = useRouter();
const keyword = ref("");
const count = ref(0);
const list = ref([]);

async function input(event) {
  keyword.value = event.target.value;
  const { data } = await instance.get("/pill/search", {
    params: {
      keyword: event.target.value,
      page: 1,
    },
  });

  list.value = data.pills;
  return data.pills;
}

const click = (pillId) => {
  localStorage.setItem("pillId", pillId);
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
  width: 65%;
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
}
</style>
