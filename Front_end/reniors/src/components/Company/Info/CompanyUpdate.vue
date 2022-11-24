<template>
  <div>
    <header>
      <div>
        <img
          style="width: 128px; max-height: 170px; margin: 48px auto"
          src="@/assets/logo.png"
          alt="logo"
        />
      </div>
    </header>
    <div v-if="pagenum == 1" class="company-update-page-box">
      <div class="update-process-box">
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

      <div class="company-update-form">
        <div class="mb-3 mt-3">
          <label for="name" class="form-label company-form-label">
            기업명
            <p class="required">*</p>
          </label>
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
            >이메일</label
          >
          <input
            type="text"
            class="form-control company-form-control"
            id="companyAppId"
            placeholder="이메일을 입력해주세요."
            name="companyAppId"
            v-model="company.companyAppId"
            readonly
          />
        </div>

        <div class="mb-3 mt-3">
          <label for="companyNum" class="form-label company-form-label"
            >사업자번호</label
          >
          <input
            type="text"
            class="form-control company-form-control"
            id="companyNum"
            placeholder="사업자번호를 입력해주세요."
            name="companyNum"
            v-model="company.companyNum"
            readonly
          />
        </div>
      </div>
    </div>
    <div v-if="pagenum == 2" class="company-update-page-box">
      <div class="update-process-box">
        <!-- <p>1</p><p class="now">2</p><p>3</p> -->
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
      <br />
      <div class="company-update-form">
        <div class="mb-3 mt-3">
          <label for="representativePhone" class="form-label company-form-label"
            >담당자번호
          <p class="required">*</p></label
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
            >대표번호<p class="required">*</p></label
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
          <label class="form-label company-form-label">기업형태<p class="required">*</p></label>
          <select
            v-model="company.typeCompany"
            class="form-control company-form-control:focus"
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
    </div>
    <div v-if="pagenum == 3" class="company-update-page-box">
      <div class="update-process-box">
        <!-- <p>1</p><p>2</p><p class="now">3</p> -->
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
      <div class="company-update-form">
        <div class="mb-3 mt-3">
          <label for="establishedAt" class="form-label company-form-label"
            >설립연도<p class="required">*</p></label
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
            >회사주소<p class="required">*</p></label
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
        <div>
          <label class="form-label">현재 회사 이미지</label>
          <div />
          <img
            :src="companyinfo.baseURL + companyinfo.companyProfile"
            alt="test"
            style="width:240px; height:auto; margin:8px auto"
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
        <div style="heigth: 200px;"></div>
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
      <button @click="updatecompany()" v-show="pagenum === 3">완료!</button>
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
      companyImg: null,
    };
  },
  watch: {
    companyinfo: function (data) {
      this.company = data;
    },
  },
  computed: {
    ...mapGetters("category", ["typecompanies"]),
    ...mapGetters("company", ["companyinfo"]),
  },
  created() {
    this.getCompany();
  },
  methods: {
    ...mapActions("company", ["updateCompany", "getCompany"]),
    increasePage() {
      this.pagenum += 1;
    },
    decreasePage() {
      this.pagenum -= 1;
    },
    updatecompany() {
      const formData = new FormData();
      if (this.companyImg) {
        formData.append("img", this.companyImg[0]);
      } else {
        formData.append("img", null);
      }
      formData.append(
        "data",
        new Blob([JSON.stringify(this.company)], { type: "application/json" })
      );
      console.log(this.company);
      this.updateCompany(formData);
      this.$router.push({ name: "companymypage" });
    },
    changeImg() {
      this.jobOpeningImg = this.$refs.img.files;
    },
  },
};
</script>

<style scoped>
.update-process-box {
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
.company-update-form {
  height: 90%;
  margin-bottom: 200px;
}
.hidden-btn {
  visibility: hidden;
}
.company-update-page-box > button {
  border: none;
  border-radius: 5px;
  background-color: var(--color-green-2);
  color: white;
  padding: 10px 50px;
  margin: 0 5px;
}
.company-update-page-box > button:hover {
  color: black;
  background-color: var(--color-green-1);
}
.company-form-control:focus {
  border-color: var(--color-green-2) !important;
  box-shadow: inset 0 1px 1px var(--color-green-1), 0 0 8px var(--color-green-2) !important;
}
.order {
  margin: 2px;
}
.required{
  color: red;
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
</style>
