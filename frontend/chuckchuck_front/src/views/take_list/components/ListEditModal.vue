<template>
    <div class="modal-overlay" @click="$emit('close')">
      <div class="modal">
        <ul class="modal-menu">
          <li @click="editListName">제목 수정하기</li>
          <li @click="deleteList">리스트 삭제하기</li>
          <li @click="finishCurrentList">복용 완료 처리하기</li>
        </ul>
      </div>
    </div>
</template>
  
<script setup>
import "vue3-carousel/dist/carousel.css";
import { takelistStore } from "@/stores/takelist";
import { defineEmits } from 'vue';

const emits = defineEmits(['close']);
const store = takelistStore();

const editListName = () => {
    store.currentTakeList.edit = !store.currentTakeList.editing || false;
}

const deleteList = async () => {
    const result = window.confirm('해당 복용 관리를 삭제하시겠습니까?')

    if(result){
        await store.deleteTakeList(store.currentTakeList.takeListId);
        await store.getTakeListPageDatas();
    }
}

const finishCurrentList = async() => {
    const result = window.confirm('해당 복용 관리를 완료하시겠습니까?')

    if(result){
        await store.finishTakeList(store.currentTakeList.takeListId);
        await store.getTakeListPageDatas();
    }
}

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
    height: 50px;
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
  