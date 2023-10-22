<template>
    <div>
        <h3>商品{{ $route.params.id }}</h3>
        <p>{{ id }}</p>
    </div>
</template>

<script>
export default {
    props: ["id"]
}
</script>
