import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '@/views/Home.vue'
import Shopping from '@/views/Shopping.vue'
import AccountBook from '@/views/AccountBook.vue'
import Preparation from '@/views/Preparation.vue'
import Mypage from '@/views/Mypage.vue'
import Report from '@/views/Report.vue'
import Login from "@/components/Home/Login"
import Signup from "@/components/Home/Signup"
import PreparationMemo from '@/components/Preparation/PreparationMemo'
import ShoppingPhotoReg from '@/components/Shopping/ShoppingPhotoReg'
import ShoppingPhotoResponse from '@/components/Shopping/ShoppingPhotoResponse'
import NonMemberPreparationEnvironment from '@/components/NonMember/NonMemberPreparationEnvironment'


Vue.use(VueRouter)

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/login",
    name: "Login",
    component: Login,
  },
  {
    path: "/signup",
    name: "Signup",
    component: Signup,
  },
  {
    path: '/shop',
    name: Shopping,
    component: Shopping
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
    path: '/memo',
    name: PreparationMemo,
    component: PreparationMemo,
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
  {
    path: '/camera',
    name: ShoppingPhotoReg,
    component: ShoppingPhotoReg,
  },
  {
    path: '/camera/result',
    name: ShoppingPhotoResponse,
    component: ShoppingPhotoResponse,
  },
  {
    path: '/nonMember/perparation',
    name: NonMemberPreparationEnvironment,
    component: NonMemberPreparationEnvironment,
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
