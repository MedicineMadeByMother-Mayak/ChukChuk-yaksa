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

  const logout = () => {
    localStorage.removeItem("token");
  };

  return { accessToken, setAccessToken, clearAccessToken, logout };
});
