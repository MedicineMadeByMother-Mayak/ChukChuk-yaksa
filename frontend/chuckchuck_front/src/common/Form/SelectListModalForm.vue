<!-- 
[사용법]

템플릿 추가 예시
<template>
    <SelectListModalForm
      v-model="msg"
      :modalData="[
        [1, '달디달디달디달'],
        [2, '단 밤양갱'],
        [3, '단 밤양갱'],
        [4, '밤양갱밤양갱밤갱'],
        [5, '밤양갱이얌'],
      ]"
    />
</template> 

[1, '달디달디달디달']는 순서대로 ['리스트 index', '리스트 이름']이다


스크립트에 아래 세줄 추가 필
<script setup>
import SelectListModalForm from "@/common/Form/SelectListModalForm.vue";
import { ref } from "vue";
const msg = ref(true);
</script>
-->

<!-- 사용법 axios편
추후 추가 예정 -->

<template>
  <div class="modal-overlay" v-if="showModal" @click="closeModal">
    <div class="modal" @click.stop="">
      <div class="modal-menu">
        <div class="modal-title">
          <img src="@/assests/img/Group.png" />
          <p>추가할 리스트를 선택하세요.</p>
        </div>
        <ul class="modal-list">
          <div class="router-link-item-container">
            <li
              v-for="(item, index) in modalData"
              :key="item[0]"
              @click="select(item[0])"
            >
              <div class="router-link-item">
                <input
                  type="radio"
                  class="radio-button"
                  :id="'radio_' + item[0]"
                  name="selecteListId"
                  :value="item[0]"
                  v-model="selecteListId"
                />
                <label :for="'radio_' + item[0]" style="margin-left: 10px">{{
                  item[1]
                }}</label>
              </div>
            </li>
          </div>
        </ul>
      </div>
      <button @click="$emit(`save`, selecteListId)" class="modal-button">
        save
      </button>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
const selecteListId = ref(null); // 선택된 항목을 저장하는 변수
const showModal = defineModel();
defineEmits(["save"]);
const props = defineProps({
  modalData: {
    type: Array,
    default: [[1, "원하는 텍스트 넣으세요"]],
  },
});

function select(Id) {
  selecteListId.value = Id;
}

const closeModal = () => {
  showModal.value = !showModal.value;
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
  z-index: 9999;
}

.modal-list {
  display: flex;
  justify-content: center;
}

.modal {
  background: white;
  width: 300px;
  max-height: 50%;
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  align-items: center;
  overflow: hidden;
}

.modal-menu {
  margin: 0;
  margin-top: 10%;
  padding: 0;
  height: 100%;
}

.modal-menu > ul {
  height: 200px;
  padding: 0;
  display: flex;
  flex-direction: column;
  gap: 15px;
  overflow-y: scroll;
}

.modal-menu li {
  background: white;
  font-size: 15px;
  font-weight: 400;
}

.modal-title {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 8%;
  font-weight: bold;
  font-size: 13px;
  color: rgb(40, 40, 40);
}

.modal-title > p {
  font-size: medium;
  font-weight: 1000;
  margin: 0;
  margin: 0;
}

.modal-title img {
  width: 20px;
  margin: 0 7px 0 0;
}

.router-link-item-container {
  overflow: auto;
  -ms-overflow-style: none; /* IE와 Edge에서 스크롤바 숨기기 */
}

::-webkit-scrollbar {
  display: none; /* Chrome, Safari에서 스크롤바 숨기기 */
}

.router-link-item {
  text-decoration: none;
  color: inherit;
  display: flex;
  margin: 0;
  margin-bottom: 10px;
  /* align-items: center; 제거 */
}
.radio-button {
  border-color: rgb(0, 0, 0);
}

.modal-button {
  border: none; /* border 없음 */
  background-color: navy; /* 남색 배경 */
  border-radius: 5px;
  color: white;
  width: 50%;
  text-align: center;
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 5% 5%;
  padding: 2%;
}
</style>
