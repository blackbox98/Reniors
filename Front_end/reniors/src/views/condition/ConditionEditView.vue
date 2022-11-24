<template>
  <div>
    <ConditionCreateView :conditionData="condition" />
  </div>
</template>

<script>
import { computed } from 'vue'
import { useStore } from 'vuex'
import { useRoute } from 'vue-router'
import ConditionCreateView from './ConditionCreateView.vue'

export default {
  name: 'ConditionEditView',
  components: {
    ConditionCreateView,
  },
  setup() {
    const store = useStore()

    const fetchEdit = () => store.dispatch('condition/fetchEdit', useRoute().params.conditionId)
    fetchEdit()
    const condition = computed(() => store.getters['condition/editData'])

    return {
      condition,
    }
  }
}
</script>

<style>

</style>