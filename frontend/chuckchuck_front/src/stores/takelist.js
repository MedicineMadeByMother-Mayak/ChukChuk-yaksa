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

  // 복용리스트 약 추가
  async function addPill(takeListId, pills) {
    try {
      const { data } = await instance.post(`/take-list/${takeListId}`, {
        pills,
      });
    } catch (error) {
      console.log(error);
    }
  }

  // 복용리스트 이름 수정
  async function rename(takeListId, newTitle) {
    try {
      const { data } = await instance.put(`/take-list/${takeListId}`, {
        pilupdateTakeName: newTitle,
      });
    } catch (error) {
      console.log(error);
    }
  }

  // 복용리스트 약 삭제
  async function deletePill(takeListId, pillId) {
    try {
      const { data } = await instance.delete(
        `/take-list/${takeListId}/${pillId}`
      );
    } catch (error) {
      console.log(error);
    }
  }

  // 복용리스트 완료
  async function finishTakeList(takeListId) {
    try {
      const { data } = await instance.put(`/take-list/${takeListId}/finish`);
    } catch (error) {
      console.log(error);
    }
  }

  // 복용리스트 삭제
  async function deleteTakeList(takeListId) {
    try {
      const { data } = await instance.delete(`/take-list/${takeListId}`);
    } catch (error) {
      console.log(error);
    }
  }

  return {
    takelistdatas,
    getUserMedicalInfoTakelist,
    takelistpagedatas,
    getTakeListPageDatas,
    addPill,
    rename,
    deletePill,
    finishTakeList,
    deleteTakeList,
  };
});
