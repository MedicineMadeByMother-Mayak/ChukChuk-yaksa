import { ref, computed } from "vue";
import { defineStore } from "pinia";
import { instance } from "@/util/mainAxios";

export const userStore = defineStore("user", () => {
  const userName = ref('');
  const bloodType = ref('');
  const birth = ref('');
  const sex = ref('');
  const height = ref('');
  const weight = ref('');

  const getUserInfo = async () => {
    try {
      const response = await instance.get('/profile');
      const userInfo = response.data;

      userName.value = userInfo.userName;
      bloodType.value = userInfo.bloodType;
      birth.value = userInfo.birth.split('T')[0];
      sex.value = userInfo.sex;
      height.value = userInfo.height;
      weight.value = userInfo.weight;

    } catch (error) {
      console.log('개인 정보를 가져오는데 실패했습니다:', error);
    }
  };

  const updateUserInfo = async (userName, bloodType, birth, sex, height, weight) => {
    try {
      const response = await instance.post('/profile', {
        userName: userName,
        sex: sex,
        birth: `${birth} 00:00:00`,
        bloodType: bloodType,
        height: height,
        weight: weight
      });

      window.location.href = '/';
    } catch (error) {
      console.log('Error:', error);
    }
  };

  return {
    userName,
    bloodType,
    birth,
    sex,
    height,
    weight,
    getUserInfo,
    updateUserInfo
  };
});