<template>
  <div class="container">
    <section><img src="@/assests/img/pillPicResult.png" /></section>
    <section class="guide">
      <p>분석된 약들을 복용목록에 추가할 수 있어요.</p>
      <div class="line"></div>
    </section>
    <section></section>
  </div>
</template>
<script setup></script>
<style scoped>
p {
  color: #535353;
}
.container {
  /* background-color: aqua; */
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 5px;
  width: 100%;
}
img {
  width: 280px;
  height: 200px;
  border-radius: 20px;
  object-fit: cover; /* 이미지가 요소에 맞게 잘립니다. */
}
.guide {
  width: 85%;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.line {
  height: 1.5px;
  border-radius: 10px;
  background-color: #bebebe;
}
</style>
