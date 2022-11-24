<template>
    <div class="header">
      <i @click="prevpage()" class="bi bi-arrow-left-circle-fill"></i>
      <router-link class="header-logo" :to="{ name: 'home' }">
        <img src="@/assets/logo_cut.png" alt="logo" />
      </router-link>
      <div></div>
    </div>
  <div class="board-main-box">
    <board-head-slider class="slider"></board-head-slider>
    <div class="title">
      <p>{{ jobname }}</p>
      <div v-if="isLogginedIn">
        <router-link
          :to="{ name: 'boardCreate', params: { category_id: category_id } }"
        >
          <i
            class="bi bi-plus-circle-fill"
            style="color: #ffd39b; font-size: 24px; margin: 0 16px"
          ></i>
        </router-link>
      </div>
    </div>

    <article-item
      v-for="article in articles"
      :key="article.boardId"
      :article="article"
    ></article-item>

    <button v-show="!isLast" class="more-btn" @click="pageUp()">더보기</button>
    <div style="margin-bottom: 100px"></div>
  </div>
</template>
<script>
import BoardHeadSlider from "@/components/board/BoardHeadSlider.vue";
import ArticleItem from "@/components/board/ArticleItem.vue";
import { mapActions, mapGetters } from "vuex";
export default {
  name: "BoardHome",
  components: {
    BoardHeadSlider,
    ArticleItem,
  },
  data() {
    return {
      category_id: Number(this.$route.params.category_id),
      jobname: "",
    };
  },
  watch: {
    $route: async function (to) {
      if (to.path.includes("boards")) {
        await this.setCurrPage(0);
        await this.clearArticles();
        await this.fetchArticles({
          categoryId: this.$route.params.category_id,
          page: 0,
        });
        this.jobname = this.parents[Number(to.params.category_id) - 1].name;
      }
    },
    async currPage() {
      if (this.currPage != 0) {
        await this.fetchArticles({
          categoryId: this.$route.params.category_id,
          page: this.currPage,
        });
      }
    },
  },
  computed: {
    ...mapGetters([
      "articles",
      "isLogginedIn",
      "parents",
      "isLast",
      "currPage",
    ]),
  },
  methods: {
    ...mapActions([
      "fetchArticles",
      "fetchParents",
      "clearArticles",
      "setCurrPage",
    ]),
    async pageUp() {
      await this.setCurrPage(this.currPage + 1);
    },
        prevpage() {
      this.$router.go(-1);
    },
  },
  async created() {
    await this.setCurrPage(0);
    await this.fetchParents();
    await this.clearArticles();
    await this.fetchArticles({
      categoryId: this.$route.params.category_id,
      page: 0,
    });
    this.jobname = this.parents[this.$route.params.category_id - 1].name;
  },
  mounted() {},
  unmounted() {},
};
</script>

<style scoped>
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
.header > .bi {
  color: var(--color-red-1);
  font-size: 30px;
  cursor: pointer;
}
.header > div {
  width: 30px;
}
.board-main-box {
  width: 100%;
}
.title {
  height: 48px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: solid 2px;
  border-bottom-color: #ff843e;
}
.title p {
  font-size: 20px;
  font-weight: bold;
  color: #ffb400;
  margin: 0 16px;
}
.more-btn {
  margin-top: 30px;
  margin-bottom: 200px;
  border-radius: 5px;
  border: none;
  padding: 8px 40px;
  background-color: var(--color-red-2);
  color: white;
  font-weight: bold;
}
@media (max-width: 760px) {
  .board-main-box {
    width: 100vw;
  }
}
</style>
