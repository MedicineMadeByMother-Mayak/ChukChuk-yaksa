<template>
  <div class="pill-card">
    <div class="pill-image">
      <img :src="imageUrl" alt="약 이미지" />
    </div>
    <div class="pill-info">
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
          fontSize="9px"
          padding="1px 4px 1px 4px"
          v-if="badge.condition"
        />
      </span>
    </div>
    <font-awesome-icon
      class="icon"
      :icon="['fas', 'circle-plus']"
      size="2xl"
      style="color: #1454b5"
    />
  </div>
</template>

<script setup>
import Badge from "@/common/Badge.vue";

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

const truncateName = (name) => {
  if (name.length > 10) {
    return name.slice(0, 10) + "..";
  } else {
    return name;
  }
};

// badges 배열 생성
const badges = [
  {
    title: "임산부 주의",
    backgroundColor: "#ff9999",
    condition: props.warningPregnant,
  },
  {
    title: "노인 주의",
    backgroundColor: "#77d461",
    condition: props.warningElders,
  },
  {
    title: "투여기간 주의",
    backgroundColor: "#ffb555",
    condition: props.warningUseDate,
  },
  {
    title: "병용 주의",
    backgroundColor: "#2fd1d1",
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
  height: 90px;
  font-size: 12px;
  display: flex;
  align-items: center;
  background-color: #ffffff;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
}

.pill-image {
  display: flex;
  height: 64px;
  padding: 5px 2px;
}

.pill-image img {
  max-width: 90px;
  max-height: 64px; /* 추가 */
  border-radius: 12px;
  padding: 0px 6px 0 5px;
  object-fit: cover;
}

.pill-info {
  width: 160px;
}

.pill-type {
  color: #3183ff;
  font-weight: bold;
  font-size: 9px;
  margin-bottom: 5px;
}

.pill-name {
  font-weight: bold;
  margin-bottom: 5px;
  font-size: 11px;
}

.icon {
  display: flex;
  margin-left: auto;
  padding: 0 15px 0 0;
}

.tags {
  justify-content: center;
  align-items: center;
}

.badge-custom {
  padding: 0 2px 0 0;
}
</style>
