<template>
  <div class="company-header container-fluid">
    <company-header></company-header>
  </div>
  <router-view></router-view>
</template>

<script>
import CompanyHeader from "@/components/common/CompanyHeader.vue";
export default {
  components: {
    CompanyHeader,
  },
};
</script>

<style scoped>
.company-header {
  position: fixed;
  top:0;
  background-color: #ffffff;
  height: 48px;
  width: 100%;
  margin: 0;
  padding: 0;
  display: flex;
  justify-content: space-between;
  align-items: center;
  box-shadow: rgba(0, 0, 0, 0.16) 0px 1px 4px;
}
</style>
