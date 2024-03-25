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
// export default {

// }
</script>

<style scoped>
.nav-bar {
  position: fixed;
  bottom: 0;
  width: 100%;
  background-color: #333;
  border-top-left-radius: 10px;
  border-top-right-radius: 10px;
  display: flex;
  justify-content: space-around;
  padding: 10px 0;
}
.nav-item {
  color: white;
  text-align: center;
}

.nav-item i {
  font-size: 24px;
}

.nav-item span {
  font-size: 12px;
}

</style>