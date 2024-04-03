<template>
  <div class="footer"></div>
</template>

<script setup></script>

<style scoped>
.footer {
  display: none; /* 기본적으로는 표시하지 않음 */
  position: absolute;
  left: 0;
  bottom: 0;
  max-width: 320px;
  width: 100%;
  background: linear-gradient(60deg, #3183ff 0%, #86e7ee 100%);
  height: 100px;
}

@media (min-height: 658px) {
  .footer {
    display: block; /* 화면의 높이가 658픽셀 이상일 때만 표시 */
  }
}
</style>
