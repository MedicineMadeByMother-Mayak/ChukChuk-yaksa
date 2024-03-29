<template>
  <div class="settings-container" style="height: 100%">
    <div class="header">
      <h3>설정</h3>
    </div>
    <div class="settings-option">
      <span>개인정보 수정</span>
    </div>
    <div class="settings-option">
      <span>업데이트 정보</span>
      <span>현재 5.8.1</span>
    </div>
    <div class="settings-toggle">
      <span>푸시알림 설정</span>
      <input type="checkbox" id="push-notification-toggle" />
      <label for="push-notification-toggle"></label>
    </div>
    <div class="settings-option">로그아웃</div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();
const pushNotificationsEnabled = ref(false);

const navigateTo = (path) => {
  router.push({ name: path });
};

const logout = () => {};
</script>

<style scoped>
.settings-container {
  font-family: Arial, sans-serif;
  background-color: #f9f9f9;
}

.header {
  color: black;
  text-align: center;
  background: linear-gradient(to left, #86e7ee, #3183ff);
  height: 60px;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.settings-option,
.settings-toggle {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 15px;
  margin: 20px 0px;
  background-color: white;
}

/* 토글 스위치를 위한 스타일링 */
input[type="checkbox"] {
  /* 숨김 처리 */
  opacity: 0;
  width: 0;
  height: 0;
  caret-color: transparent;
}

input[type="checkbox"] + label {
  /* 커스텀 토글 스위치 스타일링 */
  position: relative;
  display: inline-block;
  width: 55px;
  height: 25px;
  background-color: #ccc;
  border-radius: 25px;
  caret-color: transparent;
}

input[type="checkbox"]:checked + label {
  background-color: #3183ff;
}

input[type="checkbox"] + label:before {
  /* 토글 핸들 */
  content: "";
  position: absolute;
  left: 4px;
  top: 2px;
  width: 21px;
  height: 21px;
  border-radius: 50%;
  background-color: white;
  transition: 0.4s;
}

input[type="checkbox"]:checked + label:before {
  transform: translateX(26px);
}
</style>
