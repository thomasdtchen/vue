<template>
  <div>
    <!-- {{ this.$store.state.count }} -->
    {{ count }}
    <button @click="add">+1</button>

    <ul>
      <!-- <li v-for="todo in todos" :key="todo.id">{{ todo.text }}</li> -->
      <li v-for="todo in doneTodos" :key="todo.id">{{ todo.text }}</li>
    </ul>
  </div>
</template>

<script>
import { mapGetters, mapState } from 'vuex';




export default {
  // computed:{
  //   count(){
  //     return this.$store.state.count
  //   }
  // },
  // computed: mapState([
  //   //映射 this.count 为 $store.state.count
  //   'count', 'todos'
  // ]),
  computed:{
    ...mapState([
      'count', 'todos'
    ]),
    ...mapGetters([
      'doneTodos'
    ])
  },
  methods:{
    add(){
      //this.$store.state.count += 1
      this.$store.commit("increment", 10)
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
