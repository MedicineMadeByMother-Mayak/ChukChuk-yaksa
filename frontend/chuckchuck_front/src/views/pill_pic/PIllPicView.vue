<template>
  <div class="survey-container">
    <div class="survey-header">
      <div class="step active">1</div>
      <div class="step">2</div>
      <div class="step">3</div>
    </div>
    <div class="survey-steps">
      <div class="step-description active">알약 확인</div>
      <div class="step-description">AI 분석</div>
      <div class="step-description">결과</div>
    </div>
    <div class="survey-actions">
      <button class="survey-button">갤러리에서 알약사진 선택하기</button>
      <button class="survey-button">카메라로 알약사진 찍어하기</button>
    </div>
  </div>
</template>

<script setup>
// 여기에 Vue 3 Composition API 로직을 추가합니다.
</script>

<style scoped>
.survey-container {
  padding: 1rem;
  background: #fff;
  border-radius: 8px;
}

.survey-header {
  display: flex;
  justify-content: space-around;
  margin-bottom: 1rem;
}

.step {
  background: #e0e0e0;
  border-radius: 50%;
  width: 2rem;
  height: 2rem;
  line-height: 2rem;
  text-align: center;
  color: #555;
}

.step.active {
  background: #3183ff;
  color: white;
}

.survey-steps {
  display: flex;
  justify-content: space-between;
  margin-bottom: 1rem;
}

.step-description {
  flex: 1;
  text-align: center;
  color: #aaa;
}

.step-description.active {
  color: #3183ff;
  font-weight: bold;
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
