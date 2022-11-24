<template>
  <div>
    <header>
      <div>
        <img
          style="width: 128px; max-height: 170px; margin: 10px"
          src="@/assets/logo.png"
          alt="logo"
        />
      </div>
    </header>
    <div v-if="pagenum == 1" class="contentBox">
      <div class="regist-process-box">
        <!-- <p class="now">1</p><p>2</p><p>3</p> -->
        <img
          class="order"
          v-if="pagenum === 1"
          src="@/assets/one_active.svg"
          alt="order"
        />
        <img class="order" v-else src="@/assets/one.svg" alt="order" />
        <img
          class="order"
          v-if="pagenum === 2"
          src="@/assets/two_active.svg"
          alt="order"
        />
        <img class="order" v-else src="@/assets/two.svg" alt="order" />
        <img
          class="order"
          v-if="pagenum === 3"
          src="@/assets/three_active.svg"
          alt="order"
        />
        <img class="order" v-else src="@/assets/three.svg" alt="order" />
      </div>
      <div class="mb-3 mt-3">
        <label for="name" class="company-form-label"
          >기업명<span class="required">&nbsp;&nbsp;*</span></label
        >
        <input
          type="text"
          class="form-control company-form-control"
          id="name"
          placeholder="기업명을 입력해주세요."
          name="name"
          v-model="company.name"
        />
      </div>
      <div class="mb-3 mt-3">
        <label for="companyAppId" class="form-label company-form-label"
          >이메일<span class="required">&nbsp;&nbsp;*</span></label
        >
        <input
          type="text"
          class="form-control company-form-control"
          id="companyAppId"
          placeholder="이메일을 입력해주세요."
          name="companyAppId"
          v-model="company.companyAppId"
        />
      </div>
      <div class="mb-3 mt-3">
        <label for="companyAppPwd" class="form-label company-form-label"
          >비밀번호<span class="required">&nbsp;&nbsp;*</span></label
        >
        <input
          type="password"
          class="form-control company-form-control"
          id="companyAppPwd"
          placeholder="비밀번호를 입력해주세요."
          name="companyAppPwd"
          v-model="company.companyAppPwd"
        />
      </div>
      <div class="mb-3 mt-3">
        <label for="ComfirmcompanyAppPwd" class="form-label company-form-label"
          >비밀번호 확인<span class="required">&nbsp;&nbsp;*</span></label
        >
        <input
          type="password"
          class="form-control company-form-control"
          id="comfirmcompanyAppPwd"
          placeholder="비밀번호를 한번 더 입력해주세요."
          name="comfirmcompanyAppPwd"
          v-model="comfirmcompanyAppPwd"
        />
      </div>
    </div>
    <div v-if="pagenum == 2" class="contentBox">
      <div class="regist-process-box">
        <!-- <p class="now">1</p><p>2</p><p>3</p> -->
        <img
          class="order"
          v-if="pagenum === 1"
          src="@/assets/one_active.svg"
          alt="order"
        />
        <img class="order" v-else src="@/assets/one.svg" alt="order" />
        <img
          class="order"
          v-if="pagenum === 2"
          src="@/assets/two_active.svg"
          alt="order"
        />
        <img class="order" v-else src="@/assets/two.svg" alt="order" />
        <img
          class="order"
          v-if="pagenum === 3"
          src="@/assets/three_active.svg"
          alt="order"
        />
        <img class="order" v-else src="@/assets/three.svg" alt="order" />
      </div>
      <div class="mb-3 mt-3">
        <label for="companyNum" class="form-label company-form-label"
          >사업자번호<span class="required">&nbsp;&nbsp;*</span></label
        >
        <input
          type="text"
          class="form-control company-form-control"
          id="companyNum"
          placeholder="사업자번호를 입력해주세요."
          name="companyNum"
          v-model="company.companyNum"
        />
      </div>
      <div class="mb-3 mt-3">
        <label for="representativePhone" class="form-label company-form-label"
          >담당자번호<span class="required">&nbsp;&nbsp;*</span></label
        >
        <input
          type="text"
          class="form-control company-form-control"
          id="representativePhone"
          placeholder="담당자번호를 입력해주세요."
          name="representativePhone"
          v-model="company.representativePhone"
        />
      </div>
      <div class="mb-3 mt-3">
        <label for="companyPhone" class="form-label company-form-label"
          >대표번호<span class="required">&nbsp;&nbsp;*</span></label
        >
        <input
          type="text"
          class="form-control company-form-control"
          id="companyPhone"
          placeholder="대표번호를 입력해주세요."
          name="companyPhone"
          v-model="company.companyPhone"
        />
      </div>
      <div class="mb-3 mt-3">
        <label for="companyUrl" class="form-label company-form-label"
          >회사 홈페이지</label
        >
        <input
          type="text"
          class="form-control company-form-control"
          id="companyUrl"
          placeholder="회사 홈페이지를 입력해주세요."
          name="companyUrl"
          v-model="company.companyUrl"
        />
      </div>
      <div class="mb-3 mt-3">
        <label class="form-label company-form-label"
          >기업형태<span class="required">&nbsp;&nbsp;*</span></label
        >
        <select
          v-model="company.typeCompany"
          class="form-control company-form-control"
        >
          <option
            v-for="typeCompany in typecompanies"
            :value="typeCompany.value"
            :key="typeCompany"
          >
            {{ typeCompany.text }}
          </option>
        </select>
      </div>
    </div>
    <div v-if="pagenum == 3" class="contentBox">
      <div class="regist-process-box">
        <!-- <p class="now">1</p><p>2</p><p>3</p> -->
        <img
          class="order"
          v-if="pagenum === 1"
          src="@/assets/one_active.svg"
          alt="order"
        />
        <img class="order" v-else src="@/assets/one.svg" alt="order" />
        <img
          class="order"
          v-if="pagenum === 2"
          src="@/assets/two_active.svg"
          alt="order"
        />
        <img class="order" v-else src="@/assets/two.svg" alt="order" />
        <img
          class="order"
          v-if="pagenum === 3"
          src="@/assets/three_active.svg"
          alt="order"
        />
        <img class="order" v-else src="@/assets/three.svg" alt="order" />
      </div>
      <div class="mb-3 mt-3">
        <label for="establishedAt" class="form-label company-form-label"
          >설립연도<span class="required">&nbsp;&nbsp;*</span></label
        >
        <input
          type="text"
          class="form-control company-form-control"
          id="establishedAt"
          placeholder="설립연도를 입력해주세요."
          name="establishedAt"
          v-model="company.establishedAt"
        />
      </div>
      <div class="mb-3 mt-3">
        <label for="address" class="form-label company-form-label"
          >회사주소<span class="required">&nbsp;&nbsp;*</span></label
        >
        <input
          type="text"
          class="form-control company-form-control"
          id="address"
          placeholder="회사주소를 입력해주세요."
          name="address"
          v-model="company.address"
        />
      </div>
      <div class="mb-3 mt-3">
        <label class="form-label company-form-label">회사이미지</label>
        <input
          type="file"
          class="form-control company-form-control"
          placeholder="이미지를 선택해주세요"
          ref="img"
          @change="changeImg()"
        />
      </div>
    </div>
    <footer style="width: 312px">
      <button
        style="background-color: var(--color-green-3)"
        type="button"
        v-show="pagenum === 1"
      >
        <router-link
          style="text-decoration: none; color: white"
          :to="{ name: 'Login' }"
          >이전</router-link
        >
      </button>
      <button
        style="background-color: var(--color-green-3)"
        type="button"
        v-show="pagenum !== 1"
        @click="decreasePage"
      >
        이전
      </button>
      <button type="button" v-show="pagenum !== 3" @click="increasePage">
        다음
      </button>
      <button @click="registcompany()" v-show="pagenum === 3">완료!</button>
    </footer>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
