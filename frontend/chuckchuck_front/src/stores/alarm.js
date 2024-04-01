import { ref, computed } from "vue";
import { defineStore } from "pinia";
import { instance } from "@/util/mainAxios";

export const alarmStore = defineStore("alarm", () => {
  const alarmList = ref([]);

  // 알람 조회
  const getAlarmList = async () => {
    try {
      const { data } = await instance.get("/take-list/alarms");
      alarmList.value = data.alarmDtos;
    } catch (error) {
      console.log("알람리스트를 가져오지 못했습니다", error);
    }
  };

  return {
    alarmList,
    getAlarmList,
  }
});
