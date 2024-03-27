// stores/auth.js
import { ref, computed } from "vue";
import { defineStore } from "pinia";

export const useAuthStore = defineStore("authStore", () => {
  const accessToken = ref("");

  const setAccessToken = (token) => {
    accessToken.value = token;
  };

  const clearAccessToken = () => {
    accessToken.value = "";
  };

  return { accessToken, setAccessToken, clearAccessToken };
});
