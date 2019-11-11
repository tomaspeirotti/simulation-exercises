import Vue from 'vue'
import App from './App.vue'
import VueGoodTablePlugin from 'vue-good-table';
import 'vue-good-table/dist/vue-good-table.css'
import BootstrapVue from 'bootstrap-vue'
import Vuelidate from 'vuelidate'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import Axios from "axios";

Vue.use(Vuelidate)
Vue.use(BootstrapVue);
Vue.use(VueGoodTablePlugin);
Vue.use(Axios);
Vue.config.productionTip = false

new Vue({
  render: h => h(App)
}).$mount('#app')
