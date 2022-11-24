<template>
  <div class="condition-item">
    <p class="condition-item-left">{{ left }}</p>
    <p class="condition-item-value">{{ right }}</p>
  </div>
</template>

<script>
export default {
  name: 'ConditionItem',
  props: {
    left: String,
    right: [String, Number],
  }
}
</script>

<style scoped>
.condition-item {
  display: grid;
  grid-template-columns: 30% 70%;
  text-align: start;
}

.condition-item-left {
  color: var(--color-black-2);
  margin-bottom: 8px;
}

.condition-item-value {
  margin-bottom: 8px;
}
</style>