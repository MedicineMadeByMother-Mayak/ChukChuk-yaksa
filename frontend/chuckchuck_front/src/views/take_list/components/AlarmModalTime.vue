<template>
  <div class="modal-overlay" v-if="showModal">
    <div class="modal">
      <ul class="modal-menu">
        <li v-for="(item, index) in modalData" :key="index">
          <RouterLink
            v-if="item[1]"
            :to="{ name: item[2].Link, params: item[2].params }"
            class="router-link-item"
          >
          <div class="modal-header">
            
            <div class="close-button">
              <div class="close" @click="closeModal">&times;</div>
            </div>
            <div class="top">
              <img style="width: 20px; height: 20px" src="@/assests/img/startLogo.png" alt="">
              <div style="margin: 19px; font-size: 14px; font-weight: bold;">알람을 울릴 시간을 선택해주세요</div>
            </div>

            <div>
              <input type="radio" id="mail" name="contact" value="mail" />
              <label for="mail">우편</label>
            </div>
            <div>
              <input type="radio" id="mail" name="contact" value="mail" />
              <label for="mail">우편2</label>
            </div>

            <button class="save-button" @click="redirectToTakeList">SAVE</button>
          </div>
            
          </RouterLink>
          <hr style="margin: 0px 10px" v-if="index < modalData.length - 1" />
        </li>
      </ul>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive } from "vue";

import { useRouter } from 'vue-router';

const router = useRouter();

const redirectToTakeList = () => {
  router.push({ name: 'TakeList' }); 
};

const showModal = ref(true);
const medicines = reactive({
  '빈혈약': { isActive: false, icon: 'fa-solid fa-bell-slash' },
  '저혈압약': { isActive: false, icon: 'fa-solid fa-bell-slash' },
  '고혈압약': { isActive: false, icon: 'fa-solid fa-bell-slash' },
  '당뇨약': { isActive: false, icon: 'fa-solid fa-bell-slash' },
  '심장약': { isActive: false, icon: 'fa-solid fa-bell-slash' },
});

const toggleActive = (name) => {
  const medicine = medicines[name];
  medicine.isActive = !medicine.isActive;
  medicine.icon = medicine.isActive ? 'fa-solid fa-bell' : 'fa-solid fa-bell-slash';
};

const closeModal = () => {
  showModal.value = false;
};
// const showModal = defineModel();
const props = defineProps({
  modalData: {
    type: Array,
    default: [["원하는 텍스트 넣으세요", true, { params: {}, Link: "home" }]],
  },
});

// const performAction = (action) => {
//   console.log("Action performed:", action);
//   showModal.value = !showModal.value;
// };

// const closeModal = () => {
//   showModal.value = !showModal.value;
// };


</script>

<style scoped>
label {
  font-size: 18px;
  line-height: 2rem;
  padding: 0.2em 0.4em;
}

[type="radio"],
span {
  vertical-align: middle;
  appearance: none;
  border: max(2px, 0.1em) solid gray;
  border-radius: 50%;
  width: 1.25em;
  height: 1.25em;
  transition: border 0.5s ease-in-out;
}
[type="radio"]:checked {
  border: 0.4em solid tomato;
}
[type="radio"]:focus-visible {
  outline: max(2px, 0.1em) dotted tomato;
  outline-offset: max(2px, 0.1em);
}
[type="radio"]:hover {
  box-shadow: 0 0 0 max(4px, 0.2em) lightgray;
  cursor: pointer;
}

[type="radio"]:hover + span {
  cursor: pointer;
}
.button-container button.active {
  background-color: #FFD43B; /* 활성 버튼 배경 */
  color: white; /* 활성 버튼 텍스트 색상 */
}
.modal-overlay {
  position: fixed;
  bottom: 0;
  width: 320px;
  height: 120%;
  background: rgba(0, 0, 0, 0.6);
  caret-color: transparent;
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
  position:relative;
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
  height:10px;
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

.save-button:active{
  background-color: white;
  color: navy;
}

</style>
