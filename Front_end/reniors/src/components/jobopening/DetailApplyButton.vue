<template>
  <div class="detail-apply-button">
    <button
      @click="apply(isLogin)"
      :class="isActive ? 'apply-active' : 'apply-deactive'"
    >
      {{ isApply ? "지원완료" : "지원하기" }}
    </button>
  </div>
</template>

<script>
import { computed } from "vue";
import { useStore } from "vuex";
export default {
  name: "DetailApplyButton",
  props: {
    jobopeningId: [String, Number],
  },
  setup(props) {
    const store = useStore();

    const apply = (login) => {
      if (login === true){
        if (confirm("공고에 지원하시겠습니까?")) {
            store.dispatch("jobopening/apply", props.jobopeningId);
        }
      }
      else{
        alert("로그인이 필요합니다!");
      }
    };
    const isLogin = computed(() => store.getters["isLogginedIn"]);
    const isApply = computed(() => store.getters["jobopening/isApply"]);
    const isActive = computed(() => !isApply.value && isLogin.value);
    const fetchApply = () => store.dispatch("jobopening/fetchApply");
    fetchApply();

    return {
      apply,
      isLogin,
      isApply,
      isActive,
    };
  },
};
</script>

<style scoped>
.detail-apply-button {
  position: fixed;
  bottom: 50px;
  border: 1px solid var(--color-yellow-3);
  background-color: white;
  width: 100%;
  height: 64px;
  padding: 10px;
  align-content: center;
}

@media screen and (min-width: 720px) {
  .detail-apply-button {
    width: 70vh;
  }
}

.detail-apply-button > button {
  height: 48px;
  width: 90%;
  width: 100%;
  border: none;
  border-radius: 0.5rem;
  color: white;
}

.detail-apply-button > button.apply-active {
  background-color: var(--color-red-1);
}

.detail-apply-button > button.apply-deactive {
  background-color: var(--color-red-3);
}

.detail-apply-button > button.apply-deactive:hover {
  cursor: default;
}
</style>
