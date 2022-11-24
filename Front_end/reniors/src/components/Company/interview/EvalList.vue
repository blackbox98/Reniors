<template>
  <div class="eval-list-item-box">
    <eval-list-item
      v-for="(evalquestion, idx) in evalquestionlist"
      :key="evalquestion.id"
      :idx="idx"
      :evalquestion="evalquestion"
    ></eval-list-item>
    <div v-if="registflag">
      <eval-regist @fetch="fetch" />
    </div>
    <div class="add-flag">
      <p @click="changeflag()">
        <i class="bi bi-plus-circle"></i> 평가 항목 추가
      </p>
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
import EvalRegist from "./EvalRegist.vue";
import EvalListItem from "./EvalListItem.vue";
export default {
  components: {
    EvalRegist,
    EvalListItem,
  },
  data() {
    return {
      registflag: false,
    };
  },
  created() {
    this.getEvalQuestionList(this.$route.params.no);
    this.setheader("면접평가");
  },
  computed: {
    ...mapGetters("company", ["evalquestionlist"]),
  },
  methods: {
    ...mapActions("company", ["getEvalQuestionList", "setheader"]),
    changeflag() {
      this.registflag = !this.registflag;
    },
  },
};
</script>

<style scope>
.add-flag {
  display: flex;
  justify-content: center;
  font-size: 18px;
  font-weight: bold;
  color: var(--color-green-1);
}
.eval-list-item-box {
  margin-bottom: 6vh;
  margin-top: 64px;
}
</style>
