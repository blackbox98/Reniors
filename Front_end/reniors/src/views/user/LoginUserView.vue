<template>
  <div class="container">
    <div class="loginform">
      <img
        style="width: 80%; max-height: 170px"
        alt="logo"
        src="@/assets/logo.png"
      />
      <br />
      <form style="width: 312px">
        <b-form-input
          style="width: 100%; height: 48px"
          class="mb-2 user-form-control"
          v-model="credentials.userAppId"
          type="text"
          placeholder="아이디"
          required
        ></b-form-input>
        <b-form-input
          style="width: 100%; height: 48px"
          class="mb-4 user-form-control"
          v-model="credentials.userAppPwd"
          type="password"
          placeholder="비밀번호"
          required
        ></b-form-input>
        <button
          id="LoginBtn"
          v-bind:disabled="
            credentials.userAppId && credentials.userAppPwd == ''
          "
          @click="loginUser()"
        >
          로그인
        </button>
      </form>
      <br />
      <p class="line">또는</p>
      <div style="margin-top: 10px; margin-bottom: 25px">
        <img
          src="@/assets/kakaologin.svg"
          style="width: 80%"
          alt="kakao"
          @click="kakaologin()"
        />
      </div>
      <div style="margin: 10px">
        <router-link class="link" :to="{ name: 'FindUsername' }"
          >아이디 찾기</router-link
        >
        <router-link class="link" :to="{ name: 'FindPassword' }"
          >비밀번호 찾기
        </router-link>
      </div>
    </div>
  </div>
</template>
<script>
import { mapActions } from "vuex";

export default {
  name: "LoginUserView",
  components: {},
  data() {
    return {
      credentials: {
        userAppId: "",
        userAppPwd: "",
      },
    };
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {
    ...mapActions(["login"]),
    kakaologin() {
      window.location.replace(
        "https://kauth.kakao.com/oauth/authorize?client_id=" +
          process.env.VUE_APP_KAKAO_REST_API_KEY +
          "&redirect_uri=" +
          process.env.VUE_APP_KAKAO_REDIRECT_URI +
          "&response_type=code",
      );
    },
    async loginUser(){
      await this.login(this.credentials);
      this.$router.push("/home");
    },
  },
};
</script>

<style scoped>
.loginform {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  min-height: 80vh;
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
  cursor: pointer;
}

#LoginBtn:disabled {
  background-color: #ffc0a3;
  width: 100%;
  height: 40px;
  border-radius: 10px;
  border: none;
  color: white;
  font-weight: bold;
  font-size: 18px;
  cursor: pointer;
}

.line {
  display: flex;
  flex-basis: 100%;
  align-items: center;
  color: rgba(0, 0, 0, 0.35);
  font-weight: 400;
  font-size: 13px;
  margin: 8px 0px;
  color: #8a8a8a;
}

.line::before,
.line::after {
  content: "";
  flex-grow: 1;
  background: rgba(0, 0, 0, 0.35);
  height: 1px;
  font-size: 0px;
  line-height: 0px;
  margin: 0px 16px;
}

.link {
  display: inline;
  margin: 8px;
  font-style: normal;
  font-size: 14px;
  line-height: 17px;
  color: #6d6d6d;
  text-decoration: none;
}
.user-form-control:focus {
  border-color: var(--color-red-2) !important;
  box-shadow: inset 0 1px 1px var(--color-red-1), 0 0 8px var(--color-red-2) !important;
}
</style>
