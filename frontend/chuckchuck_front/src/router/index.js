import { createRouter, createWebHistory } from "vue-router";
import HomeView from "@/views/home/HomeView.vue";
import LoginSuccessView from "@/views/login/LoginSuccessView.vue";

import OcrListView from "@/views/ocr_list/OcrListView.vue";
import DiagnosisFilmingView from "@/views/ocr_list/DiagnosisFilmingView.vue";
import DiagnosisResultView from "@/views/ocr_list/DiagnosisResultView.vue";
import PillBagFilmingView from "@/views/ocr_list/PillBagFilmingView.vue";
import PillBagResultView from "@/views/ocr_list/PillBagResultView.vue";

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
  },
  {
    //약봉투 촬영
    path: "/ocrlist/diagnosisfilming",
    name: "diagnosisfilming",
    component: DiagnosisFilmingView,
  },
  {
    //약봉투 촬영 결과
    path: "/ocrlist/diagnosisresult",
    name: "diagnosisresult",
    component: DiagnosisResultView,
  },
  {
    //진단서 촬영
    path: "/ocrlist/pillbagfilming",
    name: "pillbagfilming",
    component: PillBagFilmingView,
  },
  {
    //진단서 촬영 결과
    path: "/ocrlist/pillbagresult",
    name: "pillbagresult",
    component: PillBagResultView,
  },
  {
    //약국 검색 메인페이지
    path: "/map",
    name: "map",
    component: MapView,
  },
  {
    //약국 검색창 페이지
    path: "/map/pharmacysearch",
    name: "pharmacysearch",
    component: PharmacySearchView,
  },
  {
    //약 사진으로 검색
    path: "/pillpic",
    name: "pillpic",
    component: PIllPicView,
  },
  {
    //약 이름으로 검색
    path: "/pillsearch",
    name: "pillsearch",
    component: PillSearchView,
  },
  {
    //약 상세정보 페이지
    path: "/pillsearch/pillsearchdetail",
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
    path: "/usermedicalinfo",
    name: "usermedicalinfo",
    component: UserMedicalInfoView,
  },
  {
    //처방내역
    path: "/usermedicalinfo/pillbag",
    name: "pillbaghistory",
    component: PillBagHistoryView,
  },
  {
    //진단내역
    path: "/usermedicalinfo/diagnosishistory",
    name: "diagnosishistory",
    component: DiagnosisHistoryView,
  },
  {
    path: "/test",
    name: "test",
    component: testView,
  },
  {
    //약효기록
    path: "/userpilleffect",
    name: "userpilleffect",
    component: UserPillEffectView,
  },
  {
    path: "/userpilleffect/effectdetail",
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
  }
];
const router = createRouter({
  history: createWebHistory(),
  routes,
});

// 전역 네비게이션 가드 설정
router.beforeEach((to, from, next) => {
  const token = localStorage.getItem("token");
  if (!token && to.name !== "login" && to.name !== "loginSuccess") {
    next({ name: "login" });
  } else {
    next();
  }
});
export default router;
