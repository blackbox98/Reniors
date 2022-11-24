<template>
    <div class="header">
      <i @click="prevpage()" class="bi bi-arrow-left-circle-fill"></i>
      <router-link class="header-logo" :to="{ name: 'home' }">
        <img src="@/assets/logo_cut.png" alt="logo" />
      </router-link>
      <div></div>
    </div>
  <div>
    <header>
      <div style="margin-top: 50px; padding: 10px; background-color: #f9f9f9">
        <span class="title">내 정보 수정</span>
      </div>
    </header>
    <br />

    <div style="width: 312px; margin: auto">
      <button @click="deleteNow" class="delete">회원 탈퇴하기</button>
      <br />
      <br />
      <p class="forminfo">이메일<span class="required">&nbsp;&nbsp;*</span></p>
      <b-form-input
        v-if="currentUser.kakaoId === ''"
        class="mb-3"
        type="email"
        v-model="currentUser.userAppId"
        disabled
      ></b-form-input>
      <b-form-input
        v-else
        class="mb-3"
        type="email"
        v-model="currentUser.kakaoId"
        disabled
      ></b-form-input>
      <p class="forminfo">
        비밀번호<span class="required">&nbsp;&nbsp;*</span>
      </p>
      <form @submit.prevent="changeTest(password)">
        <div style="display: flex; justify-content: space-between">
          <b-form-input
            class="mb-3 user-form-control"
            style="width: 80%"
            type="password"
            v-model="password"
            placeholder="새 비밀번호를 입력해주세요."
          ></b-form-input>
          <button class="change">변경</button>
        </div>
      </form>
      <b-form-input
        class="mb-3"
        type="password"
        v-model="password2"
        placeholder="새 비밀번호를 한번 더 입력해주세요."
      ></b-form-input>

      <resume-basic-form
        :currentUser="currentUser"
        action="info"
      ></resume-basic-form>
    </div>
  </div>
</template>
<script>
import { mapActions, mapGetters } from "vuex";
import ResumeBasicForm from "@/components/resume/ResumeBasicForm.vue";
export default {
  name: "MyinfoEdit",
  components: { ResumeBasicForm },
  data() {
    return {
      example: "",
      password: "",
      password2: "",
    };
  },
  computed: {
    ...mapGetters(["currentUser", "isLogginedIn"]),
  },
  setup() {},
  created() {
    this.fetchCurrentUser();
  },
  mounted() {},
  unmounted() {},
  methods: {
    ...mapActions(["fetchCurrentUser", "changePassword", "deleteUser"]),
    changeTest(data) {
      if (this.password != this.password2) {
        alert("변경하실 비밀번호를 다시 확인해주세요.");
      } else {
        this.changePassword(data);
      }
    },
    deleteNow() {
      var result = confirm("정말 리니어즈를 떠나실 건가요?");
      if (result) {
        this.deleteUser();
      }
    },
    prevpage() {
      this.$router.go(-1);
    },
  },
};
</script>

<style scoped>
.header {
  height: 48px;
  background-color: white;
  box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px -2px;
  display: flex;
  align-items: center;
  justify-content: space-around;
  position: fixed;
  width: 100vw;
  top: 0;
}
.header img {
  height: 38px;
  margin: auto;
  width: auto;
}
.header > .bi {
  color: var(--color-red-1);
  font-size: 30px;
  cursor: pointer;
}
.header > div {
  width: 30px;
}
.title {
  font-size: 22px;
  margin-top: 4px;
  color: #ffb400;
  font-weight: 900;
}

p {
  text-align: left;
}

.required {
  font-size: 13px;
  color: var(--color-red-1);
}

.forminfo {
  color: #8a8a8a;
  font-weight: 400;
  font-size: 13px;
  margin-bottom: 5px;
}

.change {
  background-color: #8cd6c1;
  height: 38px;
  border-radius: 10px;
  /* border-color: var(--color-red-2); */
  color: white;
  font-weight: 600;
  border-style: none;
  box-shadow: rgba(60, 64, 67, 0.3) 0px 1px 2px 0px,
    rgba(60, 64, 67, 0.15) 0px 1px 3px 1px;
}

.user-form-control:focus {
  border-color: var(--color-red-2) !important;
  box-shadow: inset 0 1px 1px var(--color-red-1), 0 0 8px var(--color-red-2) !important;
}

.delete {
  background-color: transparent;
  border-style: none;
  color: #37bf99;
  float: right;
  font-size: 14px;
}
</style>
