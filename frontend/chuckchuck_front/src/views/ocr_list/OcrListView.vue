<template>
  <div class="base-backgound-color">
    <Wave title="약봉투/진단서 촬영" height="0px" Link="usermedicalinfo" />
    <!-- OcrListView -->
    <div class="text">
      <div>약봉투나 진단서를 촬영하여</div>
      <div>약력과 병력을 체계적으로 관리해보세요</div>
    </div>
    <div class="buttons">
      <div>
        <button><strong>약봉투</strong> 촬영하기</button>
      </div>
      <div>
        <button><strong>진단서</strong> 촬영하기</button>
      </div>
    </div>
  </div>
  <div
    style="
      position: relative;
      text-align: center;
      background: linear-gradient(60deg, #3183ff 0%, #86e7ee 100%);
      color: black;
      margin-bottom: 10px;
      height: 117px;
    "
  >
    
  </div>
</template>

<script setup>
import Wave from "@/common/Wave.vue";

import { instance } from "@/util/mainAxios";
//요청 test
const server = async () => {
  const { data } = await instance.get("/pill/search?keyword=활&page=1");
  console.log(data);
};

server();
</script>

<style scoped>
.base-backgound-color {
  background-color: #f9f9f9;
  height: 82vh;
}

.text {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 50px 0;
  font-size: 14px;
}

.buttons {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 0px;
}

button {
  background-color: white; /* 흰색 배경 */
  border: none; /* outline 없음 */
  box-shadow: 0px 3px 3px rgba(0, 0, 0, 0.1); /* 그림자 하단 3px */
  padding: 27px 70px;
  margin: 15px;
  border-radius: 5px;
  font-size: 18px;
}
</style>
