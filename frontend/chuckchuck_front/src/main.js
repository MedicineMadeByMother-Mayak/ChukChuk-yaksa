import { createApp } from "vue";
import { createPinia } from "pinia";
import Vue3TouchEvents from "vue3-touch-events";
import App from "./App.vue";

import router from "./router";
import "./assests/css/fonts.css";
import "./assests/css/carousel.css";

// fontawesome import
import { library } from "@fortawesome/fontawesome-svg-core";
import { fas } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
// import { loadFonts } from "./plugins/webfontloader";
// loadFonts();
library.add(fas);

const app = createApp(App);
app.component("font-awesome-icon", FontAwesomeIcon);
app.use(router);
app.use(Vue3TouchEvents);
app.use(createPinia());

app.mount("#app");
