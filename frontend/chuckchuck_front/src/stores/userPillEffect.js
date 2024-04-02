import { ref, computed } from "vue";
import { defineStore } from "pinia";
import { instance } from "@/util/mainAxios";
import { isStaticProperty } from "vue/compiler-sfc";

export const userPillEffectStore = defineStore("userPillEffect", () => {
  const sideEffect = ref();
  const stop = ref([]);
  const effect = ref([]);
  const userPillEffectDtoList = ref("");

  const getUserPillEffectInfo = async (inputPillId) => {
    try {
      const { data } = await instance.get("/effects/pill/" + inputPillId);
      const userPillEffectDtos = data.userPillEffectDtos;

      userPillEffectDtoList.value = userPillEffectDtos;
      sideEffect.value = userPillEffectDtos[0];
      stop.value = userPillEffectDtos[1];
      effect.value = userPillEffectDtos[2];

      console.log(sideEffect.value);
    } catch (error) {
      console.log(error);
    }
  };

  //새로운 태그 생성
  const createTag = async (categoryId, newTagName) => {
    try {
      const { data } = await instance.post("/effects/tag", {
        categoryId: categoryId,
        tagName: newTagName,
      });
      return data;
    } catch (error) {
      console.log("error", error);
    }
  };

  //생성되어있는 태그를 현재 기록에 연결
  const createUnUsedTag = async (tagId, pillId) => {
    try {
      await instance.post("/effects/pill/tag", {
        tagId: tagId,
        pillId: pillId,
      });
    } catch (error) {
      console.log("error", error);
    }
  };

  const updateMemo = async (userPillEffectId, memo) => {
    try {
      const response = await instance.put("/effects/pill/memo", {
        userPillEffectId: userPillEffectId,
        memo: memo,
      });
    } catch (error) {
      console.log("error", error);
    }
  };

  return {
    sideEffect,
    stop,
    effect,
    userPillEffectDtoList,
    getUserPillEffectInfo,
    updateMemo,
    createTag,
    createUnUsedTag,
  };
});
