<template>
  <div>
    <div class="box">
      <button @click="deleteCareer(this.career.id)" style="margin-right: 4px">
        <img class="DELETE" src="@/assets/DELETE.svg" alt="DELETE" />
      </button>
      <button @click="updateCareer">
        <img class="EDIT" src="@/assets/EDIT.svg" alt="EDIT" />
      </button>
      <p class="title">입사날짜</p>
      <p class="contents">{{ this.start }}</p>
      <p class="title">퇴사날짜</p>
      <p class="contents">{{ this.finish }}</p>
      <p class="title">기업</p>
      <p class="contents" style="font-weight: 700">
        {{ this.career.companyName }}
      </p>
      <hr />
      <p class="contents">{{ this.career.jobContents }}</p>
    </div>
    <resume-career-form
      v-show="editshow"
      :career="career"
      @test="test"
      action="update"
    ></resume-career-form>
  </div>
</template>
<script>
import ResumeCareerForm from "@/components/resume/ResumeCareerForm.vue";
import { mapActions } from "vuex";

export default {
  name: "ResumeCareerDetail",
  props: {
    career: Object,
    action: String,
  },
  components: {
    ResumeCareerForm,
  },
  data() {
    var start = this.career.startedAt.substring(0, 10);
    var finish = this.career.finishedAt.substring(0, 10);
    return {
      start,
      finish,
      editshow: false,
    };
  },
  methods: {
    ...mapActions(["deleteCareer"]),
    updateCareer() {
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
