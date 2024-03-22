import { createRouter, createWebHistory } from "vue-router";
import PillBagHistoryView from "@/views/user_medical_info/PillBagHistoryView.vue";
import HomeView from "@/views/home/HomeView.vue";
import UserMedicalInfoView from "@/views/user_medical_info/UserMedicalInfoView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: HomeView,
    },
    // {
    //   path: '/about',
    //   name: 'about',
    //   // route level code-splitting
    //   // this generates a separate chunk (About.[hash].js) for this route
    //   // which is lazy-loaded when the route is visited.
    //   component:
    // },
    {
      path: "/usermedicalinfo/pillbag",
      name: "pillbaghistory",
      component: PillBagHistoryView,
    },
    {
      path: "/usermedicalinfo",
      name: "usermedicalinfo",
      component: UserMedicalInfoView,
    },
  ],
});

export default router;
