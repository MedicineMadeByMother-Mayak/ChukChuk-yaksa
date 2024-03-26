import { createRouter, createWebHistory } from "vue-router";
import PillBagHistoryView from "@/views/user_medical_info/PillBagHistoryView.vue";
import HomeView from "@/views/home/HomeView.vue";
import UserMedicalInfoView from "@/views/user_medical_info/UserMedicalInfoView.vue";

import testView from "@/common/testView.vue";
import DiagnosisHistoryView from "@/views/user_medical_info/DiagnosisHistoryView.vue";
import LoginView from "@/views/home/LoginView.vue";

import TakeListView from "@/views/take_list/TakeListView.vue";
import SettingView from "@/views/setting/SettingView.vue";

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
      //문진표
      path: "/usermedicalinfo",
      name: "usermedicalinfo",
      component: UserMedicalInfoView,
    },
    {
      path: "/usermedicalinfo/pillbag",
      name: "pillbaghistory",
      component: PillBagHistoryView,
    },
    {
      //복용기록
      path: "/usermedicalinfo/diagnosishistory",
      name: "diagnosishistory",
      component: DiagnosisHistoryView,
    },
    {
      path: "/test",
      name: "test",
      component: testView,
    },
    //복용리스트
    {
      path: "/take-list",
      name: "TakeList",
      component: TakeListView,
    },
    //설정
    {
      path: "/setting",
      name: "Setting",
      component: SettingView,
    },

    {
      // 로그인 화면
      path: "/login",
      name: "login",
      component: LoginView,
    },
  ],
});

export default router;
