<template>
<div class="intro">
  <div class="total">
    <div class="content">
        <div class="toptitle">
            <p class="tracking-in-contract-bck">다시 시작하는 나의 <span class="text-flicker-in-glow">일</span>생</p>
            <img class="tracking-in-expand-fwd" src="@/assets/logo_cut.png" alt="logo">
        </div>
        <div class="personaImg" >
            <img src="@/assets/persona.png" alt="personaimg">
        </div>
        <div class="bottom">
            <div class="bottomsub">
                <p><img src="@/assets/logo_korean.png" alt="">에서</p>
            </div>
            <p><span>당신의 제 2막</span>을 시작해보세요!</p>
        </div>

    </div>
    
  </div>
</div>
</template>
<script>
import router from '@/router';

export default{ 
    name:'IntroView',
    components:{},
    data(){
        return{
            sampleData:''
        };
    },
      methods:{
    onTime(){
      router.push({name:'home'})
    }
  },
  created(){
    setTimeout(this.onTime, 3000);
  } 
}
</script>

<style scoped>
.total{
    background-color: #FFF5F0;
    width: 100vw;
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    z-index: 1;
}
.toptitle p{
    font-size: 20px;
    font-weight: bold;
    color: #FF843E;
    margin: 4px;
}
.toptitle span{
    color: #37BF99;
}
.toptitle img{
    width: 80%;
    height: auto;
    margin: auto;
}
.personaImg img{
    width: 300px;
    height: auto;
    margin: 16px 8px;
}
.goBtn{
    width: 80%;
    height: 8%;
    border: none;
    border-radius: 10px;
    background-color: #FF843E;
    box-shadow: rgba(149, 157, 165, 0.2) 0px 8px 24px;
}
.goBtn p{
    font-weight: bold;
    font-size: 40px;
    color: white;
    margin: 4px;
    letter-spacing: 4px;
}
.goBtn span{
    color: #37BF99;
}
.bottomsub{
    display: flex;
    justify-content: center;
    align-items: center;
}
.bottomsub p{
    font-size: 20px;
    color: #6d6d6d;
    margin: 4px;
}
.bottomsub img{
    width: 100px;
    height: auto;
    margin: 4px;
}
.bottom p{
    font-size: 20px;
    color: #6d6d6d;
    margin: 4px;
}
.bottom span{
    text-decoration:underline #37BF99 2px;

}
.tracking-in-expand-fwd{-webkit-animation:tracking-in-expand-fwd 1s cubic-bezier(.215,.61,.355,1.000) both;animation:tracking-in-expand-fwd 1s cubic-bezier(.215,.61,.355,1.000) both}
@-webkit-keyframes tracking-in-expand-fwd{0%{letter-spacing:-.5em;-webkit-transform:translateZ(-700px);transform:translateZ(-700px);opacity:0}40%{opacity:.6}100%{-webkit-transform:translateZ(0);transform:translateZ(0);opacity:1}}@keyframes tracking-in-expand-fwd{0%{letter-spacing:-.5em;-webkit-transform:translateZ(-700px);transform:translateZ(-700px);opacity:0}40%{opacity:.6}100%{-webkit-transform:translateZ(0);transform:translateZ(0);opacity:1}}

.tracking-in-contract-bck{-webkit-animation:tracking-in-contract-bck 1s cubic-bezier(.215,.61,.355,1.000) both;animation:tracking-in-contract-bck 1s cubic-bezier(.215,.61,.355,1.000) both}
@-webkit-keyframes tracking-in-contract-bck{0%{letter-spacing:1em;-webkit-transform:translateZ(400px);transform:translateZ(400px);opacity:0}40%{opacity:.6}100%{-webkit-transform:translateZ(0);transform:translateZ(0);opacity:1}}@keyframes tracking-in-contract-bck{0%{letter-spacing:1em;-webkit-transform:translateZ(400px);transform:translateZ(400px);opacity:0}40%{opacity:.6}100%{-webkit-transform:translateZ(0);transform:translateZ(0);opacity:1}}

