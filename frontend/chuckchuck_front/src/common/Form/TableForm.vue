<!-- 사용법 -->
<!-- <template>
  <div>
    <CustomTable
      :tableData="[
        ['John Doe', 'Developer'],
        ['Jane Doe', 'Designer'],
      ]"
    />
  </div>
</template>

<script setup>
import CustomTable from "@/common/Form/TableForm.vue";
</script> 
=======================
John Doe   Developer
=======================
Jane Doe   Designer
=======================
이런 형태로 나옵니다.
-->

<script setup>
const props = defineProps({
  tableData: {
    type: Array,
    required: true,
  },
});
</script>

<template>
  <table style="border-collapse: collapse; border-radius: 8px">
    <tbody>
      <tr v-for="(item, index) in tableData" :key="index">
        <td class="center">{{ item[0] }}</td>
        <td class="center">{{ item[1] }}</td>
      </tr>
    </tbody>
  </table>
</template>

<style scoped>
table {
  width: 100%;
  border-collapse: collapse;
  border-radius: 10px;
  border-style: hidden;
  box-shadow: 0 0 0 1px #ddd;
}

td {
  border: 1px solid #ddd;
  padding: 8px;
}

.center {
  text-align: center;
}
</style>
