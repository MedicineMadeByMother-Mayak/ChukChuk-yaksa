<template>
  <div class="base-backgound-color">
    <Wave title="약봉투/진단서 촬영" height="0px" Link="home" />
    <!-- OcrListView -->
    <div class="text">
      <div>약봉투나 진단서를 촬영하여</div>
      <div>약력과 병력을 체계적으로 관리해보세요.</div>
    </div>
    <div class="buttons">
      <label for="pillBag-file-upload" class="custom-button"
        ><strong>약봉투 </strong> &nbsp;촬영하기</label
      >
      <input
        type="file"
        id="pillBag-file-upload"
        @change="pillBag"
        style="display: none"
      />
      <label for="diagnosis-file-upload" class="custom-button"
        ><strong>진단서 </strong> &nbsp;촬영하기</label
      >
      <input
        type="file"
        id="diagnosis-file-upload"
        @change="diagnosis"
        style="display: none"
      />
    </div>
  </div>
  <!-- <Footer></Footer> -->
</template>

<script setup>
import Wave from "@/common/Wave.vue";
import { useRouter } from "vue-router";
import { ocrListStore } from "@/stores/ocrList";
import { ref, computed } from "vue";

const router = useRouter();
const store = ocrListStore();

const previewImage = computed(() => store.pillBagImageSrc);

const pillBag = (event) => {
  store.setPillBagImage(event.target.files[0]);
};

const diagnosis = (event) => {
  store.setDianosisImage(event.target.files[0]);
};
</script>

<style scoped>
.base-backgound-color {
  background-color: #f9f9f9;
  /* height: 100%; */
}

.text {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 50px 0;
  font-size: 14px;
}

.buttons {
  margin-top: 15px;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 20px;
  padding-bottom: 100px;
}

.custom-button {
  width: 85%;
  height: 80px;
  display: inline-flex; /* Flexbox 사용 */
  align-items: center; /* 수직 정렬 */
  justify-content: center; /* 수평 정렬 */
  background-color: #ffffff; /* 하얀색 배경 */
  border-radius: 5px;
  cursor: pointer;
  box-shadow: 0px 3px 3px rgba(0, 0, 0, 0.1); /* 그림자 하단 3px */
  text-align: center;
  line-height: 1.5; /* 버튼 내부 텍스트의 수직 정렬을 위해 사용 */
  font-size: 19px;
}
.custom-button:hover {
  background-color: rgb(241, 241, 241); /* 흰색 배경 */
}

button:hover {
  background-color: rgb(241, 241, 241); /* 흰색 배경 */
}

button {
  background-color: white; /* 흰색 배경 */
  border: none; /* outline 없음 */
  box-shadow: 0px 3px 3px rgba(0, 0, 0, 0.1); /* 그림자 하단 3px */
  border-radius: 5px;
  width: 90%;
  height: 80px;
}
</style>
