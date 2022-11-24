<template>
    <router-link class="home-info" :to="{ name: 'Jobopening' }">
      <p>시니어를 위한 공고 보러가기 <i class="bi bi-arrow-right-square-fill"></i></p>
    </router-link>
</template>

<script>
export default {
  name: 'HomeInfo',
}
</script>

<style scoped>
.home-info {
  background:linear-gradient(
    90deg,
    var(--color-red-3) -12%,
    var(--color-red-2) 37%,
    var(--color-orange-1) 72%,
    var(--color-orange-2) 110%
  );
  width: 100%;
  display: flex;
  justify-content: center;
  align-content: center;
  text-decoration: none;
  margin-bottom: 3rem;
}
p{
  margin: 0;
  color: white;
  font-size: 20px;
  font-weight: bold;
  margin-right: 5px;
}
.bi-arrow-right-square-fill{
  font-size: 26px;
  color: white;
}
@media(max-width:760px){
  .home-info{
    display: block;
    padding: 10px;
    width: 100%;
    margin-bottom: 24px;
  }
}
</style>