<template>
  <div>
    <div for="check">이름 :{{ apply.userId }}</div>

    <div>채용과정 : {{ apply.jobOpeningProcess }}</div>
    <div>면접날짜 : {{ apply.interviewDate }}</div>
    <button @click="interviewflag()">면접일정잡기</button>
    <div v-if="this.flag">
      <datepicker v-model="applyinfo.interviewDate" />
      <button @click="updateapply()">수정</button>
    </div>
    <button @click="resumeview()">이력서보기</button>
  </div>
</template>

<script setup>
import Datepicker from "@vuepic/vue-datepicker";
import "@vuepic/vue-datepicker/dist/main.css";
// import { ref } from "vue";
import { mapActions, mapGetters } from "vuex";
// import moment from "moment";
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
      applyinfo: {
        jobOpeningProcess: null,
        interviewDate: new Date(),
      },
      //   passuserId: [],
      flag: false,
    };
  },
  computed: {
    ...mapGetters("company", ["jobopening"]),
  },
  methods: {
    ...mapActions("company", ["updateApply", "registRoom"]),
    updateapply() {
      // this.applyinfo.interviewDate = new Date(moment(
      //   this.applyinfo.interviewDate
      // ).format("YYYY-MM-DD hh:mm"));
      this.applyinfo.jobOpeningProcess = "면접";
      let data = {
        jobOpeningId: this.jobopening.id,
        applyId: this.apply.id,
        apply: this.applyinfo,
      };
      this.updateApply(data);

      let room = {
        isActive: "CLOSE",
        sessionId: "InterviewSession" + this.jobopening.id,
        userId: this.apply.userId,
        jobOpeningId: this.jobopening.id,
      };

      this.registRoom(room);
      // this.$router.go();
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

<style></style>
