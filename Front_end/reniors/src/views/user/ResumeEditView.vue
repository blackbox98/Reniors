<template>
  <div>
    <h1>이력서 작성 및 수정</h1>
    <resume-step-one></resume-step-one>

  </div>
</template>
<script>
import ResumeStepOne from '@/components/resume/ResumeStepOne.vue'
export default {
  components: { ResumeStepOne },
  data() {
    return {
      example: '',
    }
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {}
}
</script>