import Vue from 'vue'
import VueRouter from 'vue-router'
import Shopping from '@/views/Shopping.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/shop',
    name: Shopping,
    component: Shopping
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
