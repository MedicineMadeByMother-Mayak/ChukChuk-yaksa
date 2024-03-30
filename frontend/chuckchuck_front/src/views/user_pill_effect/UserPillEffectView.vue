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
      <div>
        <button class="tab-button">전체</button
      ><button class="tab-button">효과</button
      ><button class="tab-button">중단</button
      ><button class="tab-button">부작용</button>
      </div>
      <div>
        <div>
        <button class="navy-button"><font-awesome-icon icon="fa-duotone fa-circle-plus" style="--fa-primary-color: #242291; --fa-secondary-color: #ffffff;" />기록추가</button>
      </div>
      </div>
    </div>
  </HeaderForm>
  <PillContent></PillContent>
  <font-awesome-icon icon="fa-duotone fa-circle-plus" style="--fa-primary-color: #242291; --fa-secondary-color: #ffffff;" />
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
  font-size: 11px;
  border: none;
  background-color: white;
  width: 55px;
  padding: 3px 10px;
  margin: 0px;
  cursor: pointer;
  clip-path: polygon(10% 0, 90% 0, 100% 100%, 0% 100%);
  caret-color: transparent;
  border-top-left-radius: 5%;
  position: relative;
  overflow: hidden;
}

.buttons {
  width: 320px;
  display: flex;
  justify-content: space-between;
  margin: 0px 0px;
  position: absolute;
  left: 0;
  bottom: 0;
}

.navy-button {
  background-color: #242291;
  color: #ffffff;
  font-weight: bold;
  width: 65px;
  height: 21px;
  border-radius: 5px;
  padding: 3px px;
  font-size: 11px;
  border: none;
  cursor: pointer;
}
</style>
