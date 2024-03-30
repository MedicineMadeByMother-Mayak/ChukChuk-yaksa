<template>
  <div class="pill-card">
    <div class="pill-image">
      <img src="@/assests/img/tempPill.png" alt="약 이미지" />
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
          fontSize="8px"
          padding="1px 4px 1px 4px"
          style="margin: 0px 1px"
          v-if="badge.condition"
        />
      </span>
      <span v-if="flag == 4">..</span>
      <!-- 수정 end -->
    </div>
    <font-awesome-icon class="delete-icon" :icon="['fas', 'x']" />
  </div>
</template>

<script setup>
import Badge from "@/common/Badge.vue";
import { ref } from "vue";

const flag = ref(0);

const props = defineProps({
  pillId: 1,
  pillName: {
    type: String,
    default: "프로다나서캡슐",
  },
  imageUrl: "@/assests/img/tempPill.png",
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
    return name.slice(0, 13) + "..";
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
    flag.value++;
    if (trueCount > 3) {
      badge.condition = false;
    }
  }
});
</script>

<style scoped>
.pill-image {
  display: flex;
  padding: 5px 2px;
}

.pill-card {
  font-size: 12px;
  position: relative;
  display: flex;
  align-items: center;
  background-color: #ffffff;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
}

.pill-image img {
  max-width: 103px;
  max-height: 80px; /* 추가 */
  border-radius: 12px;
  padding: 0px 6px 0 5px;
}

.pill-info {
  flex-grow: 1;
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
  padding: 15px;
}

.tags {
  justify-content: center;
  align-items: center;
}

.badge-custom {
  padding: 0 0 0 0;
}

.delete-icon {
  position: absolute;
  top: 3px;
  right: 3px;
  cursor: pointer;
  margin: 3px;
}
</style>
