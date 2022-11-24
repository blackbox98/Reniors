<template>
  <div class="apply-interview-item-info-box">
    <div>이름 : {{ apply.name }}</div>
    <template v-if="new Date(apply.interviewDate).getFullYear() == 1970">
      <div>면접 날짜 : -</div>
    </template>
    <template v-else>
      <div>
        면접 날짜 : {{ new Date(apply.interviewDate).getFullYear() }}-{{
          new Date(apply.interviewDate).getMonth() + 1
        }}-{{ new Date(apply.interviewDate).getDate() }}
        {{ new Date(apply.interviewDate).getHours() }}시
        {{
          new Date(apply.interviewDate).getMinutes() == 0
            ? null
            : new Date(apply.interviewDate).getMinutes() + "분"
        }}
      </div>
    </template>
    <div>채용 현황 : {{ apply.jobOpeningProcess }}</div>
    <div class="apply-interview-btn-box">
      <button @click="resumeview()">이력서 보기</button>
      <template v-if="apply.jobOpeningProcess == '면접'">
        <button @click="interviewflag()">면접 일정 잡기</button>
      </template>
      <template v-if="apply.jobOpeningProcess == '면접심사중'">
        <router-link :to="{ name: 'usereval', params: { no: apply.userId } }">
          <button>면접 평가 보기</button></router-link
        >
      </template>
    </div>

    <div v-if="flag">
      <datepicker
        v-model="applyinfo.interviewDate"
        class="interview-datepicker"
      />
      <button @click="updateapply()">수정</button>
    </div>
  </div>
</template>

<script setup>
import Datepicker from "@vuepic/vue-datepicker";
import "@vuepic/vue-datepicker/dist/main.css";
import { mapActions, mapGetters } from "vuex";
</script>
<script>
export default {
  components: {
    Datepicker,
  },
  props: {
    apply: Object,
  },
  data() {
    return {
      flag: false,
      applyinfo: {
        jobOpeningProcess: null,
        interviewDate: new Date(),
        sessionId: null,
      },
    };
  },
  computed: {
    ...mapGetters("company", ["jobopening"]),
  },
  methods: {
    ...mapActions("company", ["updateApply", "registRoom"]),
    async updateapply() {
      if (confirm("면접 일정을 수정하시겠습니까?")) {
        this.applyinfo.jobOpeningProcess = "면접";
        this.applyinfo.sessionId = "InterviewSession" + this.jobopening.id;
        let data = {
          jobOpeningId: this.jobopening.id,
          applyId: this.apply.id,
          apply: this.applyinfo,
        };
        await this.updateApply(data);
        window.location.reload();
      }
    },
    interviewflag() {
      this.flag = !this.flag;
    },
    resumeview() {
      this.$router.push({
        name: "resumeview",
        params: { no: this.apply.userId },
      });
    },
  },
};
</script>

<style scoped>
.apply-interview-item-info-box {
  width: 100%;
  border-bottom: 2px solid var(--color-black-3);
  padding: 10px;
}
.apply-interview-item-info-box > div:not(:nth-child(4), :nth-child(5)) {
  width: 100%;
  text-align: left;
  margin-left: 10px;
  font-weight: bold;
  font-size: 16px;
}
.apply-interview-btn-box {
  display: flex;
  justify-content: space-around;
  margin-top: 10px;
}
.apply-interview-item-info-box button {
  border: none;
  background-color: var(--color-green-1);
  padding: 6px 20px;
  color: white;
  border-radius: 5px;
  font-weight: bold;
  font-size: 14px;
  margin: 0;
  box-shadow: rgba(50, 50, 93, 0.25) 0px 2px 5px -1px,
    rgba(0, 0, 0, 0.3) 0px 1px 3px -1px;
}
.interview-datepicker {
  margin: 10px 0;
}
</style>
