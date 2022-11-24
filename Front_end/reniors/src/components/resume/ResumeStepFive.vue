<template>
  <div>
    <header>
      <div style="margin-top: 16px; padding: 10px; background-color: #F9F9F9;">
        <span class="title">이력서 작성</span>
      </div>
    
      <div style="float: right; margin-top: 5px">
        <img class="order" src="@/assets/one.svg" alt="order">
        <img class="order" src="@/assets/two.svg" alt="order">
        <img class="order" src="@/assets/three.svg" alt="order">
        <img class="order" src="@/assets/four.svg" alt="order">
        <img class="order" src="@/assets/five_active.svg" alt="order">          
      </div>
    </header>

    <div class="content">
      <img class="five" src="@/assets/bigfive.svg" alt="five">
      <p class="text1">포트폴리오</p>
      <p class="text2">포트폴리오를 첨부해주세요.</p>
      <div style="width:312px; margin:0 auto;">
        <label for="formFile" class="form-label"></label>
        <input class="form-control" type="file" id="formFile">
      </div>
      <footer>
        <button style="background-color: #FFC0A3" type="button"><router-link style="text-decoration:none; color: white;" :to="{ name: 'ResumeStepFour' }">이전</router-link></button>
        <button style="background-color: #FF843E" type="button"><router-link style="text-decoration:none; color: white;" :to="{ name: 'home' }">완료!</router-link></button>
      </footer>
    </div>

  </div>
</template>
<script>
export default {
  name: 'ResumeStepFive',
  components: {},
  data() {
    return {
      example: '',
      // cnt: 1
    }
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {
      // add(){
      // this.cnt += 1
    // }
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

  .five{
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


</style>