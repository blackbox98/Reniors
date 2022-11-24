<template>
  <div class="header-component">
    <router-link class="header-logo" :to="{ name: 'home' }">
      <img src="@/assets/logo.png" alt="logo">
    </router-link>
  </div>
</template>

<script>
export default {
  name: 'HeaderComponent',
}
</script>

<style scoped>
.header-component {
  display: flex;
  justify-content: center;
}

.header-logo {
  width: 100%;
  display: flex;
  justify-content: center;
}

.header-logo > img {
  width: 100%;
  object-fit: cover;
  padding: 0;
}
@media(min-width: 720px){
  .header-logo > img {
    width: 400px;
  }
}
</style>