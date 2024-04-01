import { ref, computed } from "vue";
import { instance } from "@/util/mainAxios";
import { defineStore } from "pinia";

export const pillPicStore = defineStore("pillPic", () => {
  const currentStep = ref(1);
  const pillImage = ref();
  const results = ref();

  //사진 첨부시 실행
  const getPillPic = (file, nextStep) => {
    setCurrentStep(nextStep);
    pillImage.value = file;
  };

  //로딩 끝나면 실행
  const endLoading = (nextStep) => {
    setCurrentStep(nextStep);
  };

  function setCurrentStep(val) {
    currentStep.value = val;
  }

  const photoSearch = async () => {
    try {
      const formData = new FormData();
      formData.append('image', pillImage.value); // 'image' 키를 가진 이미지 데이터 추가
      const response = await instance.post(`/pill/photo-search`, formData, {
        headers: {
          'Content-Type' : 'multipart/form-data'
        }
      });
      results.value = response.data
      console.log(results.value);
    } catch (error) {
      console.error('Error posting data:', error);
      throw error;
    }
  };

  return { currentStep, getPillPic, endLoading, photoSearch, results };
});
