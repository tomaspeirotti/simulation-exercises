import Vue from 'vue'
import App from './App.vue'
import VueGoodTablePlugin from 'vue-good-table';
import 'vue-good-table/dist/vue-good-table.css'
import BootstrapVue from 'bootstrap-vue'
import Vuelidate from 'vuelidate'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.use(Vuelidate)
Vue.use(BootstrapVue);
Vue.use(VueGoodTablePlugin);
Vue.config.productionTip = false

new Vue({
  render: h => h(App)
}).$mount('#app')
