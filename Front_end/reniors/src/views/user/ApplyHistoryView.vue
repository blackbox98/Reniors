<template>
  <div>
    <apply-history-card v-for="apply in applies" :apply="apply" :key="apply"></apply-history-card>
  </div>
</template>
<script>
import ApplyHistoryCard from '@/components/user/ApplyHistoryCard.vue'
// import { mapActions, mapGetters } from 'vuex'
export default {
  name: 'ApplyHistoryView',
  components: { ApplyHistoryCard },
  props: {
    applies: Array
  },
  data() {
    return {
      example: '',
    }
  },
  computed: {
  // ...mapGetters('jobopening', ['applies'])
  },
  setup() {},
  created() {
    // this.fetchApply()
  },
  mounted() {},
  unmounted() {},
  methods: {
    // ...mapActions('jobopening', ['fetchApply']),
  }
}
</script>

<style scoped>
 .title {
    font-size:22px;
    margin-top: 4px;
    color: #FFB400;
    font-weight: 900;
  }
</style>