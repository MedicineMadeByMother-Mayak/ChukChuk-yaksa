<template></template>
<script setup>
import { ref, onMounted } from "vue";
import { useRouter } from "vue-router";
import { useAuthStore } from "@/stores/auth";
import Cookies from "js-cookie";
const { VITE_REDIRECT_URI_PARAM_COOKIE_NAME, VITE_VUE_URI } = import.meta.env;

const authStore = useAuthStore();
const router = useRouter();
const accessToken = ref("");

onMounted(() => {
  // URL 파라미터 파싱
  const url = new URL(window.location.href);
  accessToken.value = url.searchParams.get("token");
  // 액세스 토큰 설정 및 localStorage에도 저장
  authStore.setAccessToken(accessToken.value);
  localStorage.setItem("token", JSON.stringify(accessToken.value));
  // 이후 인증 실패시 리다이렉트를 위해 쿠키 생성
  Cookies.set(VITE_REDIRECT_URI_PARAM_COOKIE_NAME, VITE_VUE_URI, {
    expires: 30,
  });
  // 메인페이지로 이동
  router.push({ name: "home" });
});
</script>
<style scoped></style>
