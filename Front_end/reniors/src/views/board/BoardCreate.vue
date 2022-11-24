<template>
  <real-header></real-header>
  <div class="board-create-box">
    <board-head-slider></board-head-slider>
    <div class="title">
      <router-link
        :to="{ name: 'boardMain', params: { category_id: categoryId } }"
      >
        <i
          class="bi bi-arrow-left-circle-fill"
          style="color: #ffd39b; font-size: 24px; margin: 0 16px"
        ></i>
      </router-link>
      <p>{{ parents[categoryId - 1].name }}</p>
    </div>
    <article-form
      :article="article"
      action="create"
      :category_pk="categoryId"
    ></article-form>
  </div>
</template>
<script>
import BoardHeadSlider from "@/components/board/BoardHeadSlider.vue";
import ArticleForm from "@/components/board/ArticleForm.vue";
import { mapGetters } from "vuex";
import RealHeader from "@/components/RealHeader.vue";

export default {
  name: "BoardCreate",
  components: { ArticleForm, BoardHeadSlider, RealHeader },
  data() {
    return {
      categoryId: this.$route.params.category_id,
      article: {
        pk: null,
        title: "",
        contents: "",
      },
    };
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {},
  computed: {
    ...mapGetters(["parents"]),
  },
};
</script>

<style scoped>
.board-create-box {
  width: 100%;
}
@media (max-width: 760px) {
  .board-create-box {
    width: 100vw;
  }
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
</style>
