<template>
  <template v-if="!jobopenings">
    <div class="condition-result-view">
      <p>조건에 맞는 공고가 없습니다 😅</p>
    </div>
  </template>
  <template v-else>
    <div class="condition-result-view">
      <p>
        총 <span>{{ jobopenings.length }}</span
        >건의 공고가 있어요 😄
      </p>
      <ConditionResultItem
        v-for="(jobopening, index) in jobopenings"
        :key="index"
        :jobopening="jobopening"
      />
    </div>
  </template>
</template>

<script>
import { computed } from "vue";
import { useStore } from "vuex";
import ConditionResultItem from "@/components/condition/ConditionResultItem.vue";

export default {
  name: "ConditionResultView",
  components: {
    ConditionResultItem,
  },
  setup() {
    const store = useStore();

    const jobopenings = computed(() => store.getters["condition/jobopenings"]);

    return {
      jobopenings,
    };
  },
};
</script>

<style>
.condition-result-view {
  margin: auto;
  width: 900px;
  justify-content: center;
}

.condition-result-view > p {
  text-align: start;
  font-weight: bold;
  width: 900px;
  margin: auto;
}

.condition-result-view > p > span {
  color: var(--color-green-1);
}

@media(max-width: 760px){
  .condition-result-view {
    width: 328px;
  }

  .condition-result-view > p {
    width: 328px;
  }
}
</style>
