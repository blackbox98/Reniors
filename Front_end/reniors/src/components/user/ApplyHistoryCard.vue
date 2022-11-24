<template>
  <div >

    <button @click="detail" style="margin-bottom: 14px">
      <div class="big" style="">
        <p style="font-size: 17px; font-weight:700; ">{{ apply.jobOpeningName }}</p>
        <p style="font-size: 15px; color: #6D6D6D; margin-bottom: 3px;">{{ apply.companyName }}</p>
        <p style="font-size: 13px; color: #ffb400; font-weight:600;">{{ apply.jobChildCategoryName }}</p>
      </div>
      <div class="small" style="margin: auto; padding-left: 5px;">
        <p style="font-size: 13px; font-weight: 700;">{{ apply.jobOpeningProcess }}</p>
        <p style="margin-top: 5px; color: #37bf99; font-weight: 700;" v-if="apply.interviewDate">{{ apply.interviewDate.slice(6,7) + '/' + apply.interviewDate.slice(8,10) }}</p>
      </div>
    </button>
  </div>
</template>
<script>
import router from "@/router";
export default {
  name: 'ApplyHistoryCard',
  props: {
    apply: Object
  },
  components: {},
  data() {
    return {
      example: '',
    }
  },
  computed: {
  },
  setup() {},
  created() {

  },
  mounted() {},
  unmounted() {},
  methods: {
    detail() {
      router.push({ name: 'JobopeningDetail', params: { jobopeningId: this.apply.jobOpeningId } })
    },
  }
}
</script>

<style scoped>
p {
  margin-bottom: 0px;
}

button {
  background-color: #fff5f0; 
  display: flex; 
  flex-direction: row; 
  width: 312px;
  padding: 15px;
  /* padding-left: 15px; */
  border-radius: 10px;
  border-width: thin;
  border-color: #fff5f0;
  border-style: solid;
  box-shadow: rgba(0, 0, 0, 0.16) 0px 1px 4px;
}

.big {
  background-color: #fff5f0; 
  width: 220px; 
  text-align: left;
  border-style: none solid none none;
  border-width: thin;
  border-color: #eaeaea;
}

.small {
  width: 92px;
  background-color: #fff5f0; 
}
</style>