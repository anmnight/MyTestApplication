<template>
  <div id="app">
    <img src="./assets/logo.png" v-on:click="test()">
    <HelloWorld/>
  </div>
</template>

<script>
import HelloWorld from './components/HelloWorld'

export default {
  name: 'app',
  methods:{
    test:function () {
      if(window.JavaBridge===undefined||window.JavaBridge===null){
        alert("device is null")
      }else {
        window.JavaBridge.onSynJavaScriptCall("test","test params");
      }
    }
  },
  components: {
    HelloWorld
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
