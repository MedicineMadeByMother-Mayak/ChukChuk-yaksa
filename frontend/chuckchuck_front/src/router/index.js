import { createRouter, createWebHistory } from "vue-router";
import HomeView from "@/views/home/HomeView.vue";
import LoginSuccessView from "@/views/login/LoginSuccessView.vue";

import OcrListView from "@/views/ocr_list/OcrListView.vue";
import DiagnosisResultView from "@/views/ocr_list/DiagnosisResultView.vue";
import PillBagResultView from "@/views/ocr_list/PillBagResultView.vue";
import OcrSelectView from "@/views/ocr_list/OcrSelectView.vue";
import FilmingView from "@/views/ocr_list/FilmingView.vue";

import testView from "@/common/testView.vue";
import PillBagHistoryView from "@/views/user_medical_info/PillBagHistoryView.vue";
import UserMedicalInfoView from "@/views/user_medical_info/UserMedicalInfoView.vue";
import DiagnosisHistoryView from "@/views/user_medical_info/DiagnosisHistoryView.vue";
import LoginView from "@/views/login/LoginView.vue";

import PillSearchView from "@/views/pill_search/PillSearchView.vue";
import PillSearchDetailView from "@/views/pill_search/PillSearchDetailView.vue";

import SettingView from "@/views/setting/SettingView.vue";

import TakeListView from "@/views/take_list/TakeListView.vue";

import MapView from "@/views/pharmacy_search/MapView.vue";
import PharmacySearchView from "@/views/pharmacy_search/PharmacySearchView.vue";

import PIllPicView from "@/views/pill_pic/PIllPicView.vue";

import UserPillEffectView from "@/views/user_pill_effect/UserPillEffectView.vue";
import EffectDetailView from "@/views/user_pill_effect/EffectDetailView.vue";
import LoadingView from "@/views/home/LoadingView.vue";
import RegistInfoView from "@/views/home/RegistInfoView.vue";
import testingPage from "@/common/testingPage.vue";

const routes = [
  {
    //메인페이지
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    //로그인 성공시 처리용 페이지
    path: "/login-success",
    name: "loginSuccess",
    component: LoginSuccessView,
  },
  {
    //약봉투/진단서 촬영 메인페이지
    path: "/ocrlist",
    name: "ocrlist",
    component: OcrListView,
    children: [
      {
        //진단서 촬영결과
        path: "dianosis",
        name: "diagnosisresult",
        component: DiagnosisResultView,
      },
      {
        //약봉투 촬영결과
        path: "pill-bag",
        name: "pillbagresult",
        component: PillBagResultView,
      },
    ],
  },
  {
    //약국검색 - 약검색 페이지
    path: "/pharmacy",
    name: "pharmacysearch",
    component: PharmacySearchView,
    children: [
      {
        //약국 검색창 페이지
        path: "result",
        name: "map",
        component: MapView,
      },
    ],
  },
  {
    //약 사진으로 검색
    path: "/pillpic",
    name: "pillpic",
    component: PIllPicView,
  },
  {
    //약 이름으로 검색
    path: "/pill",
    name: "pillsearch",
    component: PillSearchView,
  },
  {
    //약 상세
    path: "/pill/:id",
    name: "pilldetail",
    component: PillSearchDetailView,
  },

  {
    //설정
    path: "/setting",
    name: "Setting",
    component: SettingView,
  },
  {
    //복용리스트
    path: "/take-list",
    name: "TakeList",
    component: TakeListView,
  },

  {
    //문진표
    path: "/medical-info",
    name: "usermedicalinfo",
    component: UserMedicalInfoView,
  },

  {
    //진단내역
    path: "/medical-info/diagnosishistory",
    name: "diagnosishistory",
    component: DiagnosisHistoryView,
  },

  {
    //처방내역
    path: "/medical-info/pillbag",
    name: "pillbaghistory",
    component: PillBagHistoryView,
  },

  {
    //약효기록
    path: "/user-pill-effect",
    name: "userpilleffect",
    component: UserPillEffectView,
  },

  {
    //약효기록 상세
    path: "/user-pill-effect/:pillId",
    name: "/effectdetail",
    component: EffectDetailView,
  },

  {
    // 로그인 화면
    path: "/login",
    name: "login",
    component: LoginView,
  },
  {
    // 로딩 화면
    path: "/loading",
    name: "loading",
    component: LoadingView,
  },
  {
    // 개인정보등록
    path: "/registinfo",
    name: "registinfo",
    component: RegistInfoView,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

// 전역 네비게이션 가드 설정
router.beforeEach((to, from, next) => {
  const token = localStorage.getItem("token");
  if (
    !token &&
    to.name !== "login" &&
    to.name !== "loginSuccess" &&
    to.name !== "loading"
  ) {
    next({ name: "login" });
  } else {
    next();
  }
});
export default router;
