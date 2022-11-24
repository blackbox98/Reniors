<template>
    <div class="header">
        <router-link class="header-logo" :to="{ name: 'home' }">
            <img src="@/assets/logo_cut.png" alt="logo">
        </router-link>
    </div>
  <div class="home">
    <div class="sub-title">
        <img class="persona" src="@/assets/persona.png" alt="persona">
      <div class="korean-logo-box">
        <div>
          <img class="korean-logo" src="@/assets/logo_korean.png" alt="logo">
          <p style="color:var(--color-green-1); margin: 0;"> 다시 시작하는 나의 <span style="color:#F3620F">일</span>생</p>
        </div>
      </div>
    </div>
    <HomeInfo />
    <HomeNotice :login="isLogin"/>

    <hr/>
    <div v-if="isLogin">
      <HomeJobopeningList type="추천 채용공고" :jobopenings="hotJobopenings" />
      <hr>
      <HomeYoutubeList type="추천 영상" :youtubes="youtubes"/>
    </div>
    <div v-else>
      <HomeJobopeningList type="핫한 채용공고 🔥" :jobopenings="hotJobopenings" />
      <hr>
      <HomeYoutubeList type="추천 영상" :youtubes="youtubes" />
    </div>
  </div>
</template>

<script>
import { computed } from 'vue'
import { useStore } from 'vuex'
import HomeNotice from '@/components/home/HomeNotice.vue'
import HomeInfo from '@/components/home/HomeInfo.vue'
import HomeJobopeningList from '@/components/home/HomeJobopeningList.vue'
import HomeYoutubeList from '@/components/home/HomeYoutubeList.vue'

export default {
  name: 'HomeView',
  components: {
    HomeNotice,
    HomeInfo,
    HomeJobopeningList,
    HomeYoutubeList,
},
  setup() {
    const store = useStore()

    // Youtube API 가 하루에 50건으로 한정 되어있어 오류를 발생 시킴
    //const fetchYoutube = () => store.dispatch('home/fetchYoutubes', '취업정보')
    const fetchHot = () => store.dispatch('home/fetchHot')
    //fetchYoutube()
    fetchHot()

    let recommendJobopenings=null;
    const isLogin = computed(() => store.getters['isLogginedIn'])
    if(isLogin.value){
      recommendJobopenings = computed(() => store.state.home.recommendJobopenings)
    }
    const hotJobopenings=computed(() => store.getters['home/hotJobopenings']);
    //const youtubes = computed(() => store.getters['home/youtubes'])

    //const isYoutube = computed(() => store.getters['home/isYoutube'])
    const youtubes = [];
    return {
      isLogin, hotJobopenings, recommendJobopenings, youtubes,
    }

  },
}
</script>

<style scoped>
.header{
    height: 48px;
    background-color: white;
    width: 100%;
    box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px -2px;
    display: flex;
    align-items: center;
    justify-content: center;
    position: fixed;
    top: 0;
}
.header img{
    height: 38px;
    margin: auto;
    width: auto;
}
.home {
  width: 100%;
  margin-bottom: 100px;
  padding-top: 50px;
}
.sub-title{
  display: flex;
  width: 100%;
  height: 120px;
  justify-content: space-around;
  align-items: center;
  background-color: #FFF5F0;
}
.persona{
  height: 100px;
  margin-left: 8px;
}
.korean-logo{
  height: 80px;
  line-height: 200px;
}
.korean-logo-box{
  margin: auto 8px auto 0;
  display: flex;
  align-items: center;
}
.korean-logo-box > p{
  font-weight: bold;
  font-size: 20px;
  letter-spacing: 5px;
}
@media(max-width:760px){
  .persona{
    height: 100px;
  }
  .korean-logo{
    height: 40px;
  }
  .korean-logo-box > p{
    font-size: 14px;
    letter-spacing: 2px;
  }
}

.intro{
  width: 100vw;
  height: 100vh;
  position: fixed;
  animation-duration: 2.2s;
  animation-name: slidein;
  visibility: hidden;
  z-index: -10;
}

@keyframes slidein {
  from {
    visibility: visible;
    z-index: 1000;
  }

  to {
  }
}

</style>
