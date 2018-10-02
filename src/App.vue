<template>
  <div id="app">
    <input type="button" class="btn btn-warning fixed-top m-3" value="Restart" onClick="window.location.href=window.location.href">
    <h2>IMPRENTA</h2>
    <HelloWorld msg="Welcome to Your Vue.js App"/>
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
  properties: {

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
