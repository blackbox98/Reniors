<template>
  <div class="total">
    <!-- header -->
    <div class="header">
      <router-link class="header-logo" :to="{ name: 'home' }">
        <img src="@/assets/logo_cut.png" alt="logo" />
      </router-link>
    </div>
    <!-- subtitles -->
    <div class="sub">
      <div class="subBox">
        <p class="sub-title">
          <span style="color: #ff843e">리니어즈</span>에서 채용공고를
          조회해보세요🙂
        </p>
        <p>1. 전체공고에서는 모든 공고를 조회할 수 있습니다.</p>
        <p>2. 맞춤공고를 통해 관심있는 공고를 보여드려요.</p>
      </div>
    </div>

    <!-- buttons -->
    <div class="jobopening-container">
      <JobOpeningMainPage />
    </div>
  </div>
</template>

<script>
import JobOpeningMainPage from "@/components/jobopening/JobOpeningMainPage.vue";
import { mapActions, mapGetters, useStore } from "vuex";
import { computed } from "@vue/runtime-core";
export default {
  name: "JobopeningView",
  components: {
    JobOpeningMainPage,
  },
  methods: {
    ...mapActions("jobopening", ["fetchRecommend", "clearRecommend"]),
  },
  computed: {
    ...mapGetters("jobopening", ["recommendJobopenings"]),
  },
  setup() {
    const store = useStore();
    const isLogginedIn = computed(() => store.getters["isLogginedIn"]);
    const clearRecommend = () => store.dispatch("jobopening/clearRecommend");
    clearRecommend();
    const fetchRecommend = () => store.dispatch("jobopening/fetchRecommend");
    if (isLogginedIn.value) {
      fetchRecommend();
    }
  },
};
</script>

<style scoped>
.total {
  display: flex;
  flex-direction: column;
  justify-content: center;
  min-height: 100vh;
  min-width: 100vw;
  background-color: #fff5f0;
}

.header {
  min-width: 100vw;
  height: 48px;
  background-color: white;
  box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px -2px;
  display: flex;
  align-items: center;
  justify-content: center;
  position: fixed;
  top: 0;
}

.header img {
  height: 38px;
  margin: auto;
  width: auto;
}

.sub {
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 16px auto 40px;
  width: 90%;
  height: 30%;
  border-radius: 16px;
  background-color: rgba(256, 256, 256, 0.6);
  box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px 0px;
  padding: 16px 8px;
}

.subBox {
  text-align: left;
}

.sub-title {
  font-size: 16px;
  font-weight: bold;
}

.subBox p {
  font-size: 14px;
  margin: 0;
}

.contentBox {
  display: flex;
  justify-content: center;
  align-items: center;
}

.jobopening-container {
  padding: 10px;
  margin-bottom: 48px;
  width: 100%;
}
</style>
