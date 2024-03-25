<template>
  <nav class="nav-bar">
    <div class="nav-item">
      <router-link to="/"><i class="fas fa-home"></i><span>홈</span></router-link>
    </div>
    <div class="nav-item">
      <router-link to="/take-list"><i class="fas fa-user"></i><span>복용관리</span></router-link>
    </div>
    <div class="nav-item">
      <router-link to="/usermedicalinfo"><i class="fas fa-file-alt"></i><span>문진표</span></router-link>
    </div>
    <div class="nav-item">
      <router-link to="/setting"><i class="fas fa-cog"></i><span>설정</span></router-link>
    </div>
  </nav>
</template>

<script>
</script>

<style scoped>
.nav-bar {
  position: fixed;
  bottom: 16px;
  left: 7%;
  width: 85%;
  height: 50px;
  display: flex;
  align-items: center;

  background-color: white;
  border-radius: 22px;
  box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.2);
  z-index: 1000;
  transition: top 0.3s ease; /* 부드러운 이동 효과 */
}
.nav-item {
  color: white;
  text-align: center;
  flex: 1;
}

.nav-item i {
  font-size: 24px;
}

.nav-item span {
  font-size: 12px;
}

</style>