<template>
  <div class="modal-overlay" v-if="showModal" @click="closeModal">
    <div class="modal" @click.stop="">
      <div class="close-button">
        <div class="close" @click="closeModal">&times;</div>
      </div>
      <div class="modal-title">
        <img src="@/assests/img/Group.png" />
        <span><strong>알람을 울릴 시간을 선택해주세요.</strong></span>
      </div>
      <ul class="modal-menu">
        <li
          @click="
            () => {
              select = 0;
            }
          "
        >
          <div class="router-link-item">
            <label :id="0">
              <input
                type="radio"
                :for="0"
                class="radio-button"
                name="selectTime"
                v-model="selectTime"
              />매일
              <select id="hourSelect" v-model="hours">
                <option v-for="hour in hoursOptions" :key="hour" :value="hour">
                  {{ hour }}
                </option>
              </select>
              :
              <select id="minuteSelect" v-model="minutes">
                <option
                  v-for="minute in minutesOptions"
                  :key="minute"
                  :value="minute"
                >
                  {{ minute }}
                </option>
              </select>
              에 알람을 울립니다.</label
            >
          </div>
        </li>

        <li
          @click="
            () => {
              select = 1;
            }
          "
        >
          <div class="router-link-item">
            <label :id="1">
              <input
                :for="1"
                type="radio"
                class="radio-button"
                name="selectTime"
                v-model="selectTime"
              />
              <select id="hourSelect" v-model="hours_second">
                <option v-for="hour in hoursOptions" :key="hour" :value="hour">
                  {{ hour }}
                </option>
              </select>
              :
              <select id="minuteSelect" v-model="minutes_second">
                <option
                  v-for="minute in minutesOptions"
                  :key="minute"
                  :value="minute"
                >
                  {{ minute }}
                </option>
              </select>
              기준,
              <select id="hourSelect" v-model="selectCycle">
                <option v-for="hour in hoursOptions" :key="hour" :value="hour">
                  {{ hour }}
                </option>
              </select>
              시간마다 알람을 울립니다.</label
            >
          </div>
        </li>
        <button class="save-button" @click="turnOn">SAVE</button>
      </ul>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from "vue";
import { alarmStore } from "@/stores/alarm";

const hoursOptions = Array.from({ length: 24 }, (_, i) =>
  String(i).padStart(2, "0")
);
const minutesOptions = Array.from({ length: 60 }, (_, i) =>
  String(i).padStart(2, "0")
);

const props = defineProps({
  modalData: {
    type: Array,
    default: [[1, "원하는 텍스트 넣으세요"]],
  },
  selectTakeList: {
    type: Number,
    default: 0,
  },
  createOrModify: {
    type: Number,
    default: 0,
  },
});

const showModal = defineModel();
const store = alarmStore();

const selectTime = ref(null);
const selectCycle = ref(12);
const select = ref(0);
const currentDateTime = ref("");
const hours = ref("");
const hours_second = ref("");
const minutes = ref("");
const minutes_second = ref("");

onMounted(() => {
  currentDateTime.value = getCurrentDateTime();
  // 시간 실시간 업데이트
  // setInterval(() => {
  //   currentDateTime.value = getCurrentDateTime();
  // }, 1000); // 매 초마다 현재 시간 업데이트
});

// 알람 등록
async function turnOn() {
  // 0 이면 생성 1 이면 수정
  if (props.createOrModify === 0) {
    if (select.value === 0) {
      // 24시간 주기
      await store.turnOn(props.selectTakeList, currentDateTime.value, 24);
    } else {
      // cycle 주기
      await store.turnOn(
        props.selectTakeList,
        currentDateTime.value,
        selectCycle.value
      );
    }
  } else if (props.createOrModify === 1) {
    if (select.value === 0) {
      // 24시간 주기
      await store.modify(props.selectTakeList, currentDateTime.value, 24);
    } else {
      // cycle 주기
      await store.modify(
        props.selectTakeList,
        currentDateTime.value,
        selectCycle.value
      );
    }
  }
  store.getAlarmList();
  store.getOffAlarmList();
  closeModal();
}

// 모달 닫기
const closeModal = () => {
  showModal.value = !showModal.value;
};

// 현재 날짜와 시간을 문자열로 가져오는 함수입니다.
function getCurrentDateTime() {
  const now = new Date();
  const year = now.getFullYear();
  const month = String(now.getMonth() + 1).padStart(2, "0");
  const day = String(now.getDate()).padStart(2, "0");
  hours.value = String(now.getHours()).padStart(2, "0");
  minutes.value = String(now.getMinutes()).padStart(2, "0");
  const seconds = String(now.getSeconds()).padStart(2, "0");
  hours_second.value = String(now.getHours()).padStart(2, "0");
  minutes_second.value = String(now.getMinutes()).padStart(2, "0");

  return `${year}-${month}-${day} ${hours.value}:${minutes.value}:${seconds}`;
}
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
  bottom: 0px;
  position: absolute;
  display: flex;
  flex-direction: column;
  animation: slideIn 0.7s ease-in-out;
}

.modal-title {
  display: flex;
  align-items: center;
  margin-top: 5px;
  margin-left: 20px;
  margin-bottom: 10px;
}

.modal-title img {
  width: 20px;
  height: 20px;
  margin-right: -15px;
  margin-left: 10%;
}

.modal-title span {
  margin: 19px;
  font-size: 14px;
  font-weight: bold;
  margin-right: -11px;
}

.modal-menu li {
  background: white;
  font-size: 11px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 20px;
}
.modal-menu {
  padding: 0;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin-top: 5px;
}
.router-link-item {
  text-decoration: none;
  color: inherit;
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 0;
}

.router-link-item > label {
  display: flex;
  align-items: center;
  justify-content: center;
}

.time-zone {
  width: 18px;
  height: 15px;
  background-color: #f0efef;
  text-align: center;
}

.radio-button {
  border-color: rgb(0, 0, 0);
}

.txt {
  text-overflow: ellipsis;
  white-space: nowrap;
}

.save-button {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 25px;
  width: 80%;
  border: none; /* border 없음 */
  background-color: navy; /* 남색 배경 */
  color: white;
  border-radius: 5px;
  padding: 0px;
  font-size: smaller;
}

.save-button:active {
  background-color: white;
  color: navy;
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
</style>
