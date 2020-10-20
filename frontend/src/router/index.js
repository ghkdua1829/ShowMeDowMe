import Vue from 'vue'
import VueRouter from 'vue-router'
import Shopping from '@/views/Shopping.vue'
import User from '@/views/User.vue'
import AccountBook from '@/views/AccountBook.vue'
import Preparation from '@/views/Preparation.vue'
import Mypage from '@/views/Mypage.vue'
import Report from '@/views/Report.vue'
 
Vue.use(VueRouter)

const routes = [
  {
    path: '/shop',
    name: Shopping,
    component: Shopping
  },
  {
    path: '/user',
    name: User,
    component: User,
  },
  {
    path: '/accountbook',
    name: AccountBook,
    component: AccountBook,
  },
  {
    path: '/perparation',
    name: Preparation,
    component: Preparation,
  },
  {
    path: '/mypage',
    name: Mypage,
    component: Mypage,
  },
  {
    path: '/report',
    name: Report,
    component: Report,
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
