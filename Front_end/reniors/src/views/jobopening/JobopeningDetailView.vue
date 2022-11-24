<template>
  <div>
    <NavBar :jobopeningId="jobopeningId" />
    <b-tabs
      content-class="mt-3" fill
      class="jobopening-detail-tab"
      active-nav-item-class="bg-transparent font-weight-bold text-dark"
    >
      <b-tab title="모집조건" title-link-class="text-secondary" ><DetailCondition /></b-tab>
      <b-tab title="상세모집내용" title-link-class="text-secondary"><DetailContent /></b-tab>
      <b-tab title="기업정보" title-link-class="text-secondary"><DetailInfo /></b-tab>
    </b-tabs>
    <DetailApplyButton :jobopeningId="jobopeningId" />
  </div>
</template>

<script>
import { useRoute } from "vue-router";
import { useStore } from "vuex";
import NavBar from "@/components/jobopening/NavBar.vue";
import DetailCondition from "@/components/jobopening/DetailCondition.vue";
import DetailContent from "@/components/jobopening/DetailContent.vue";
import DetailInfo from "@/components/jobopening/DetailInfo.vue";
import DetailApplyButton from "@/components/jobopening/DetailApplyButton.vue";

export default {
  name: "JobopeningDetailView",
  components: {
    NavBar,
    DetailCondition,
    DetailContent,
    DetailInfo,
    DetailApplyButton,
  },
  setup() {
    const store = useStore();
    const jobopeningId = useRoute().params.jobopeningId;
    const selectJobopening = () =>
      store.dispatch("jobopening/selectJobopening", jobopeningId);
    selectJobopening();

    return {
      jobopeningId,
      selectJobopening,
    };
  },
};
</script>

<style scoped>
.jobopening-detail-tab {
  background-color: var(--color-red-4);
  padding-bottom: 5px;
  margin-bottom: 104px;
}
.tab-title-class{
  color: black !important;
}
.tab-title-class:hover{
  color: black !important;
}
.tab-title-class:visited{
  color: black !important;
}
.jobopening-detail-tab button {
  color: white !important;
}

.jobopening-detail-tab button:hover {
  color: white;
}

.jobopening-detail-tab button.active {
  font-weight: bold;
}
.jobopening-detail-tab .nav-tabs {
    --bs-nav-tabs-border-width: 1px;
    --bs-nav-tabs-border-color: #dee2e6;
    --bs-nav-tabs-border-radius: 0.375rem;
    --bs-nav-tabs-link-hover-border-color: #e9ecef #e9ecef #dee2e6;
    --bs-nav-tabs-link-active-color: #FF843E;
    --bs-nav-tabs-link-active-bg: #fff;
    --bs-nav-tabs-link-active-border-color: #dee2e6 #dee2e6 #fff;
    border-bottom: var(--bs-nav-tabs-border-width) solid var(--bs-nav-tabs-border-color);
}
.jobopening-detail-tab .nav {
    --bs-nav-link-padding-x: 1rem;
    --bs-nav-link-padding-y: 0.5rem;
    --bs-nav-link-font-weight: bold;
    --bs-nav-link-color: black;
    --bs-nav-link-hover-color: black;
    --bs-nav-link-disabled-color: red;
    display: flex;
    flex-wrap: wrap;
    padding-left: 0;
    margin-bottom: 0;
    list-style: none;
  justify-content: space-evenly;
  background-color: var(--color-red-1);
}
.jobopening-detail-tab .nav-link {
  color:#FF843E
}
</style>
