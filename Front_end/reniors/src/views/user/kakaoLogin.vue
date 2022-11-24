<template>
  <div class="container">
    <div class="kakaoform">
      <br>
      <img
        style="width: 60%; max-height: 90px; margin: 10px"
        alt="logo"
        src="@/assets/logo_cut.png"
      />
      <br />
      <hr />
      <div>
        <p style="font-size: 14px">
          리니어즈 이용을 위한 추가정보를 입력해주세요!
        </p>

        <p class="forminfo">이름<span class="required">&nbsp;&nbsp;*</span></p>
        <b-form-input
          class="mb-3 user-form-control"
          v-model="forms.name"
          type="text"
          placeholder="이름을 입력해주세요."
        ></b-form-input>
        <p class="forminfo">
          휴대전화<span class="required">&nbsp;&nbsp;*</span>
        </p>
        <b-form-input
          class="mb-3 user-form-control"
          v-model="forms.phone"
          type="text"
          placeholder="예시)01012345678"
        ></b-form-input>
        <p class="forminfo">주소<span class="required">&nbsp;&nbsp;*</span></p>
        <div class="address">
          <b-form-input
            class="mb-3 user-form-control"
            style="width: 90%"
            v-model="forms.address"
            type="text"
            placeholder="주소 검색"
            readonly
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
          v-model="forms.extraAddress"
          type="text"
          placeholder="상세주소를 입력해주세요"
        ></b-form-input>
        <p class="forminfo">
          최종학력<span class="required">&nbsp;&nbsp;*</span>
        </p>
        <b-form-select class="mb-3 user-form-control" v-model="forms.lastEdu">
          <option
            v-for="lastedu in lastedus"
            :value="lastedu.value"
            :key="lastedu"
          >
            {{ lastedu.text }}
          </option></b-form-select>
        <p class="forminfo">경력년수<span class="required">&nbsp;&nbsp;*</span></p>
        <b-form-input
          class="mb-3 user-form-control"
          v-model="forms.totalCareer"
          type="number"
          placeholder="경력년수를 입력해주세요."
        ></b-form-input>
        <p class="forminfo">생년월일</p>
        <b-form-input
          class="mb-3 user-form-control"
          v-model="forms.birth"
          type="date"
          placeholder="생년-월-일"
        ></b-form-input>
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
        <button id="LoginBtn" @click="kregist()">완료!</button>
      </div>
    </div>
  </div>
</template>
<script>
import { mapActions, mapState } from "vuex";
import axios from "axios";

export default {
  name: "kakaoLogin",
  components: {},
  data() {
    return {
      codes: "",
      forms: {
        name: "",
        phone: "",
        address: "",
        extraAddress: "",
        lastEdu: null,
        birth: "",
        gender: "",
        isOpen: "CLOSE",
        kakaoId: "",
        totalCareer: 0,
        userProfile: "",
      },
      info: {
        email: "",
        gender: "",
      },
      userImg: "",
    };
  },
  setup() {},
  created() {
    this.create();
  },
  computed: {
    ...mapState("category", ["lastedus"]),
  },
  mounted() {},
  unmounted() {},
  methods: {
    ...mapActions(["kakaologin", "kakaoRegist"]),
    changeImg() {
      this.userImg = this.$refs.img.files;
    },
    create() {
      this.codes = this.$route.query.code;
      this.getInfo();
    },
    getInfo() {
      axios
        .get(
          "https://i7b307.p.ssafy.io/api/users/kakao/callback?code=" +
            this.codes,
        )
        .then((res) => {
          this.forms.kakaoId = res.data.kakaoUserInfo.email;
          this.forms.gender = res.data.kakaoUserInfo.gender;
          this.info.email = res.data.kakaoUserInfo.email;
          this.info.gender = res.data.kakaoUserInfo.gender;
          this.check(this.info);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    check(info) {
      axios({
        url: "https://i7b307.p.ssafy.io/api/users/kakao/check",
        method: "post",
        data: JSON.stringify(info),
      })
        .then((res) => {
          if (res.data.res) {
            this.kakaologin(this.info);
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },

    kregist() {
      const formData = new FormData();
      formData.append("img", this.userImg[0]);
      formData.append(
        "data",
        new Blob([JSON.stringify(this.forms)], { type: "application/json" }),
      );

      this.kakaoRegist(formData);
    },

    execDaumPostcode() {
      new window.daum.Postcode({
        oncomplete: (data) => {
          if (this.forms.extraAddress !== "") {
            this.forms.extraAddress = "";
          }
          if (data.userSelectedType === "R") {
            // 사용자가 도로명 주소를 선택했을 경우
            this.forms.address = data.roadAddress;
          } else {
            // 사용자가 지번 주소를 선택했을 경우(J)
            this.forms.address = data.jibunAddress;
          }

          // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
          if (data.userSelectedType === "R") {
            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
              this.forms.extraAddress += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if (data.buildingName !== "" && data.apartment === "Y") {
              this.forms.extraAddress +=
                this.forms.extraAddress !== ""
                  ? `, ${data.buildingName}`
                  : data.buildingName;
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if (this.forms.extraAddress !== "") {
              this.forms.extraAddress = `(${this.forms.extraAddress})`;
            }
          } else {
            this.forms.extraAddress = "";
          }
        },
      }).open();
    },
  },
};
</script>
<style scoped>
.kakaoform {
  margin-bottom: 6vh;
}

.findform {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  min-height: 80vh;
}

p {
  color: #0f0f0f;
  font-weight: 800;
  font-size: 16px;
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
  text-align: left;
}

.address {
  display: flex;
  justify-content: space-between;
}

.search {
  border: 0px;
  background-color: transparent;
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

.user-form-control:focus {
  border-color: var(--color-red-2) !important;
  box-shadow: inset 0 1px 1px var(--color-red-1), 0 0 8px var(--color-red-2) !important;
}

.user-form-control{
  color: #443731;
}
</style>
