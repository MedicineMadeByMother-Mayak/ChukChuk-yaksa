<template>로그인 처리 페이지</template>
<script setup>
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import { useAuthStore } from "@/stores/auth";
import { instance } from "@/util/mainAxios";

const authStore = useAuthStore();
const router = useRouter();
const accessToken = ref("");

//요청 test
const server = async () => {
  const { data } = await instance.get(
    "http://localhost:8080/api/v1/pill/search?keyword=활&page=1"
  );
  console.log("응답은 ", data);
};

onMounted(() => {
  // URL 파라미터 파싱
  const url = new URL(window.location.href);
  accessToken.value = url.searchParams.get("token");
  // 액세스 토큰 설정
  authStore.setAccessToken(accessToken.value);
  // 메인페이지로 이동
  router.push({ name: "login" });
});
</script>
<style scoped></style>
