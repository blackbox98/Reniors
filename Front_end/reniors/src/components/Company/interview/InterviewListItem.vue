<template>
  <div class="interview-date">
    {{ dateFormat(interviewapply.interviewDate) }}
  </div>
  <div class="interview-list-item">
    <div class="title">{{ interviewapply.jobOpeningName }}</div>
    <div class="name">지원자 : {{ interviewapply.name }}</div>
    <div class="part">
      지원 분야 : {{ interviewapply.jobChildCategoryName }}
    </div>
    <div class="interview-detail-btn">
      <template v-if="interviewapply.jobOpeningProcess == '면접심사중'">
        <button class="about-interview-btn">
          <router-link
            :to="{
              name: 'usereval',
              params: { no: this.interviewapply.userId },
            }"
          >
            면접평가보기
          </router-link>
        </button>
      </template>
      <template v-else>
        <button class="about-interview-btn" @click="mobileCheck()">
          <a v-if="this.isMobile">면접보기</a>
          <router-link v-else
            :to="{
              name: 'companyInterviewVideo',
              params: {
                no: this.interviewapply.id,
                jobOpeningId: this.interviewapply.jobOpeningId,
              },
            }"
          >
            면접보기
          </router-link>
        </button>
      </template>
    </div>
  </div>
  <hr />
</template>

<script>
import { mapActions, mapGetters } from "vuex";
import dayjs from "dayjs";

export default {
  data(){
    return{
    }
  },
  props: {
    interviewapply: Object,
  },
  computed:{
    ...mapGetters("company", ["isMobile"]),
  },
  mounted(){
    this.$nextTick(() => {
      window.addEventListener('resize', this.setIsMobile);
    })
  },
  created(){
    this.setIsMobile();
  },
  methods: {
    ...mapActions("company", ["setInterviewer", "setIsMobile"]),
    dateFormat(val) {
      return dayjs(val).format("YYYY년 MM월 DD일 HH시 MM분");
    },
    mobileCheck(){
      if(this.isMobile){
        alert("모바일로는 불가능합니다,\nPC로 접속해주세요!")
      }
    },
    
  },
};
</script>

<style scope>
.interview-date {
  width: 100%;
  text-align: left;
  font-weight: bold;
  font-size: 15px;
  margin-left: 5px;
}
.interview-list-item {
  width: 900px;
  padding: 20px;
  border-radius: 10px;
  margin-bottom: 20px;
  border-color: var(--color-black-2);
  box-shadow: inset 0 0 1px 1px var(--color-black-3),
    0 0 5px var(--color-black-3);
}
.interview-list-item > .title {
  width: 100%;
  text-align: left;
  font-weight: bold;
  font-size: 17px;
}
.interview-list-item > .name {
  width: 100%;
  text-align: right;
  font-size: 13px;
  color: var(--color-green-1);
}
.interview-list-item > .part {
  width: 100%;
  text-align: right;
  font-size: 13px;
  color: var(--color-green-1);
}
.interview-list-item > .interview-detail-btn {
  display: flex;
  justify-content: flex-end;
  margin-top: 10px;
}
.about-interview-btn {
  padding: 5px 10px;
  background-color: var(--color-red-2);
  border: none;
  color: white;
  border-radius: 8px;
  font-size: 16px;
  box-shadow: rgba(50, 50, 93, 0.25) 0px 6px 12px -2px,
    rgba(0, 0, 0, 0.3) 0px 3px 7px -3px;
}
.about-interview-btn:link {
  color: white;
}
.about-interview-btn:visited {
  color: white;
}
.about-interview-btn > a {
  text-decoration: none;
  color: white;
}
.about-interview-btn > a:hover{
  color: white;
}
@media(max-width: 760px){
  .interview-list-item {
    width: 328px;
  }
}
</style>
