<template>
  <nav class="nav-bar">
    <div class="nav-item">
      <RouterLink class="link" :to="{ name: 'home' }">
        <img src="@/assests/icon/navbar/home.svg" alt="" />
      </RouterLink>
    </div>

    <div class="nav-item">
      <RouterLink class="link" :to="{ name: 'TakeList' }">
        <img src="@/assests/icon/navbar/takelist.svg" alt="" />
      </RouterLink>
    </div>
    <div class="nav-item">
      <RouterLink class="link" :to="{ name: 'usermedicalinfo' }">
        <img src="@/assests/icon/navbar/diagnosis.svg" alt="" />
      </RouterLink>
    </div>
    <div class="nav-item">
      <RouterLink class="link" :to="{ name: 'Setting' }">
        <img src="@/assests/icon/navbar/setting.svg" alt="" />
      </RouterLink>
    </div>
  </nav>
</template>
<script></script>

<style scoped>
.nav-bar {
  /* background-color: aqua; */
  /* position: fixed; */
  /* bottom: 20px; */
  /* left: 160px; */
  /* transform: translateX(-50%); */
  width: 280px;
  height: 55px;
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
.nav-item img {
  width: auto;
  height: 38px;
  margin-top: 8px;
}

.link {
  text-decoration: none;
}
</style>
