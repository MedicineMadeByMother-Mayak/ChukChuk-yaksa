import { defineStore } from "pinia";
import { instance } from "@/util/mainAxios";
import {ref} from "vue";

export const useTakeListStore = defineStore("takeList", () => {
  const takeListInfos = ref();

 
  const getTakeListInfo = async () => {
    try {
      const response = await instance.get('/take-list?period=true');
      takeListInfos.value = response.data;
      console.log(response)
    } catch (error) {
      console.log("GET 복용리스트 ERROR 발생:", error);
    }
  };

  return {
    takeListInfos,
    getTakeListInfo,
  };
});