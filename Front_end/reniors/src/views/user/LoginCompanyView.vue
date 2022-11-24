<template>
  <div class="container">
    <div class="loginform">
      <img
        style="width: 80%; max-height: 170px"
        alt="logo"
        src="@/assets/logo.png"
      />
      <br />
      <form style="width: 312px" @submit.prevent="companylogin(companyinfo)">
        <!-- <form style="width: 312px;"> -->
        <b-form-input
          style="width: 100%; height: 48px"
          class="mb-2 company-form-control"
          v-model="companyinfo.companyAppId"
          type="text"
          placeholder="아이디"
          required
        ></b-form-input>
        <b-form-input
          style="width: 100%; height: 48px"
          class="mb-4 company-form-control"
          v-model="companyinfo.companyAppPwd"
          type="password"
          placeholder="비밀번호"
          required
        ></b-form-input>
        <button
          id="CLoginBtn"
          v-bind:disabled="
            companyinfo.companyAppId && companyinfo.companyAppPwd == ''
          "
        >
          로그인
        </button>
      </form>
      <br />
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
  name: "LoginCompanyView",
  components: {},
  data() {
    return {
      companyinfo: {
        companyAppId: "",
        companyAppPwd: "",
      },
    };
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {
    ...mapActions("company", ["companylogin"]),
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

#CLoginBtn {
  background-color: #37bf99;
  width: 100%;
  height: 40px;
  border-radius: 10px;
  border: none;
  color: white;
  font-weight: bold;
  font-size: 18px;
  cursor: pointer;
}

#CLoginBtn:disabled {
  background-color: #8cd6c1;
  width: 100%;
  height: 40px;
  border-radius: 10px;
  border: none;
  color: white;
  font-weight: bold;
  font-size: 18px;
  cursor: pointer;
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

.company-form-control:focus {
  border-color: var(--color-green-2) !important;
  box-shadow: inset 0 1px 1px var(--color-green-1), 0 0 8px var(--color-green-2) !important;
}
</style>
