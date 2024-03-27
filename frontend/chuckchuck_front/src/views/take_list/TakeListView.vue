<template>
  <Wave title="복용 관리" height="60px" />
  <vueper-slides class="no-shadow ex--center-mode" :arrows-outside="false" bullets-outside transition-speed="250" :interval="2000">
  <vueper-slide
    class = 'vueper-slide'
    v-for="i in 3"
    :key="i"
    :title="i.toString()"
    />
  </vueper-slides>

  <div class="alarms">
  </div>

  <div class="menu">
    <div class="menu-left">
      <img src="@/assests/icon/pill.png" alt="복용리스트">
      <div>복용중</div>
    </div>
    <div class="menu-right">
      <button class="gray">과거에먹은약</button>
      <button class="blue">추가</button>
    </div>
  </div>
  <hr class="line">
  
 
</template>

<script>
  import Wave from '@/common/Wave.vue';
  import { VueperSlides, VueperSlide } from 'vueperslides'
  import 'vueperslides/dist/vueperslides.css'
  // import { ref, onUnmounted } from 'vue';

  export default {
  components: { VueperSlides, VueperSlide, Wave }
}
</script>
  
<style>
.ex--center-mode {
  margin-top: 30px;
  left: 50%;
  transform: translateX(-50%); 
  width: 80%;
}
.vueper-slide {
  position: fixed;
  left: 50%;
  transform: translateX(-50%); 
  width: 70%;
  height: 80px;
  display: flex;
  align-items: center;
  box-shadow: 0.1em 0.1em 0.1em rgba(0, 0, 0, 0.1);
  background-color: #ffffff;
  border-radius: 10px;
  z-index: 1000;
  transition: top 0.3s ease;
}

.alarms {
   background-color: lightskyblue;
   height: 50px;
}
.menu{
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 90%;
  margin-left: 20px;
  margin-top: 10px;
  position: relative;
}

.menu-left {
  display: flex;
  align-items: center;
}
.menu-left img {
  width: 80px;
  margin-right: 5px;
}

.menu-right {
  text-align: right;
  margin-bottom: 10px;
}
.menu img {
  width: 10px;
  height: 10px;
}

button {
  display: inline;
  margin-right: 10px;
  margin-top: 10px;
}
.gray {
    background-color: #cccccc;
    color: #ffffff; 
    font-weight: bold; 
    border-radius: 5px;
    padding: 5px 10px; 
    font-size: 12px;
    border: none;
    cursor: pointer; 
    margin-right: 5px;
  }
  .blue {
    background-color: #001f3f;
    color: #ffffff; 
    font-weight: bold; 
    border-radius: 5px; 
    padding: 5px 15px; 
    font-size: 12px; 
    border: none;
    cursor: pointer; 
  }

.line{
  color: gray;
  width: 90%;
  margin-top: 20px;
  
}

</style>
