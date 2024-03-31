<template>
  <div class="modal-overlay" v-if="showModal">
    <div class="modal">
      <div class="close-button">
        <div class="close" @click="closeModal">&times;</div>
      </div>
      <div class="modal-title">
        <img src="@/assests/img/Group.png" />
        <span><strong>알람을 울릴 시간을 선택해주세요.</strong></span>
      </div>
      <ul class="modal-menu">
        <li v-for="(item, index) in modalData" :key="index">
          <div class="router-link-item">
            <input
              type="radio" 
              class="radio-button"
              :id="'radio_' + index"
              name="selectTime"
              :value="index"
              v-model="selectTime"
            />
            <label :for="'radio_' + index">{{ item[1] }}</label>
          </div>
        </li>
        <button class="save-button" @click="redirectToTakeList">SAVE</button>
          
     
      </ul>
    </div>
  </div>
</template>

<script setup>
import { ref} from "vue";
import { useRouter } from 'vue-router';

const router = useRouter();
const showModal = defineModel();
const selectTime = ref(null);

const redirectToTakeList = () => {
  router.push({ name: 'TakeList' }); 
};

const closeModal = () => {
  showModal.value = !showModal.value;
};
const props = defineProps({
  modalData: {
    type: Array,
    default: [[1, "원하는 텍스트 넣으세요"]],
  },
});

</script>

<style scoped>

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
  bottom: 0px;
	position: absolute;
  display: flex;
  flex-direction: column;
}

.modal-title {
  display: flex;
  align-items: center;
  margin-top: 15px;
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
  margin: 19px; font-size: 14px; font-weight: bold;
  margin-right: -11px;
}

.modal-menu li {
  background: white;
  font-size: 11px;
  display: flex;
  margin: 0 50px 12px 0;
}
.modal-menu {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.router-link-item {
  text-decoration: none;
  color: inherit;
  display: flex;
  align-items: start;
  margin: 0;
}
.radio-button {
  border-color: rgb(0, 0, 0);
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
  margin: 0 40px 0 0;
}


.save-button:active{
  background-color: white;
  color: navy;
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



</style>
