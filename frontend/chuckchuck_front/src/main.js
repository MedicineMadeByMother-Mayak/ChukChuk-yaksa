import { createApp } from "vue";
import { createPinia } from "pinia";
import Vue3TouchEvents from "vue3-touch-events";
import App from "./App.vue";

import router from "./router";
import "./assests/css/fonts.css";
import "./assests/css/carousel.css";

const app = createApp(App);

app.use(router);
app.use(Vue3TouchEvents);
app.use(createPinia());

app.mount("#app");
