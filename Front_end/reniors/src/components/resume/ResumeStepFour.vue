<template>
  <div>
    <header>
      <div style="margin-top: 16px; padding: 10px; background-color: #F9F9F9;">
        <span class="title">이력서 작성</span>
      </div>
    
      <div style="float: right; margin-top: 5px; padding-right: 10px;" >
        <img class="order" src="@/assets/one.svg" alt="order">
        <img class="order" src="@/assets/two.svg" alt="order">
        <img class="order" src="@/assets/three.svg" alt="order">
        <img class="order" src="@/assets/four_active.svg" alt="order">      
      </div>
    </header>

    <div class="content" style="padding: 0px 8px 8px 8px;">
      <img class="four" src="@/assets/bigfour.svg" alt="four">
      <p class="text1">수상경력</p>
      <p class="text2">수상경력을 입력해주세요.</p>
      <div style="width:312px; margin:0 auto;">
        <resume-award-detail v-for="award in awards" :award="award" :key="award.id"></resume-award-detail>
        <hr>
        <resume-award-form v-if="formshow" @show="show" :award="award" action="create"></resume-award-form>
        <div v-show="formshow==false">
          <button class="add" @click="show"><img class="plus" style="margin-bottom: 5px" src="@/assets/plus.svg" alt="plus">  수상경력 추가</button>
        </div>

      </div>
      <footer>
        <button style="background-color: #FFC0A3" type="button"><router-link style="text-decoration:none; color: white;" :to="{ name: 'ResumeStepThree' }">이전</router-link></button>
        <button style="background-color: #FF843E" type="button"><router-link style="text-decoration:none; color: white;" :to="{ name: 'ResumeDetail' }">완료!</router-link></button>
      </footer>
    </div>

  </div>
</template>
<script>
import ResumeAwardForm from '@/components/resume/ResumeAwardForm.vue'
import ResumeAwardDetail from '@/components/resume/ResumeAwardDetail.vue'
import { mapActions, mapGetters } from 'vuex'

export default {
  name: 'ResumeStepFour',
  components: { ResumeAwardForm, ResumeAwardDetail },
  data() {
    return {
      award: {
        name: '',
        awardedAt: '',
      },
      formshow: true
    }
  },
  computed: {
    ...mapGetters(['awards']),
  },
  setup() {},
  created() {
    this.fetchAward()
  },
  mounted() {},
  unmounted() {},
  methods: {
    ...mapActions(['fetchAward']),
    show(){
      this.formshow = !(this.formshow)
    }
  }
}
</script>

<style scoped>
  header{
    height: 84px;
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

  .four{
    float: left;
    margin: 16px;
  }

  .order{
    margin: 2px;
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