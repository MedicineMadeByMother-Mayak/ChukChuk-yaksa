<template>
  <div class="survey-container">
    <div class="survey-header">
      <div :class="{ step: true, active: currentStep === 1 }">1</div>
      <div class="line"></div>
      <div :class="{ step: true, active: currentStep === 2 }">2</div>
      <div class="line"></div>
      <div :class="{ step: true, active: currentStep === 3 }">3</div>
    </div>
    <div class="survey-steps">
      <div :class="{ 'step-description': true, active: currentStep === 1 }">
        알약 촬영
      </div>
      <div :class="{ 'step-description': true, active: currentStep === 2 }">
        AI 분석
      </div>
      <div :class="{ 'step-description': true, active: currentStep === 3 }">
        결과
      </div>
    </div>
  </div>
</template>

<script setup>
const props = defineProps({
  currentStep: {
    type: Number,
    default: 1, // 기본값은 1로 설정
  },
});
</script>

<style scoped>
.line {
  height: 1px;
  width: 50px;
  border-radius: 10px;
  border: 2px solid #eff0f6;
  background-color: #eff0f6;
}
.survey-container {
  padding: 1rem;
  /* background: #fff; */
  border-radius: 8px;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 1px;
}

.survey-header {
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 0.3rem;
  gap: 11px;
}

.step {
  background: #eff0f6;
  border-radius: 50%;
  width: 2rem;
  height: 2rem;
  line-height: 2rem;
  text-align: center;
  color: #797979; /* 원하는 색상값을 넣어주세요 */
  font-weight: normal; /* 텍스트를 굵게 처리 */
  font-size: medium;
  transition: background-color 0.3s ease, color 0.3s ease; /* 애니메이션 효과 설정 */
}
.step.active {
  background: #739aff;
  color: #000000;
  transition: background-color 0.3s ease, color 0.3s ease; /* 애니메이션 효과 설정 */
}

.survey-steps {
  width: 100%;
  display: flex;
  /* justify-content: space-between; */
  gap: 35px;
  margin-bottom: 1rem;
}

.step-description {
  flex: 1;
  text-align: center;
  color: rgb(88, 88, 88);
  font-size: 8px;
  /* font-weight: bold; */
}

.step-description.active {
  color: rgb(88, 88, 88);
}
</style>
