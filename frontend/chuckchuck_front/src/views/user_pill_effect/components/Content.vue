<template>
  <div class="pill-card">
    <div class="pill-image">
      <!-- <img :src="imageUrl" alt="약 이미지" /> -->
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
          v-if="badge.condition"
        />
      </span>
      <span
        v-else
        class="badge-custom"
        v-for="(badge, elseindex) in props.tags"
        :key="elseindex"
      >
        <Badge :title="badge.tagName" backgroundColor="#898989" color="white" />
      </span>
    </div>
    <div class="ellipsis-icon" @click="openModal">
      <font-awesome-icon
        :icon="['fas', 'ellipsis-vertical']"
        style="color: #a1a1a1"
      />
    </div>
  </div>
  <AddModalForm
    v-model="msg"
    @delet-pill-effect="deletPillEffect"
    :modalData="[
      [
        '수정',
        '하기',
        true,
        { params: { pillId: props.pillId }, Link: 'effectdetail' },
      ],
      ['삭제', '하기', false, { emitName: 'deletPillEffect' }],
    ]"
  />
</template>

<script setup>
import Badge from "@/common/Badge.vue";
import { ref } from "vue";
import AddModalForm from "@/common/Form/AddModalForm.vue";
import { pillEffectStore } from "@/stores/pilleffect";

const msg = ref(false); //모달창 관리하는 변수 ref로 반드시 설정해주세요
const pilleffectstore = pillEffectStore();
const categoryflag = defineModel();
const props = defineProps({
  tabnum: {
    type: Number,
    default: 0,
  },
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

async function deletPillEffect() {
  await pilleffectstore.deletFillEffect(props.pillId);
}

const truncateName = (name) => {
  if (name.length > 10) {
    return name.slice(0, 8) + "..";
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
    backgroundColor: "#FFBB55",
    condition: stopbadge,
  },
];

function openModal() {
  msg.value = true;
}
</script>

<style scoped>
.pill-card {
  width: 85%;
  height: 70px;
  display: flex;
  align-items: center;
  gap: 7px;
  background-color: #ffffff;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
  padding: 5px 10px;
}

.pill-image {
  display: flex;
  width: 100px;
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
  font-weight: bold;
  font-size: 10px;
  margin-bottom: 2px;
}

.pill-name {
  font-weight: bold;
  margin-bottom: 5px;
  font-size: 15px;
}

.icon {
  /* padding: 15px; */

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

.ellipsis-icon {
  width: 1%;
  height: 100%;
  display: flex;
  font-size: 20px;
  justify-content: end;
  align-items: start;
  cursor: pointer;
  margin-top: 10px;
}
</style>
