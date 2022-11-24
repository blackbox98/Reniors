<template>
  <div class="eval-question-list-item">
    <div class="section1">
      <span class="question-number"> 평가 번호 : {{ idx + 1 }} </span>
      <i @click="changeflag()" class="bi bi-pencil-square"></i>
      <i @click="deleteQuestion()" class="bi bi-trash"></i>
    </div>
    <div v-if="updateflag">
      <eval-update :evalquestion="evalquestion" />
    </div>
    <div v-else class="section2">
      <span> 평가 항목 : {{ evalquestion.contents }} </span>
    </div>
  </div>
  <br />
</template>

<script>
import { mapActions } from "vuex";
import EvalUpdate from "./EvalUpdate.vue";
export default {
  components: {
    EvalUpdate,
  },
  props: {
    evalquestion: Object,
    idx: Number,
  },
  data() {
    return {
      updateflag: false,
    };
  },
  methods: {
    ...mapActions("company", ["deleteEvalQuestion"]),
    changeflag() {
      this.updateflag = !this.updateflag;
    },
    deleteQuestion() {
      if (confirm("삭제하시겠습니까?")) {
        this.deleteEvalQuestion(this.evalquestion.id);
      }
    },
  },
};
</script>

<style scope>
.eval-question-list-item {
  width: 900px;
  padding: 15px 20px;
  font-size: 16px;
  box-shadow: rgba(0, 0, 0, 0.15) 1.95px 1.95px 2.6px;
}
.eval-question-list-item > .section1 {
  display: flex;
  margin-bottom: 3px;
}
.eval-question-list-item > .section1 > .question-number {
  width: 100%;
  text-align: left;
  color: var(--color-red-2);
}
.eval-question-list-item > .section1 > i {
  color: var(--color-black-2);
  margin: auto 3px;
}
.eval-question-list-item > .section2 {
  width: 100%;
  text-align: left;
}
@media(max-width: 760px){
  .eval-question-list-item {
    width: 328px;
    padding: 5px 10px;
  }
}
</style>
