import { ref, computed } from "vue";
import { defineStore } from "pinia";

export const pillPicStore = defineStore("pillPic", () => {
  const currentStep = ref(1);
  const pillImage = ref();

  //사진 첨부시 실행
  const getPillPic = (file, nextStep) => {
    setCurrentStep(nextStep);
    pillImage.value = file;
    console.log(currentStep.value);
  };

  //로딩 끝나면 실행
  const endLoading = (nextStep) => {
    setCurrentStep(nextStep);
    console.log(currentStep.value);
  };

  function setCurrentStep(val) {
    currentStep.value = val;
  }

  return { currentStep, getPillPic, endLoading };
});
