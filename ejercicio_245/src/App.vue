<template>
  <div id="app">
    <h2>Boletería - Ejercicio 245</h2>
    <HelloWorld/>
  </div>
</template>

<script>
import HelloWorld from './components/HelloWorld.vue' 

export default {
  name: 'app',
  data () {
    return {}
  },
  components: {
    HelloWorld
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
