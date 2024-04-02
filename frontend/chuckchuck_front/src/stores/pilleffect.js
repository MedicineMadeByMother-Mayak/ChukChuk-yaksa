// getpillEffectDatas(text: {type : String })

import { ref, computed } from "vue";
import { defineStore } from "pinia";
import { instance } from "@/util/mainAxios";

export const pillEffectStore = defineStore("pilleffect", () => {
  const pillEffectDatas = ref([]);

  const getpillEffectDatas = async (text) => {
    try {
      pillEffectDatas.value = (
        await instance.get("/effects", {
          params: {
            categoryid: 0,
            keyword: text,
            page: 1,
          },
        })
      ).data;
    } catch (error) {
      console.log("개인 정보를 가져오는데 실패했습니다:", error);
    }
  };

  return {
    pillEffectDatas,
    getpillEffectDatas,
  };
});
