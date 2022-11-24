<template>
  <div class="listbox formGroup">
    <div class="title">{{ index + 1 }}. {{ evalquestion.contents }}</div>
    <div class="box1">
      <input
        id="score"
        class="form-control score"
        type="number"
        v-model="usereval.score"
        placeholder="숫자만 입력 가능합니다."
      />
      <button class="submitBtn" @click="regist()">저장</button>
    </div>
    <div class="box">
      <textarea
        id="memo"
        class="form-control memo"
        type="text"
        v-model="usereval.memo"
        placeholder="메모를 남겨보세요."
      ></textarea>
    </div>
    <hr />
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
export default {
  props: {
    evalquestion: Object,
    applyinfo: Object,
    index: Number,
  },
  data() {
    return {
      usereval: {
        score: null,
        memo: null,
        evalQuestionId: this.evalquestion.id,
      },
    };
  },
  computed: {
    ...mapGetters("company", ["interviewer"]),
  },
  methods: {
    ...mapActions("company", ["registUserEval"]),
    regist() {
      if (confirm("점수를 저장하시겠습니까?")) {
        let data = {
          jobOpeningId: this.applyinfo.jobOpeningId,
          userId: this.applyinfo.userId,
          usereval: this.usereval,
        };
        this.registUserEval(data);
      }
    },
  },
};
</script>

<style scoped>
.title {
  font-size: 28px;
  font-weight: bold;
  text-align: left;
}
label {
  display: flex;
  justify-content: start;
  font-size: 14px;
  color: #3a3a3a;
}
.score {
  height: 5vh;
  width: 26vw;
  margin: 0 1vw 0 0;
}
.memo {
  height: 15vh;
  padding: 8px 10px 8px 10px;
  margin: 0 0 4px 0;
  resize: none;
}
.formGroup {
  margin: 8px 4px 0 4px;
}
.box {
  margin: 4px;
}
.box1 {
  margin: 4px;
  display: flex;
  align-items: center;
}
.submitBtn {
  width: 4vw;
  height: 5vh;
  margin: 0;
  border: none;
  border-radius: 5px;
  background-color: #ffb252;
  color: white;
  font-size: 16px;
  font-weight: bold;
}
</style>
