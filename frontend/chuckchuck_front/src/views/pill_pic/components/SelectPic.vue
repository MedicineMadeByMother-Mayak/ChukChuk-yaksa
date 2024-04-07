<template>
  <div class="buttons">
    <label for="file-upload" class="custom-button"
      >갤러리에서 알약사진 선택하기</label
    >
    <input
      type="file"
      id="file-upload"
      @change="handleImageUpload"
      style="display: none"
    />
    <label class="custom-button">카메라로 알약사진 촬영하기</label>
  </div>
  <Footer></Footer>
</template>

<script setup>
import Wave from "@/common/Wave.vue";
import Footer from "@/common/FooterColor.vue";
import { ref } from "vue";
import { pillPicStore } from "@/stores/pillPic";
const store = pillPicStore();

const handleImageUpload = (event) => {
  const file = ref(event.target.files[0]);
  store.getPillPic(file.value, 2);
};
</script>

<style scoped>
.buttons {
  width: 100%;
  height: 200px;
  margin-top: 15px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 0px;
  gap: 20px;
  margin-bottom: 90px;
}

.custom-button {
  width: 85%;
  height: 80px;
  display: inline-flex; /* Flexbox 사용 */
  align-items: center; /* 수직 정렬 */
  justify-content: center; /* 수평 정렬 */
  /* padding: 10px 20px; */
  background-color: #ffffff; /* 하얀색 배경 */
  color: #000000; /* 검은색 텍스트 */
  border-radius: 5px;
  cursor: pointer;
  box-shadow: 0px 3px 3px rgba(0, 0, 0, 0.1); /* 그림자 하단 3px */
  font-size: 16px;
}

.custom-button:hover {
  background-color: rgb(241, 241, 241); /* 흰색 배경 */
}

button:hover {
  background-color: rgb(241, 241, 241); /* 흰색 배경 */
}
</style>
