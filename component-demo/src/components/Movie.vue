<template>
    <div>
        <h1> {{ title }} </h1>
        <h2> {{ rating }}</h2>
        <button @click="collect">Movie Collect</button>

    </div>
</template>

<script>
export default {
    props: ["title", "rating"],
    created: function(){
        console.log("Movie Created")
    },
    methods: {
        collect(){
            alert("Collect successful")
        }
    }
}
</script>