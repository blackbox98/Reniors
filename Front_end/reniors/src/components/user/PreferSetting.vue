<template>
  <div style="width: 360px" class="prefer-setting-box">
    <header style="margin-bottom: 10px">
      <br />
      <img
        style="width: 60%; max-height: 90px; margin: 10px"
        src="@/assets/logo_cut.png"
        alt="logo"
      />
      <br />

      <div style="float: right; margin-top: 5px">
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
          style="margin-right: 20px"
          v-if="page === 3"
          src="@/assets/three_active.svg"
          alt="order"
        />
        <img
          class="order"
          style="margin-right: 20px"
          v-else
          src="@/assets/three.svg"
          alt="order"
        />
      </div>
    </header>

    <div style="width: 312px; margin: auto">
      <div v-show="page === 1">
        <img class="num" src="@/assets/bigone.svg" alt="one" />
        <p class="text1">전문분야 및 세부업무 선택</p>
        <p class="text2">전문분야를 선택해주세요!</p>
        <br />
        <div class="prefer-setting-select">
          <select
            class="form-select"
            size="10"
            aria-label="size 3 select example"
            v-model="recommend.jobParentCategoryId"
            style="height: 150px"
          >
            <option
              class="m-2"
              v-for="parent in parents"
              :key="parent"
              :value="parent.id"
            >
              {{ parent.name }}
            </option>
          </select>
        </div>
        <br />
      </div>

      <div v-show="page === 2" style="height: 600px">
        <img class="num" src="@/assets/bigtwo.svg" alt="two" />
        <p class="text1">지역 선택</p>
        <p class="text2">일하고 싶은 지역을 선택해주세요!</p>
        <br />
        <div>
          <select
            class="form-select"
            size="10"
            aria-label="size 3 select example"
            style="margin-bottom: 10px"
            v-model="area"
          >
            <option
              class="m-2"
              v-for="sido in sidos"
              :key="sido"
              :value="sido.id"
            >
              {{ sido.name }}
            </option>
          </select>
          <select
            v-show="area"
            class="form-select"
            size="10"
            aria-label="size 3 select example"
            v-model="recommend.gugunId"
            style="height: 200px"
          >
            <option
              class="m-2"
              v-for="gugun in guguns"
              :key="gugun"
              :value="gugun.id"
            >
              {{ gugun.name }}
            </option>
          </select>
        </div>
      </div>

      <div v-show="page === 3">
        <img class="num" src="@/assets/bigthree.svg" alt="three" />
        <p class="text1">희망 근무일수 및 연봉</p>
        <p class="text2">희망 근무일수와 희망 연봉을 입력해주세요!</p>
        <br />
        <div class="mb-3">
          <p class="subtitle">
            <i class="bi bi-caret-right-fill"></i> 근무 일수
          </p>
          <p class="text3" style="padding-left: 10px; margin-bottom: 0px">
            주 몇 회 일하고 싶으신가요?
          </p>
          <p class="text3" style="padding-left: 10px">
            1~6회 사이로 입력해주세요.
          </p>
          <div style="width: 150px; float: right">
            <label for="exampleFormControlInput1" class="form-label"
              >주&emsp;</label
            >
            <input
              type="number"
              class="form-control"
              v-model="recommend.workingDay"
              id="exampleFormControlInput1"
              style="display: inline; width: 80px; text-align: right"
              placeholder=""
            />
            <label for="exampleFormControlInput1" class="form-label"
              >&ensp;일</label
            >
          </div>
          <br />
          <br />
          <br />
          <p class="subtitle"><i class="bi bi-caret-right-fill"></i> 연봉</p>
          <p class="text3" style="padding-left: 10px">
            희망 연봉을 알려주세요.
          </p>
          <div style="width: 150px; float: right">
            <input
              type="number"
              class="form-control"
              v-model="recommend.minSalary"
              id="exampleFormControlInput1"
              style="display: inline; width: 80px; text-align: right"
              placeholder=""
            />
            <label for="exampleFormControlInput1" class="form-label"
              >&ensp;만원</label
            >
          </div>
        </div>
        <br />
      </div>
    </div>

    <footer style="width: 312px">
      <button
        style="background-color: #ffc0a3"
        type="button"
        v-show="page === 1"
      >
        <router-link
          style="text-decoration: none; color: white"
          :to="{ name: 'MyPage' }"
          >이전</router-link
        >
      </button>
      <button
        style="background-color: #ffc0a3"
        type="button"
        v-show="page !== 1"
        @click="decreasePage"
      >
        이전
      </button>
      <button type="button" v-show="page !== 3" @click="increasePage">
        다음
      </button>
      <button @click="prefer" v-show="page === 3">완료!</button>
    </footer>
  </div>
</template>
<script>
import { mapActions, mapGetters, mapState } from "vuex";

export default {
  name: "PreferSetting",
  components: {},
  props: { action: String },
  data() {
    return {
      jobparents: null,
      area: null,
      page: 1,
      recommend: {
        jobParentCategoryId: null,
        gugunId: null,
        minSalary: null,
        workingDay: null,
      },
    };
  },
  computed: {
    ...mapGetters(["parents"]),
    ...mapState("category", ["jobchilds", "sidos", "guguns"]),
  },

  watch: {
    "recommend.jobParentCategoryId"(data) {
      this.getJobChild(data);
    },

    area(data) {
      this.getGugun(data);
    },
  },
  setup() {},
  created() {
    this.getSido();
    this.fetchParents();
    this.fetchPrefer();
  },

  mounted() {},
  unmounted() {},
  methods: {
    ...mapActions([
      "fetchParents",
      "createPrefer",
      "fetchPrefer",
      "updatePrefer",
    ]),
    ...mapActions("category", ["getJobChild", "getSido", "getGugun"]),
    increasePage() {
      this.page += 1;
    },
    decreasePage() {
      this.page -= 1;
    },
    prefer() {
      if (this.action === "create") {
        this.createPrefer(this.recommend);
      } else if (this.action === "update") {
        this.updatePrefer(this.recommend);
      }
    },
  },
};
</script>

<style scoped>
.prefer-setting-box {
  margin: auto;
}

.prefer-setting-select {
  margin: auto auto;
  width: 100%;
}

p {
  text-align: left;
}

label {
  color: #8a8a8a;
  font-weight: 400;
  font-size: 13px;
  margin-bottom: 5px;
}

header {
  height: 136px;
  border-style: none none solid none;
  border-width: 0.5px;
  border-color: #eaeaea;
}

.title {
  font-size: 22px;
  margin-top: 4px;
  color: #ffb400;
  font-weight: 900;
}

.order {
  margin: 2px;
}

.num {
  float: left;
  margin: 16px;
}

.text1 {
  font-size: 18px;
  padding: 20px 4px 6px 4px;
  font-weight: 900;
  margin: 0px;
}

.text2 {
  font-size: 15px;
  color: #6d6d6d;
  margin-bottom: 5px;
}

.text3 {
  font-size: 13px;
  color: #37bf99;
  padding-left: 10px;
}

.subtitle {
  font-size: 15px;
  font-weight: 700;
}
footer > button {
  background-color: var(--color-red-2);
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
</style>
