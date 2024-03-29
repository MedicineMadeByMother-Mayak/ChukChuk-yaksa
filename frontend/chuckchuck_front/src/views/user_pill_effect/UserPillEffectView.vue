<template>
  <HeaderForm title="약효기록" height="120px">
    <div class="header-content">
      <SearchBar
        class="SearchBar"
        height="30px"
        width="300px"
        iconWidth="15px"
        fontSize="12px"
        marginLeft="15%"
      ></SearchBar>
    </div>
    <div class="buttons">
      <button class="tab-button">전체</button
      ><button class="tab-button">효과</button
      ><button class="tab-button">중단</button
      ><button class="tab-button">부작용</button>
    </div>
  </HeaderForm>
  <PillContent></PillContent>
</template>

<script setup>
import HeaderForm from "@/common/Form/HeaderForm.vue";
import PillContent from "./components/Content.vue";
import SearchBar from "@/common/SearchBar.vue";
</script>

<style scoped>
.header-content {
  display: flex;
  justify-content: center;
  align-items: center;
}

.tab-button {
  font-family: Cafe24SsurroundAir;
  border: none;
  background-color: white;
  width: 60px;
  padding: 3px 10px;
  margin: 0px;
  cursor: pointer;
  clip-path: polygon(20% 0, 80% 0, 100% 100%, 0% 100%);
  caret-color: transparent;
}

.buttons {
  display: flex;
  justify-content: space-between;
  margin: 0px 0px;
  position: absolute;
  left: 0;
  bottom: 0;
}
</style>
