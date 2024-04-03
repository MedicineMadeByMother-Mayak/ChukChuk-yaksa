<!-- 사용법     
  <HeaderForm :title="'사랑해요'" :height="'100px'" :Link="''" :fontsize="10px">
    여기에 내용을 넣으세요
  </HeaderForm>  
-->

<template>
  <div>
    <HeaderFormOnlyString :title="title" :Link="Link" />
    <div class="header" :style="{ height }">
      <div class="inner-header flex">
        <p></p>
      </div>
      <slot></slot>
    </div>
  </div>
</template>

<script setup>
import HeaderFormOnlyString from "@/common/Form/HeaderFormOnlyString.vue";
const props = defineProps({
  title: {
    type: String,
    default: "title : 제목, height 높이",
  },
  height: {
    type: String,
    default: "100px",
  },
  Link: {
    type: String,
    default: "home",
  },
  fontSize: {
    type: String,
    default: "20px",
  },
});
</script>

<style scoped>
body {
  margin: 0;
}

h1 {
  position: relative;
  right: 11.8px;
  font-weight: bold;
  letter-spacing: 2px;
  font-size: 20px;
  margin: 10px;
}

.inner-header h1 {
  content: "";
}

.arrow.left {
  flex-grow: 0; /* 아이콘이 추가 공간을 차지하지 않도록 함 */
  margin-right: auto; /* 오른쪽에 자동 마진을 줘서 왼쪽으로 밀어냄 */
}

.header {
  position: relative;
  text-align: center;
  background: linear-gradient(to right, #3183ff 0%, #86e7ee 100%);
  color: black;
  margin-bottom: 10px;
}

.inner-header {
  width: 100%;
  margin: 0;
  padding: 0;
}

.flex {
  /*Flexbox for containers*/
  display: flex;
  justify-content: space-between;
  align-items: center;
  text-align: center;
  padding: auto;
}

.arrow {
  border: solid black;
  border-width: 0 5px 5px 0;
  display: inline-block;
  padding: 6px;
  caret-color: transparent;
  margin-left: 10px;
}

.left {
  transform: rotate(135deg);
  -webkit-transform: rotate(135deg);
}
</style>
