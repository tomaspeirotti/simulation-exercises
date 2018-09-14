<template>
  <div id="app">
    <input type="button" class="btn btn-warning fixed-top m-3" value="Resetear simulacion" onClick="window.location.href=window.location.href">
    <!-- <b-row>
      
      <b-col class="px-4" cols="6">
        <b-row class="mb-2">
          <b-col sm="12" class="text-center"><h4>Configurar politica B</h4></b-col>
        </b-row>
        <b-row class="mb-2">
          <b-col sm="7" class="text-left"><label for="input-small">Cantidad de dias a simular:</label></b-col>
          <b-col sm="3">
            <b-form-input id="input-small" v-model="configPolicyB.daysToSimulate" size="sm" type="number"></b-form-input>
          </b-col>
        </b-row>
        <b-row class="mb-2">
          <b-col sm="7" class="text-left"><label for="input-small">Stock inicial:</label></b-col>
          <b-col sm="3">
            <b-form-input id="input-small" v-model="configPolicyB.initialStock" size="sm" type="number"></b-form-input>
          </b-col>
        </b-row>
        <b-row class="mb-2">
          <b-col sm="7" class="text-left"><label for="input-small">Cantidad Pedida:</label></b-col>
          <b-col sm="3">
            <b-form-input id="input-small" disabled v-model="configPolicyB.amountToOrder" size="sm" type="text"></b-form-input>
          </b-col>
        </b-row>
        <b-row class="mb-2">
          <b-col sm="7" class="text-left"><label for="input-small">El pedido se realiza cada (dias):</label></b-col>
          <b-col sm="3">
            <b-form-input id="input-small" v-model="configPolicyB.daysToOrder" size="sm" type="number"></b-form-input>
          </b-col>
        </b-row>
        <b-row class="mb-2">
          <b-col sm="7" class="text-left"><label for="input-small">Costo Almacenamiento por Unidad:</label></b-col>
          <b-col sm="3">
            <b-form-input id="input-small" v-model="configPolicyB.km" size="sm" type="number"></b-form-input>
          </b-col>
        </b-row>
        <b-row class="mb-2">
          <b-col sm="7" class="text-left"><label for="input-small">Costo de stock-out por Unidad:</label></b-col>
          <b-col sm="3">
            <b-form-input id="input-small" v-model="configPolicyB.ks" size="sm" type="number"></b-form-input>
          </b-col>
        </b-row>
        <b-form-group label="Realiza pedido el primer dia?">
          <b-form-radio-group v-model="configPolicyB.orderFirstDay" :options="optionsFirstDayB" name="radio">
          </b-form-radio-group>
        </b-form-group>
      </b-col>
    </b-row> -->
    <h2>Politica A</h2>
    <HelloWorld @clicked="toggleA" msg="Welcome to Your Vue.js App"/>
    <hr>
    <h2 class="mt-5">Politica B</h2>
    <Policyb @clicked="toggleB"/>
    <b-modal title="Politica Ganadora" id="winnerModal" ref="winnerModal">
      La Politica Ganadora es la politica {{winner}} por una diferencia de {{diff}}
    </b-modal>
  </div>
</template>

<script>
import HelloWorld from './components/HelloWorld.vue'
import Policyb from './components/Policyb.vue'

export default {
  name: 'app',
  data () {
    return {
      amountA: 0,
      amountB: 0,
      clickedA: false,
      clickedB: false,
      winner: '',
      diff: 0
    }
  },
  components: {
    HelloWorld,
    Policyb
  },
  methods: {
    toggleA (value) {
      this.amountA = value
      this.clickedA = true
      if (this.clickedB === true) {
        if (this.amountA > this.amountB) {
          this.winner = 'B'
          this.diff = this.amountA - this.amountB
        } else if (this.amountA < this.amountB) {
          this.winner = 'A'
          this.diff = this.amountB - this.amountA
        } else {
          this.winner = 'Empate'
          this.diff = 0
        }
        this.showWinner()
      }
    },
    toggleB (value) {
      this.amountB = value
      this.clickedB = true
      if (this.clickedA === true) {
        if (this.amountA > this.amountB) {
          this.winner = 'B'
          this.diff = this.amountA - this.amountB
        } else if (this.amountA < this.amountB) {
          this.winner = 'A'
          this.diff = this.amountB - this.amountA
        } else {
          this.winner = 'Empate'
          this.diff = 0
        }
        this.showWinner()
      }
    },
    showWinner () {
      this.$refs.winnerModal.show()
    }
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
