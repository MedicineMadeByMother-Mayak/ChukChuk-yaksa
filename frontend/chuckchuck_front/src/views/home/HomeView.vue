<template>
  <div>home</div>
  <table class="table table-striped">
    <tbody>
      <tr>
        <td>
          <a v-on:click="naverLogin">Naver</a>
        </td>
      </tr>
      <tr>
        <td>
          <a v-on:click="kakoLogin">Kakao</a>
        </td>
      </tr>
      <tr>
        <td>
          <button v-on:click="googleLogin">Google</button>
        </td>
      </tr>
    </tbody>
  </table>
</template>

<script setup>
const {
  VITE_REDIRECT_URI,
  VITE_VUE_URL,
  VITE_REDIRECT_URI_PARAM_COOKIE_NAME,
  VITE_SPRING_URL_FOR_LOGIN,
} = import.meta.env;

//로그인
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

<style scoped></style>
