<template>
  <div class="header">
        <router-link class="header-logo" :to="{ name: 'home' }">
            <img src="@/assets/logo_cut.png" alt="logo">
        </router-link>
    </div>
</template>

<script>
export default {
  name: 'HeaderComponent',
}
</script>

<style scoped>
.header{
    width: 100%;
    height: 48px;
    background-color: white;
    box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px -2px;
    display: flex;
    align-items: center;
    justify-content: center;
    position: fixed;
    top: 0;
    z-index: 1;
}
.header img{
    height: 38px;
    margin: auto;
    width: auto;
}
</style>