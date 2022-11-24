<template>
  <div style="width: 360px">
    <header style="margin-bottom: 10px">
      <br>
      <img
        style="width: 60%; max-height: 90px; margin: 10px"
        src="@/assets/logo_cut.png"
        alt="logo"
      />
      <br />
    
      <div style="float: right; margin-top: 5px; padding-right: 10px;">
        <img class="order" src="@/assets/one_active.svg" alt="order">
        <img class="order" src="@/assets/two.svg" alt="order">
        <img class="order" src="@/assets/three.svg" alt="order">
        <img class="order" src="@/assets/four.svg" alt="order">        
      </div>
    </header>

    <div class="content" style="padding: 0px 8px 8px 8px;">
      <img class="one" src="@/assets/bigone.svg" alt="one">
      <p class="text1">기본정보 입력</p>
      <p class="text2">기본정보에서 수정할 사항이 있을 시 수정해주세요.</p>
      <div style="width:312px; margin:0 auto; ">
        <div class="basic">
          <resume-basic-form :currentUser="currentUser"></resume-basic-form>
          <br>
          <br>
        </div>
      </div>
    </div>


  </div>
</template>
<script>
import { mapActions, mapGetters, } from 'vuex'
import ResumeBasicForm from '@/components/resume/ResumeBasicForm.vue'

export default {
  name: 'ResumeStepOne',
  components: { ResumeBasicForm },
  data() {
    return {
      editUser: {
        // 변수 이름 설정

        // userAppId: this.currentUser.userAppId,
        // userAppPwd: this.currentUser.userAppPwd,
        // name: this.currentUser.name,
        // phone: this.currentUser.phone,
        // address: this.currentUser.address,
        // extraAddress: '',
        // lastEdu: this.currentUser.lastEdu,
        // birth: this.currentUser.birth,
        // gender: this.currentUser.gender,
        // isOpen: this.currentUser.isOpen,
        // kakaoId: this.currentUser.kakaoId,
        // portfolioName: this.currentUser.portfolioName,
        // portfolioPath: this.currentUser.portfolioPath,
        // profileImgName: this.currentUser.profileImgName,
        // profileImgPath: this.currentUser.profileImgPath,
        // totalCareer: this.currentUser.totalCareer,      
        // 이력서 공개 여부        
      }
    }
  },
  computed: {
    ...mapGetters(['currentUser', 'isLogginedIn'])
  },
  setup() {},
  mounted() {},
  unmounted() {},
  methods: {
    ...mapActions(['fetchCurrentUser'])
  },
  created() {
    this.fetchCurrentUser()
  },
}
</script>

<style scoped>
  header{
    height: 136px;
    border-style: none none solid none;
    border-width: 0.5px;
    border-color: #EAEAEA;
  }

  .title {
    font-size:22px;
    margin-top: 4px;
    color: #FFB400;
    font-weight: 900;
  }
/* .nav {
  height: 64px;
  background-color: white;
  box-shadow: 0px 2px 0px rgba(0, 0, 0, 0.25);
  margin-bottom: 10px;
} */

  .order{
    margin: 2px;

  }

  .one{
    float: left;
    margin: 16px;
  }

  .text1{
    text-align: left;
    font-size: 18px;
    padding: 20px 4px 6px 4px;
    font-weight: 900;
    margin: 0px;
  }

  .text2{
    text-align: left;
    font-size: 15px;
    padding-right: 50px;
    color: #6D6D6D;
  }




</style>
