<template>
    <div class="header">
      <i @click="prevpage()" class="bi bi-arrow-left-circle-fill"></i>
      <router-link class="header-logo" :to="{ name: 'home' }">
        <img src="@/assets/logo_cut.png" alt="logo" />
      </router-link>
      <div></div>
    </div>
  <div class="total">
    <div class="head2">
      <router-link class="mx-3 rl" :to="{ name: 'QuestionList' }"
        >답변 작성</router-link
      >
      <router-link class="mx-3 rl" :to="{ name: 'VideoPracticeList' }"
        >화상 연습</router-link
      >
    </div>
    <div class="headsub">
      <p
        class="my-0"
        style="text-align: start; font-size: 16px"
      >
        연습하고 싶은 질문을 선택하고
      </p>
      <p style="text-align: start; font-size: 16px">
        화상면접을 경험해보세요!
      </p>
    </div>

    <video-question
      v-for="(question, idx) in questions"
      :key="idx"
      :question="question.question"
      :idx="idx"
      :id="question.id"
    ></video-question>
    <div style="margin-bottom: 200px;"></div>
    <router-link :to="{ name: 'PracticePage' }">
      <div class="submit">
        <button class="Btn">연습하기</button>
      </div>
    </router-link>
  </div>
</template>
<script>
import VideoQuestion from "@/components/practice/VideoQuestion.vue";
import { mapActions, mapGetters } from "vuex";
export default {
  name: "VideoPracticeList",
  components: { VideoQuestion },
  data() {
    return {
      sampleData: "",
    };
  },
  setup() {},
  async created() {
    this.fetchQuestions();
    await this.clearSelected();
  },
  computed: {
    ...mapGetters(["questions"]),
  },
  methods: {
    ...mapActions(["fetchQuestions", "clearSelected"]),
        prevpage() {
      this.$router.go(-1);
    },
  },
};
</script>

<style scoped>
.header {
  height: 48px;
  background-color: white;
  box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px -2px;
  display: flex;
  align-items: center;
  justify-content: space-around;
  position: fixed;
  width: 100vw;
  top: 0;
}
.header img {
  height: 38px;
  margin: auto;
  width: auto;
}
.header > .bi {
  color: var(--color-red-1);
  font-size: 30px;
  cursor: pointer;
}
.header > div {
  width: 30px;
}
.total {
  background-color: #fff5f0;
  width: 100%;
}
.headsub {
  margin: 16px auto;
  width: 336px;
  padding-left: 8px;
}
.rl {
  text-decoration: none;
  color: white;
}
.rl:hover {
  font-weight: bold;
}

.head2 {
  background-color: #ff843e;
  height: 48px;
  display: flex;
  align-items: center;
  justify-content: space-around;
  margin-top: 48px;
}

.head2 a.router-link-exact-active {
  font-weight: bold;
}

.question {
  margin: 4px 20px;
  padding-left: 8px;
  height: 88px;
  width: 320px;
  border-radius: 10px;
  border: 1px solid #ff843e;
  box-shadow: 1px 1px 1px gray;
  display: flex;
  align-items: center;
}
p {
  margin: 0px;
}
.submit {
  position: fixed;
  left: 0;
  display: flex;
  justify-content: center;
  bottom: 50px;
  width: 100%;
  margin: auto;
  background-color: white;
  border-top: solid 0.5px #ffedbf;
}
.Btn {
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: var(--color-red-2);
  height: 40px;
  width: 340px;
  margin: 8px auto;
  border-radius: 10px;
  border: none;
  color: white;
  font-weight: bold;
  font-size: 16px;
  cursor: pointer;
}
</style>
