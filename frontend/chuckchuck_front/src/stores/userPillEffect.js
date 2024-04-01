import { ref, computed } from "vue";
import { defineStore } from "pinia";
import { instance } from "@/util/mainAxios";

export const userPillEffectStore = defineStore("userPillEffect", () => {

    const sideEffect = ref();
    const stop = ref([]);
    const effect = ref([]);
    const userPillEffectDtoList = ref('');

    const getUserPillEffectInfo = async (inputPillId) => {
        try{
            const response = await instance.get('/effects/pill/' + inputPillId);
            const userPillEffectDtos = response.data.userPillEffectDtos;

            userPillEffectDtoList.value = userPillEffectDtos;

            sideEffect.value = userPillEffectDtos[0];
            stop.value = userPillEffectDtos[1];
            effect.value = userPillEffectDtos[2];


            console.log(sideEffect.value);
        } catch(error) {
            console.log(error);
        }
    }

    return {
        sideEffect,
        stop,
        effect,
        userPillEffectDtoList,
        getUserPillEffectInfo
    }
})