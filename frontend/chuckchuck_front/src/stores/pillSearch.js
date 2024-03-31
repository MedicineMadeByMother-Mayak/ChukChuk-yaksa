import { ref, computed } from "vue";
import { defineStore } from "pinia";
import { instance } from "@/util/mainAxios";

export const pillSearchStore = defineStore("pillSearch", () => {
    const pillId = ref('');
    const name = ref('');
    const company = ref('');
    const effect = ref('');
    const basis = ref('');
    const caution = ref('');
    const capacity = ref('');
    const imageUrl = ref('');
    const type = ref('');
    const warningPregnant = ref('');
    const warningUseDate = ref('');
    const warningElders = ref('');
    const warningTogether = ref('');

    const getPillInfo = async (input_pillId) => {
        try {
            const response = await instance.get('pill/detail/' + input_pillId);
            const pillInfo = response.data;
    
            pillId.value = pillInfo.pillDetailDto.pill_id;
            name.value = pillInfo.pillDetailDto.name;
            company.value = pillInfo.pillDetailDto.company;
            effect.value = pillInfo.pillDetailDto.effect;
            basis.value = pillInfo.pillDetailDto.basis;
            caution.value = pillInfo.pillDetailDto.caution;
            capacity.value = pillInfo.pillDetailDto.capacity;
            imageUrl.value = pillInfo.pillDetailDto.image_url || "/src/assests/img/tempPill.png";
            type.value = pillInfo.pillDetailDto.type;
            warningPregnant.value = pillInfo.pillDetailDto.warning_pregnant;
            warningUseDate.value = pillInfo.pillDetailDto.warning_use_date;
            warningElders.value = pillInfo.pillDetailDto.warning_elders;
            warningTogether.value = pillInfo.pillDetailDto.warning_together;

            console.log(pillInfo);
        } catch (error) {
            console.log(error);
        }

    }

    return {
        pillId,
        name,
        company,
        effect,
        basis,
        caution,
        capacity,
        imageUrl,
        type,
        warningPregnant,
        warningUseDate,
        warningElders,
        warningTogether,
        getPillInfo
    };
})

export const pillSearchStore = defineStore("pillSearch", () => {
  const keyword = ref("");
  const page = ref(1);

  async function input(newKeyword, newPage) {
    keyword.value = newKeyword;
    page.value = newPage;
    const { data } = await instance.get("/pill/search", {
      params: {
        keyword: keyword.value,
        page: page.value,
      },
    });

    return data;
  }

  return { keyword, page, input };
});
