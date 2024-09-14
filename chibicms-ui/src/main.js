import { createApp } from 'vue'
import App from './App.vue'
import './index.css'
import "./assets/main.scss"
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import router from '@/router'
import {createPinia} from 'pinia'
import { createPersistedState } from 'pinia-persistedstate-plugin'
import locale from 'element-plus/dist/locale/zh-cn.js'
import MakeitCaptcha from 'makeit-captcha'
import 'makeit-captcha/dist/captcha.min.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

const app = createApp(App);
const pinia = createPinia();
const persist = createPersistedState();
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
  }

pinia.use(persist)
app.use(pinia)
app.use(router)
app.use(ElementPlus,{locale});
app.use(MakeitCaptcha)
app.mount('#app');


