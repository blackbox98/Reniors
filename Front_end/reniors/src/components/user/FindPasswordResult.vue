<template>
  <div class="container">
    <div class="findform">
			<img style="width: 80%; max-height: 170px;" alt="logo" src="@/assets/logo.png" />
      
			<p style="font-weight: 1000; margin-bottom: 16px">비밀번호 찾기</p>

      <p style="font-size: 14px; font-weight: 400; color: #6D6D6D;">임시 비밀번호가 이메일로 전송되었습니다.</p>
      <!-- <p style="font-weight: 700;">{{ this.id }}</p>
      <p style="font-size: 14px; font-weight: 400; color: #6D6D6D; ">입니다.</p> -->

      
      <button id="LoginBtn" style="margin-top: 40px;">
        <router-link style="text-decoration:none; color: white;" :to="{ name:'LoginUser' }">
          로그인하러 가기
        </router-link>
      </button>
      


    </div>

  </div>
</template>
<script>
// import { mapGetters } from 'vuex'
export default {
  name: 'FindPasswordResult',
  components: {},
  data() {
    return {
      example: '',
    }
  },
  computed: {
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {}
}
</script>


<style scoped>
.findform {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  min-height: 80vh;
}
p {
  margin-bottom: 5px;
}

#LoginBtn {
  background-color: var(--color-red-2);
  width: 100%;
  height: 40px;
  border-radius: 10px;
  border: none;
  color: white;
  font-weight: bold;
  font-size: 18px;
  /* box-shadow: 0 4px 4px -1px rgba(0, 0, 0, 0.1), 0 2px 2px -1px rgba(0, 0, 0, 0.06); */
  cursor: pointer;
}

</style>