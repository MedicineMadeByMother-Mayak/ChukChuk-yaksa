<template>
  <div class="login-page-container">
    <div class="login-logo">
      <img class="wave" src="../../assests/img/startLogo.png" alt="Logo" />
      <div>
        <div class="login-sub-title">슬기로운 복용생활,</div>
        <div class="login-title">척척약사</div>
      </div>
    </div>

    <div class="login-options">
      <button class="login-btn google" @click="googleLogin">
        <img src="../../assests/img/Google.png" alt="" />
        <p>구글로 로그인</p>
      </button>
      <button class="login-btn naver" @click="naverLogin">
        <img src="../../assests/img/네이버 로고.png" alt="" />
        <p>네이버 로그인</p>
      </button>
      <button class="login-btn kakao" @click="kakoLogin">
        <img src="../../assests/img/Kakao.png" alt="" />
        <p>카카오 로그인</p>
      </button>
    </div>
  </div>
</template>

<script setup>
const {
  VITE_REDIRECT_URI,
  VITE_VUE_URL,
  VITE_REDIRECT_URI_PARAM_COOKIE_NAME,
  VITE_SPRING_URL_FOR_LOGIN,
} = import.meta.env;

const googleLogin = () => {
  const loginUrl = getLoginURI("google");
  window.location.href = loginUrl;
};
const kakoLogin = () => {
  const loginUrl = getLoginURI("kakao");
  window.location.href = loginUrl;
};
const naverLogin = () => {
  const loginUrl = getLoginURI("naver");
  window.location.href = loginUrl;
};

const getLoginURI = (social) => {
  return (
    VITE_SPRING_URL_FOR_LOGIN +
    "/oauth2/authorization/" +
    social +
    "?" +
    VITE_REDIRECT_URI_PARAM_COOKIE_NAME +
    "=" +
    VITE_REDIRECT_URI
  );
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Inter:wght@400;500;700&display=swap");
@keyframes rotateAndPause {
  0% {
    transform: rotate(0deg);
  }

  55% {
    transform: rotate(360deg);
  }

  100% {
    transform: rotate(360deg);
  }
}

@keyframes slideAndFade {
  from {
    opacity: 0;
    transform: translateY(30%);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}
.wave {
  animation: rotateAndPause 3s ease-in-out infinite;
}

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
  animation: slideAndFade 1s ease-out forwards;
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
