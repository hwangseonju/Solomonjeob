// import Vue from 'vue'
// import VueRouter from 'vue-router'

// Vue.use(VueRouter)

// export default new VueRouter({
//     mode: 'history', // check
//     routes: [
// 		{
// 			path: '/',
// 			redirect: '/login',
// 		},
// 		{
// 			path: '/login',
// 			component: () => import('@/views/LoginPage.vue'),
// 		},
// 		{
// 			path: '/signup',
// 			component: () => import('@/views/SignupPage.vue'),
// 		},
// 		{
// 			path: '/:catchAll(.*)*',
// 			component: () => import('@/views/NotFoundPage.vue'),
// 		},
// 	],
// });
import { createWebHistory, createRouter } from 'vue-router'

const routes = [
	{
		path: '/',
		redirect: '/login',
	},
	{
		path: '/login',
		component: () => import('@/views/LoginPage.vue'),
	},
	{
		path: '/signup',
		component: () => import('@/views/SignupPage.vue'),
	},
	{
		path: '/:catchAll(.*)*',
		component: () => import('@/views/NotFoundPage.vue'),
	},
]
const router = createRouter({
	history: createWebHistory(),
	routes
})

export default router