// getpillEffectDatas(text: {type : String })

import { ref, computed } from "vue";
import { defineStore } from "pinia";
import { instance } from "@/util/mainAxios";

export const pillEffectStore = defineStore("pilleffect", () => {
  const pillEffectDatas = ref([]);

  const getpillEffectDatas = async (text, page) => {
    try {
      pillEffectDatas.value = (
        await instance.get("/effects", {
          params: {
            categoryid: 0,
            keyword: text,
            page: page,
          },
        })
      ).data;
    } catch (error) {
      console.log("약효 기록을 불러오는데 실패했습니다.", error);
    }
  };

  const deletFillEffect = async (effectId) => {
    try {
      const response = await instance.delete(`/effects/pill/${effectId}`);
    } catch (error) {
      console.log("약효 기록을 불러오는데 실패했습니다.", error);
    }
  };

  return {
    pillEffectDatas,
    getpillEffectDatas,
    deletFillEffect,
  };
});
