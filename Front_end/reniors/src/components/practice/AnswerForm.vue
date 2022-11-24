<template>
  <div>
    <form @submit.prevent="onSubmit" class="formGroup">
      <div class="box">
        <label for="answer"></label>
        <textarea
          type="text"
          class="form-control content"
          id="answer"
          placeholder="내용을 입력해주세요."
          v-model="req.writeAns"
        ></textarea>
      </div>
      <div class="submit">
        <button type="submit" class="Btn">저장</button>
      </div>
    </form>
  </div>
</template>
<script>
import { mapActions, mapGetters } from "vuex";

export default {
  name: "AnswerForm",
  components: {},
  props: {
    action: String,
  },
  data() {
    return {
      questionId: this.$route.params.question_id,
      req:{
        writeAns: "",
      }
    };
  },
  setup() {},
  async created() {
    if(this.action==="update"){
      await this.fetchAnswer(this.$route.params.question_id);
      this.req.writeAns = this.answer.answer;
    }

  },
  mounted() {},
  unmounted() {},
  watch: {},
  computed: {
    ...mapGetters(["answer"]),
  },
  methods: {
    ...mapActions(["createAnswer", "updateAnswer", "fetchAnswer"]),
    async onSubmit() {
      if (!this.req.writeAns) {
        alert("내용을 작성해주세요!");
      }
      if (this.action === "create") {
        await this.createAnswer({
          questionId: this.questionId,
          content: this.req.writeAns,
        });
      } else if (this.action === "update") {
        await this.updateAnswer({
          questionId: this.questionId,
          content: this.req.writeAns,
        });
      }
      await this.$router.go(-1);
    },
  },
};
</script>

<style scoped>
.formGroup {
  margin: 8px 4px 0 4px;
}
.box {
  margin: 4px;
  display: flex;
  justify-content: center;
}
.content {
  margin: 8px 4px;
  height: 440px;
  padding: 8px 10px 8px 10px;
}

.Btn {
  background-color: var(--color-red-2);
  height: 40px;
  width: 340px;
  margin: 8px 0;
  border-radius: 10px;
  border: none;
  color: white;
  font-weight: bold;
  font-size: 16px;

  /* box-shadow: 0 4px 4px -1px rgba(0, 0, 0, 0.1), 0 2px 2px -1px rgba(0, 0, 0, 0.06); */
  cursor: pointer;
}
.content:focus {
  border-color: var(--color-red-2) !important;
  box-shadow: inset 0 1px 1px var(--color-red-1), 0 0 8px var(--color-red-2) !important;
}
</style>
