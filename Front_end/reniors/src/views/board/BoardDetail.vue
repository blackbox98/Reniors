<template>
  <real-header></real-header>
  <div class="board-detail-box">
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

    <div class="box">
      <!-- 제목, 글쓴이, 시간 -->
      <div>
        <p class="title2">{{ article.title }}</p>
        <div class="bottom">
          <img
            class="person"
            :src="article.baseURL + article.userProfile"
            alt="person"
          />
          <p class="userName">{{ article.userName }} |</p>
          <p class="time">
            {{ article.updatedAt.slice(0, 4) }}.{{
              article.updatedAt.slice(5, 7)
            }}.{{ article.updatedAt.slice(8, 10) }}
          </p>
        </div>
      </div>
      <!-- 수정 삭제 -->
      <div v-if="isAuthor">
        <div class="icons">
          <router-link
            :to="{
              name: 'boardUpdate',
              parmas: { category_id: categoryId, board_id: articleId },
            }"
          >
            <i
              class="bi bi-pencil-square"
              style="font-size: 20px; margin: 4px; color: #6d6d6d"
            ></i>
          </router-link>
          <i
            class="bi bi-trash-fill"
            style="font-size: 20px; color: #6d6d6d; margin: 4px"
            @click="
              deleteArticle({ article_pk: articleId, categoryId: categoryId })
            "
          ></i>
        </div>
      </div>
    </div>

    <!-- 글 본문 -->
    <div class="contents">
      <p>{{ article.contents }}</p>
    </div>

    <!-- 댓글 컴포넌트 -->
    <comment-list :comments="comments"></comment-list>
    <div style="margin-bottom: 100px"></div>
  </div>
</template>
<script>
import BoardHeadSlider from "@/components/board/BoardHeadSlider.vue";
import CommentList from "@/components/board/CommentList.vue";
import RealHeader from "@/components/RealHeader.vue";
import { mapActions, mapGetters } from "vuex";
export default {
  name: "BoardDetail",
  components: { CommentList, BoardHeadSlider, RealHeader },
  data() {
    return {
      categoryId: this.$route.params.category_id,
      articleId: this.$route.params.board_id,
    };
  },
  computed: {
    ...mapGetters([
      "article",
      "isAuthor",
      "currentUser",
      "parents",
      "comments",
    ]),
  },
  setup() {},
  mounted() {},
  unmounted() {},
  methods: {
    ...mapActions(["fetchArticle", "deleteArticle", "fetchComments"]),
  },
  created() {
    this.fetchArticle(this.articleId);
    this.fetchComments(this.articleId);
  },
};
</script>

<style scoped>
.board-detail-box {
  width: 100%;
}
@media (max-width: 760px) {
  .board-detail-box {
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
.box {
  height: 80px;
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.box a {
  text-decoration: none;
  color: black;
  display: flex;
  justify-content: start;
  align-items: center;
}
.icons {
  margin: 16px;
  display: flex;
}

.title2 {
  margin: 0 8px;
  font-size: 18px;
  display: flex;
  justify-content: start;
  font-weight: bold;
}
.bottom {
  display: flex;
  margin: 0 0 0 8px;
}
.person {
  width: 24px;
  height: 24px;
  border-radius: 50%;
  margin: 0 4px;
}
.userName {
  margin: 0 8px;
  font-size: 14px;
  color: #6d6d6d;
  display: flex;
  justify-content: start;
  align-items: center;
}
.time {
  font-size: 12px;
  color: #6d6d6d;
  margin: 0;
  display: flex;
  justify-content: start;
  align-items: center;
}
.contents {
  display: flex;

  align-items: center;
  text-align: left;
  border-bottom: solid 2px #ff843e;
}
.contents p {
  font-size: 14px;
  margin: 8px 8px 16px 8px;
}
</style>
