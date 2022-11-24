<template>
  <div>
    <div class="box">
      <button @click="deleteAward(this.award.id)" style="margin-right: 4px">
        <img class="DELETE" src="@/assets/DELETE.svg" alt="DELETE" />
      </button>
      <button @click="updateAward">
        <img class="EDIT" src="@/assets/EDIT.svg" alt="EDIT" />
      </button>
      <p class="name">{{ this.award.name }}</p>

      <p class="title">취득 날짜</p>
      <p class="contents">{{ this.date }}</p>
    </div>
    <resume-award-form
      v-show="editshow"
      :award="award"
      @test="test"
      action="update"
    ></resume-award-form>
  </div>
</template>
<script>
import ResumeAwardForm from "@/components/resume/ResumeAwardForm.vue";
import { mapActions } from "vuex";

export default {
  name: "ResumeAwardDetail",
  props: {
    award: Object,
  },
  components: {
    ResumeAwardForm,
  },
  data() {
    var date = this.award.awardedAt.substring(0, 10);
    return {
      date,
      editshow: false,
    };
  },
  methods: {
    ...mapActions(["deleteAward"]),
    updateAward() {
      this.editshow = !this.editshow;
    },
    test() {
      this.editshow = !this.editshow;
    },
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
};
</script>

<style scoped>
p {
  margin-bottom: 0px;
  text-align: left;
}

.box {
  border-radius: 10px;
  background-color: #f9f9f9;
  box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.25);

  margin-bottom: 20px;
  padding: 15px;
}

.title {
  font-size: 12px;
  margin-bottom: 1px;
  margin-left: 1px;
  color: #6d6d6d;
}

.name {
  margin-bottom: 20px;
  font-weight: 700;
  color: black;
}

.contents {
  margin-bottom: 2px;
  font-weight: 500;
  color: black;
}

button {
  float: right;
  margin: 2px;
  border: none;
  background-color: transparent;
}
/* .during{
  font-size: 13px;
}

.company {
  font-weight: 700;
  margin-bottom: 5px;
} */
</style>
