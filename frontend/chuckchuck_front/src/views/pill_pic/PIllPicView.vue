<template>
  <div class="base-backgound-color">
    <Wave title="사진으로 약찾기" height="10px" Link="usermedicalinfo" />
    <!-- OcrListView -->
    <div class="text">
      <p>무슨약인지 잊어버리셨나요?</p>
      <p>알약사진을 찍으면 AI가 해당 약을 분석해드려요.</p>
    </div>

    <div class="survey-container">
      <div class="survey-header">
        <div class="step active">1</div>
        <div class="step">2</div>
        <div class="step">3</div>
      </div>
      <div class="survey-steps">
        <div class="step-description active">알약 촬영</div>
        <div class="step-description">AI 분석</div>
        <div class="step-description">결과</div>
      </div>
    </div>

    <div class="buttons">
      <button>갤러리에서 알약사진 선택하기</button>
      <button>카메라로 알약사진 촬영하기</button>
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
  ></div>
</template>

<script setup>
import Wave from "@/common/Wave.vue";
</script>

<style scoped>
p {
  margin: 0px;
  padding: 0px;
}
.base-backgound-color {
  display: flex;
  flex-direction: column;
  background-color: #f9f9f9;
  height: 83vh;
}

.text {
  display: flex;
  flex-direction: column;
  align-items: center;
  font-size: 14px;
  margin: 15px 0px;
}

.buttons {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 0px;
  gap: 20px;
}

button {
  background-color: white; /* 흰색 배경 */
  border: none; /* outline 없음 */
  box-shadow: 0px 3px 3px rgba(0, 0, 0, 0.1); /* 그림자 하단 3px */
  /* padding: 27px 40px; */
  /* margin: 15px; */
  border-radius: 5px;
  font-size: 15px;
  width: 80vw;
  height: 80px;
}

.survey-container {
  padding: 1rem;
  /* background: #fff; */
  border-radius: 8px;
}

.survey-header {
  display: flex;
  justify-content: space-around;
  margin-bottom: 1rem;
}

.step {
  background: #eff0f6;
  border-radius: 50%;
  width: 2rem;
  height: 2rem;
  line-height: 2rem;
  text-align: center;
  color: #797979; /* 원하는 색상값을 넣어주세요 */
  font-weight: bold; /* 텍스트를 굵게 처리 */
  font-size: medium;
}
.step.active {
  background: #739aff;
  color: #000000;
}

.survey-steps {
  display: flex;
  justify-content: space-between;
  margin-bottom: 1rem;
}

.step-description {
  flex: 1;
  text-align: center;
  color: rgb(75, 75, 75);
}

.step-description.active {
  color: rgb(75, 75, 75);
}

.survey-button {
  display: block;
  width: 100%;
  padding: 1rem;
  margin-bottom: 0.5rem;
  background: #f7f7f7;
  border: none;
  border-radius: 5px;
  box-shadow: inset 0 0 0 1px #ddd;
  cursor: pointer;
  transition: all 0.3s;
}

.survey-button:hover {
  background: #e0e0e0;
}
</style>
