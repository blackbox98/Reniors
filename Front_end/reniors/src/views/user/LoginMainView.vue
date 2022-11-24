<template>
  <div class="list">
    <div class="loginbox">
      <img style="width: 60%" alt="logo" src="@/assets/logo.png" />
      <br />
      <div class="loginmenu">
        <div id="userlogin">
          <router-link :to="{ name: 'LoginUser' }">
            <button class="userbtn">개인 로그인</button>
          </router-link>
        </div>
        <div id="companylogin">
          <router-link :to="{ name: 'LoginCompany' }">
            <button class="companybtn">기업 로그인</button>
          </router-link>
        </div>
      </div>
      <br />
      <br />
      <p class="line m-0">혹시 계정이 없으신가요?</p>
      <br />
      <router-link class="link" style="color: #ff843e" :to="{ name: 'Signup' }">
        개인 회원가입
      </router-link>
      <router-link
        class="link"
        style="color: #37bf99"
        :to="{ name: 'companyregist' }"
      >
        기업 회원가입
      </router-link>
      <br />
      <div style="margin-top: 18px; margin-bottom: 25px">
        <img
          src="@/assets/kakaosignup.svg"
          style="width: 80%; height: 22px"
          alt="kakao"
          @click="kakaologin()"
        />
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "LoginMainView",
  components: {},
  data() {
    return {
      example: "",
    };
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {
    kakaologin() {
      window.location.replace(
        "https://kauth.kakao.com/oauth/authorize?client_id=" +
          process.env.VUE_APP_KAKAO_REST_API_KEY +
          "&redirect_uri=" +
          process.env.VUE_APP_KAKAO_REDIRECT_URI +
          "&response_type=code",
      );
    },
  },
};
</script>

<style scoped>
.list {
  padding-top: 11vh;
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}

.loginbox {
  width: 100%;
  justify-content: center;
  align-items: center;
  margin: 8px 0px;
}

.loginmenu {
  display: flex;
  justify-content: center;
  align-items: center;
}

.loginmenu > div {
  margin: 0px 5px;
}

.userbtn {
  width: auto;
  height: 200px;
  border: solid;
  background-color: #ff843e;
  border-radius: 10px;
  color: white;
  font-weight: bold;
  padding: 10px;
  font-size: 20px;
  border-style: none;
  box-shadow: rgba(0, 0, 0, 0.1) 0px 4px 12px;
}

.companybtn {
  width: auto;
  height: 200px;
  border: solid;
  background-color: #37bf99;
  border-radius: 10px;
  color: white;
  font-weight: bold;
  padding: 10px;
  font-size: 20px;
  border-style: none;
  box-shadow: rgba(0, 0, 0, 0.1) 0px 4px 12px;
}

.proposal {
  color: #6d6d6d;
  font-size: 16px;
}

.link {
  display: inline;
  margin: 8px;
  font-style: normal;
  font-size: 14px;
  line-height: 17px;
  color: #6d6d6d;
  text-decoration: none;
  font-weight: bold;
  font-size: 16px;
}

.line {
  flex-basis: 100%;
  margin: auto;
  color: rgba(0, 0, 0, 0.35);
  font-weight: 400;
  font-size: 13px;
  margin: 8px 0px;
  color: #8a8a8a;
}
</style>
