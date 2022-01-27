import { createWebHistory, createRouter } from 'vue-router';
import Home from '@/views/Home.vue';
import Login from '@/views/Member/LoginPage.vue';
import Signup from '@/views/Member/SignupPage.vue';
import Question from '@/views/Question/QuestionPage.vue';
import NotFoundPage from '@/views/NotFoundPage.vue';
const routes = [
    {
        path: '/',
        component: Home,
    },
    {
        path: '/Home',
        component: Home,
    },
    {
        path: '/login',
        component: Login,
    },
    {
        path: '/signup',
        component: Signup,
    },
    {
        path: '/question',
        component: Question,
    },
    {
        path: '/:anything(.*)*', //  '/:catchAll(.*)*'
        component: NotFoundPage,
    },
    
]
const router = createRouter({
    history: createWebHistory(),
    routes
})
export default router