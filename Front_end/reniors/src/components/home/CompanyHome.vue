<template>
  <img style="width: 80%" alt="logo" src="@/assets/logo.png" />
  <div class="company-main-btn">
    <div class="company-main-btn-top">
      <div @click="jobopening()" class="company-main-btn-top-item">
        <div>
          <i class="bi bi-file-earmark-text"></i>
        </div>
        <div>채용관리</div>
      </div>

      <div @click="interview()" class="company-main-btn-top-item">
        <div>
          <i class="bi bi-camera-video"></i>
        </div>
        <div>면접</div>
      </div>
    </div>

    <div class="company-main-btn-bottom">
      <div @click="mypage()" class="company-main-btn-bottom-item">
        <div>
          <i class="bi bi-person"></i>
        </div>
        <div>마이페이지</div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions } from "vuex";

export default {
  methods: {
    ...mapActions("company", ["setheader", "getCompany"]),
    jobopening() {
      this.setheader("채용관리");
      this.$router.push({ name: "companyjobopening" });
    },
    interview() {
      this.setheader("면접");
      this.$router.push({ name: "companyinterview" });
    },
    mypage() {
      this.setheader("마이페이지");
      this.$router.push({ name: "companymypage" });
    },
  },
  created() {
    this.getCompany();
  },
};
</script>

<style scoped>
/* .company-main-btn{
  display: flex;
} */
.company-main-btn-top {
  display: flex;
  justify-content: center;
}
.company-main-btn-top-item {
  /* position: relative; */
  width: 150px;
  height: 200px;
  margin: 12px;
  background-color: #37bf99;
  color: #eaeaea;
  border-radius: 12px;
  font-weight: bold;
  box-shadow: 0.4px 0.4px 0.4px 0.4px gray;
  cursor: pointer;
}
.company-main-btn-top-item > div:first-child {
  margin-top: 50px;
}
.company-main-btn-top-item > div > i {
  font-size: 44px;
}
.company-main-btn-top-item > div > div {
  font-size: 20px;
}
.company-main-btn-bottom {
  display: flex;
  justify-content: center;
}
.company-main-btn-bottom-item {
  width: 150px;
  height: 200px;
  background-color: #eaeaea;
  color: #6d6d6d;
  border-radius: 12px;
  font-weight: bold;
  box-shadow: 0.4px 0.4px 0.4px 0.4px gray;
  cursor: pointer;
}
.company-main-btn-bottom-item > div:first-child {
  margin-top: 50px;
}
.company-main-btn-bottom-item > div > i {
  font-size: 44px;
}
.company-main-btn-bottom-item > div > div {
  font-size: 20px;
}
</style>
