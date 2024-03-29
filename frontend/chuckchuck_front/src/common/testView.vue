<template>
  <div>
    <HeaderForm :title="'사랑해요'" :height="'100px'" :Link="'home'">
      <div>사랑해요</div>
    </HeaderForm>
    <!-- <Wave title="문진표" height="100px" /> -->
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
import PillBagContent from "@/common/PillInfo.vue";
import HeaderForm from "@/common/Form/HeaderForm.vue";
</script>

<style scoped></style>
