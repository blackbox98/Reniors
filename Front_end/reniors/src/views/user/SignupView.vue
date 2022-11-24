<template>
  <div class="container">
    <header>
      <div>
        <br />
        <img
          style="width: 60%; max-height: 90px; margin: 10px"
          src="@/assets/logo_cut.png"
          alt="logo"
        />
        <br />
        <div style="float: right">
          <img
            class="order"
            v-if="page === 1"
            src="@/assets/one_active.svg"
            alt="order"
          />
          <img class="order" v-else src="@/assets/one.svg" alt="order" />
          <img
            class="order"
            v-if="page === 2"
            src="@/assets/two_active.svg"
            alt="order"
          />
          <img class="order" v-else src="@/assets/two.svg" alt="order" />
          <img
            class="order"
            v-if="page === 3"
            src="@/assets/three_active.svg"
            alt="order"
          />
          <img class="order" v-else src="@/assets/three.svg" alt="order" />
        </div>
      </div>
    </header>

    <div>
      <br />
      <div class="signupform">
        <transition name="slide-fade">
          <div v-show="page === 1" style="max-width: 351px">
            <p style="font-size: 14px">간단한 회원가입을 진행하려고 해요.</p>
            <p>
              먼저, 로그인 시 사용하실 <span>이메일</span>과
              <span>비밀번호</span>를 입력해주세요!
            </p>
            <br />
            <div>
              <p class="forminfo">
                이메일<span class="required">&nbsp;&nbsp;*</span>
              </p>
              <b-form-input
                class="mb-3 user-form-control"
                v-model="user.userAppId"
                type="email"
                placeholder="사용하실 이메일을 입력해주세요."
              ></b-form-input>
              <button
                class="check"
                :style="
                  user.userAppId
                    ? 'background-color: var(--color-green-1);'
                    : 'background-color: var(--color-green-3);'
                "
                @click="idcheck(user.userAppId)"
              >
                중복확인
              </button>
            </div>
            <br />
            <br />
            <div>
              <p class="forminfo">
                비밀번호<span class="required">&nbsp;&nbsp;*</span>
              </p>
              <b-form-input
                class="mb-3 user-form-control"
                v-model="user.userAppPwd"
                type="password"
                placeholder="비밀번호를 입력해주세요."
              ></b-form-input>
              <p class="forminfo">
                비밀번호 확인<span class="required">&nbsp;&nbsp;*</span>
              </p>
              <b-form-input
                class="mb-3 user-form-control"
                v-model="password"
                type="password"
                placeholder="비밀번호를 한번 더 입력해주세요."
              ></b-form-input>
            </div>
          </div>
        </transition>
        <transition name="slide-fade">
          <div v-show="page === 2" style="max-width: 351px">
            <p>
              회원님의 <span>이름</span>과 <span>전화번호</span>,
              <span>생년월일</span>, <span>주소</span>를 알려주실 수 있나요?
            </p>
            <br />
            <p class="forminfo">
              이름<span class="required">&nbsp;&nbsp;*</span>
            </p>
            <b-form-input
              class="mb-3 user-form-control"
              v-model="user.name"
              type="text"
              placeholder="이름을 입력해주세요."
            ></b-form-input>
            <p class="forminfo">
              휴대전화<span class="required">&nbsp;&nbsp;*</span>
            </p>
            <b-form-input
              class="mb-3 user-form-control"
              v-model="user.phone"
              type="text"
              placeholder="예시) 01012345678"
            ></b-form-input>
            <p class="forminfo">생년월일</p>
            <b-form-input
              class="mb-3 user-form-control"
              v-model="user.birth"
              type="date"
              placeholder="생년-월-일"
            ></b-form-input>
            <p class="forminfo">
              주소<span class="required">&nbsp;&nbsp;*</span>
            </p>
            <div class="address">
              <b-form-input
                class="mb-3 user-form-control"
                style="width: 90%"
                v-model="user.address"
                type="text"
                placeholder="주소 검색"
              ></b-form-input>
              <button
                class="search"
                style="margin-bottom: 16px"
                @click="execDaumPostcode()"
                type="button"
                value="우편번호 찾기"
              >
                <img src="@/assets/searching.png" alt="search" />
              </button>
            </div>
            <b-form-input
              class="mb-3 user-form-control"
              v-model="user.extraAddress"
              type="text"
              placeholder="상세주소를 입력해주세요"
            ></b-form-input>
          </div>
        </transition>
        <transition name="slide-fade">
          <div v-show="page === 3">
            <p style="font-size: 14px">마지막 단계입니다!</p>
            <p>
              <span>최종학력</span>, <span>경력년수</span>, <span>성별</span>을
              입력해주세요! <br /><span>프로필 사진</span>도 넣어주시면 좋구요
              :)
            </p>

            <br />
            <p class="forminfo">
              최종학력<span class="required">&nbsp;&nbsp;*</span>
            </p>
            <b-form-select
              class="mb-3 user-form-control"
              v-model="user.lastEdu"
            >
              <option
                v-for="lastedu in lastedus"
                :value="lastedu.value"
                :key="lastedu"
              >
                {{ lastedu.text }}
              </option></b-form-select
            >
            <p class="forminfo">
              경력년수<span class="required">&nbsp;&nbsp;*</span>
            </p>
            <b-form-input
              class="mb-3 user-form-control"
              v-model="user.totalCareer"
              type="number"
              placeholder="경력년수를 입력해주세요."
            ></b-form-input>
            <p class="forminfo">
              성별<span class="required">&nbsp;&nbsp;*</span>
            </p>
            <b-form-select
              class="mb-3 user-form-control"
              v-model="user.gender"
              :options="gender"
            ></b-form-select>
            <div class="mb-3 mt-3">
              <p class="forminfo">프로필 사진</p>
              <input
                type="file"
                class="form-control user-form-control"
                placeholder="이미지를 선택해주세요"
                ref="img"
                @change="changeImg()"
              />
            </div>
          </div>
        </transition>
        <footer style="width: 312px">
          <button
            style="background-color: var(--color-red-3)"
            type="button"
            v-show="page === 1"
          >
            <router-link
              style="text-decoration: none; color: white"
              :to="{ name: 'Login' }"
              >이전</router-link
            >
          </button>
          <button
            style="background-color: var(--color-red-1)"
            type="button"
            v-show="page !== 1"
            @click="decreasePage"
          >
            이전
          </button>
          <button type="button" v-show="page !== 3" @click="increasePage">
            다음
          </button>
          <button @click="regist()" v-show="page === 3">완료!</button>
        </footer>
      </div>
    </div>
  </div>
