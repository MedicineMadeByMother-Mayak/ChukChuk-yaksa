import { ref, computed } from "vue";
import { defineStore } from "pinia";
import { instance } from "@/util/mainAxios";

export const alarmStore = defineStore("alarm", () => {
  const alarmList = ref([]);
  const offAlarmList = ref([]);
  // 활성화된 알람 조회
  const getAlarmList = async () => {
    try {
      const { data } = await instance.get("/take-list/alarms", {
        params: { isAlarm: true },
      });
      alarmList.value = data.alarmDtos;
    } catch (error) {
      console.log("활성화된 알람리스트를 가져오지 못했습니다", error);
    }
  };

  // 비활성화된 알람 조회
  const getOffAlarmList = async () => {
    try {
      const { data } = await instance.get("/take-list/alarms", {
        params: { isAlarm: false },
      });
      offAlarmList.value = data.alarmDtos;
      return data;
    } catch (error) {
      console.log("비활성화된 알람리스트를 가져오지 못했습니다", error);
    }
  };

  // 알람 등록
  async function turnOn(takeListId, alarmTime, cycle) {
    try {
      const { data } = await instance.post(`/take-list/alarms/${takeListId}`, {
        alarmTime,
        cycle,
      });
    } catch (error) {
      console.log(error);
    }
  }

  // 알람 수정
  async function modify(takeListId, alarmTime, cycle) {
    try {
      const { data } = await instance.put(`/take-list/alarms/${takeListId}`, {
        alarmTime: alarmTime,
        cycle: cycle,
      });
    } catch (error) {
      console.log(error);
    }
  }

  // 알람 비활성화
  async function turnOff(takeListId) {
    try {
      const { data } = await instance.delete(`/take-list/alarms/${takeListId}`);
    } catch (error) {
      console.log(error);
    }
  }

  return {
    alarmList,
    getAlarmList,
    offAlarmList,
    getOffAlarmList,
    turnOn,
    turnOff,
    modify,
  };
});
