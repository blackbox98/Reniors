<template>
    <div class="header">
      <i @click="prevpage()" class="bi bi-arrow-left-circle-fill"></i>
      <router-link class="header-logo" :to="{ name: 'home' }">
        <img src="@/assets/logo_cut.png" alt="logo" />
      </router-link>
      <div></div>
    </div>
  <!-- Login -->
  <div class="condition-container" v-if="isLoggedin">
    <ConditionList />
    <router-view></router-view>
  </div>

  <!-- notLogin -->
  <div class="notlogTotal" v-if="!isLoggedin">
    <div class="notlog">
      <div class="notlogBox">
        <p class="notlogp">맞춤 공고 서비스는</p>
        <p class="notlogp">
          <router-link :to="{ name: 'Login' }" style="text-decoration: none"
            ><span style="color: #37bf99"> 로그인</span></router-link
          >
          후 이용해주세요🙂
        </p>
        <router-link :to="{ name: 'Login' }">
          <button class="notlogBtn">
            로그인 하러 가기 <i class="bi bi-arrow-right-square-fill"></i>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
import { useStore } from "vuex";
import { computed } from "vue";
import ConditionList from "@/components/condition/ConditionList.vue";

export default {
  name: "ConditionView",
  components: {
    ConditionList,
  },
  setup() {
    const store = useStore();
    const isLoggedin = computed(() => store.getters["isLogginedIn"]);
    const fetchParents = () => store.dispatch("category/getJobParent");
    const fetchSido = () => store.dispatch("category/getSido");
    fetchParents();
    fetchSido();
    return { isLoggedin };
  },
  methods:{
        prevpage() {
      this.$router.go(-1);
    },
  }
};
</script>

<style scoped>
.header {
  height: 48px;
  background-color: white;
  box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px -2px;
  display: flex;
  align-items: center;
  justify-content: space-around;
  position: fixed;
  width: 100vw;
  top: 0;
}
.header img {
  height: 38px;
  margin: auto;
  width: auto;
}
.header > .bi {
  color: var(--color-red-1);
  font-size: 30px;
  cursor: pointer;
}
.header > div {
  width: 30px;
}
.condition-container {
  height: 1500px;
  margin-top: 56px;
}
.notlogTotal {
  display: flex;
  width: 100%;
  height: 100vh;
  flex-direction: column;
  justify-content: center;
  background-color: #fff5f0;
}
.noglog {
  display: flex;
  justify-content: center;
  align-items: center;
}
.notlogBox {
  background-color: rgba(256, 256, 256, 0.5);
  border: none;
  border-radius: 10px;
  margin: 8px;
  padding: 32px 8px;
}
.notlogp {
  font-size: 24px;
  font-weight: bold;
  color: #ff843e;
}
.notlogBtn {
  background-color: #ff843e;
  border: none;
  border-radius: 5px;
  color: white;
  font-weight: bold;
  font-size: 20px;
  width: 72%;
  padding: 8px;
  margin: 8px;
}
.notlogBtn i {
  font-size: 24px;
  margin: auto 8px;
}
</style>
