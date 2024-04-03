<template>
  <div class="page">
    <HeaderForm :title="type" />
    <div class="text-container">
      <div>
        척척약사가 <strong>{{ analysis }}</strong
        >를 분석중이에요.
      </div>
      <div>잠시만 기다려주세요.</div>
    </div>
    <div class="img-container">
      <svg
        width="286"
        height="381"
        viewBox="0 0 286 381"
        fill="none"
        xmlns="http://www.w3.org/2000/svg"
      >
        <path
          d="M11.5439 5.29839H35.8508C37.0378 5.29839 38 4.33616 38 3.14919C38 1.96223 37.0378 1 35.8508 1H7C3.68629 1 1 3.68629 1 7V80.7281C1 81.9828 2.01718 83 3.27193 83C4.52668 83 5.54386 81.9828 5.54386 80.7281V11.2984C5.54386 7.98468 8.23015 5.29839 11.5439 5.29839Z"
          fill="white"
        />
        <rect x="43" y="116" width="201" height="230" rx="10" fill="white" />
        <rect x="43" y="116" width="201" height="230" rx="10" />
        <path
          d="M11.5263 376.597H42.7984C44.0143 376.597 45 377.582 45 378.798C45 380.014 44.0143 381 42.7984 381H6C2.68629 381 0 378.314 0 375V299.763C0 298.237 1.23711 297 2.76316 297C4.28921 297 5.52631 298.237 5.52631 299.763V370.597C5.52631 373.91 8.21261 376.597 11.5263 376.597Z"
          fill="white"
        />
        <path
          d="M274.579 4.45565H251.228C249.997 4.45565 249 3.45821 249 2.22782C249 0.99743 249.997 0 251.228 0H279C282.314 0 285 2.68629 285 6V82.7895C285 84.0103 284.01 85 282.789 85C281.569 85 280.579 84.0103 280.579 82.7895V10.4556C280.579 7.14194 277.893 4.45565 274.579 4.45565Z"
          fill="white"
        />
        <path
          d="M275.456 376.597H251.202C249.986 376.597 249 377.582 249 378.798C249 380.014 249.986 381 251.202 381H280C283.314 381 286 378.314 286 375V299.272C286 298.017 284.983 297 283.728 297C282.473 297 281.456 298.017 281.456 299.272V370.597C281.456 373.91 278.77 376.597 275.456 376.597Z"
          fill="white"
        />
        <ellipse cx="143" cy="119.5" rx="98" ry="11.5" fill="#B0B0B0" />
        <rect
          x="65"
          y="29"
          width="163"
          height="32"
          rx="16"
          fill="url(#paint0_linear_322_4650)"
          v-if="showImg[4]"
        />
        <rect
          x="65.5"
          y="29.5"
          width="162"
          height="31"
          rx="15.5"
          stroke="url(#paint1_linear_322_4650)"
          stroke-opacity="0.7"
          v-if="showImg[4]"
        />
        <rect
          x="65"
          y="48"
          width="163"
          height="29"
          rx="14.5"
          fill="url(#paint2_linear_322_4650)"
          v-if="showImg[3]"
        />
        <rect
          x="65.5"
          y="48.5"
          width="162"
          height="28"
          rx="14"
          stroke="url(#paint3_linear_322_4650)"
          stroke-opacity="0.7"
          v-if="showImg[3]"
        />
        <rect
          x="65"
          y="68"
          width="163"
          height="32"
          rx="16"
          fill="url(#paint4_linear_322_4650)"
          v-if="showImg[2]"
        />
        <rect
          x="65.5"
          y="68.5"
          width="162"
          height="31"
          rx="15.5"
          stroke="url(#paint5_linear_322_4650)"
          stroke-opacity="0.7"
          v-if="showImg[2]"
        />
        <rect
          x="65"
          y="90"
          width="163"
          height="29"
          rx="14.5"
          fill="url(#paint6_linear_322_4650)"
          v-if="showImg[1]"
        />
        <rect
          x="65.5"
          y="90.5"
          width="162"
          height="28"
          rx="14"
          stroke="url(#paint7_linear_322_4650)"
          stroke-opacity="0.7"
          v-if="showImg[1]"
        />
        <rect
          x="65"
          y="108"
          width="163"
          height="31"
          rx="15.5"
          fill="url(#paint8_linear_322_4650)"
          v-if="showImg[0]"
        />
        <rect
          x="65.5"
          y="108.5"
          width="162"
          height="30"
          rx="15"
          stroke="url(#paint9_linear_322_4650)"
          stroke-opacity="0.7"
          v-if="showImg[0]"
        />
        <path
          d="M151.042 193.98L151.042 188.96L151.042 173.925C151.042 173.076 150.682 172.329 150.141 171.788C149.575 171.248 148.803 170.887 148.005 170.887L137.995 170.887C137.146 170.887 136.399 171.248 135.859 171.788C135.319 172.329 134.958 173.101 134.958 173.925L134.958 188.96C134.958 190.041 134.058 190.942 132.977 190.942L117.948 190.942C117.099 190.942 116.353 191.302 115.812 191.843C115.272 192.383 114.912 193.13 114.912 193.98L114.912 203.994C114.912 204.818 115.272 205.564 115.812 206.131C116.353 206.671 117.099 207.032 117.948 207.032L129.709 207.032L129.709 210.996L117.974 210.996C116.044 210.996 114.294 210.198 113.059 208.937C111.798 207.675 111 205.925 111 204.02L111 194.005C111 192.075 111.798 190.324 113.059 189.088C114.294 187.827 116.07 187.029 117.974 187.029L131.021 187.029L131.021 173.977C131.021 172.046 131.819 170.321 133.08 169.06C134.315 167.798 136.091 167 137.995 167L148.005 167C149.936 167 151.66 167.772 152.921 169.06C154.182 170.321 154.979 172.072 154.979 173.977L154.979 189.011L154.979 194.031C154.979 198.716 153.049 202.964 149.961 206.028C146.899 209.117 142.653 211.048 137.969 211.048L136.245 211.048L136.245 207.083L137.969 207.083C141.546 207.083 144.814 205.59 147.182 203.247C149.55 200.827 151.042 197.558 151.042 193.98Z"
          fill="#00E1F0"
        />
        <path
          d="M134.958 203.995L134.958 209.015L134.958 224.023C134.958 224.873 135.318 225.62 135.859 226.16C136.399 226.727 137.145 227.061 137.995 227.061L148.005 227.061C148.829 227.061 149.575 226.727 150.141 226.16C150.681 225.62 151.042 224.873 151.042 224.023L151.042 209.015C151.042 207.908 151.917 207.033 153.023 207.033L168.026 207.033C168.875 207.033 169.622 206.672 170.162 206.131C170.728 205.565 171.063 204.793 171.063 203.995L171.063 193.98C171.063 193.131 170.728 192.384 170.162 191.844C169.622 191.303 168.875 190.943 168.026 190.943L156.317 190.943L156.317 186.978L168.026 186.978C169.956 186.978 171.706 187.75 172.967 189.038C174.202 190.299 175 192.05 175 193.98L175 203.995C175 205.926 174.202 207.65 172.967 208.912C171.706 210.173 169.93 210.971 168.026 210.971L155.005 210.971L155.005 224.023C155.005 225.98 154.233 227.705 152.946 228.966C151.659 230.202 149.935 231 148.005 231L137.995 231C136.065 231 134.315 230.202 133.079 228.966C131.818 227.705 131.021 225.954 131.021 224.023L131.021 209.015L131.021 203.995C131.021 199.309 132.925 195.062 136.013 191.972C139.101 188.883 143.347 186.978 148.031 186.978L149.729 186.978L149.729 190.943L148.031 190.943C144.428 190.943 141.186 192.41 138.792 194.778C136.425 197.147 134.958 200.391 134.958 203.995Z"
          fill="#145BDA"
        />
        <path
          d="M117.663 264.28H121.163V266.44H117.663V264.28ZM110.743 271.26H123.363V277.8H120.703V273.4H110.743V271.26ZM120.703 259.26H123.363V270.46H120.703V259.26ZM112.023 262.68H114.163V263.34C114.163 266.6 112.523 269.48 108.983 270.5L107.743 268.44C110.723 267.56 112.023 265.44 112.023 263.34V262.68ZM112.523 262.68H114.683V263.34C114.683 265.3 115.983 267.32 118.963 268.12L117.763 270.2C114.203 269.22 112.523 266.44 112.523 263.34V262.68ZM108.383 261.18H118.323V263.26H108.383V261.18ZM112.023 259.2H114.683V262.08H112.023V259.2ZM135.822 264.28H139.322V266.44H135.822V264.28ZM128.902 271.26H141.522V277.8H138.862V273.4H128.902V271.26ZM138.862 259.26H141.522V270.46H138.862V259.26ZM130.182 262.68H132.322V263.34C132.322 266.6 130.682 269.48 127.142 270.5L125.902 268.44C128.882 267.56 130.182 265.44 130.182 263.34V262.68ZM130.682 262.68H132.842V263.34C132.842 265.3 134.142 267.32 137.122 268.12L135.922 270.2C132.362 269.22 130.682 266.44 130.682 263.34V262.68ZM126.542 261.18H136.482V263.26H126.542V261.18ZM130.182 259.2H132.842V262.08H130.182V259.2ZM157.94 261.66H161.14V263.84H157.94V261.66ZM157.94 265.68H161.14V267.86H157.94V265.68ZM156.1 259.26H158.76V270.18H156.1V259.26ZM146.4 270.94H158.76V277.78H156.1V273.06H146.4V270.94ZM149.34 260.26C152.2 260.26 154.38 262.16 154.38 264.8C154.38 267.44 152.2 269.32 149.34 269.32C146.48 269.32 144.3 267.44 144.3 264.8C144.3 262.16 146.48 260.26 149.34 260.26ZM149.34 262.52C147.94 262.52 146.9 263.34 146.9 264.8C146.9 266.26 147.94 267.1 149.34 267.1C150.74 267.1 151.78 266.26 151.78 264.8C151.78 263.34 150.74 262.52 149.34 262.52ZM166.498 260.68H168.658V263.38C168.658 267.96 167.058 272.26 163.558 273.98L161.958 271.84C165.078 270.38 166.498 266.82 166.498 263.38V260.68ZM167.038 260.68H169.158V263.38C169.158 266.7 170.478 270.08 173.438 271.52L171.838 273.64C168.498 271.94 167.038 267.82 167.038 263.38V260.68ZM174.178 259.26H176.858V277.78H174.178V259.26ZM176.258 266.38H179.538V268.6H176.258V266.38Z"
          fill="#194772"
        />
        <defs>
          <linearGradient
            id="paint0_linear_322_4650"
            x1="66.956"
            y1="29"
            x2="119.268"
            y2="122.765"
            gradientUnits="userSpaceOnUse"
          >
            <stop stop-color="white" stop-opacity="0.59" />
            <stop offset="1" stop-color="white" stop-opacity="0.21" />
          </linearGradient>
          <linearGradient
            id="paint1_linear_322_4650"
            x1="65"
            y1="29"
            x2="119.187"
            y2="124.282"
            gradientUnits="userSpaceOnUse"
          >
            <stop stop-color="white" />
            <stop offset="1" stop-color="white" stop-opacity="0" />
          </linearGradient>
          <linearGradient
            id="paint2_linear_322_4650"
            x1="66.956"
            y1="48"
            x2="111.822"
            y2="136.739"
            gradientUnits="userSpaceOnUse"
          >
            <stop stop-color="white" stop-opacity="0.59" />
            <stop offset="1" stop-color="white" stop-opacity="0.21" />
          </linearGradient>
          <linearGradient
            id="paint3_linear_322_4650"
            x1="65"
            y1="48"
            x2="111.535"
            y2="138.292"
            gradientUnits="userSpaceOnUse"
          >
            <stop stop-color="white" />
            <stop offset="1" stop-color="white" stop-opacity="0" />
          </linearGradient>
          <linearGradient
            id="paint4_linear_322_4650"
            x1="66.956"
            y1="68"
            x2="119.268"
            y2="161.765"
            gradientUnits="userSpaceOnUse"
          >
            <stop stop-color="white" stop-opacity="0.59" />
            <stop offset="1" stop-color="white" stop-opacity="0.21" />
          </linearGradient>
          <linearGradient
            id="paint5_linear_322_4650"
            x1="65"
            y1="68"
            x2="119.187"
            y2="163.282"
            gradientUnits="userSpaceOnUse"
          >
            <stop stop-color="white" />
            <stop offset="1" stop-color="white" stop-opacity="0" />
          </linearGradient>
          <linearGradient
            id="paint6_linear_322_4650"
            x1="66.956"
            y1="90"
            x2="111.822"
            y2="178.739"
            gradientUnits="userSpaceOnUse"
          >
            <stop stop-color="white" stop-opacity="0.59" />
            <stop offset="1" stop-color="white" stop-opacity="0.21" />
          </linearGradient>
          <linearGradient
            id="paint7_linear_322_4650"
            x1="65"
            y1="90"
            x2="111.535"
            y2="180.292"
            gradientUnits="userSpaceOnUse"
          >
            <stop stop-color="white" />
            <stop offset="1" stop-color="white" stop-opacity="0" />
          </linearGradient>
          <linearGradient
            id="paint8_linear_322_4650"
            x1="66.956"
            y1="108"
            x2="116.777"
            y2="200.181"
            gradientUnits="userSpaceOnUse"
          >
            <stop stop-color="white" stop-opacity="0.59" />
            <stop offset="1" stop-color="white" stop-opacity="0.21" />
          </linearGradient>
          <linearGradient
            id="paint9_linear_322_4650"
            x1="65"
            y1="108"
            x2="116.629"
            y2="201.713"
            gradientUnits="userSpaceOnUse"
          >
            <stop stop-color="white" />
            <stop offset="1" stop-color="white" stop-opacity="0" />
          </linearGradient>
        </defs>
      </svg>
    </div>
  </div>
