<template>
  <div class="pill-card">
    <div class="pill-image">
      <img :src="imageUrl" alt="약 이미지" />
      <!-- <img src="@/assests/img/tempPill.png" alt="약 이미지" /> -->
    </div>
    <div class="pill-info" @click="clickDetail(pillId)">
      <div class="pill-type">
        <strong>{{ type }}</strong>
      </div>
      <div class="pill-name">
        <strong>{{ truncateName(pillName) }}</strong>
      </div>
      <span class="badge-custom" v-for="(badge, index) in badges" :key="index">
        <Badge
          :title="badge.title"
          :backgroundColor="badge.backgroundColor"
          color="white"
          v-if="badge.condition"
        />
      </span>
    </div>
    <font-awesome-icon
      class="icon"
      :icon="['fas', 'circle-plus']"
      size="2xl"
      style="color: #1454b5"
      @click="$emit('clickModal', pillId)"
    />
  </div>
</template>

<script setup>
import { pillSearchStore } from "@/stores/pillSearch";
import { useRouter } from "vue-router";
import Badge from "@/common/Badge.vue";

const router = useRouter();
const store = pillSearchStore();
defineEmits(["clickModal"]);
const props = defineProps({
  pillId: Number,
  pillName: {
    type: String,
    default: "프로다나서캡슐",
  },
  imageUrl: {
    type: String,
    default: "../../assests/img/tempPill.png",
  },
  type: {
    type: String,
    default: "항히스타민제",
  },
  warningPregnant: {
    type: Boolean,
    default: false,
  },
  warningUseDate: {
    type: Boolean,
    default: false,
  },
  warningElders: {
    type: Boolean,
    default: false,
  },
  warningTogether: {
    type: Boolean,
    default: false,
  },
});

async function clickDetail(pillId) {
  // await store.getPillInfo(pillId);
  router.push({
    name: "pilldetail",
    params: { id: pillId },
  });
}

async function clickModal() {}

const truncateName = (name) => {
  if (name.length > 10) {
    return name.slice(0, 8) + "..";
  } else {
    return name;
  }
};

// badges 배열 생성
const badges = [
  {
    title: "임산부 주의",
    backgroundColor: "#FF7070",
    condition: props.warningPregnant,
  },
  {
    title: "노약자 주의",
    backgroundColor: "#77D461",
    condition: props.warningElders,
  },
  {
    title: "투여기간 주의",
    backgroundColor: "#2FD1D1",
    condition: props.warningUseDate,
  },
  {
    title: "병용 주의",
    backgroundColor: "#DFDFDF",
    condition: props.warningTogether,
  },
];

// 이미 두 개의 참인 요소가 있다면 나머지 요소의 condition을 false로 변경
let trueCount = 0;
badges.forEach((badge, index) => {
  if (badge.condition) {
    trueCount++;
    if (trueCount >= 3) {
      badge.condition = false;
    }
  }
});
</script>

<style scoped>
.pill-card {
  width: 82%;
  height: 70px;
  display: flex;
  align-items: center;
  gap: 10px;
  background-color: #ffffff;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
  padding: 5px 10px;
}

.pill-image {
  display: flex;
  width: 120px;
  height: 65px; /* 추가 */
  overflow: hidden;
  border-radius: 8px;
}
.pill-image img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.pill-info {
  width: 160px;
}

.pill-type {
  color: #3183ff;
  font-weight: 600;
  font-size: 10px;
  margin-bottom: 2px;
}

.pill-name {
  font-weight: bold;
  margin-bottom: 5px;
  font-size: 15px;
  color: #303030;
}

.icon {
  display: flex;
  font-size: 30px;
}

.tags {
  justify-content: center;
  align-items: center;
}

.badge-custom {
  padding: 0 4px 0 0;
}
</style>
