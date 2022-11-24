<template>
  <div>
    <Splide class="condition-list" :options="options">
      <SplideSlide
        class="condition-item"
        v-for="(condition, index) in conditions"
        :key="index"
        @click="routeResult(condition.id, $event)"
      >
        <div class="condition-item-header">
          <p class="condition-item-number">맞춤공고{{ index + 1 }}</p>
          <div class="condition-item-function">
            <font-awesome-icon
              icon="fa-solid fa-gear"
              @click.stop="editCondition(condition.id)"
            />
            <font-awesome-icon
              icon="fa-regular fa-trash-can"
              @click="deleteCondition(condition.id)"
            />
          </div>
        </div>
        <div class="condition-item-preview">
          <div class="condition-item-region">
            <font-awesome-icon icon="fa-solid fa-location-dot" />
            <template v-if="condition.hopeAreaResponseList.length == 0">
              <p>맞춤지역이 없습니다.</p>
            </template>
            <template v-else>
              <p>
                {{ condition.hopeAreaResponseList[0].gugun }} 등
                {{ condition.hopeAreaResponseList.length }}지역
              </p>
            </template>
          </div>

          <template v-if="!condition.jobParentCategoryName">
            <p>맞춤 직무를 설정해보세요.</p>
          </template>
          <template v-else>
            <p>{{ condition.jobParentCategoryName }}</p>
          </template>
        </div>
        <button class="condition-item-button" @click.stop="popover">
          더 보기
        </button>
        <div class="condition-item-popover">
          <p>고용형태</p>
          <p>{{ condition.typeEmployment }}</p>
          <p>최종학력</p>
          <p>{{ condition.lastEdu }}</p>
          <p>경력</p>
          <p>{{ condition.minCareer }}년 미만</p>
          <p>근무일수</p>
          <p>주 {{ condition.workingDay }}일</p>
        </div>
      </SplideSlide>
      <SplideSlide class="condition-item-create" @click="routeCreate">
        <i>아이콘</i>
        <p>당신의 맞춤 공고를 만들어 보세요 !</p>
      </SplideSlide>
    </Splide>
  </div>
</template>

<script>
import { mapActions, mapGetters, useStore } from "vuex";
import { useRouter } from "vue-router";
import { Splide, SplideSlide } from "@splidejs/vue-splide";

export default {
  name: "ConditionList",
  components: {
    Splide,
    SplideSlide,
  },
  setup() {
    const store = useStore();
    const router = useRouter();

    const routeCreate = (event) => {
      if (event.currentTarget.classList.contains("is-active"))
        router.push({ name: "ConditionCreate" });
    };
    const routeResult = (id, event) => {
      if (event.currentTarget.classList.contains("is-active")) {
        router.push({ name: "ConditionResult", params: { conditionId: id } });
        const search = () => store.dispatch("condition/search", id);
        search();
      }
    };

    const popover = (event) => {
      if (event.target.innerText === "더 보기") event.target.innerText = "닫기";
      else event.target.innerText = "더 보기";
      event.target.parentElement.classList.toggle("popover-active");
      event.target.classList.toggle("active");
      event.target.nextSibling.classList.toggle("active");
    };

    const options = {
      padding: 50,
      arrows: false,
      pagination: false,
      isNavigation: true,
      gap: "1rem",
    };

    const deleteCondition = (id) =>
      store.dispatch("condition/deleteCondition", id);
    const editCondition = (id) =>
      router.push({ name: "ConditionEdit", params: { conditionId: id } });

    return {
      popover,
      routeCreate,
      routeResult,
      deleteCondition,
      editCondition,
      options,
    };
  },
  created(){
    this.fetchConditions();
  },
  computed:{
    ...mapGetters("condition",["conditions"])
  },
  methods:{
    ...mapActions("condition",["fetchConditions"])
  },
};
</script>

<style scoped>
@import "@splidejs/splide/dist/css/themes/splide-default.min.css";

.splide__track--nav > .splide__list > .splide__slide {
  position: relative;
  border: none;
  border-radius: 0.5rem;
  height: 140px;
  width: 70vw;
  margin: 5px;
  margin-bottom: 15px;
  padding: 10px;
  transition: height ease 0.5s;
}

.splide__track--nav > .splide__list > .splide__slide.is-active {
  border: none;
  box-shadow: 0px 0px 3px var(--color-black-1);
}
.splide__track--nav > .splide__list > .splide__slide.popover-active {
  height: 225px;
}

.splide__track--nav > .splide__list > .splide__slide.is-active > button {
  box-shadow: 0px 1.5px 2px var(--color-black-2);
}

.condition-list {
  margin-bottom: 10px;
  width: 900px;
  margin: auto;
}

.condition-item {
  background-color: var(--color-orange-2);
  width: 328px;
}

.condition-item-header {
  display: flex;
  justify-content: space-between;
  font-size: 12px;
}

.condition-item-number {
  font-weight: bold;
}

.condition-item-function {
  display: flex;
  gap: 6px;
}

.condition-item-region {
  display: flex;
  justify-content: center;
  gap: 8px;
  font-size: 20px;
  height: 30px;
}

.condition-item-create {
  display: flex;
  flex-direction: column;
  justify-content: center;
  border: 1px dashed black !important;
  background-color: var(--color-green-4);
  font-weight: bold;
}

.condition-item-popover {
  display: none;
  border-radius: 0.4rem;
  background-color: white;
  padding: 10px 20px;
}

.condition-item-popover.active {
  display: grid;
  grid-template-columns: 80px 140px;
}

.condition-item-popover p {
  margin: 0;
  font-size: 12px;
  text-align: start;
}

.condition-item-button {
  position: absolute;
  bottom: 0;
  width: 56px;
  height: 26px;
  transform: translate(-50%, 50%);
  border: 1px solid var(--color-yellow-1);
  border-radius: 1rem;
  background-color: white;
  font-size: 12px;
}
@media(max-width: 760px){
  .condition-list {
    width: 328px;
  }
}
</style>
