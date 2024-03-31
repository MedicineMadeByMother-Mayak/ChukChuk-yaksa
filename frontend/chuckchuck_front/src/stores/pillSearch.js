import { ref } from "vue";
import { instance } from "@/util/mainAxios";
import { defineStore } from "pinia";

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
