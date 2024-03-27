<template>
  <Wave title="복용 목록" height="60px" />
  <div v-for="(item, index) in items" :key="index">
    <img :src="item.imageUrl" :alt="'Image ' + (index + 1)" />
  </div>
  <div cla ss="carousel-container">
    <div class="carousel">

      <div v-for="(item, index) in items" :key="index">
        <img :src="item.imageUrl" :alt="'Image ' + (index + 1)" />
      </div>
  
      <div class="carousel-buttons">
        <button class="prev-button" @click="prev" :class="{ 'active': currentIndex > 0 }"></button>
        <button class="next-button" @click="next" :class="{ 'active': currentIndex < items.length - 1 }"></button>
        <button class="next-button" @click="next" :class="{ 'active': currentIndex < items.length - 1 }"></button>
      </div>
    </div>
  </div>
</template>

<script setup>
  import Wave from '@/common/Wave.vue';
  // import { ref, onUnmounted } from 'vue';

  // const items = ref([
  //   { imageUrl: 'https://via.placeholder.com/728x90.png' },
  //   { imageUrl: 'https://via.placeholder.com/728x90.png' },
  //   { imageUrl: 'https://via.placeholder.com/728x90.png' }
  // ]);

  // let currentIndex = ref(0);

  // function prev() {
  //   currentIndex.value = (currentIndex.value - 1 + items.value.length) % items.value.length;
  // }

  // function next() {
  //   currentIndex.value = (currentIndex.value + 1) % items.value.length;
  // }

  // const interval = setInterval(next, 3000); // 3 seconds interval for automatic sliding

  
  // onUnmounted(() => {
  //   clearInterval(interval);
  // });
</script>
  
<style>
  /* .carousel {
    overflow: hidden;
    position: relative;
  }

  .carousel ul {
    display: flex;
    transition: transform 0.5s ease;
    background-color:yellow;
  }
  .carousel ul ul{
    margin-left: 0px;
    background-color:aqua;
  }

  .carousel-buttons {
    display: flex;
    justify-content: center;
    margin-top: 5px;
    margin-bottom: 5px;
  }

  .carousel-buttons button {
    background-color: #ccc;
    color: #fff;
    border: none;
    border-radius: 50%;
    cursor: pointer;
    width: 5px;
    height: 11px;
    z-index: 999;
    margin-left: 5px; /* 간격 조정 */
    /* transition: all 0.3s ease;
  } */

  /* .carousel-buttons button.active {
    background-color: #888;
    transform: scale(1.2);
  } */
</style>
