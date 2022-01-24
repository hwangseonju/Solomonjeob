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
const app = createApp(App)
app.use(router)
app.mount('#app')