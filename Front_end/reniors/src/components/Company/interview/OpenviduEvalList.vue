<template>
  <div class="eval-total">
    <openvidu-eval-list-item
      v-for="(evalquestion, index) in evalquestionlist"
      :index="index"
      :key="evalquestion.id"
      :evalquestion="evalquestion"
      :applyinfo="applyinfo"
    ></openvidu-eval-list-item>

    <button @click="finish()" class="finishBtn">평가 마치기</button>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
import OpenviduEvalListItem from "./OpenviduEvalListItem.vue";
export default {
  components: {
    OpenviduEvalListItem,
  },
  props: {
    applyinfo: Object,
  },
  created() {
    this.getEvalQuestionList(this.applyinfo.jobOpeningId);
  },
  computed: {
    ...mapGetters("company", ["evalquestionlist"]),
  },
  methods: {
    ...mapActions("company", [
      "getEvalQuestionList",
      "finishInterview",
      "updateApply",
    ]),
    ...mapActions("home", ["createNotice"]),
    finish() {
      let apply = {
        jobOpeningProcess: "면접심사중",
        interviewDate: null,
        sessionId: null,
      };
      this.updateApply({
        jobOpeningId: this.applyinfo.jobOpeningId,
        applyId: this.applyinfo.id,
        apply: apply,
      });
      this.createNotice({
        jobOpeningProcess: "면접심사중",
        userId: this.applyinfo.userId,
        applyId: this.applyinfo.id,
      });
      alert("평가가 완료되었습니다.");
    },
  },
};
</script>

<style>
.eval-total {
  width: 35vw - 48px;
  height: 56vh;
  border: none;
  background-color: white;
  padding: 24px;
  margin: 0 8px;
  border-radius: 5px 5px 0 0;
}
.finishBtn {
  width: 10vw;
  height: 6vh;
  border: none;
  border-radius: 10px;
  background-color: #8cd6c1;
  color: white;
  font-size: 20px;
  text-align: center;
  margin-bottom: 24px;
}
</style>
