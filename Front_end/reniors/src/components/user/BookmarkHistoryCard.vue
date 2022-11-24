<template>
  <div >
    <button @click="detail" style="margin-bottom: 14px">
      <div class="big" style="">
        <p style="font-size: 17px; font-weight:700; ">{{ bookmark.jobOpeningResponse.title }}</p>
        <p style="font-size: 15px; color: #6D6D6D; margin-bottom: 3px;">{{ bookmark.jobOpeningResponse.companyName }}</p>
        <p style="font-size: 13px; color: #ffb400; font-weight:600;">{{ bookmark.jobOpeningResponse.typeEmployment }}</p>
      </div>
      <div class="small" style="margin: auto; padding-left: 5px;">
        <p style="font-size: 12px; font-weight: 700; color: #37bf99;">{{ bookmark.jobOpeningResponse.createdDate.slice(0,10) }}</p>
        <p style="font-size: 12px; margin-top: 5px; color: #37bf99; font-weight: 700;">{{ '~' + bookmark.jobOpeningResponse.finishedDate.slice(0,10) }}</p>
      </div>
    </button>
  </div>
</template>
<script>
import router from "@/router";
export default {
  name: 'BookmarkHistoryCard',
  props: {
    bookmark: Object,
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
     router.push({ name: 'JobopeningDetail', params: { jobopeningId: this.bookmark.jobOpeningResponse.id } })
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