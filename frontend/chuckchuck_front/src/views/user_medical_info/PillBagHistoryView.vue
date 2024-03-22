<template>
  <div>
    <Wave title="문진표" height="100px" />
    <CustomTable
      :tableData="[
        ['John Doe', 'Developer'],
        ['Jane Doe', 'Designer'],
      ]"
    />
  </div>
</template>

<script setup>
import Wave from "@/common/Wave.vue";
import CustomTable from "@/common/Form/TableForm.vue";
</script>
