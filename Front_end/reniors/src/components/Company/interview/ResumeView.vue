<template>
  <div class="resume-info-box">
    <img :src="resume.baseURL + resume.userProfile" />
    <div class="resume-name">{{ resume.name }}</div>
    <div class="resume-base-info-box">
      <div>{{ resume.gender == "F" ? "여자" : "남자" }}</div>
      <div>
        {{ new Date(resume.birth).getFullYear() }}-{{
          new Date(resume.birth).getMonth()
        }}-{{ new Date(resume.birth).getDate() }}
      </div>
      <div>{{ resume.lastEdu }}</div>
    </div>

    <div>경력&emsp; {{ resume.totalCareer }}년</div>
    <div>
      <span style="color: #8a8a8a"><i class="bi bi-telephone"></i></span>&emsp;
      {{ resume.phone }}
    </div>
    <div class="location-info-box">
      <div class="location-title">
        <span style="color: #8a8a8a"><i class="bi bi-house-door"></i></span>
      </div>
      <div style="display: flex; line-height: 32px">
        <div>{{ resume.address }}</div>
        <div style="margin-right: 10px">,</div>
        <div>{{ resume.extraAddress }}</div>
      </div>
    </div>

    <div v-if="resume.careerDetails.length != 0">
      <hr />
      <p class="subtitle mb-3 sub-title">경력 사항</p>
      <p
        class="box pt-3 pb-3"
        v-for="career in resume.careerDetails"
        :key="career"
      >
        <span style="color: #8a8a8a">기업명</span
        ><span>&emsp;{{ career.companyName }}<br /></span>
        <span style="color: #8a8a8a">입사일</span
        ><span>&emsp;{{ career.startedAt.slice(0, 10) }}<br /></span>
        <span style="color: #8a8a8a">퇴사일</span
        ><span>&emsp;{{ career.finishedAt.slice(0, 10) }}<br /></span>
        <span style="color: #8a8a8a"></span><br /><span
          >{{ career.jobContents }}<br
        /></span>
      </p>
    </div>

    <div v-if="resume.licenses.length != 0">
      <hr />
      <p class="subtitle mb-3 sub-title">자격증</p>
      <p
        class="box pt-3 pb-3"
        v-for="(license, index) in resume.licenses"
        :key="index"
      >
        <span style="color: #8a8a8a">자격증</span
        ><span>&emsp;{{ license.name }}<br /></span>
        <span style="color: #8a8a8a">취득일</span
        ><span>&emsp;{{ license.passedAt.slice(0, 10) }}<br /></span>
        <span style="color: #8a8a8a">등급</span
        ><span>&emsp;&emsp;{{ license.grade }}<br /></span>
      </p>
    </div>

    <div v-if="resume.awards.length != 0">
      <hr />
      <p class="subtitle mb-3 sub-title">수상 내역</p>
      <p v-for="award in resume.awards" :key="award">
        <span style="color: #8a8a8a;">수상명</span
        ><span>&emsp;{{ award.name }}<br /></span>
        <span style="color: #8a8a8a">수상일</span
        ><span>&emsp;{{ award.awardedAt.slice(0, 10) }}<br /></span>
      </p>
    </div>
  </div>
</template>

<script>
import { mapActions, mapMutations, mapGetters } from "vuex";
export default {
  data() {
    return {
      birth: null,
      resumeinfo: null,
    };
  },
  props: {
    applyinfo: Object,
  },
  watch: {
    resume: function (data) {
      this.birth = new Date(data.birth);
      this.resumeinfo = data;
    },
  },
  computed: {
    ...mapGetters("company", ["resume"]),
    ...mapMutations("company", ["CLEAR_INTERVIEWER"]),
  },
  async created() {
    if (this.applyinfo) {
      await this.getResume(this.applyinfo.userId);
    } else {
      await this.getResume(this.$route.params.no);
    }
    await this.CLEAR_INTERVIEWER;
  },
  methods: {
    ...mapActions("company", ["getResume"]),
  },
};
</script>

<style scoped>
* {
  font-size: 20px;
}
.resume-info-box {
  width: 35vw - 48px;
  border: none;
  background-color: white;
  padding: 24px;
  margin: 24px 8px 24px 8px;
  border-radius: 5px 5px 0 0;
}
.resume-info-box > img {
  width: 90px;
  height: 120px;
  float: right;
  border-radius: 5px;
}
.resume-info-box > .resume-name {
  font-size: 24px;
  font-weight: bold;
  color: var(--color-green-1);
  border: none;
}
.resume-info-box > div {
  width: 100%;
  text-align: left;
  margin-top: 10px;
  font-size: 20px;
  margin-right: 20px;
}
/* .resume-base-info-box {
  
} */
.resume-base-info-box > div {
  display: inline-block;
  margin-right: 20px;
  margin-top: 4px;
}
.resume-info-box > .location-info-box {
  display: flex;
}
.resume-info-box > div > .location-title {
  line-height: 32px;
  margin-right: 20px;
}
.resume-info-box .sub-title {
  font-size: 20px;
  font-weight: bold;
}
</style>
