<template>
  <div class="total">
    <user-eval-list-item
      v-for="(usereval, idx) in userevallist"
      :key="usereval"
      :idx="idx"
      :usereval="usereval"
    ></user-eval-list-item>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";
import UserEvalListItem from "./UserEvalListItem.vue";
export default {
  components: {
    UserEvalListItem,
  },
  computed: {
    ...mapGetters("company", ["userevallist", "jobopening"]),
  },
  created() {
    let data = {
      jobOpeningId: this.jobopening.id,
      userId: this.$route.params.no,
    };
    this.getUserEvalList(data);
  },
  methods: {
    ...mapActions("company", ["getUserEvalList"]),
  },
};
</script>

<style scoped>
.total {
  margin-top: 64px;
  margin-bottom: 56px;
}
</style>
