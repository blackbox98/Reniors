<template>
<div>
  <div class="company-jobOpening-detail-box">
    <div>
      <p class="title">{{ jobopening.title }}</p>
      <p class="date">마감일 : {{ jobopening.finishedDate.split('T')[0]}}</p>
      <p v-if="jobopening.jobOpeningProcess == '최종합격'" class="finish">채용 마감</p>
      <p v-else class="ing">{{jobopening.jobOpeningProcess}}</p>
    </div>
    
    <router-link
      :to="{
        name: 'companyjobopeningupdate',
        params: { no: this.$route.params.no },
      }"
      class="detail-btn"
    >
      수정
    </router-link>
    <button @click="deletejobopening()" class="detail-btn">삭제</button>
    <button @click="finish()" class="detail-btn">채용 종료</button>
  </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
export default {
  data() {
    return {
      jobopeningdetail: {},
    };
  },
  created() {
    this.getJobOpening(this.$route.params.no);
  },
  watch: {},
  computed: {
    ...mapGetters("company", ["jobopening"]),
  },
  methods: {
    ...mapActions("company", [
      "getJobOpening",
      "deleteJobOpening",
      "finishJobOpening",
    ]),
    deletejobopening() {
      if (confirm("공고를 삭제하시겠습니까?")) {
        this.deleteJobOpening(this.$route.params.no);
        this.$router.push({ name: "companyjobopeninglist" });
      }
    },
    finish() {
      if (confirm("채용을 종료하시겠습니까?")) {
        this.finishJobOpening(this.jobopening.id);
        this.$router.push({ name: "companyjobopeninglist" });
      }
    },
  },
};
</script>

<style scoped>
.company-jobOpening-detail-box {
  padding: 20px;
  margin-top: 20px;
  border: none;
  margin-top: 64px;
}
.company-jobOpening-detail-box .title {
  font-size: 40px;
  font-weight: bold;
}
.company-jobOpening-detail-box .date {
  font-size: 24px;
}
.company-jobOpening-detail-box .finish {
  font-size: 24px;
  color: var(--color-red-1);
}
.company-jobOpening-detail-box .ing {
  font-size: 24px;
  color: var(--color-green-1);
}

.company-jobOpening-detail-box > .detail-btn {
  width: 320px;
  display: block;
  margin: auto;
  margin-top: 10px;
  background-color: var(--color-green-1);
  border-radius: 5px;
  border: none;
  padding: 5px;
  color: white;
  font-size: 20px;
  font-weight: bold;
  text-decoration: none;
}
.company-jobOpening-detail-box > .detail-btn:link {
  color: white;
}
.company-jobOpening-detail-box > .detail-btn:visited {
  color: white;
}
</style>
