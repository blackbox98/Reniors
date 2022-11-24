<template>
  <div class="apply-resume-item-info-box">
    <div>이름 : {{ apply.name }}</div>
    <div>채용 현황 : {{ apply.jobOpeningProcess }}</div>
    <button @click="resumeview()">이력서 보기</button>
  </div>
</template>

<script setup>
import "@vuepic/vue-datepicker/dist/main.css";
</script>
<script>
export default {
  components: {},
  props: {
    apply: Object,
  },
  data() {
    return {};
  },
  computed: {},
  methods: {
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
.apply-resume-item-info-box {
  width: 100%;
  border-bottom: 2px solid var(--color-black-3);
  padding: 10px;
}
.apply-resume-item-info-box > div {
  width: 100%;
  text-align: left;
  margin-left: 10px;
  font-weight: bold;
  font-size: 16px;
}
.apply-resume-item-info-box > button {
  border: none;
  background-color: var(--color-green-2);
  padding: 6px 20px;
  color: black;
  border-radius: 5px;
  font-weight: bold;
  margin-top: 10px;
  font-size: 14px;
}
</style>
