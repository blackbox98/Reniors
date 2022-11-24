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

      <div style="float: right; margin-top: 5px; padding-right: 10px">
        <img class="order" src="@/assets/one.svg" alt="order">
        <img class="order" src="@/assets/two_active.svg" alt="order">
        <img class="order" src="@/assets/three.svg" alt="order">
        <img class="order" src="@/assets/four.svg" alt="order">      
      </div>
    </header>
    <div class="content" style="padding: 0px 8px 8px 8px;">
      <img class="two" src="@/assets/bigtwo.svg" alt="two">
      <p class="text1">경력사항</p>
      <p class="text2">경력사항을 입력해주세요.</p>
      <div style="width:312px; margin:0 auto;">
        <resume-career-detail v-for="career in careers" :career="career" :key="career.id"></resume-career-detail>
        <hr>
        <resume-career-form v-if="formshow" @show="show" :career="career" action="create"></resume-career-form>
        <!-- <resume-career-form :cnt = 'cnt'></resume-career-form> -->
        <div v-show="formshow==false">
          <button class="add" @click="show"><img class="plus" style="margin-bottom: 5px" src="@/assets/plus.svg" alt="plus">경력 추가</button>
        </div>
       
        
      </div>
      <footer>
        <button style="background-color: #FFC0A3" type="button"><router-link style="text-decoration:none; color: white;" :to="{ name: 'ResumeStepOne' }">이전</router-link></button>
        <button style="background-color: #FF843E" type="button"><router-link style="text-decoration:none; color: white;" :to="{ name: 'ResumeStepThree' }">다음</router-link></button>
      </footer>


    </div>

  </div>
</template>
<script>
import ResumeCareerForm from '@/components/resume/ResumeCareerForm.vue'
import ResumeCareerDetail from '@/components/resume/ResumeCareerDetail.vue'
import { mapActions, mapGetters } from 'vuex'

export default {
  name: 'ResumeStepTwo',
  components: { ResumeCareerForm, ResumeCareerDetail },
  data() {
    return {
      career: {
        companyName: '',
        finishedAt: '',
        jobContents: '',
        startedAt: '',
      },
      formshow: true
    }
  },
  computed: {
    ...mapGetters(['careers']),
  },
  setup() {},
  created() {
    this.fetchCareer()
  },
  mounted() {},
  unmounted() {},
  methods: {
    ...mapActions(['fetchCareer']),
    show(){
      this.formshow = !(this.formshow)
    }
  }
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

  .order{
    margin: 2px;
  }

  .two{
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
  
  .content{
    margin-bottom: 100px;
  }

  footer {
    width: 312px;
    height: 50px;
    position: fixed;
    left: 50%;
    transform: translate(-50%, 0);
    bottom: 30px;
    display: flex;
    justify-content: space-between;
    background-color: white;
  }

  footer > button{    
    width: 45%;
    height: 80%;
    /* height: 40px; */
    border-radius: 10px;
    border: none;
    color: white;
    font-weight: bold;
    font-size: 18px;
    /* box-shadow: 0 4px 4px -1px rgba(0, 0, 0, 0.1), 0 2px 2px -1px rgba(0, 0, 0, 0.06); */
    cursor: pointer;
  }

  .add {
    border-style: none; 
    background-color: white;
    color: #37BF99;
    /* font-weight: bold; */
    font-size: 18px;
  }


</style>