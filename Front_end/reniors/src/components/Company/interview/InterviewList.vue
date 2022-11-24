<template>
  <div
    v-if="interviewapplylistasc.length === 0"
    class="interview-list-item-box"
  >
    <div class="interview-list-item-empty">
      <p class="item-empty-msg">예정된 면접 정보가 없습니다.</p>
    </div>
  </div>
  <div v-else class="interview-list-item-box">
    <interview-list-item
      v-for="interviewapply in interviewapplylistasc"
      :key="interviewapply.id"
      :interviewapply="interviewapply"
    ></interview-list-item>
  </div>
</template>

<script>
import { mapActions, mapMutations, mapGetters } from "vuex";
import InterviewListItem from "./InterviewListItem.vue";
export default {
  components: { InterviewListItem },
  created() {
    this.CLEAR_INTERVIEW_APPLY_LIST_ASC;
    this.getinterviewapplylistasc(this.$route.params.no);
    this.setheader("면접일정");
  },
  methods: {
    ...mapActions("company", ["getinterviewapplylistasc", "setheader"]),
  },
  computed: {
    ...mapGetters("company", ["interviewapplylistasc"]),
    ...mapMutations("company", ["CLEAR_INTERVIEW_APPLY_LIST_ASC"]),
  },
};
</script>

<style scope>
.interview-list-item-box {
  margin-bottom: 6vh;
  margin-top: 64px;
}

.interview-list-item-empty {
  width: 328px;
  padding: 20px;
  border-radius: 10px;
  margin-top: 64px;
  margin-bottom: 20px;
  border-color: var(--color-black-2);
  box-shadow: inset 0 0 1px 1px var(--color-black-3),
    0 0 5px var(--color-black-3);
}

.interview-list-item-empty > .item-empty-msg {
  width: 100%;
  font-weight: bold;
  font-size: 17px;
}
</style>
