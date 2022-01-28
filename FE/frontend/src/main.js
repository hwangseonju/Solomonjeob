// import Vue from 'vue'
// import App from './App.vue'
// import router from '@/routes/index'

// Vue.config.productionTip = false

// new Vue({
// 	render: h => h(App),
// 	router,
// }).$mount('#app')

import { createApp } from 'vue'
import App from '@/App.vue'
import router from '@/router'
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'
import store from '@/store.js'
const app = createApp(App)
app.use(router)
app.use(store).mount('#app')