<template>
  <nav class="nav-bar">
    <div class="nav-item">
      <RouterLink class="link" :to="{name : 'home'}">
        <div class="link-item"><img src="@/assests/icon/navbar/home.png" alt="홈"></div>
        <div class="link-item"><span>홈</span></div>
      </RouterLink>
    </div>
    
    <div class="nav-item">
      <RouterLink class="link" :to="{name : 'TakeList'}">
        <div class="link-item"><img src="@/assests/icon/navbar/pill.png" alt="복용리스트"></div>
        <div class="link-item"><span>복용관리</span></div>
      </RouterLink>
    </div>
    <div class="nav-item">
      <RouterLink class="link" :to="{name : 'usermedicalinfo'}">
        <div class="link-item"><img src="@/assests/icon/navbar/diagnosis.png" alt="문진표"></div>
        <div class="link-item"><span>문진표</span></div>
      </RouterLink>
    </div>
    <div class="nav-item">
      <RouterLink class="link" :to="{name : 'Setting'}">
        <div class="link-item"><img src="@/assests/icon/navbar/settings.png" alt="설정"></div>
        <div class="link-item"><span>설정</span></div>
      </RouterLink>
    </div>
  </nav>
</template>
<script>
</script>

<style scoped>
.nav-bar {
  position: fixed;
  bottom: 25px;
  left: 50%;
  transform: translateX(-50%); /* 가운데 정렬을 위해 위치 조정 */
  width: 89%;
  height: 50px;
  display: flex;
  align-items: center;
  box-shadow: 0 0.3em 0.3em rgba(0, 0, 0, 0.3);
  /* border-top: 0.5px solid rgba(0,0,0,0.1); */
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
.link-item {
  display: flex;
  justify-content: center;
}
.nav-item i {
  font-size: 17px;
  color: black;
  margin-top: 4px;
}
.nav-item img {
  width: 20px;
  height: 20px;
  margin-top: 8px;
}
.nav-item span{
  font-size: 8px;
  color: black;
  margin-top: 2.5px;

}

.link {
  text-decoration: none;
}


</style>