import { ref, computed } from "vue";
import { defineStore } from "pinia";
import { instance } from "@/util/mainAxios";

export const diseaseStore = defineStore("disease", () => {
  const diseaseInfos = ref();

  const getDiseaseInfo = async () => {
    try {
      const response = await instance.get("/record/disease");
      diseaseInfos.value = response.data.results;
    } catch (error) {
      console.log("개인 정보를 가져오는데 실패했습니다:", error);
    }
  };

  return {
    diseaseInfos,
    getDiseaseInfo,
  };
});
