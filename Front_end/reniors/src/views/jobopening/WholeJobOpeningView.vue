<template>
    <div class="header">
      <i @click="prevpage()" class="bi bi-arrow-left-circle-fill"></i>
      <router-link class="header-logo" :to="{ name: 'home' }">
        <img src="@/assets/logo_cut.png" alt="logo" />
      </router-link>
      <div></div>
    </div>
  <div>
    <search-bar></search-bar>

    <!-- content -->
    <div class="jobopening-container">
      <RecommendTagList />
    </div>

    <JobopeningList />
  </div>
</template>

<script>
import RecommendTagList from "@/components/jobopening/RecommendTagList.vue";
import JobopeningList from "@/components/jobopening/JobopeningList.vue";
import SearchBar from "@/components/SearchBar.vue";
import { mapGetters } from "vuex";
export default {
  name: "JobopeningView",
  components: {
    RecommendTagList,
    JobopeningList,
    SearchBar,
  },
  computed: {
    ...mapGetters("jobopening", ["isrecommend"]),
  },
  created() {
    if (this.isrecommend) {
      document.documentElement.style.setProperty("--recommend-height", `380px`);
    } else {
      document.documentElement.style.setProperty("--recommend-height", `76px`);
    }
  },
  methods:{
    prevpage() {
      this.$router.go(-1);
    },
  }
};
</script>

<style scoped>
:root {
  --recommend-height: ;
}
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
.jobopening-container {
  background-color: var(--color-red-3);
  border-radius: 0.5rem;
  padding: 10px;
  margin-top: 120px;
  /* height: 380px; */
}
.header > .bi {
  color: var(--color-red-1);
  font-size: 30px;
  cursor: pointer;
}
.header > div {
  width: 30px;
}
@media(max-width: 760px){
  .jobopening-container {
    width: 100vw;
}
}
</style>
