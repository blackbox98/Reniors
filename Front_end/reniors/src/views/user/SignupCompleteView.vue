<template>
  <div class="container">
    <header>
      <img style="width: 40%; max-height: 170px; margin: 10px" src="@/assets/logo.png" alt="logo" >
    </header>
    <div class="completeMsg">
      <font-awesome-icon id="checkicon" icon="fa-regular fa-circle-check" />
      <p class="complete">회원가입 완료!</p>
    </div>
    <div>
      <button type="button"><router-link style="text-decoration:none; color: white;" :to="{ name: 'Login' }">로그인하러 가기</router-link></button>
    </div>
  </div>
</template>
<script>
export default {
  name: 'SignupCompleteView',
  components: {},
  data() {
    return {
      example: '',
    }
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {}
}
</script>
<style scoped>
header {
  height: 120px;
  border-style: none  none solid none;
  border-width: 0.5px;
  border-color: #EAEAEA;
}
  
.completeMsg {
  height: 60vh;
  padding-top: 10vh;
}

.complete {
  color: #37bf99;
  font-weight: bold;
  font-size: 24px;
  margin-top: 5vh;
}

#checkicon {
  font-size: 180px;
  color: #37bf9870;
}

button {    
    background-color: var(--color-red-2);
    width: 40vh;
    height: 40px;
    /* height: 40px; */
    border-radius: 8px;
    border: none;
    color: white;
    font-weight: bold;
    font-size: 18px;
    /* box-shadow: 0 4px 4px -1px rgba(0, 0, 0, 0.1), 0 2px 2px -1px rgba(0, 0, 0, 0.06); */
    cursor: pointer;
  }
</style>