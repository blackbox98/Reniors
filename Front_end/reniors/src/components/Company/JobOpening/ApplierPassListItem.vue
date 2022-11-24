<template>
  <div class="apply-pass-item-info-box">
    <div>이름 : {{ apply.name }}</div>
    <div>채용 현황 : {{ apply.jobOpeningProcess }}</div>
    <div class="apply-pass-btn-box">
      <button @click="resumeview()">이력서 보기</button>
      <router-link :to="{ name: 'usereval', params: { no: apply.userId } }">
        <button>면접 평가 보기</button></router-link
      >
    </div>
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
  created() {},
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
.apply-pass-item-info-box {
  width: 100%;
  border-bottom: 2px solid var(--color-black-3);
  padding: 10px;
}
.apply-pass-item-info-box > div:not(:last-child) {
  width: 100%;
  text-align: left;
  margin-left: 10px;
  font-weight: bold;
  font-size: 16px;
}
.apply-pass-btn-box {
  display: flex;
  justify-content: space-around;
  margin-top: 10px;
}
.apply-pass-item-info-box button {
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
</style>
