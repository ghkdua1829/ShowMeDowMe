import Vue from 'vue'
import App from './App.vue'
// Add vue router
import router from './router'
// Add vue vuetify
import vuetify from './plugins/vuetify';
// Add vuex
import store from './vuex/index'

Vue.config.productionTip = false

new Vue({
  router,
  vuetify,
  store,
  render: h => h(App)
}).$mount('#app')
