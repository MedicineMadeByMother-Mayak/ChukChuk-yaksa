<!-- 사용법     
  <HeaderForm :title="'사랑해요'" :height="'100px'">
    여기에 내용을 넣으세요
  </HeaderForm>  
-->

<template>
  <div class="header">
    <div class="inner-header flex" :style="{ height }">
      <i class="arrow left"></i>
      <h1>{{ title }}</h1>
      <p></p>
    </div>
    <slot></slot>
  </div>
</template>

<script setup>
const props = defineProps({
  title: {
    type: String,
    default: "title : 제목, height 높이",
  },
  height: {
    type: String,
    default: "148px",
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
  margin-top: 5px;
}

.inner-header h1 {
  flex-grow: 1; /* h1 태그가 가능한 모든 공간을 차지하도록 함 */
  text-align: center; /* h1 내의 텍스트 중앙 정렬 */
}

.arrow.left {
  flex-grow: 0; /* 아이콘이 추가 공간을 차지하지 않도록 함 */
  margin-right: auto; /* 오른쪽에 자동 마진을 줘서 왼쪽으로 밀어냄 */
}

.header {
  position: relative;
  text-align: center;
  background: linear-gradient(60deg, #3183ff 0%, #86e7ee 100%);
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

.inner-header > i {
  margin: 10px;
}

.arrow {
  border: solid black;
  border-width: 0 5px 5px 0;
  display: inline-block;
  padding: 6px;
  caret-color: transparent;
}

.left {
  transform: rotate(135deg);
  -webkit-transform: rotate(135deg);
}
</style>