</template>

<script setup>
import HeaderForm from "@/common/Form/HeaderForm.vue";
import { onMounted, onUnmounted, ref } from "vue";
import EffectFace from "@/assests/icon/effectFace.svg";
import { useRoute } from "vue-router";
import { defineProps } from "vue";

const props = defineProps({
  analysis: {
    type: String,
    default: "-",
  },
});

const showImg = ref([false, false, false, false, false]);

onMounted(() => {
  const roadingInterval = setInterval(() => {
    setTimeout(() => {
      showImg.value[0] = true;
    }, 500);

    setTimeout(() => {
      showImg.value[1] = true;
    }, 1000);

    setTimeout(() => {
      showImg.value[2] = true;
    }, 1500);

    setTimeout(() => {
      showImg.value[3] = true;
    }, 2000);

    setTimeout(() => {
      showImg.value[4] = true;
    }, 2500);

    setTimeout(() => {
      showImg.value[0] = false;
      showImg.value[1] = false;
      showImg.value[2] = false;
      showImg.value[3] = false;
      showImg.value[4] = false;
    }, 3000);
  }, 3000);
});

onUnmounted(() => {});
</script>

<style scoped>
.page {
  background: linear-gradient(to right, #3183ff 0%, #86e7ee 100%);
}

.text-container {
  display: flex;
  justify-content: center;
  font-size: 15px;
  flex-direction: column;
  align-items: center;
  margin-bottom: 20px;
}

.img-container {
  display: flex;
  justify-content: center;
}
</style>
