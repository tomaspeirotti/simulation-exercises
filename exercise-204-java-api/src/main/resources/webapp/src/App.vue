<template>
  <div id="app">
    <h2>Ejercicio 20</h2>
    <Simulation/>
  </div>
</template>

<script>
    import Simulation from './components/Simulation.vue'

    export default {
  name: 'app',
  data () {
    return {}
  },
  components: {
    Simulation
  },
  properties: {}
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
