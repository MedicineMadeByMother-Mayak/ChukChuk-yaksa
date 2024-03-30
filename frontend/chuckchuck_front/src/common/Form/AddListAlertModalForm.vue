<!-- 사용법 RouterLink편
<template>
    <ModalForm v-model="msg" :modalData="[
    ['원하는 텍스트', RouterLink면 true axios면 false, {params:{ 전송하는 데이터 }, Link:'연결을 원하는 링크'],
    ['원하는 텍스트', RouterLink면 true axios면 false, {params:{ 전송하는 데이터 }, Link:'연결을 원하는 링크'],
    ]" /> 
</template> 

<script setup>
    import { ref } from "vue";
    const msg = ref(true); //모달창 관리하는 변수 ref로 반드시 설정해주세요
</script>
-->

<template>
  <div class="modal-overlay" v-if="showModal" @click="closeModal">
    <div class="modal">
      <div class="modal-title">
        <img src="@/assests/img/Group.png" />
        <span><strong>추가할 리스트를 선택하세요.</strong></span>
      </div>
      <ul class="modal-menu">
        <li v-for="(item, index) in modalData" :key="index">
          <div class="router-link-item">
            <input
              type="radio"
              class="radio-button"
              :id="'radio_' + index"
              name="selecteListId"
              :value="index"
              v-model="selecteListId"
            />
            <label :for="'radio_' + index">{{ item[1] }}</label>
          </div>
        </li>
        <button class="modal-button">save</button>
      </ul>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
const selecteListId = ref(null); // 선택된 항목을 저장하는 변수
const showModal = defineModel();
const props = defineProps({
  modalData: {
    type: Array,
    default: [[1, "원하는 텍스트 넣으세요"]],
  },
});

const closeModal = () => {
  showModal.value = !showModal.value;
};
</script>

<style scoped>
.modal-overlay {
  position: fixed;
  top: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.6);
  display: flex;
  align-items: center;
  justify-content: center;
  caret-color: transparent;
}

.modal {
  background: white;
  width: 80%;
  max-width: 265px;
  border-radius: 10px;
  overflow: hidden;
}

.modal-title {
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 25px 0 20px -10px;
  font-weight: bold;
  font-size: 13px;
  color: rgb(40, 40, 40);
}

.modal-title img {
  width: 15px;
  margin: 0 7px 0 0;
}

.modal-menu li {
  background: white;
  font-size: 11px;
  display: flex;
  margin: 0 0 12px 0;
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

.modal-button {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 25px;
  width: 80%;
  border: none; /* border 없음 */
  background-color: navy; /* 남색 배경 */
  color: white;
  border-radius: 3px;
  margin: 21px 0 28px 0;
  padding: 0px;
}

</style>
