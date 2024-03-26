import { createRouter, createWebHistory } from "vue-router";
import HomeView from "@/views/home/HomeView.vue";

import OcrListView from "@/views/ocr_list/OcrListView.vue";
import DiagnosisFilmingView from "@/views/ocr_list/DiagnosisFilmingView.vue";
import DiagnosisResultView from "@/views/ocr_list/DiagnosisResultView.vue";
import PillBagFilmingView from "@/views/ocr_list/PillBagFilmingView.vue";
import PillBagResultView from "@/views/ocr_list/PillBagResultView.vue";

import testView from "@/common/testView.vue";
import PillBagHistoryView from "@/views/user_medical_info/PillBagHistoryView.vue";
import UserMedicalInfoView from "@/views/user_medical_info/UserMedicalInfoView.vue";
import DiagnosisHistoryView from "@/views/user_medical_info/DiagnosisHistoryView.vue";

import PillSearchView from "@/views/pill_search/PillSearchView.vue";
import PillSearchDetailView from "@/views/pill_search/PillSearchDetailView.vue";

import SettingView from "@/views/setting/SettingView.vue";

import TakeListView from "@/views/take_list/TakeListView.vue";

import MapView from "@/views/pharmacy_search/MapView.vue";
import PharmacySearchView from "@/views/pharmacy_search/PharmacySearchView.vue";
import ResultView from "@/views/pharmacy_search/ResultView.vue";

import PIllPicView from "@/views/pill_pic/PIllPicView.vue";

import UserPillEffectView from "@/views/user_pill_effect/UserPillEffectView.vue";
import EffectDetailView from "@/views/user_pill_effect/EffectDetailView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      //메인페이지
      path: "/",
      name: "home",
      component: HomeView,
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
      //약국 검색 결과
      path: "/mapview/resultview",
      name: "resultview",
      component: ResultView,
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
  ],
});

export default router;
