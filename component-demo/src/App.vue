<template>
  <div id="app">
    <Movie v-for="movie in movies" v-bind:key="movie.id" :title="movie.title" :rating="movie.rating"></Movie>
    <Hello></Hello>
  </div>
</template>

<script>
import Movie from './components/Movie.vue'
import Hello from './components/Hello.vue'

export default {
  name: 'App',
  components: {
    Movie,
    Hello
  },
  data: function(){
        return {
            movies: [
                {id: 1, title: '金刚狼1', rating: 8.7},
                {id: 2, title: '金刚狼2', rating: 8.8},
                {id: 3, title: '金刚狼3', rating: 8.9},
            ]
        }
    },
  created: function(){
      console.log("App Created")
    },
  mounted: function(){
    console.log("App Mounted")
  } 
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
