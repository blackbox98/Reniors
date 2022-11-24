<template>
<div class="total">
  <img
    :src="companyimg"
    alt="test"
    class="logo-img"
  />
  <div style="display:flex; justify-content:center;">
  <div>
  <div class="company-info-box">
    <div>{{ companyinfo.name }}</div>
    <div>{{ companyinfo.companyAppId }}</div>
    <div>{{ companyinfo.companyPhone }}</div>
  </div>
  <div class="process-box">
    <div class="left-box">
      <div>
        {{ jobopeninglisting.length }}
      </div>
      <div>채용중</div>
    </div>

    <div class="right-box">
      <div>
        {{ jobopeninglisted.length }}
      </div>
      <div>채용완료</div>
    </div>
  </div>
  </div>
  </div>
 
  <router-link :to="{ name: 'companyupdate' }" class="company-to-update-btn"
    >기업 정보 수정</router-link
  >
  </div>
</template>

<script>
import { mapActions, mapGetters, mapMutations } from "vuex";
export default {
  created() {
    this.CLEAR_JOBOPENING_LIST;
    this.getJobOpeningList();
    this.setheader("마이페이지")
    // this.getCompany();
  },
  computed: {
    ...mapGetters("company", [
      "jobopeninglisting",
      "jobopeninglisted",
      "companyinfo",
      "companyimg"
    ]),
    ...mapMutations("company", ["CLEAR_JOBOPENING_LIST"]),
  },
  methods: {
    ...mapActions("company", ["getJobOpeningList", "getCompany","setheader"]),
  },
};
</script>

<style scoped>
.total{
  margin-top: 64px;
  width: 100vw;
}
.logo-img {
  width: 120px;
  height: 120px;
  border-radius: 90%;
  border: 2px solid var(--color-black-4);
  margin-bottom: 20px;
}

.company-info-box {
  width: 360px;
}
.company-info-box > div:first-child {
  font-weight: bold;
  font-size: 20px;
  margin-bottom: 10px;
}
.process-box {
  width: 360px;
  display: flex;
  justify-content: space-around;
  margin-top: 10px;
  margin-bottom: 30px;
}
.process-box .left-box {
  height: 100%;
  width: 50%;
  border-right: 1px solid black;
}
.process-box .right-box {
  height: 100%;
  width: 50%;
  border-left: 1px solid black;
}
.company-to-update-btn {
  border-radius: 5px;
  background-color: var(--color-green-2);
  padding: 10px 50px;
  color: white;
}

.company-to-update-btn:link {
  color: white;
  text-decoration: none;
}
.company-to-update-btn:hover {
  background-color: var(--color-green-1);
  color: black;
}
</style>
