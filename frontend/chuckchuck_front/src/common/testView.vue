<template>
  <div>
    <Wave title="문진표" height="100px" />
    <CustomTable
      :tableData="[
        ['John Doe', 'Developer'],
        ['Jane Doe', 'Designer'],
      ]"
    />
    <Badge />
    <Accordian> 사랑해요 </Accordian>
    <PillBagContent />
  </div>
</template>

<script setup>
import Wave from "@/common/Wave.vue";
import CustomTable from "@/common/Form/TableForm.vue";
import Badge from "@/common/Badge.vue";
import Accordian from "@/views/user_medical_info/components/Accordian.vue";
import PillBagContent from "@/views/user_medical_info/components/PillBagContent.vue";
</script>
