<template>
  <Wave title="복용 관리" height="60px" />
  <vueper-slides autoplay class="no-shadow ex--center-mode" :arrows-outside="false" bullets-outside transition-speed="250" :interval="2000">
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
      <button class="gray-button">과거에먹은약</button>
      <button class="navy-button">추가</button>
    </div>
  </div>
  <hr class="line">
  <div class="pill-info" v-for="(data, index) in dumydata.result" :key="index">
    <div>{{data.createDate}} [{{ data.takeListName }}]</div>
    <img src="@/assests/icon/edit.png" alt="홈">

  </div>
  
 
</template>

<script>
  import Wave from '@/common/Wave.vue';
  import { VueperSlides, VueperSlide } from 'vueperslides'
  import 'vueperslides/dist/vueperslides.css'
  // import { ref, onUnmounted } from 'vue';

  export default {
  components: { VueperSlides, VueperSlide, Wave},

  setup() {
    const dumydata = {
      count: 1, // 건수
      result: [
        {
          createDate: "2024-03-04",
          takeListName: "위염약",
          pills: [
            { type: "기침 및 감기 치료제", name: "페니라민정" },
            { type: "항히스타민제", name: "프라닥사캡슐" }
          ]
        }
      ]
    };
    return {dumydata};
  }
};

  
</script>
  
<style>

.ex--center-mode {
  margin-top: 0px;
  left: 50%;
  transform: translateX(-50%); 
  width: 100%;
}
.vueper-slide {
  position: fixed;
  left: 50%;
  transform: translateX(-50%); 
  width: 90%;
  height: 80px;
  display: flex;
  align-items: center;
  box-shadow: 0.1em 0.1em 0.1em rgba(0, 0, 0, 0.1);
  background-color: #ffffff;
  border-radius: 10px;
  z-index: 1000;
  transition: top 0.3s ease;
}
.vueperslides__bullet .default {
  background-color: rgba(0, 0, 0, 0.3);
  border: none;
  box-shadow: none;
  transition: 0.3s;
  width: 10px;
  height: 10px;
  margin-left: -10px;
  margin-top: 0px;
  
}
.vueperslides__bullet--active .default {background-color: rgb(66, 66, 66);}


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
  overflow: hidden;
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
.gray-button {
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
  .navy-button {
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
  width: 90%;
  margin-top: 0px; 
}
.pill-info {
  margin-left: 20px;
  font-weight: bold;
  display: flex;
  align-items: center;
}
.pill-info p{
  flex: 1;
}
.pill-info img {
  width: 20px; 
  height: 20px;

}
</style>
