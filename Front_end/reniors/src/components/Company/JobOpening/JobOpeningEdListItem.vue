<template>
  <div class="company-jobOpening-ing-list-item">
    <div class="title">
      {{ jobopening.title }}
    </div>
    <div class="jobOpening-detail-btn-box">
      <div>
        <router-link :to="{ name: 'companyjobopeningdetail', params: { no: jobopening.id } }">
          <i class="bi bi-search"></i>
        </router-link>
        <!-- <router-link>지원자n명</router-link> -->
      </div>   
      <div class="job-category">
        {{ jobopening.jobChildCategoryName }}
      </div>      
    </div>
 
  </div>

</template>

<script>
export default {
  props: {
    jobopening: Object,
  },
};
</script>

<style scoped>
.company-jobOpening-ing-list-item{
  width: 900px;
  padding: 20px;
  border-radius: 10px;
  margin-bottom: 16px;
  border-color: var(--color-black-2); 
  box-shadow: inset 0 0 1px 1px var(--color-black-3), 0 0 5px var(--color-black-3);
}
.company-jobOpening-ing-list-item > .title {
  width: 100%;
  text-align: left;
  font-weight: bold;
  font-size: 16px;
}
.company-jobOpening-ing-list-item > div >.job-category {
  width: 100%;
  text-align: right;
  font-size: 15px;
  color: var(--color-orange-2);
}
.jobOpening-detail-btn-box{
  display: flex;
  margin-top: 10px;
}
.jobOpening-detail-btn-box > div > a {
  text-decoration: none;
  color: var(--color-black-2);
}
.jobOpening-detail-btn-box > div > a > .bi-search {
  color: var(--color-black-2);
  font-size: 20px;
}
.jobOpening-detail-btn-box > div > a:link {
  color: var(--color-black-2);;
}
.jobOpening-detail-btn-box > div > a:visited {
  color: var(--color-black-2);;
}
@media(max-width: 760px){
  .company-jobOpening-ing-list-item {
    width: 328px;
  }
}
</style>
