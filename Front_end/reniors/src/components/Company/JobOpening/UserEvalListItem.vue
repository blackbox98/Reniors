<template>
  <div class="usereval-list-item">
    <div class="eval-number">평가 번호 : {{ idx + 1 }}</div>
    <span class="question-title">· 평가 항목 :</span>
    <span>&nbsp;{{ usereval.contents }}</span>
    <br />
    <span class="question-title">· 평가 점수 :</span>
    <span>&nbsp;{{ usereval.score }}</span>
    <br />
    <span class="question-title">· 비고</span>
    <textarea v-model="memo" readonly></textarea>
  </div>
  <hr />
</template>

<script>
export default {
  props: {
    usereval: Object,
    idx: Number,
  },
  data() {
    return {
      memo: this.usereval.memo,
    };
  },
};
</script>

<style scope>
.usereval-list-item {
  width: 900px;
  padding: 10px 15px;
  font-size: 16px;
  text-align: left;
  border-radius: 8px;
  box-shadow: rgba(50, 50, 93, 0.25) 0px 2px 5px -1px,
    rgba(0, 0, 0, 0.3) 0px 1px 3px -1px;
}

.usereval-list-item > .eval-number {
  width: 100%;
  margin-bottom: 3px;
  text-align: left;
  font-weight: bold;
  color: var(--color-red-2);
}

.usereval-list-item > span {
  color: var(--color-black-1);
}

.usereval-list-item > .question-title {
  font-weight: bold;
}

.usereval-list-item > textarea {
  width: 100%;
  color: var(--color-black-1);
  padding: 8px;
  border-radius: 5px;
  background-color: var(--color-orange-3);
  border: none;
  box-shadow: rgba(60, 64, 67, 0.3) 0px 1px 2px 0px,
    rgba(60, 64, 67, 0.15) 0px 1px 3px 1px;
  resize: none;
}
@media(max-width: 760px){
  .usereval-list-item {
  width: 328px;
  }
}
</style>
