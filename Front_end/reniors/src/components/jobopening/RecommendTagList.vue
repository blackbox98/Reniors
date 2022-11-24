<template>
  <div class="recommend-tag-list">
    <template v-if="isLoggedin && isrecommend">
      <div class="recommend-tag-title">
        <h2>조건에 맞는 <span>추천 공고</span>입니다.</h2>
        <button @click="movesetting()">재설정</button>
      </div>
    </template>
    <template v-else-if="isLoggedin && !isrecommend && setRecommendCondition">
      <div class="not-recommend-tag-title">
        <h2>조건에 맞는 <span>추천 공고</span>가 없습니다!</h2>
      </div>
    </template>
    <template v-else-if="isLoggedin && !isrecommend && !setRecommendCondition">
      <div class="not-recommend-tag-title">
        <h2>관심 조건을 설정하고 <span>추천 공고</span>를 받아보세요!</h2>
      </div>
    </template>
    <template v-else>
      <div class="notlogTotal" v-if="!isLoggedin">
        <div class="notlog">
          <div class="notlogBox">
            <p class="notlogp">추천 공고 서비스는</p>
            <p class="notlogp">
              <router-link :to="{ name: 'Login' }" style="text-decoration: none"
                ><span style="color: #37bf99"> 로그인</span></router-link
              >
              후 이용해주세요🙂
            </p>
            <router-link :to="{ name: 'Login' }">
              <button class="notlogBtn">
                로그인 하러 가기 <i class="bi bi-arrow-right-square-fill"></i>
              </button>
            </router-link>
          </div>
        </div>
      </div>
    </template>
    <div class="jobopening-list">
      <div v-if="isrecommend" class="jobopening-scroll">
        <RecommendJobOpeningItem
          v-for="jobopening in recommendJobopenings"
          :key="jobopening"
          :jobopening="jobopening"
        />
      </div>
    </div>
  </div>
</template>

<script scoped>
import { mapGetters, useStore } from "vuex";
import { computed } from "vue";
import RecommendJobOpeningItem from "./RecommendJobOpeningItem.vue";

export default {
  name: "RecommendTagList",
  components: {
    RecommendJobOpeningItem,
  },
  setup() {
    const store = useStore();
    const isLoggedin = computed(() => store.getters["isLogginedIn"]);
    return { isLoggedin };
  },
  watch: {
    recommendJobopenings: function (data) {
      if (data) {
        let len = 0;
        if (data.length % 2 == 1) len = data.length / 2 + 0.5;
        else len = data.length / 2;
        document.documentElement.style.setProperty("--columns", len);
      }
    },
  },
  computed: {
    ...mapGetters("jobopening", [
      "recommendJobopenings",
      "isrecommend",
      "setRecommendCondition",
    ]),
  },
  methods: {
    movesetting() {
      this.$router.push({ name: "UpdateSetting" });
    },
  },
  created() {
    if (!this.recommendJobopenings)
      document.documentElement.style.setProperty("--columns", 0);
    else {
      let len = 0;
      if (this.recommendJobopenings.length % 2 == 1)
        len = this.recommendJobopenings.length / 2 + 0.5;
      else len = this.recommendJobopenings.length / 2;
      document.documentElement.style.setProperty("--columns", len);
    }
  },
};
</script>

<style scoped>
:root {
  --columns: 4;
}

.recommend-tag-title {
  display: flex;
  justify-content: space-between;
  margin: 15px 15px 10px 15px;
  height: 30px;
}

.recommend-tag-title > h2 {
  margin: 0;
  font-size: 18px;
  font-weight: bold;
}

.recommend-tag-title > h2 > span {
  color: var(--color-red-1);
  font-size: 20px;
}

.recommend-tag-title > button {
  box-sizing: border-box;
  box-shadow: 1px 1px var(--color-black-2);
  background-color: var(--color-red-1);
  border: none;
  border-radius: 0.4rem;
  padding: 0px 15px;
  color: white;
}

.not-recommend-tag-title {
  display: flex;
  justify-content: center;
  margin: 15px 15px 10px 15px;
  height: 30px;
}

.not-recommend-tag-title > h2 {
  margin: 0;
  font-size: 14px;
  font-weight: bold;
}

.not-recommend-tag-title > h2 > span {
  color: var(--color-red-1);
  font-size: 17px;
}

.jobopening-list > div {
  display: grid;
  grid-template-rows: 150px 150px;
  grid-template-columns: repeat(var(--columns), 170px);
  margin-top: 10px;
  justify-items: center;
}

@media screen and (min-width: 720px) {
  .jobopening-list > div {
    grid-template-rows: 150px 150px;
    grid-template-columns: repeat(var(--columns), 170px);
    margin-top: 10px;
    justify-items: center;
  }
}

.notlogTotal {
  display: flex;
  width: 100%;
  flex-direction: column;
  justify-content: center;
  background-color: #fff5f0;
}

.noglog {
  display: flex;
  justify-content: center;
  align-items: center;
}

.notlogBox {
  background-color: rgba(256, 256, 256, 0.5);
  border: none;
  border-radius: 10px;
  margin: 8px;
  padding: 10px 8px;
}

.notlogp {
  font-size: 24px;
  font-weight: bold;
  color: #ff843e;
}

.notlogBtn {
  background-color: #ff843e;
  border: none;
  border-radius: 5px;
  color: white;
  font-weight: bold;
  font-size: 20px;
  width: 72%;
  padding: 8px;
  margin: 8px;
}

.notlogBtn i {
  font-size: 24px;
  margin: auto 8px;
}

.jobopening-scroll {
  overflow-y: hidden;
  overflow-x: scroll;
}

.jobopening-scroll::-webkit-scrollbar {
  height: 5px;
}

.jobopening-scroll::-webkit-scrollbar-thumb {
  background-color: var(--color-green-1);
  border-radius: 10px;
}

.jobopening-scroll::-webkit-scrollbar-track {
  background-color: var(--color-green-3);
  border-radius: 10px;
}
</style>
