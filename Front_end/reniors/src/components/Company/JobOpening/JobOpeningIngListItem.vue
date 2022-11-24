<template>
  <div class="company-jobOpening-ing-list-item">
    <div class="title">
      {{ jobopening.title }}
    </div>
    <div class="sub-title">
      {{ jobopening.jobOpeningProcess }}
    </div>
    <div class="job-category">{{ jobopening.jobChildCategoryName }}</div>

    <div class="jobOpening-detail-btn-box">
      <router-link
        :to="{
          name: 'companyjobopeningdetail',
          params: { no: this.jobopening.id },
        }"
      >
        <i class="bi bi-gear"></i>
      </router-link>

      <router-link
        :to="{ name: 'applylist', params: { no: this.jobopening.id } }"
        class="to-apply-list-btn"
      >
        <i class="bi bi-person-lines-fill"></i> 지원자
        <p>{{ jobopening.applies }}</p>
        명
      </router-link>
    </div>
  </div>
</template>

<script scoped>
export default {
  props: {
    jobopening: Object,
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
  font-size: 16px;
}
.company-jobOpening-ing-list-item > .sub-title {
  width: 100%;
  text-align: left;
  font-weight: bold;
  font-size: 14px;
}
.company-jobOpening-ing-list-item > .job-category {
  width: 100%;
  text-align: right;
  font-size: 12px;
  color: var(--color-orange-2);
}
.jobOpening-detail-btn-box {
  display: flex;
  justify-content: space-between;
  margin-top: 10px;
}
.jobOpening-detail-btn-box > a {
  text-decoration: none;
  color: var(--color-black-2);
}
.jobOpening-detail-btn-box > a > .bi-gear {
  font-size: 20px;
}
.to-apply-list-btn:link {
  color: white;
}
.to-apply-list-btn:visited {
  color: white;
}
.to-apply-list-btn {
  padding: 5px 10px;
  background-color: var(--color-red-1);
  border-radius: 5px;
  font-size: 15px;
  box-shadow: rgba(50, 50, 93, 0.25) 0px 2px 5px -1px,
    rgba(0, 0, 0, 0.3) 0px 1px 3px -1px;
}
.to-apply-list-btn > p {
  font-size: 16px;
  font-weight: bold;
  display: inline;
}
@media(max-width: 760px){
  .company-jobOpening-ing-list-item {
    width: 328px;
  }
}
</style>
