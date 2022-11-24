<template>
  <div>
    <div class="jobopening-box">
      <p style="font-size:20px; margin-bottom: 4px; font-weight: bold;">{{ jobopening.title }}</p>
      <p style="font-size:16px; margin-bottom:4px">{{ jobopening.companyResponse.name }}</p>
      <div class="jobopening-box-date">
        <div class="start-badge" style="font-size:14px">시작</div>
        <p>{{ jobopening.createdDate?.split('T')[0] }}</p>
      </div>
      <div class="jobopening-box-date">
        <div class="end-badge" style="font-size:14px">종료</div>
        <p class="end-date">{{ jobopening.finishedDate?.split('T')[0] }}</p>
      </div>
    </div>
    <div class="jobopening-box">
      <h3>모집조건</h3>
      <hr>
      <ConditionItem left="모집기간" :right="period" />
      <ConditionItem left="경력" :right="jobopening.minCareer + '년 이상'" />
      <ConditionItem left="학력" :right="jobopening.lastEdu" />
    </div>
    <div class="jobopening-box">
      <h3>근무조건</h3>
      <hr>
      <ConditionItem left="연봉" :right="jobopening.minSalary/10000 + ' 만원'" />
      <ConditionItem left="지역" :right="sidos?.find((sido) => sido.id===jobopening?.sidoId)?.name + ' ' + guguns?.find((gugun) => gugun.id===jobopening?.gugunId)?.name" />
      <ConditionItem left="근무일" :right="'주 ' + jobopening.workingDay + '일'" />
    </div>
    <div class="jobopening-box">
      <h3>모집내용</h3>
      <hr>
      <ConditionItem left="직책" :right="jobopening.jobPosition" />
      <ConditionItem left="직무" :right="parents?.find((parent) => parent.id===jobopening?.jobParentCategory)?.name" />
      <ConditionItem left="직무(상세)" :right="childs?.find((child) => child.id===jobopening?.jobChildCategoryId)?.name" />
      <ConditionItem left="고용형태" :right="jobopening.typeEmployment" />
      <ConditionItem left="모집인원" :right="jobopening.numberPeople + '명'" />
    </div>
  </div>
</template>

<script>
import { computed } from 'vue'
import { useStore } from 'vuex'
import ConditionItem from './ConditionItem.vue'

export default {
  name: 'DetailCondition',
  components: {
    ConditionItem,
  },
  setup() {
    const store = useStore()
    
    const jobopening = computed(() => store.getters['jobopening/selectedJobopening'])
    const period = computed(() => jobopening.value.createdDate?.split('T')[0] + ' ~ ' + jobopening.value.finishedDate?.split('T')[0])

    const fetchParents = () => store.dispatch('category/getJobParent')
    fetchParents()
    const parents = computed(() => store.state.category.jobparents)
    const fetchChild = () => store.dispatch('category/getJobChild', jobopening.value.jobParentCategory)
    fetchChild()
    const childs = computed(() => store.state.category.jobchilds)

    const fetchSido = () => store.dispatch('category/getSido')
    const fetchGugun = (sidoId) => store.dispatch('category/getGugun', sidoId)
    fetchSido()
    fetchGugun(jobopening.value.sidoId)
    const sidos = computed(() => store.state.category.sidos)
    const guguns = computed(() => store.state.category.guguns)
    return {
      jobopening, period, parents, childs, sidos, guguns,
    }
  },
}
</script>

<style scoped>

.jobopening-box {
  background-color: white;
  border-radius: 1rem;
  margin: 10px;
  padding: 8px 16px;
  text-align: start;
  
}

.jobopening-box p, .jobopening-box h3 {
  margin: 0;
}

.jobopening-box h5 {
  padding: 5px 0;
  font-size: 18px;
}

.jobopening-box > h3 {
  font-size: 16px;
}

.jobopening-box-date {
  display: flex;
}

.jobopening-box-date > p {
  padding-top: 4px;
}

.start-badge, .end-badge {
  width: 50px;
  border-radius: 1rem;
  text-align: center;
  margin: 3px;
  margin-right: 10px;
}

.start-badge {
  border: 1px solid var(--color-black-2);
  color: var(--color-black-2);
}

.end-badge {
  border: 1px solid red;
  color: red;
}

.jobopening-box-date > .end-date {
  color: red;
}

.jobopening-box > hr {
  width: 340px;
  transform: translateX(-10px);
  border: 1px solid var(--color-red-3);
  margin: 5px 0px;
}

</style>