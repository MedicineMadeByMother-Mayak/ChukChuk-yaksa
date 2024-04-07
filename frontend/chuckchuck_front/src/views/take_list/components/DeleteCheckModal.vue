<!-- 사용방법
  <template>

  <DeleteCheckModal v-model="msg" :modalData="[['어떤 약에 대한 알람을 등록하시겠어요?', true, {}, {}]]"/>

</template>

<script setup>
import DeleteCheckModal from '@/views/take_list/components/DeleteCheckModal.vue';
import {ref} from "vue";
const msg = ref(true);
</script> -->

<template>
  <div class="modal-overlay" v-if="showModal">
    <div class="modal">
      <div class="modal-header">
        <div class="close-button">
          <div class="close" @click="$emit('close')">&times;</div>
        </div>
        <div>
          <div class="top">
            <img
              style="width: 20px; height: 20px"
              src="@/assests/img/startLogo.png"
              alt=""
            />
            <div style="margin: 18px 9px; font-size: 14px; font-weight: bold">
              {{ truncateName(pillName) }}을
              <span style="color: red">삭제</span>하시겠어요?
            </div>
          </div>
          <div class="text-container">
            <span class="text"
              >[ <strong style="">{{ takeListName }}</strong> ] 리스트에서</span
            >
            <span class="text" style="margin: 5px 10px"
              ><strong>{{ pillName }}</strong
              >을 삭제합니다.</span
            >
          </div>
          <div style="display: flex; justify-content: center">
            <button class="delete-button" @click="deletePill">DELETE</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive } from "vue";
import { useRouter } from "vue-router";
import { takelistStore } from "@/stores/takelist";
const store = takelistStore();

const router = useRouter();

const redirectToTakeList = () => {
  router.push({ name: "TakeList" }); // 'AlarmModalTime'은 목적지 컴포넌트의 라우터 이름입니다.
};
const showModal = ref(true);

const closeModal = () => {
  showModal.value = false;
};

const props = defineProps({
  takeListId: {
    type: Number,
  },
  takeListName: {
    type: String,
  },
  pillId: {
    type: Number,
  },
  pillName: {
    type: String,
    default: "프로다나서캡슐",
  },
  imageUrl: {
    type: String,
    default: "@/assests/img/tempPill.png",
  },
});

const deletePill = async () => {
  await store.deletePill(props.takeListId, props.pillId);
  await store.getTakeListPageDatas();
  closeModal();
};

const truncateName = (name) => {
  if (name.length > 5) {
    return name.slice(0, 7) + "...";
  } else {
    return name;
  }
};
</script>

<style scoped>
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.6);
  display: flex;
  align-items: center;
  justify-content: center;
  caret-color: transparent;
  z-index: 1000;
}

.modal {
  background: white;
  width: 300px;
  height: 30%;
  border-radius: 10px;
  overflow: hidden;
}

.modal-menu {
  list-style: none;
  padding: 0;
  margin: 0;
}

/* 제목 CSS  */
.top {
  display: flex;
  justify-content: center;
  align-items: center;
}

.top img {
  width: 20px;
  height: 20px;
  margin-right: 0px;
}

.top div {
  font-size: 14px;
}

/* X버튼 CSS */
.close-button {
  position: relative;
  margin-left: auto;
}

.close {
  color: #aaaaaa;
  font-size: 18px;
  font-weight: bold;
  text-align: right;
  margin-right: 14px;
}

.router-link-item {
  text-decoration: none;
  color: inherit;
}

/* 알람 목록 CSS */
.alarm-img {
  width: 10px;
  height: 10px;
  align-self: flex-start;
}

.text-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}

.pill-text-container {
  font-size: 20px;
  font-weight: bold;
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  gap: 8px;
}

.text {
  /* white-space: ;  */
  text-align: center;
  text-overflow: ellipsis;
}

.delete-button {
  width: 50%;
  height: 27px;
  margin: 20px;
  font-weight: bold;
  font-size: 12px;
  color: white;
  background-color: #565656;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  transition-duration: 0.3s;
}

.delete-button:active {
  background-color: white;
  color: navy;
}
</style>