.text-flicker-in-glow{-webkit-animation:text-flicker-in-glow 4s linear both;animation:text-flicker-in-glow 4s linear both}
@-webkit-keyframes text-flicker-in-glow{0%{opacity:0}10%{opacity:0;text-shadow:none}10.1%{opacity:1;text-shadow:none}10.2%{opacity:0;text-shadow:none}20%{opacity:0;text-shadow:none}20.1%{opacity:1;text-shadow:0 0 30px rgba(255,255,255,.25)}20.6%{opacity:0;text-shadow:none}30%{opacity:0;text-shadow:none}30.1%{opacity:1;text-shadow:0 0 30px rgba(255,255,255,.45),0 0 60px rgba(255,255,255,.25)}30.5%{opacity:1;text-shadow:0 0 30px rgba(255,255,255,.45),0 0 60px rgba(255,255,255,.25)}30.6%{opacity:0;text-shadow:none}45%{opacity:0;text-shadow:none}45.1%{opacity:1;text-shadow:0 0 30px rgba(255,255,255,.45),0 0 60px rgba(255,255,255,.25)}50%{opacity:1;text-shadow:0 0 30px rgba(255,255,255,.45),0 0 60px rgba(255,255,255,.25)}55%{opacity:1;text-shadow:0 0 30px rgba(255,255,255,.45),0 0 60px rgba(255,255,255,.25)}55.1%{opacity:0;text-shadow:none}57%{opacity:0;text-shadow:none}57.1%{opacity:1;text-shadow:0 0 30px rgba(255,255,255,.55),0 0 60px rgba(255,255,255,.35)}60%{opacity:1;text-shadow:0 0 30px rgba(255,255,255,.55),0 0 60px rgba(255,255,255,.35)}60.1%{opacity:0;text-shadow:none}65%{opacity:0;text-shadow:none}65.1%{opacity:1;text-shadow:0 0 30px rgba(255,255,255,.55),0 0 60px rgba(255,255,255,.35),0 0 100px rgba(255,255,255,.1)}75%{opacity:1;text-shadow:0 0 30px rgba(255,255,255,.55),0 0 60px rgba(255,255,255,.35),0 0 100px rgba(255,255,255,.1)}75.1%{opacity:0;text-shadow:none}77%{opacity:0;text-shadow:none}77.1%{opacity:1;text-shadow:0 0 30px rgba(255,255,255,.55),0 0 60px rgba(255,255,255,.4),0 0 110px rgba(255,255,255,.2),0 0 100px rgba(255,255,255,.1)}85%{opacity:1;text-shadow:0 0 30px rgba(255,255,255,.55),0 0 60px rgba(255,255,255,.4),0 0 110px rgba(255,255,255,.2),0 0 100px rgba(255,255,255,.1)}85.1%{opacity:0;text-shadow:none}86%{opacity:0;text-shadow:none}86.1%{opacity:1;text-shadow:0 0 30px rgba(255,255,255,.6),0 0 60px rgba(255,255,255,.45),0 0 110px rgba(255,255,255,.25),0 0 100px rgba(255,255,255,.1)}100%{opacity:1;text-shadow:0 0 30px rgba(255,255,255,.6),0 0 60px rgba(255,255,255,.45),0 0 110px rgba(255,255,255,.25),0 0 100px rgba(255,255,255,.1)}}@keyframes text-flicker-in-glow{0%{opacity:0}10%{opacity:0;text-shadow:none}10.1%{opacity:1;text-shadow:none}10.2%{opacity:0;text-shadow:none}20%{opacity:0;text-shadow:none}20.1%{opacity:1;text-shadow:0 0 30px rgba(255,255,255,.25)}20.6%{opacity:0;text-shadow:none}30%{opacity:0;text-shadow:none}30.1%{opacity:1;text-shadow:0 0 30px rgba(255,255,255,.45),0 0 60px rgba(255,255,255,.25)}30.5%{opacity:1;text-shadow:0 0 30px rgba(255,255,255,.45),0 0 60px rgba(255,255,255,.25)}30.6%{opacity:0;text-shadow:none}45%{opacity:0;text-shadow:none}45.1%{opacity:1;text-shadow:0 0 30px rgba(255,255,255,.45),0 0 60px rgba(255,255,255,.25)}50%{opacity:1;text-shadow:0 0 30px rgba(255,255,255,.45),0 0 60px rgba(255,255,255,.25)}55%{opacity:1;text-shadow:0 0 30px rgba(255,255,255,.45),0 0 60px rgba(255,255,255,.25)}55.1%{opacity:0;text-shadow:none}57%{opacity:0;text-shadow:none}57.1%{opacity:1;text-shadow:0 0 30px rgba(255,255,255,.55),0 0 60px rgba(255,255,255,.35)}60%{opacity:1;text-shadow:0 0 30px rgba(255,255,255,.55),0 0 60px rgba(255,255,255,.35)}60.1%{opacity:0;text-shadow:none}65%{opacity:0;text-shadow:none}65.1%{opacity:1;text-shadow:0 0 30px rgba(255,255,255,.55),0 0 60px rgba(255,255,255,.35),0 0 100px rgba(255,255,255,.1)}75%{opacity:1;text-shadow:0 0 30px rgba(255,255,255,.55),0 0 60px rgba(255,255,255,.35),0 0 100px rgba(255,255,255,.1)}75.1%{opacity:0;text-shadow:none}77%{opacity:0;text-shadow:none}77.1%{opacity:1;text-shadow:0 0 30px rgba(255,255,255,.55),0 0 60px rgba(255,255,255,.4),0 0 110px rgba(255,255,255,.2),0 0 100px rgba(255,255,255,.1)}85%{opacity:1;text-shadow:0 0 30px rgba(255,255,255,.55),0 0 60px rgba(255,255,255,.4),0 0 110px rgba(255,255,255,.2),0 0 100px rgba(255,255,255,.1)}85.1%{opacity:0;text-shadow:none}86%{opacity:0;text-shadow:none}86.1%{opacity:1;text-shadow:0 0 30px rgba(255,255,255,.6),0 0 60px rgba(255,255,255,.45),0 0 110px rgba(255,255,255,.25),0 0 100px rgba(255,255,255,.1)}100%{opacity:1;text-shadow:0 0 30px rgba(255,255,255,.6),0 0 60px rgba(255,255,255,.45),0 0 110px rgba(255,255,255,.25),0 0 100px rgba(255,255,255,.1)}}

.intro{
  width: 100vw;
  height: 100vh;
  position: fixed;
  animation-duration: 3s;
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
