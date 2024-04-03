<!-- 사용방법
  <template>

  <AlarmModal v-model="msg" :modalData="[['어떤 약에 대한 알람을 등록하시겠어요?', true, {}, {}]]"/>

</template>

<script setup>
import AlarmModal from '@/views/take_list/components/AlarmModal.vue';
import {ref} from "vue";
const msg = ref(true);
</script> -->

<template>
  <div class="modal-overlay" v-if="showModal" @click="closeModal">
    <div class="modal" @click.stop="">
      <ul class="modal-menu" @click.stop="">
        <li v-for="(item, index) in modalData" :key="index">
          <RouterLink
            v-if="item[1]"
            :to="{ name: item[2].Link, params: item[2].params }"
            class="router-link-item"
          >
            <div class="modal-header" @click.prevent="">
              <div class="close-button">
                <div class="close" @click="closeModal">&times;</div>
              </div>

              <div class="top">
                <img
                  style="width: 20px; height: 20px"
                  src="@/assests/img/startLogo.png"
                  alt=""
                />
                <div style="margin: 9px; font-size: 14px; font-weight: bold">
                  어떤 약에 대한 알람을
                  <span style="color: green">등록</span>하시겠어요?
                </div>
              </div>
              <div class="button-container">
                <button
                  v-for="(alarm, idx) in store.offAlarmList"
                  :key="alarm.takeListId"
                  @click.prevent="clickAlarm(alarm.takeListId)"
                >
                  <font-awesome-icon
                    :icon="['fas', 'bell']"
                    size="xs"
                    style="color: gray"
                  />
                  <!-- <font-awesome-icon :icon="medicine.isActive ? ['fas', 'bell'] : ['fas', ]" style="color: gray;"/> -->
                  <span class="text">{{ alarm.takeListName }}</span>
                </button>
              </div>
              <button
                class="save-button"
                @click.prevent="$emit('save', selectTakeList)"
              >
                SAVE
              </button>
            </div>
          </RouterLink>
          <hr style="margin: 0px 10px" v-if="index < modalData.length - 1" />
        </li>
      </ul>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from "vue";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import { library } from "@fortawesome/fontawesome-svg-core";
import { faBell, faBellSlash } from "@fortawesome/free-solid-svg-icons";
import { alarmStore } from "@/stores/alarm";
library.add(faBell, faBellSlash);

const store = alarmStore();
defineEmits(["save"]);
const showModal = defineModel();
const selectTakeList = ref(0);

const props = defineProps({
  modalData: Array,
});

const medicines = reactive({});

function clickAlarm(id) {
  selectTakeList.value = id;
}

const toggleActive = (name) => {
  medicines[name].isActive = !medicines[name].isActive;
};

// 창 닫기
const closeModal = () => {
  showModal.value = false;
};

onMounted(async () => {
  await store.getOffAlarmList();
});
</script>

<style scoped>

@keyframes slideIn {
  from {
    transform: translateY(100%);
  }
  to {
    transform: translateY(0%);
  }
}
.button-container button.active {
  background-color: #ffd43b;
  color: white;
}
.modal-overlay {
  position: fixed;
  bottom: 0;
  width: 320px;
  height: 658px;
  margin-left: -10px;
  background: rgba(0, 0, 0, 0.6);
  caret-color: transparent;
  z-index: 9999;
  backdrop-filter: blur(1px);
}

.modal {
  background: white;
  width: 100%;
  height: 40%;
  border-top-left-radius: 15px;
  border-top-right-radius: 15px;
  overflow: hidden;
  bottom: 0px;
  position: absolute;
  animation: slideIn 0.7s ease-in-out;
}

.modal-menu {
  list-style: none;
  padding: 0;
  margin: 0;
}

/* .modal-menu li {
  text-align: center;
  background: white;
  color: black;
  font-weight: bold;
  cursor: pointer;
}

.modal-menu hr {
  margin: 0;
  border: none;
  border-top: 1px solid #ccc;
}

.modal-menu li:hover {
  background-color: #f2f2f2;
}

.modal-menu li:not(hr):hover {
  background-color: #f7f7f7;
} */

/* 제목 CSS  */
.top {
  display: flex;
  align-items: center;
  margin-top: 1px;
  margin-left: 25px;
  margin-bottom: 10px;
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
  font-size: 28px;
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

/* Button 스타일 */
.button-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  gap: 8px;
}

.button-container button {
  display: flex;
  align-items: center;
  justify-content: space-around;
  width: 80px;
  height: 25px;
  margin: 5px;
  background-color: white;
  box-shadow: 0 0 0.8em rgba(0, 0, 0, 0.2);
  border-radius: 8px;
  border: none;
  outline: none;
  cursor: pointer;
  font-weight: bold;
  font-size: 10px;
}

.button-container button:active,
.button-container button:focus {
  background-color: #aaaaaa;
  color: white;
  font-weight: bold;
}

/* 버튼 이미지 스타일 */
.alarm-img {
  width: 10px;
  height: 10px;
  margin-left: 0;
  margin-top: 7px;
}

.text {
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}

.save-button {
  margin-top: 30px;
  margin-left: 50%;
  transform: translateX(-50%);
  width: 70%;
  height: 30px;
  font-weight: bold;
  font-size: 12px;
  color: white;
  background-color: navy;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  transition-duration: 0.3s;
}

.save-button:active {
  background-color: white;
  color: navy;
}
</style>
