<template>
  <div class="total">
    <span v-if="!isEditing" class="content">
      <div>
        <p class="title2">{{ comment.contents }}</p>
        <div class="bottom">
          <img
            class="person"
            :src="comment.baseURL + comment.userProfile"
            alt="person"
          />
          <p class="userName">{{ comment.userName }} |</p>
          <p class="time">
            {{ comment.updatedAt.slice(0, 4) }}.{{
              comment.updatedAt.slice(5, 7)
            }}.{{ comment.updatedAt.slice(8, 10) }}.
            {{ (Number(comment.updatedAt.slice(11, 13)) + 8) % 24 }}:{{
              comment.updatedAt.slice(14, 16)
            }}
          </p>
        </div>
      </div>
    </span>

    <span v-if="isEditing" class="commentForm">
      <img
        class="person"
        :src="comment.baseURL + comment.userProfile"
        alt="person"
      />
      <input type="text" v-model="contents" style="" />
      <p
        @click="onUpdate"
        class="btn-open my-0"
        style="font-size: 14px; color: #37bf99"
      >
        수정
      </p>
      <p @click="switchIsEditing" class="btn-close my-0"></p>
    </span>

    <span v-if="currentUser.id === comment.userid && !isEditing" class="comed">
      <p @click="switchIsEditing" class="btn-open" style="color: #37bf99">
        수정
      </p>
      <p @click="onDelete()" style="color: #f3620f">삭제</p>
    </span>
  </div>
</template>
<script>
import { mapActions, mapGetters } from "vuex";

export default {
  name: "CommentItem",
  components: {},
  props: {
    comment: Object,
  },
  data() {
    return {
      isEditing: false,
      contents: this.comment.contents,
      categoryId: this.$route.params.category_id,
      boardId: this.$route.params.board_id,
    };
  },
  computed: {
    ...mapGetters(["currentUser", "isAuthor"]),
  },
  watch: {
    comment: function () {
      this.fetchComments(this.boardId);
    },
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {
    ...mapActions(["updateComment", "deleteComment", "fetchComments"]),
    switchIsEditing() {
      this.isEditing = !this.isEditing;
    },
    onUpdate() {
      this.updateComment({
        categoryId: this.categoryId,
        boardId: this.boardId,
        commentId: this.comment.commentId,
        contents: this.contents,
      });
      this.isEditing = false;
    },
    onDelete() {
      this.deleteComment({
        categoryId: this.categoryId,
        boardId: this.boardId,
        commentId: this.comment.commentId,
        contents: this.contents,
      });
    },
  },
};
</script>

<style scoped>
.comed {
  display: flex;
  align-items: end;
  justify-content: end;
  margin: 8px;
}
.comed p {
  margin: 0 2px;
  font-size: 14px;
}
.commentForm {
  width: 100%;
  height: 32px;
  display: flex;
  margin: 8px 4px;
  align-items: center;
  justify-content: space-between;
}
input {
  width: 75%;
  border: solid 1px #8a8a8a;
  height: 90%;
  border-radius: 5px;
  padding: 4px;
}
.total {
  border-bottom: solid 1px #ffd39b;
  display: flex;
  justify-content: space-between;
  height: 80px;
  align-items: center;
}
.content {
  height: 64px;
  display: flex;
  align-items: center;
  justify-content: left;
  margin: 0;
}
.title2 {
  margin: 4px 8px;
  font-size: 16px;
  display: flex;
  justify-content: start;
}
.bottom {
  display: flex;
  margin: 2px 8px;
}
.person {
  width: 28px;
  height: 28px;
  border-radius: 50%;
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
@media (max-width: 760px) {
  .person {
    width: 32px;
    height: 32px;
  }
}
</style>
