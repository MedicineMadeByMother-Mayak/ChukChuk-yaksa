import { ref, computed } from "vue";
import { defineStore } from "pinia";
import { instance } from "@/util/mainAxios";

export const takelistStore = defineStore("takelist", () => {
  const takelistdatas = ref([]);
  const takelistpagedatas = ref([]);

  // 문진표 조회
  const getUserMedicalInfoTakelist = async () => {
    try {
      takelistdatas.value = (
        await instance.get("/take-list", {
          params: { period: true },
        })
      ).data.results;
    } catch (error) {
      console.log("개인 정보를 가져오는데 실패했습니다:", error);
    }
  };

  // 복용리스트 조회
  const getTakeListPageDatas = async () => {
    try {
      const { data } = await instance.get("/take-list", {
        params: { period: false },
      });

      takelistpagedatas.value = data.results;
    } catch (error) {
      console.log("복용리스트 GET 가져오지 못했습니다", error);
    }
  };

  return {
    takelistdatas,
    getUserMedicalInfoTakelist,
    takelistpagedatas,
    getTakeListPageDatas,
  };
});
