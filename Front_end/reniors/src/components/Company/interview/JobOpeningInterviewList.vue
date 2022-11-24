<template>
  <div class="total">
  <div class="company-jobOpening-list-title">면접 공고</div>
  <div class="jobopening-interview-list-item-box">
    <job-opening-interview-list-item
      v-for="jobopening in jobopeninglisting"
      :key="jobopening.id"
      :jobopening="jobopening"
    ></job-opening-interview-list-item>
  </div>
  </div>
</template>

<script>
import { mapActions, mapMutations, mapGetters } from "vuex";
import JobOpeningInterviewListItem from "./JobOpeningInterviewListItem.vue";
export default {
  components: { JobOpeningInterviewListItem },
  created() {
    this.CLEAR_JOBOPENING_LIST;
    this.getJobOpeningList();
  },
  computed: {
    ...mapGetters("company", ["jobopeninglisting"]),
    ...mapMutations("company", ["CLEAR_JOBOPENING_LIST"]),
  },
  methods: {
    ...mapActions("company", ["getJobOpeningList"]),
    regjobopening() {
      this.$router.push({ name: "companyjobopeningregist" });
    },
  },
};
</script>

<style scoped>
.total{
  margin-top: 48px;
}
.company-jobOpening-list-title {
    font-size: 24px;
    font-weight: 1000;
    width: 320px;
    text-align: left;
    margin-top: 35px;
    margin-bottom: 22px;
}
.jobopening-interview-list-item-box {
  margin-bottom: 100px;
}
</style>
