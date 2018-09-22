<template>
  <div id="app">
    <input type="button" class="btn btn-warning fixed-top m-3" value="Restart" onClick="window.location.href=window.location.href">
    <h2>IMPRENTA</h2>
    <HelloWorld @clicked="toggleA" msg="Welcome to Your Vue.js App"/>
    <hr>
    <b-modal title="Politica Ganadora" id="winnerModal" ref="winnerModal">
      La Politica Ganadora es la politica {{winner}} por una diferencia de {{diff}}
    </b-modal>
  </div>
</template>

<script>
import HelloWorld from './components/HelloWorld.vue'

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
    HelloWorld
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
