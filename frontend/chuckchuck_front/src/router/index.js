import { createRouter, createWebHistory } from "vue-router";
import PillBagHistoryView from "@/views/user_medical_info/PillBagHistoryView.vue";
import HomeView from "@/views/home/HomeView.vue";
import UserMedicalInfoView from "@/views/user_medical_info/UserMedicalInfoView.vue";
import TakeListView from "@/views/take_list/TakeListView.vue";
import SettingView from "@/views/setting/SettingView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    //메인페이지
    {
      path: "/",
      name: "home",
      component: HomeView,
    },
    {
      path: "/usermedicalinfo/pillbag",
      name: "pillbaghistory",
      component: PillBagHistoryView,
    },
    //문진표
    {
      path: "/usermedicalinfo",
      name: "usermedicalinfo",
      component: UserMedicalInfoView,
    },
    //복용리스트
    {
      path: '/take-list',
      name: "takeList",
      component: TakeListView,
    },
    //설정
    {
      path: '/setting',
      name: "settingView",
      component: SettingView,
    },

  ],
});

export default router;
