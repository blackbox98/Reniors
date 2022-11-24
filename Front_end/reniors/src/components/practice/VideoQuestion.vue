<template>
  <div class="total">
    <div>
      <div v-if="!selected" class="question" @click="onCheck(id)">
        <p>Q{{ idx + 1 }}. {{ question }}</p>
      </div>
      <div
        v-if="selected && !selected.includes(id)"
        class="question"
        @click="onCheck(id)"
      >
        <p>Q{{ idx + 1 }}. {{ question }}</p>
      </div>
      <div
        v-if="selected && selected.includes(id)"
        class="question"
        @click="onCheck(id)"
      >
        <p>Q{{ idx + 1 }}. {{ question }}</p>
        <div class="number">
          <p style="color: white; font-size: 16px; font-weight: bold">
            {{ selected.indexOf(id) + 1 }}
          </p>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { mapActions, mapGetters } from "vuex";

export default {
  name: "QuestionItem",
  components: {},
  props: {
    question: String,
    idx: Number,
    id: Number,
  },
  data() {
    return {};
  },
  methods: {
    ...mapActions(["pushSelected"]),
    onCheck(id) {
      this.pushSelected(id);
    },
  },
  computed: {
    ...mapGetters(["selected"]),
  },
};
</script>

<style scoped>
.number {
  background-color: #37bf99;
  border-radius: 30px;
  margin: 0 16px;
  width: 20px;
  height: 20px;
  display: flex;
  align-items: center;
  justify-content: center;
}
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
  display: flex;
  justify-content: space-between;
}
.question p {
  margin: 0px 8px 0px;
}
.question:hover {
  color: #ff843e;
}
@media (max-width: 760px) {
  .question {
    width: 336px;
  }
}
</style>
