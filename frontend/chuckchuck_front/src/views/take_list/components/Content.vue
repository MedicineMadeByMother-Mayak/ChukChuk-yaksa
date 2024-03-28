<template>
  <div>
    <nav-bar>
      <nav-item>
        helo
      </nav-item>
      
    </nav-bar>

  </div>
</template>

<script setup>

</script>

<style>

.nav-bar {
  position: fixed;
  bottom: 25px;
  left: 50%;
  transform: translateX(-50%); 
  width: 89%;
  height: 50px;
  display: flex;
  align-items: center;
  box-shadow: 0 0.3em 0.3em rgba(0, 0, 0, 0.3);
  background-color: #ffffff;
  border-radius: 22px;
  z-index: 1000;
  transition: top 0.3s ease; 

}
.nav-item {
  display: flex;
  flex-direction: column;
  align-items: center; 
  color: black;
  flex: 1;
}

</style>