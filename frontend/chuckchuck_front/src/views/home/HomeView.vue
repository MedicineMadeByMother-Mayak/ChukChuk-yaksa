<template>
  <div>home (로그인 완료!)</div>
  <button @onclick="server">+</button>
</template>

<script setup>
//요청 test
const server = async () => {
  console.log("응답아 와라");
  const { data } = await instance.get(
    "http://localhost:8080/api/v1/pill/search?keyword=활&page=1"
  );
  console.log("응답은 ", data);
};
</script>

<style scoped></style>
