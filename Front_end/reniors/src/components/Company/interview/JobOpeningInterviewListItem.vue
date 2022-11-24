<template>
  <div class="company-jobOpening-ing-list-item">
    <div class="title">
      {{ jobopening.title }}
    </div>
    <div class="job-category">
      {{ jobopening.jobChildCategoryName }}
    </div>
    <div class="period">
      {{ dateFormat(jobopening.createdDate) }} ~
      {{ dateFormat(jobopening.finishedDate) }}
    </div>

    <div class="jobOpening-detail-btn-box">
      <router-link
        :to="{ name: 'companyjobopeningdetail', params: { no: jobopening.id } }"
      >
        <i class="bi bi-gear"></i>
      </router-link>
      <div>
        <button class="to-apply-list-btn">
          <router-link
            :to="{
              name: 'companyinterviewlist',
              params: { no: jobopening.id },
            }"
          >
            면접일정 관리
          </router-link>
        </button>
        <button class="to-apply-list-btn">
          <router-link
            :to="{ name: 'companyeval', params: { no: jobopening.id } }"
          >
            면접평가 관리
          </router-link>
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import dayjs from "dayjs";

export default {
  props: {
    jobopening: Object,
  },
  methods: {
    interview() {
      this.$router.push({
        name: "companyinterviewlist",
        params: { no: this.jobopening.id },
      });
    },
    eval() {
      this.$router.push({
        name: "companyeval",
        params: { no: this.jobopening.id },
      });
    },
    dateFormat(val) {
      return dayjs(val).format("YYYY-MM-DD");
    },
  },
};
</script>

<style scoped>
.company-jobOpening-ing-list-item {
  width: 900px;
  padding: 20px;
  border-radius: 10px;
  margin-bottom: 16px;
  border-color: var(--color-black-2);
  box-shadow: inset 0 0 1px 1px var(--color-black-3),
    0 0 5px var(--color-black-3);
}
.company-jobOpening-ing-list-item > .title {
  width: 100%;
  text-align: left;
  font-weight: bold;
  font-size: 17px;
}
.company-jobOpening-ing-list-item > .job-category {
  width: 100%;
  text-align: right;
  font-size: 14px;
  color: var(--color-green-1);
}
.company-jobOpening-ing-list-item > .period {
  width: 100%;
  text-align: right;
  font-size: 14px;
  color: var(--color-green-1);
}
.jobOpening-detail-btn-box {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 10px;
}
.jobOpening-detail-btn-box > div > .to-apply-list-btn > a {
  text-decoration: none;
}
.jobOpening-detail-btn-box > a > .bi-gear {
  font-size: 20px;
  color: var(--color-black-2);
}
.to-apply-list-btn {
  height: 40px;
  margin: auto 3px;
  background-color: var(--color-red-2);
  border-radius: 8px;
  border: none;
  font-size: 16px;
  box-shadow: rgba(50, 50, 93, 0.25) 0px 6px 12px -2px,
    rgba(0, 0, 0, 0.3) 0px 3px 7px -3px;
}
.to-apply-list-btn > a:link {
  color: white;
}
.to-apply-list-btn > a:visited {
  color: white;
}
@media(max-width: 760px){
  .company-jobOpening-ing-list-item {
    width: 328px;
  }
}
</style>
