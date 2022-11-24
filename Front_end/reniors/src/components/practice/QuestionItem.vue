<template>
  <div class="total">
    <div v-if="!checklist.includes(id)">
      <router-link
        :to="{ name: 'QuestionAnswer', params: { question_id: id } }"
      >
        <div class="question">
          <p>Q{{ idx + 1 }}. {{ question }}</p>
        </div>
      </router-link>
    </div>
    <div v-if="checklist.includes(id)">
      <router-link :to="{ name: 'AnswerUpdate', params: { question_id: id } }">
        <div class="question2">
          <p>Q{{ idx + 1 }}. {{ question }}</p>
          <i
            class="bi bi-check-circle-fill"
            style="color: #37bf99; font-size: 20px; margin: 0 16px"
          ></i>
        </div>
      </router-link>
    </div>
  </div>
</template>
<script>
export default {
  name: "QuestionItem",
  components: {},
  props: {
    question: String,
    idx: Number,
    id: Number,
    checklist: Array,
  },
  data() {
    return {};
  },
};
</script>

<style scoped>
.total {
  display: flex;
  justify-content: center;
  width: 360px;
}
a {
  text-decoration: none;
  color: black;
}
a:hover {
  color: #ff843e;
}
.question {
  margin: 4px;
  padding-left: 8px;
  height: 88px;
  width: 700px;
  border-radius: 10px;
  border: 1px solid #ff843e;
  box-shadow: 1px 1px 1px gray;
  display: flex;
  align-items: center;
  background-color: white;
}
.question p {
  margin: 0px 8px 0px;
}
.question2 {
  margin: 4px;
  padding-left: 8px;
  height: 88px;
  width: 700px;
  border-radius: 10px;
  border: 1px solid #ff843e;
  box-shadow: 1px 1px 1px gray;
  display: flex;
  align-items: center;
  background-color: white;
  display: flex;
  justify-content: space-between;
}
.question2 p {
  margin: 0px 8px 0px;
}
@media (max-width: 760px) {
  .question {
    width: 336px;
  }
  .question2 {
    width: 336px;
  }
}
</style>
