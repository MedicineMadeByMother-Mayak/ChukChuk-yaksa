import { ref, computed } from "vue";
import { defineStore } from "pinia";
import { instance } from "@/util/mainAxios";

export const ocrListStore = defineStore("ocrList", () => {
  const pillBagImage = ref();
  const diagnosisImage = ref();
  const pillBagResult = ref();
  const diagnosisResult = ref();
  const pillBagImageSrc = ref();
  const diagnosisImageSrc = ref();

  //약봉투 사진 첨부시 실행
  const setPillBagImage = async (file) => {
    pillBagImage.value = file;
    await getSrc(file, pillBagImageSrc);
  };

  //진단서 사진 첨부시 실행
  const setDianosisImage = async (file) => {
    diagnosisImage.value = file;
    await getSrc(file, diagnosisImageSrc);
  };

  //미리보기 src 생성
  async function getSrc(file, src) {
    const reader = new FileReader();
    reader.onload = (e) => {
      src.value = e.target.result;
    }; // ref previewImage 값 변경
    reader.readAsDataURL(file);
  }

  //약봉투 사진 분석시 실행
  const analysisPillBagImage = async () => {
    try {
      // 분석 axios 실행
      const formData = new FormData();
      formData.append("file", pillBagImage.value);
      const { data } = await instance.post("/record/ocr/pill-bag", formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      });

      await delay(3000);
      pillBagResult.value = data;
    } catch (error) {
      // 에러 처리
      alert("약봉투 이미지 분석 중 오류 발생:", error);
    }
  };

  //진단서 사진 분석시 실행
  const analysisDianosisImage = async () => {
    try {
      // 분석 axios 실행
      const formData = new FormData();
      formData.append("file", diagnosisImage.value);
      const { data } = await instance.post("/record/ocr/diagnosis", formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      });
      await delay(3000);
      diagnosisResult.value = data;
    } catch (error) {
      // 에러 처리
      alert("진단서 이미지 분석 중 오류 발생:", error);
    }
  };

  function delay(ms) {
    return new Promise((resolve) => setTimeout(resolve, ms));
  }

  //약봉투 약 저장
  const savePillBag = async () => {
    await instance.post("/record/pill-bag", pillBagResult.value);
  };

  //진단서 저장
  const saveDiagnosis = async () => {
    await instance.post("/record/diagnosis", diagnosisResult.value);
  };

  return {
    setPillBagImage,
    setDianosisImage,
    pillBagImage,
    diagnosisImage,
    analysisPillBagImage,
    analysisDianosisImage,
    pillBagResult,
    diagnosisResult,
    pillBagImageSrc,
    diagnosisImageSrc,
    saveDiagnosis,
    savePillBag,
  };
});
