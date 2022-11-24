<template>
  <div class="footer">
    <router-link class="footroute" :to="{ name: 'company' }">
      <i class="bi bi-house"></i>
      <p>홈</p>
    </router-link>

    <router-link
      @click="this.setheader('채용관리')"
      class="footroute"
      :to="{ name: 'companyjobopening' }"
    >
      <i class="bi bi-file-earmark-text"></i>
      <p>채용공고</p>
    </router-link>

    <router-link
      @click="this.setheader('면접')"
      class="footroute"
      :to="{ name: 'companyinterview' }"
    >
      <i class="bi bi-camera-video"></i>
      <p>면접</p>
    </router-link>

    <div v-if="isLogginedIn" class="footer-profile" @click="dropdown">
      <img :src="this.companyimg" alt="" />
      <div class="footer-profile-dropdown" id="footerDropdown">
        <router-link
          @click="this.setheader('마이페이지')"
          :to="{ name: 'companymypage' }"
          class="footer-profile-dropdown-item"
          >마이페이지</router-link
        >
        <br />
        <router-link
          class="footer-profile-dropdown-item"
          @click="this.companylogout()"
          :to="{ name: 'Login' }"
          >로그아웃</router-link
        >
      </div>
    </div>
    <router-link v-else class="footroute" to="/login">
      <i class="bi bi-person"></i>
      <p>로그인</p>
    </router-link>
    <!-- 
    <router-link
      class="footroute"
      :class="{ 'now-link': this.$route.path.startsWith('/boards') }"
      :to="{ name: 'boardMain', params: { category_id: 1 } }"
    >
      <i class="bi bi-chat-left-quote"></i>
      <p>커뮤니티</p>
    </router-link> -->
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
export default {
  name: "FooterComponent",
  data() {
    return {
      inter: { id: 1 },
      check: false,
    };
  },
  methods: {
    ...mapActions(["removeToken"]),
    ...mapActions("company", ["companylogout", "setheader"]),
    dropdown() {
      let loginCompany = document.querySelector("#footerDropdown");
      if (loginCompany) {
        loginCompany.classList.toggle("active");
      }
    },
  },
  computed: {
    ...mapGetters(["currentUser", "isLogginedIn"]),
    ...mapGetters("company", ["companyinfo", "companyimg"]),
  },
};
</script>

<style>
.footer {
  position: fixed;
  display: flex;
  align-items: center;
  justify-content: space-around;
  height: 50px;
  width: 100%;
  bottom: 0;
  border: 1px solid var(--color-black-4);
  background: var(--color-black-5);
}
.footer a {
  font-weight: bold;
  color: #2c3e50;
  text-decoration: none;
  width: 25%;
}
.footer > div{
  width: 25%;
}
.footroute p {
  font-size: 12px;
  margin: 0px 0px 4px;
}

.footer a.router-link-active {
  color: #f3620f;
}
.now-link {
  color: #f3620f !important;
}
.footer-profile {
  position: relative;
  height: 50px;
  width: 50px;
  padding: 5px;
}

.footer-profile img {
  border-radius: 5rem;
  height: 100%;
}

.footer-profile-dropdown {
  position: absolute;
  display: none;
  left: 50%;  
  bottom: 60px;
  margin-left: -50px;
  width: 100px;
  background-color: white;
  border: 1px solid black;
  border-radius: 0.5rem;
}

.footer-profile-dropdown > a,
.footer-profile-dropdown > p {
  font-size: 14px;
  margin: 0;
}

.footer-profile-dropdown:before {
  content: "";
  position: absolute;
  bottom: 0;
  left: 50%;
  width: 0;
  height: 0;
  border: 7px solid black;
  border-top-color: #ffffff;
  border-bottom: 0;
  margin-left: -7px;
  margin-bottom: -7px;
}

.footer-profile-dropdown:after {
  content: "";
  position: absolute;
  bottom: -1px;
  left: 50%;
  width: 0;
  height: 0;
  border: 7px solid white;
  border-top-color: transparent;
  border-bottom: 0;
  margin-left: -7px;
  margin-bottom: -7px;
}

.footer-profile-dropdown.active {
  display: block;
  animation: fade-in 0.5s;
  animation-fill-mode: forwards;
}

@keyframes fade-in {
  from {
    opacity: 0;
    transform: translate3d(0, 20%, 0);
  }
  to {
    opacity: 1;
    transform: translateZ(0);
  }
}

.footer-profile-dropdown-item:hover {
  cursor: pointer;
}
</style>
