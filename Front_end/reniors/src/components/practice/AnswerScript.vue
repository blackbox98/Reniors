<template>
  <div class="total">
    <div v-if="checklist.includes(selectedQ[idx])" class="answer">
      {{ answer.answer }}
    </div>
    <div v-if="!checklist.includes(selectedQ[idx])" class="answer">
      <p></p>
    </div>
  </div>
</template>
<script>
import { mapActions, mapGetters } from "vuex";

export default {
  name: "AnswerScript",
  components: {},
  props: {
    idx: Number,
    checklist: Array,
    selectedQ: Array,
  },
  data() {
    return {
      sampleData: "",
    };
  },
  computed: {
    ...mapGetters(["answer"]),
  },
  async created() {
    if (this.checklist.includes(this.selectedQ[this.idx])) {
      await this.fetchAnswer(this.selectedQ[this.idx]);
    }
  },

  methods: {
    ...mapActions(["fetchAnswer"]),
  },
  watch: {
    idx: function (data) {
      if (this.checklist.includes(this.selectedQ[this.idx])) {
        this.fetchAnswer(this.selectedQ[data]);
      }
    },
  },
};
</script>

<style scoped>
.total {
  display: flex;
  justify-content: center;
  animation: fadeInUp 1s;
}
@keyframes fadeInUp {
  from {
    opacity: 0;
    transform: translateY(20px);
  }
  to {
    opacity: 1;
    transform: none;
  }
}
.answer {
  background-color: white;
  overflow: scroll;
  padding: 2px;
  /* position: fixed; */
  bottom: 120px;
  height: 150px;
  width: 600px;
  border: solid #d9d9d9 1px;
  border-radius: 5px;
}
@meida (max-width:760px) {
  .answer {
    width: 344px;
  }
}
</style>
