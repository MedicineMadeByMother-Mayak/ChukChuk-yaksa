import axios from "axios";
import { httpStatusCode } from "@/util/http-status";
import { ref, onMounted } from "vue";
import { useAuthStore } from "@/stores/auth";
const { VITE_SPRING_URL } = import.meta.env;
import router from "../router/index"; // Vue Router 인스턴스를 가져옴

//axios 헤더 설정
const instance = axios.create({
  baseURL: VITE_SPRING_URL,
  headers: {
    "Content-Type": "application/json;charset=utf-8",
  },
  withCredentials: true, // withCredentials 설정 추가
});

//요청 인터셉터 설정 (store를 밖에서 선언하면 오류남 주의)
instance.interceptors.request.use(async (config) => {
  // const authStore = useAuthStore();
  // const accessToken = authStore.accessToken;

  //전역 액세스토큰 없으면 localstorage에서 가져오기
  const accessToken = JSON.parse(localStorage.getItem("token"));

  config.headers.Authorization = `Bearer ${accessToken}`;

  if (accessToken) {
    config.headers.Authorization = `Bearer ${accessToken}`;
  }

  return config;
});

//응답 인터셉터 설정
instance.interceptors.response.use(
  async (response) => {
    return response;
  },
  async (error) => {
    const {
      config,
      response: { status },
    } = error;

    if (status === httpStatusCode.UNAUTHORIZED) {
      //accesstoken이 유효하지 않으므로, store의 유저정보를 clear한다.
      // const authStore = useAuthStore();
      // authStore.clearAccessToken();

      //재로그인 불가시 로그인페이지로 이동하고 alert 띄우기
      //const refreshToken = getRefreshToken();
      alert("로그인 시간이 만료되었습니다. 다시 로그인해주세요.");
      router.push({ name: "login" });

      //return instance.request(error.config);
    }

    return Promise.reject(error);
  }
);

export { instance };
