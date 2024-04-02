<template>
  <div ref="observerElement"></div>
</template>

<script setup>
import { onMounted, onBeforeUnmount, ref } from "vue";

// Composition API를 사용하여 setup 함수 정의
defineProps();
const observerElement = ref(null);
const observer = ref(null);
const observerOptions = {
  rootMargin: "0px",
  threshold: 1.0,
};

onMounted(() => {
  observer.value = new IntersectionObserver((entries) => {
    if (entries[0].isIntersecting) {
      // 관찰 대상이 교차되었을 때 intersect 이벤트를 호출합니다.
      emit("show");
    } else {
      emit("hidden");
    }
  }, observerOptions);

  if (observerElement.value) {
    observer.value.observe(observerElement.value);
  }
});

onBeforeUnmount(() => {
  if (observer.value) {
    observer.value.disconnect();
  }
});

// 이벤트를 emit하기 위해 defineEmits를 사용합니다.
const emit = defineEmits(["show", "hidden"]);
</script>
