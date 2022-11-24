<template>
  <div class="company-header">
    <div class="company-left-header">
      <i @click="prevpage()" class="bi bi-arrow-left-circle-fill"></i>
      <div class="company-header-msg">{{ this.header }}</div>
    </div>
    <!-- <div class="company-right-header">
      <i @click="mainpage()" class="bi bi-house"></i>
      <div class="company-header-profile">
        <img
          :src="this.companyimg"
          alt="test"
          class="company-header-img"
        />
        <div class="company-header-profile-dropdown">
        <router-link :to="{ name: 'companymypage' }" class="company-header-profile-dropdown-item">마이페이지</router-link> <br>
        <a class="company-header-profile-dropdown-item" @click="logout()">로그아웃</a> 
      </div>
      </div>
    </div> -->
  </div>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  computed: {
    ...mapGetters("company", ["header", "companyimg"]),
  },
  methods: {
    prevpage() {
      this.$router.go(-1);
    },
    mainpage() {
      this.$router.push({ name: "company" });
    },
    logout() {
      localStorage.removeItem("vuex");
      localStorage.removeItem("token");
      this.$router.push({ name: "Login" });
    },
  },
};
</script>

<style scoped>
.company-header-img {
  width: 40px;
  height: 40px;
  padding-bottom: 3px;
}
.company-header {
  display: flex;
  justify-content: space-between;
  width: 100vw;
  z-index: 1;
}
.company-header > div > .bi {
  width: 25px;
  height: 25px;
  color: #8cd6c1;
  font-size: 25px;
  cursor: pointer;
}
.company-header-msg {
  display: flex;
  justify-content: flex-start;
  margin-left: 12px;
  margin-top: 5.6px;
  width: 108px;
  color: #37bf99;
  font-weight: bold;
  /* margin-left: 10px; */
}
.company-left-header {
  display: flex;
  justify-content: space-between;
  width: 120px;
  margin-left: 12px;
}
.company-right-header {
  display: flex;
  justify-content: space-between;
  width: 80px;
  margin-right: 4px;
}
.company-header-profile {
  position: relative;
  display: inline-block;
}

.company-header-profile-dropdown {
  display: none;
  position: absolute;
  z-index: 1;
}

.company-header-profile-dropdown-item {
  font-size: 10px;
  font-weight: bold;
  display: block;
  margin-bottom: -16px;
  text-align: center;
  text-decoration: none;
  color: inherit;
}

.company-header-profile-dropdown-item:hover {
  background-color: #bcf1e3;
  border-radius: 4px;
  font-size: 10px;
  display: block;
  margin-bottom: -16px;
  text-align: center;
  text-decoration: none;
  color: inherit;
}

.company-header-profile:hover .company-header-profile-dropdown {
  background-color: #ecfffa;
  border: 1px solid black;
  border-radius: 8px;
  display: block;
  padding: 4px;
  width: 60px;
  height: 46px;
}
</style>
