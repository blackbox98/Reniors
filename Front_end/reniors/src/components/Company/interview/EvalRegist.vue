<template>
  <div class="input-question-box">
    <div class="question-contents">평가 항목</div>
    <input
      type="text"
      v-model="evalquestion.contents"
      placeholder="평가 항목을 입력해주세요."
      class="input-contents"
    />
    <button class="save-btn" @click="regist()">저장하기</button>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
export default {
  data() {
    return {
      evalquestion: {
        jobOpeningId: this.$route.params.no,
        contents: "",
      },
    };
  },
  computed: {
    ...mapGetters("company", ["jobopening"]),
  },
  methods: {
    ...mapActions("company", ["registEvalQuestion"]),
    regist() {
      this.registEvalQuestion(this.evalquestion);
      this.$emit("fetch");
    },
    
  },
};
</script>

<style scope>
.input-question-box {
  padding: 10px;
  margin: 10px auto;
  border-radius: 10px;
  border-color: var(--color-black-2);
  box-shadow: inset 0 0 1px 1px var(--color-black-3),
    0 0 5px var(--color-black-3);
}
.input-question-box > .question-contents {
  text-align: left;
  padding-left: 10px;
  font-size: 18px;
  color: var(--color-black-1);
}
.input-question-box > .input-contents {
  width: 93%;
  height: 40px;
  margin-left: 11px;
  margin-bottom: 12px;
  border-radius: 8px;
  border: 2px solid var(--color-black-3);
  display: block;
}
.input-question-box > .save-btn {
  padding: 5px 10px;
  width: 70%;
  height: 40px;
  background-color: var(--color-red-2);
  border-radius: 8px;
  border: none;
  font-size: 18px;
  font-weight: bold;
  color: white;
  box-shadow: rgba(50, 50, 93, 0.25) 0px 2px 5px -1px,
    rgba(0, 0, 0, 0.3) 0px 1px 3px -1px;
}
.input-question-box > .save-btn:link {
  color: white;
}
.input-question-box > .save-btn:visited {
  color: white;
}
</style>
