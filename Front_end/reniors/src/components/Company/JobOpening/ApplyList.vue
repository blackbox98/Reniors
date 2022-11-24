<template>
  <div class="company-jobOpening-info-box">
    <div>
      <div class="title">
        {{ jobopeningdetail.title }}
      </div>
      <div class="sub-title">
        {{ jobopeningdetail.jobOpeningProcess }}
      </div>
    </div>
    <div class="job-category">{{ jobopeningdetail.jobChildCategoryName }}</div>
    <div class="apply-number">
      지원자 수
      <p>{{ jobopeningdetail.applies }}</p>
      명
    </div>
  </div>
  <div class="tab-btn-box">
    <span
      class="tab-btn"
      :class="{ 'now-tab': this.progress == '서류심사중' }"
      @click="change('서류심사중')"
      >서류</span
    >
    <span
      class="tab-btn"
      :class="{ 'now-tab': this.progress == '면접심사중' }"
      @click="change('면접심사중')"
      >면접</span
    >
    <span
      class="tab-btn"
      :class="{ 'now-tab': this.progress == '최종합격' }"
      @click="change('최종합격')"
      >합격</span
    >
  </div>
  <div class="apply-list-box">
    <template v-if="this.progress == '서류심사중'">
      <applier-resume-list
        :jobopeningdetail="jobopeningdetail"
      ></applier-resume-list>
    </template>

    <template v-if="this.progress == '면접심사중'">
      <applier-interview-list
        :jobopeningdetail="jobopeningdetail"
      ></applier-interview-list>
    </template>

    <template v-if="this.progress == '최종합격'">
      <applier-pass-list
        :jobopeningdetail="jobopeningdetail"
      ></applier-pass-list>
    </template>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
// import ApplyListItem from "./ApplyListItem.vue";
import ApplierResumeList from "./ApplierResumeList.vue";
import ApplierInterviewList from "./ApplierInterviewList.vue";
import ApplierPassList from "./ApplierPassList.vue";
export default {
  components: { ApplierResumeList, ApplierInterviewList, ApplierPassList },
  data() {
    return {
      jobopeningdetail: {},
      progress: "",
    };
  },
  async created() {
    await this.getJobOpening(this.$route.params.no);
    this.setheader("지원자관리");
  },
  mounted() {},
  watch: {
    jobopening: function (data) {
      this.jobopeningdetail = data;
      this.progress = data.jobOpeningProcess;
    },
  },
  computed: {
    ...mapGetters("company", ["jobopening"]),
  },
  methods: {
    ...mapActions("company", ["getJobOpening", "setheader"]),
    change(data) {
      this.progress = data;
    },
  },
};
</script>

<style scoped>
.company-jobOpening-info-box {
  width: 900px;
  padding: 20px;
  border-radius: 10px;
  margin-bottom: 16px;
  border-color: var(--color-black-2);
  box-shadow: inset 0 0 1px 1px var(--color-black-3),
    0 0 5px var(--color-black-3);
  margin-top: 56px;
}
.company-jobOpening-info-box > div > .title {
  font-size: 16px;
  font-weight: bold;
  width: 100%;
  text-align: left;
}
.company-jobOpening-info-box > div > .sub-title {
  font-size: 14px;
  font-weight: bold;
  width: 100%;
  text-align: left;
}
.company-jobOpening-info-box > div > p {
  font-size: 16px;
  font-weight: bold;
  display: inline;
}
.company-jobOpening-info-box > .job-category {
  width: 100%;
  text-align: right;
  font-size: 12px;
  color: var(--color-orange-2);
}
.company-jobOpening-info-box > .apply-number {
  padding: 5px 10px;
  background-color: var(--color-red-1);
  border-radius: 5px;
  font-size: 15px;
  color: white;
  box-shadow: rgba(50, 50, 93, 0.25) 0px 2px 5px -1px,
    rgba(0, 0, 0, 0.3) 0px 1px 3px -1px;
}
.apply-list-box {
  width: 900px;
  border-color: var(--color-black-2);
  -webkit-box-shadow: 1px 16px 18px -2px var(--color-black-3);
  box-shadow: 1px 16px 18px -2px var(--color-black-3);
  padding-bottom: 0;
  margin-bottom: 100px;
  border-top: none;
}
.tab-btn-box {
  display: flex;
  justify-content: space-between;
  width: 900px;
}
.tab-btn-box > .tab-btn {
  width: 30%;
  border: 1px solid var(--color-black-3);
  padding: 8px;
  border-radius: 5px 5px 0 0;
  font-weight: bold;
  font-size: 14px;
  line-height: 20px;
  text-align: center;
}
.now-tab {
  background-color: var(--color-green-1);
  color: white;
  width: 40% !important;
  border-bottom: none !important;
  font-size: 20px !important;
}
@media (max-width: 760px) {
  .company-jobOpening-info-box {
    width: 328px;
  }
  .apply-list-box{
    width: 328px;
  }
  .tab-btn-box{
    width: 328px;
  }
}
</style>