</template>
<script>
import { mapActions, mapState } from "vuex";
import { useToast } from "bootstrap-vue-3";
import axios from "axios";
import drf from "@/api/drf";

export default {
  name: "SignupView",
  components: {},
  data() {
    return {
      user: {
        userAppId: "",
        userAppPwd: "",
        name: "",
        phone: "",
        address: "",
        extraAddress: "",
        lastEdu: null,
        birth: "",
        gender: "",
        isOpen: "CLOSE",
        kakaoId: "",
        profileImgName: "",
        profileImgPath: "",
        totalCareer: 0,
      },
      gender: [
        { value: "공개안함", text: "성별을 선택해주세요." },
        { value: "M", text: "남자" },
        { value: "F", text: "여자" },
        { value: "공개안함", text: "공개안함" },
      ],
      page: 1,
      password: "",
      userImg: "",
      idconfirm: false,
    };
  },
  setup() {
    const toast = useToast();

    return {
      toast,
    };
  },
  created() {},
  mounted() {},
  unmounted() {},
  computed: {
    ...mapState("category", ["lastedus"]),
  },
  methods: {
    ...mapActions(["registUser"]),
    changeImg() {
      this.userImg = this.$refs.img.files;
    },
    regist() {
      if (this.user.userAppId == "") {
        this.toast.show(
          { body: "사용하실 이메일을 입력해주세요." },
          { variant: "success", pos: "middle-center", delay: 1000 },
        );
      } else if (this.user.userAppPwd == "") {
        this.toast.show(
          { body: "사용하실 패스워드를 입력해주세요." },
          { variant: "success", pos: "middle-center", delay: 1000 },
        );
      } else if (this.user.userAppPwd != this.password) {
        this.toast.show(
          { body: "비밀번호를 다시 확인해주세요." },
          { variant: "success", pos: "middle-center", delay: 1000 },
        );
      } else if (this.user.name == "") {
        this.toast.show(
          { body: "이름을 입력해주세요." },
          { variant: "success", pos: "middle-center", delay: 1000 },
        );
      } else if (this.user.phone == "") {
        this.toast.show(
          { body: "전화번호를 입력해주세요." },
          { variant: "success", pos: "middle-center", delay: 1000 },
        );
      } else if (this.user.address == "") {
        this.toast.show(
          { body: "주소를 입력해주세요." },
          { variant: "success", pos: "middle-center", delay: 1000 },
        );
      } else if (this.user.gender == "") {
        this.toast.show(
          { body: "성별을 선택해주세요." },
          { variant: "success", pos: "middle-center", delay: 1000 },
        );
      } else if (this.user.totalCareer == null) {
        this.toast.show(
          { body: "경력년수를 입력해주세요." },
          { variant: "success", pos: "middle-center", delay: 1000 },
        );
      } else if (this.idconfirm == false) {
        this.toast.show(
          { body: "아이디를 확인해주세요." },
          { variant: "success", pos: "middle-center", delay: 1000 },
        );
      } else {
        const formData = new FormData();
        formData.append("img", this.userImg[0]);
        formData.append(
          "data",
          new Blob([JSON.stringify(this.user)], {
            type: "application/json",
          }),
        );
        this.registUser(formData);
      }
    },
    idcheck(id) {
      if (this.user.userAppId.includes("@")) {
        axios({
          url: drf.user.idcheck(id),
          method: "get",
        })
          .then((res) => {
            if (res.data.res) {
              this.toast.show(
                { body: "이미 사용중인 아이디입니다." },
                { variant: "danger", pos: "middle-center", delay: 1000 },
              );
              this.idconfirm = false;
            } else {
              this.toast.show(
                { body: "사용 가능한 아이디입니다." },
                { variant: "success", pos: "middle-center", delay: 1000 },
              );
              this.idconfirm = true;
            }
          })
          .catch((err) => {
            console.log(err);
          });
      } else {
        this.toast.show(
          { body: "올바른 이메일 형식이 아닙니다." },
          { variant: "success", pos: "middle-center", delay: 1000 },
        );
      }
    },

    signup() {
      axios({
        url: "https://i7b307.p.ssafy.io/api/users/regist",
        method: "post",
        data: this.user,
      })
        .then(() => {
          this.$router.push({ name: "Login" });
        })
        .catch((err) => {
          console.log(err);
        });
    },
    increasePage() {
      this.page += 1;
    },
    decreasePage() {
      this.page -= 1;
    },
    execDaumPostcode() {
      new window.daum.Postcode({
        oncomplete: (data) => {
          if (this.user.extraAddress !== "") {
            this.user.extraAddress = "";
          }
          if (data.userSelectedType === "R") {
            // 사용자가 도로명 주소를 선택했을 경우
            this.user.address = data.roadAddress;
          } else {
            // 사용자가 지번 주소를 선택했을 경우(J)
            this.user.address = data.jibunAddress;
          }

          // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
          if (data.userSelectedType === "R") {
            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
              this.user.extraAddress += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if (data.buildingName !== "" && data.apartment === "Y") {
              this.user.extraAddress +=
                this.user.extraAddress !== ""
                  ? `, ${data.buildingName}`
                  : data.buildingName;
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if (this.user.extraAddress !== "") {
              this.user.extraAddress = `(${this.user.extraAddress})`;
            }
          } else {
            this.user.extraAddress = "";
          }
        },
      }).open();
    },
  },
};
</script>

<style scoped>
.check {
  background-color: var(--color-green-3);
  width: 90px;
  height: 38px;
  border-radius: 10px;
  color: white;
  font-weight: 600;
  font-size: 14px;
  border-style: none;
  box-shadow: rgba(60, 64, 67, 0.3) 0px 1px 2px 0px,
    rgba(60, 64, 67, 0.15) 0px 1px 3px 1px;
  float: right;
}

header {
  height: 130px;
  border-style: none none solid none;
  border-width: 0.5px;
  border-color: #eaeaea;
}

span {
  color: var(--color-green-1);
  font-size: 17px;
}

p {
  color: #0f0f0f;
  font-weight: 800;
  font-size: 16px;
  text-align: left;
}

.order {
  margin: 2px;
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

.signupform {
  display: flex;
  flex-direction: column;
  align-items: center;
  height: 600px;
}

.address {
  display: flex;
  justify-content: space-between;
}

.search {
  border: 0px;
  background-color: transparent;
}

footer > button {
  background-color: var(--color-red-1);
  width: 45%;
  height: 80%;
  border-radius: 10px;
  border: none;
  color: white;
  font-weight: bold;
  font-size: 18px;
  cursor: pointer;
}

footer {
  position: fixed;
  transform: translate(-50%, 0);
  bottom: 60px;
  left: 50%;
  width: 100%;
  height: 50px;
  display: flex;
  justify-content: space-between;
}

.user-form-control:focus {
  border-color: var(--color-red-2) !important;
  box-shadow: inset 0 1px 1px var(--color-red-1), 0 0 8px var(--color-red-2) !important;
}

.slide-fade-enter-active {
  position: absolute;
  transition: all 0.3s ease-in;
}

.slide-fade-leave-active {
  position: absolute;
  transition: all 0.3s ease-out;
}

.slide-fade-enter-from,
.slide-fade-leave-to {
  transform: translateX(20px);
  opacity: 0;
}

input[type="date"]::-webkit-datetime-edit-text,
input[type="date"]::-webkit-datetime-edit-month-field,
input[type="date"]::-webkit-datetime-edit-day-field,
input[type="date"]::-webkit-datetime-edit-year-field {
  color: #8a8a8a;
}

/* .user-form-control{
  color: #8a8a8a;
} */
</style>
