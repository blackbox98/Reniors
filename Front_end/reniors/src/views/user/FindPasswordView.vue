<template>
  <div class="container">
    <div class="findform">
      <img
        style="width: 80%; max-height: 170px"
        alt="logo"
        src="@/assets/logo.png"
      />
      <p style="font-weight: 1000">비밀번호 찾기</p>
      <p style="font-size: 14px; font-weight: 400; color: #6d6d6d">
        이름과 이메일을 입력하면 임시 비밀번호를 보내드립니다.
      </p>
      <br />
      <form style="width: 312px" @submit.prevent="findPwd(credentials)">
        <!-- <form style="width: 312px;"> -->
        <b-form-input
          style="width: 100%; height: 48px"
          class="mb-2 user-form-control"
          v-model="credentials.name"
          type="text"
          placeholder="이름을 입력해주세요"
          required
        ></b-form-input>
        <b-form-input
          style="width: 100%; height: 48px"
          class="mb-4 user-form-control"
          v-model="credentials.userAppId"
          type="text"
          placeholder="이메일을 입력해주세요"
          required
        ></b-form-input>
        <button
          id="LoginBtn"
          v-bind:disabled="credentials.name && credentials.userAppId == ''"
        >
          비밀번호 찾기
        </button>
      </form>
    </div>
  </div>
</template>
<script>
import { mapActions } from "vuex";
export default {
  name: "FindPasswordView",
  components: {},
  data() {
    return {
      credentials: {
        name: "",
        userAppId: "",
      },
    };
  },
  computed: {},
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {
    ...mapActions(["findPwd"]),
  },
};
</script>

<style scoped>
.findform {
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
  /* box-shadow: 0 4px 4px -1px rgba(0, 0, 0, 0.1), 0 2px 2px -1px rgba(0, 0, 0, 0.06); */
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
  /* box-shadow: 0 4px 4px -1px rgba(0, 0, 0, 0.1), 0 2px 2px -1px rgba(0, 0, 0, 0.06); */
  cursor: pointer;
}

.user-form-control:focus {
  border-color: var(--color-red-2) !important;
  box-shadow: inset 0 1px 1px var(--color-red-1), 0 0 8px var(--color-red-2) !important;
}
</style>
