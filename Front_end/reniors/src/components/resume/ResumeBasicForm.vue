<template>
  <div>
    <div class="basic">
      <form @submit.prevent="update()">
        <p class="forminfo">이름<span class="required">&nbsp;&nbsp;*</span></p>
        <b-form-input
          class="mb-3"
          type="text"
          v-model="userEdit.name"
        ></b-form-input>
        <p class="forminfo">
          생년월일<span class="required">&nbsp;&nbsp;*</span>
        </p>
        <b-form-input
          class="mb-3"
          type="date"
          v-model="userEdit.birth"
        ></b-form-input>
        <p class="forminfo">주소<span class="required">&nbsp;&nbsp;*</span></p>
        <div class="address">
          <b-form-input
            class="mb-3"
            style="width: 90%"
            v-model="userEdit.address"
            type="text"
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
          class="mb-3"
          v-model="userEdit.extraAddress"
          type="text"
          placeholder="상세주소를 입력해주세요"
        ></b-form-input>
        <p class="forminfo">
          연락처<span class="required">&nbsp;&nbsp;*</span>
        </p>
        <b-form-input
          class="mb-3"
          type="text"
          v-model="userEdit.phone"
        ></b-form-input>
        <p class="forminfo">
          최종학력<span class="required">&nbsp;&nbsp;*</span>
        </p>
        <b-form-select class="mb-3" v-model="userEdit.lastEdu">
          <option
            v-for="lastedu in lastedus"
            :value="lastedu.value"
            :key="lastedu"
          >
            {{ lastedu.text }}
          </option></b-form-select
        >
        <div class="mb-3 mt-3">
          <br />
          <div class="form-check">
            <input
              class="form-check-input"
              type="checkbox"
              value=""
              v-model="userEdit.changeProfile"
              id="flexCheckDefault"
            />
            <label
              class="form-check-label"
              for="flexCheckDefault"
              style="font-size: 15px"
            >
              혹시 <span style="color: #ffb400">프로필 사진</span>을 변경하고
              싶으신가요?
            </label>
            <br />
          </div>
          <br />
          <input
            v-show="userEdit.changeProfile"
            type="file"
            class="form-control"
            placeholder="이미지를 선택해주세요"
            ref="img"
            @change="changeImg()"
          />
        </div>
        <footer style="width: 312px">
          <button v-if="this.action == 'info'">완료</button>
          <button v-else>다음</button>
        </footer>
      </form>
    </div>
  </div>
</template>
<script>
import { mapActions, mapState } from "vuex";
export default {
  name: "ResumeBasicForm",
  components: {},
  props: {
    currentUser: Object,
    action: String,
  },

  data() {
    return {
      userEdit: {
        changeProfile: false,
        address: this.currentUser.address,
        extraAddress: this.currentUser.extraAddress,
        birth: this.currentUser.birth,
        gender: this.currentUser.gender,
        isOpen: this.currentUser.isOpen,
        lastEdu: this.currentUser.lastEdu,
        name: this.currentUser.name,
        phone: this.currentUser.phone,
        totalCareer: this.currentUser.totalCareer,
      },
      userImg: "",
    };
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  computed: {
    ...mapState("category", ["lastedus"]),
  },
  methods: {
    ...mapActions(["updateUser"]),
    execDaumPostcode() {
      new window.daum.Postcode({
        oncomplete: (data) => {
          if (this.extraAddress !== "") {
            this.extraAddress = "";
          }
          if (data.userSelectedType === "R") {
            // 사용자가 도로명 주소를 선택했을 경우
            this.userEdit.address = data.roadAddress;
          } else {
            // 사용자가 지번 주소를 선택했을 경우(J)
            this.userEdit.address = data.jibunAddress;
          }

          // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
          if (data.userSelectedType === "R") {
            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
              this.extraAddress += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if (data.buildingName !== "" && data.apartment === "Y") {
              this.extraAddress +=
                this.extraAddress !== ""
                  ? `, ${data.buildingName}`
                  : data.buildingName;
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if (this.extraAddress !== "") {
              this.extraAddress = `(${this.extraAddress})`;
            }
          } else {
            this.extraAddress = "";
          }
        },
      }).open();
    },
    changeImg() {
      this.userImg = this.$refs.img.files;
    },
    update() {
      const formData = new FormData();
      formData.append("img", this.userImg[0]);
      formData.append(
        "data",
        new Blob([JSON.stringify(this.userEdit)], { type: "application/json" }),
      );
      this.updateUser(formData);
    },
  },
};
</script>

<style scoped>
p {
  text-align: left;
}

.forminfo {
  color: #8a8a8a;
  font-weight: 400;
  font-size: 13px;
  margin-bottom: 5px;
}

.required {
  font-size: 13px;
  color: var(--color-red-1);
}

.basic {
  margin: 0px;
  height: 650px;
}

input[type="date"]::-webkit-datetime-edit-text,
input[type="date"]::-webkit-datetime-edit-month-field,
input[type="date"]::-webkit-datetime-edit-day-field,
input[type="date"]::-webkit-datetime-edit-year-field {
  color: #888;
}

.address {
  display: flex;
  justify-content: space-between;
}

.search {
  border: 0px;
  background-color: transparent;
}

footer {
  width: 100%;
  height: 50px;
  position: fixed;
  left: 50%;
  transform: translate(-50%, 0);
  bottom: 60px;
  background-color: white;
}

footer > button {
  margin: 0 auto;
  background-color: var(--color-red-2);
  width: 100%;
  height: 80%;
  /* height: 40px; */
  border-radius: 10px;
  border: none;
  color: white;
  font-weight: bold;
  font-size: 18px;
  /* box-shadow: 0 4px 4px -1px rgba(0, 0, 0, 0.1), 0 2px 2px -1px rgba(0, 0, 0, 0.06); */
  cursor: pointer;
}
</style>
