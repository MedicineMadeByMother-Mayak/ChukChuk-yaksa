<template>
  <div class="login-page-container">
    <div class="login-logo">
      <img src="../../assests/img/startLogo.png" alt="Logo" />
      <div>
        <div class="login-sub-title">슬기로운 복용생활,</div>
        <div class="login-title">척척약사</div>
      </div>
    </div>

    <div class="login-options">
      <button class="login-btn google">
        <img src="../../assests/img/Google.png" alt="" />
        <p>구글로 로그인</p>
      </button>
      <button class="login-btn naver">
        <img src="../../assests/img/네이버 로고.png" alt="" />
        <p>네이버 로그인</p>
      </button>
      <button class="login-btn kakao">
        <img src="../../assests/img/Kakao.png" alt="" />
        <p>카카오 로그인</p>
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: "LoginPage",
  data() {
    return {};
  },
  methods: {
    loginWithNaver() {},
    loginWithGoogle() {},
    loginWithKakao() {},
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Inter:wght@400;500;700&display=swap");

.login-page-container {
  background: #ffffff;
  display: flex;
  flex-direction: column;
  justify-content: center;
  height: 100%;
}

.login-info {
  display: flex;
}

.login-logo img {
  width: 100px;
  margin-right: 5.75px;
}

.login-logo {
  display: flex;
  width: 100%;
  justify-content: center;
}

.login-logo > div {
  margin-top: auto 0 auto 0;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.login-sub-title {
  font-weight: 600;
  font-size: 13px;
  /* font-family: "Inter", sans-serif; */
  color: #181818;
}

.login-title {
  color: #181818;
  width: 100%;
  font-family: "Inter", sans-serif;
  font-size: 40px;
  font-weight: 1000;
  letter-spacing: -4px;
}

.login-options {
  width: 100%;
  margin-top: 5rem;
}

.login-btn {
  display: flex;
  width: 16rem;
  height: 3rem;
  margin: 10px auto;
  border-radius: 4px;
  border: none;
  cursor: pointer;
  align-items: center;
  font-size: 1rem;
  font-weight: 500;
  padding-left: 61px;
}

.login-btn > img {
  width: 1.2rem;
}

.login-btn > p {
  font-family: "ABeeZee", sans-serif;
  font-weight: 600;
  margin-left: 13px;
}

.naver > img {
  width: 1rem;
}

.naver > p {
  margin-left: calc(13px + 0.2rem);
}

.login-btn.naver {
  background: #03c75a;
  color: #ffffff;
}

.login-btn.google {
  background: #ffffff;
  color: #000000;
  border: 1px solid #d5d5d5;
}

.login-btn.kakao {
  background: #fee500;
  color: #000000;
}
</style>
