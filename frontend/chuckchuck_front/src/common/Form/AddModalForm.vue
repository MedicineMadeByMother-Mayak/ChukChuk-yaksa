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
      <ul class="modal-menu">
        <li v-for="(item, index) in modalData" :key="index">
          <RouterLink
            v-if="item[1]"
            :to="{ name: item[2].Link, params: item[2].params }"
            class="router-link-item"
          >
            <div style="margin: 9px; font-size: 14px">{{ item[0] }}</div>
          </RouterLink>
          <hr style="margin: 0px 10px" v-if="index < modalData.length - 1" />
        </li>
      </ul>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";

const showModal = defineModel();
const props = defineProps({
  modalData: {
    type: Array,
    default: [["원하는 텍스트 넣으세요", true, { params: {}, Link: "home" }]],
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
  left: 0;
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
  max-width: 300px;
  border-radius: 10px;
  overflow: hidden;
}

.modal-menu {
  list-style: none;
  padding: 0;
  margin: 0;
}

.modal-menu li {
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
}

.router-link-item {
  text-decoration: none;
  color: inherit;
}
</style>
