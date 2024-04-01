import { ref, computed } from "vue";
import { defineStore } from "pinia";
import { instance } from "@/util/mainAxios";

export const takelistStore = defineStore("takelist", () => {
  const takelistdatas = ref([]);

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

  return {
    takelistdatas,
    getUserMedicalInfoTakelist,
  };
});