export default {
  data() {
    return {
      pagenum: 1,
      comfirmcompanyAppPwd: "",
      company: {
        name: "",
        companyAppId: "",
        companyAppPwd: "",
        establishedAt: "",
        companyUrl: "",
        address: "",
        companyNum: "",
        companyPhone: "",
        representativePhone: "",
        typeCompany: "",
      },
      companyImg: "",
    };
  },
  computed: {
    ...mapGetters("category", ["typecompanies"]),
  },
  methods: {
    ...mapActions("company", ["registCompany"]),
    increasePage() {
      this.pagenum += 1;
    },
    decreasePage() {
      this.pagenum -= 1;
    },
    changeImg() {
      this.companyImg = this.$refs.img.files;
      // console.log(this.companyImg);
    },
    registcompany() {
      if (this.company.companyAppPwd != this.comfirmcompanyAppPwd) {
        alert("비밀번호가 동일하지 않습니다.");
      } else {
        const formData = new FormData();
        formData.append("img", this.companyImg[0]);
        formData.append(
          "data",
          new Blob([JSON.stringify(this.company)], {
            type: "application/json",
          }),
        );
        this.registCompany(formData);
        this.$router.push({ name: "Login" });
      }
    },
  },
};
</script>

<style scoped>
.regist-process-box {
  width: 100%;
  display: flex;
  justify-content: right;
  border-style: none none solid none;
  border-width: 0.5px;
  border-color: #eaeaea;
  padding: 5px 0;
}
.company-form-label {
  width: 100%;
  text-align: left;
}
.company-form-control:focus {
  border-color: var(--color-green-2) !important;
  box-shadow: inset 0 1px 1px var(--color-green-1), 0 0 8px var(--color-green-2) !important;
}
.order {
  margin: 2px;
}
.required {
  font-size: 13px;
  color: var(--color-red-1);
  display: inline;
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
footer > button {
  background-color: var(--color-green-1);
  width: 45%;
  height: 80%;
  border-radius: 10px;
  border: none;
  color: white;
  font-weight: bold;
  font-size: 18px;
  cursor: pointer;
}
.contentBox{
  padding: 0 8px;
}

</style>
