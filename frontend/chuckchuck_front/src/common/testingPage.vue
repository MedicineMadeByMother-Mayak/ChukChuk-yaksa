<template>
    <!-- 1. AddModalForm 사용 예시 -->
    <!-- <AddModalForm v-model="msg" :modalData="[
    ['a트', true, { params: {}, Link:'home'}],
    ['n트', true, { params: {}, Link:'Setting'}],
    ]" />  -->

    <!-- 2. SelectListModalForm 사용 예시 -->
    <!-- <SelectListModalForm
      v-model="msg"
      :modalData="[
        [1, '달디달디달디달'],
        [2, '단 밤양갱'],
        [3, '단 밤양갱'],
        [4, '밤양갱밤양갱밤갱'],
        [5, '밤양갱이얌'],
      ]"
    /> -->

    <!-- 3. AlarmModal 사용 예시 -->
    <!-- <AlarmModal v-model="msg" :modalData="[['어떤 약에 대한 알람을 등록하시겠어요?', true, {}, {}]]"/> -->

    <!-- 4. AddListAlertModalForm 사용 예시 -->
    <!-- <AddListAlertModalForm v-model="msg" :modalData="[
    ['원하는 텍스트', true, {params:{}, Link:'home'}],
    ]" />  -->
<!-- 
    <AlarmModalTimeForm
      v-model="msg"
      :modalData="[['어떤 약에 대한 알람을 등록하시겠어요?', true, {}, {}]]"
    />   -->
    <!-- <AlarmModalTime
      v-model="msg"
      :modalData="[
        [1, '매일 17:35에 알람을 울립니다.'],
        [2, '17:35 기준, 12시간마다 알람을 울립니다.'],
      ]"
    />  -->

    <SelectModal v-model="msg" :modalData="[
    ['제목 수정하기', true, { params: {}, Link:'TakeList'}],
    ['리스트 삭제하기', true, { params: {}, Link:'Setting'}],
    ['복용 완료 처리하기', true, { params: {}, Link:'Setting'}],
    ]" /> 


</template>

<script setup>
// AddModalForm 사용 예시 
import AddModalForm from "@/common/Form/AddModalForm.vue";
//SelectListModalForm 사용 예시
import SelectListModalForm from "@/common/Form/SelectListModalForm.vue";
//AlarmModal 사용 예시
import AlarmModal from '@/views/take_list/components/AlarmModal.vue';
// AddListAlertModalForm 사용 예시  => SelectListModalForm 하고 같은 듯
import AddListAlertModalForm from '@/common/Form/AddListAlertModalForm.vue'

// AlarmModalTime.vue
import AlarmModalTime from '@/views/take_list/components/AlarmModalTime.vue';


// SelectModal.vue
import SelectModal from '@/views/take_list/components/SelectModal.vue';
import { ref } from "vue";
const msg = ref(true);
</script>

<style scoped>
.basic-background {
  background-color: blueviolet;
  height: 100vh;
}
</style>
