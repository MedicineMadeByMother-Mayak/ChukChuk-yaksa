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

      <span
        v-if="categoryflag"
        class="badge-custom"
        v-for="(badge, index) in badges"
        :key="index"
      >
        <Badge
          :title="badge.title"
          :backgroundColor="badge.backgroundColor"
          color="white"
          fontSize="8px"
          padding="1px 3px"
          style="margin: 0px 1px"
          v-if="badge.condition"
        />
      </span>
      <span
        v-else
        class="badge-custom"
        v-for="(badge, elseindex) in props.tags"
        :key="elseindex"
      >
        <Badge
          :title="badge.tagName"
          backgroundColor="#898989"
          color="white"
          fontSize="8px"
          padding="1px 4px 1px 4px"
          style="margin: 0px 1px"
        />
      </span>
    </div>
    <div class="ellipsis-icon">
      <font-awesome-icon :icon="['fas', 'ellipsis-vertical']" />
    </div>
  </div>
</template>

<script setup>
import Badge from "@/common/Badge.vue";
import { ref } from "vue";

const categoryflag = defineModel();
const props = defineProps({
  pillId: {
    type: Number,
    default: 1,
  },
  pillName: {
    type: String,
    default: "프로다나서캡슐",
  },
  imageUrl: "@/assests/img/tempPill.png",
  type: {
    type: String,
    default: "항히스타민제",
  },
  company: {
    type: String,
    default: "항히스타민제",
  },
  categories: {
    type: Array,
    default: ["전체", "부작용"],
  },
  tags: {
    type: Array,
    default: [
      //태그 리스트(String List)
      {
        tagId: 0, //태그 id값
        tagName: "알러지 진정", //태그 이름
      },
    ],
  },
});

const truncateName = (name) => {
  if (name.length > 10) {
    return name.slice(0, 13) + "..";
  } else {
    return name;
  }
};

const badges = ref([]);

let sideeffectbadge = false;
let effectbadge = false;
let stopbadge = false;

props.categories.forEach((category, index) => {
  if (category === "부작용") {
    sideeffectbadge = true;
  } else if (category === "중단") {
    stopbadge = true;
  } else if (category === "효과") {
    effectbadge = true;
  }
});

badges.value = [
  {
    title: "부작용",
    backgroundColor: "#ff7070",
    condition: sideeffectbadge,
  },
  {
    title: "효과",
    backgroundColor: "#77d461",
    condition: effectbadge,
  },
  {
    title: "중단",
    backgroundColor: "#f9ed84",
    condition: stopbadge,
  },
];
</script>

<style scoped>
.pill-image {
  display: flex;
  padding: 5px 2px;
}

.pill-card {
  caret-color: transparent;
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

.ellipsis-icon {
  position: absolute;
  width: 20px;
  height: 20px;
  top: 3px;
  right: 3px;
  cursor: pointer;
  margin: 3px 5px;
  display: flex;
  justify-content: end;
  align-items: start;
}
</style>
