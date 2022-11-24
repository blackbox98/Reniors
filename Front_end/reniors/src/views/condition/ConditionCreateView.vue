<template>
  <div class="condition-create-view">
    <form action="" @submit.prevent="submit(payload)" :key="payload.name">
      <button class="condition-create-init" @click.prevent="initPayload">
        <span>⟳</span> 초기화
      </button>
      <label for="name">맞춤공고 설정</label><br />
      <input type="text" v-model="payload.name" name="name" id="name" />
      <hr />
      <label for="sido">시/도 설정</label><br />
      <select
        v-model="selectSido.data"
        name="sido"
        id="sido"
        @change="fetchGugun(selectSido.data)"
      >
        <option v-for="(sido, index) in sidos" :key="index" :value="sido.id">
          {{ sido.name }}
        </option>
      </select>
      <hr />
      <label for="region">지역 설정 (다중 선택)</label><br />
      <select name="region" id="region" @change="selectHopearea">
        <option v-for="(gugun, index) in guguns" :key="index" :value="gugun.id">
          {{ gugun.name }}
        </option>
      </select>
      <div class="select-region-list">
        <div
          class="region-item"
          v-for="(hopearea, index) in hopeareas"
          :key="index"
        >
          <p v-if="hopearea !== null">
            {{ guguns.find((gugun) => gugun.id == hopearea)?.name }}
          </p>
          <font-awesome-icon
            v-if="hopearea !== null"
            icon="fa-solid fa-circle-xmark"
            @click="deleteHope(hopearea)"
          />
        </div>
      </div>
      <hr />
      <label for="parent">직종 설정</label><br />
      <select
        v-model="payload.parent"
        name="parent"
        id="parent"
        @change="fetchChilds(payload.parent)"
      >
        <option
          v-for="(parent, index) in parents"
          :key="index"
          :value="parent.id"
        >
          {{ parent.name }}
        </option>
      </select>
      <hr />
      <label for="child">세부 업무 (다중 선택)</label><br />
      <select name="child" id="child" @change="selectHopechild">
        <option v-for="(child, index) in childs" :key="index" :value="child.id">
          {{ child.name }}
        </option>
      </select>
      <div class="select-region-list">
        <div
          class="region-item"
          v-for="(hopechild, index) in hopechilds"
          :key="index"
        >
          <p v-if="hopechild !== null">
            {{ childs.find((child) => child.id == hopechild)?.name }}
          </p>
          <font-awesome-icon
            v-if="hopechild !== null"
            icon="fa-solid fa-circle-xmark"
            @click="deleteChild(hopechild)"
          />
        </div>
      </div>
      <hr />
      <label for="type">고용 형태</label><br />
      <select v-model="payload.type" name="type" id="type">
        <option v-for="(type, index) in types" :key="index" :value="type.value">
          {{ type.text }}
        </option>
      </select>
      <hr />
      <label for="lastEdu">최종 학력</label><br />
      <select v-model="payload.lastEdu" name="lastEdu" id="lastEdu">
        <option
          v-for="(lastEdu, index) in lastEdus"
          :key="index"
          :value="lastEdu.value"
        >
          {{ lastEdu.text }}
        </option>
      </select>
      <hr />
      <label for="minCareer">최소 경력</label><br />
      <select v-model="payload.minCareer" name="minCareer" id="minCareer">
        <option value="">최소 경력을 선택해주세요.</option>
        <option
          v-for="(minCareer, index) in minCareers"
          :key="index"
          :value="minCareer.id"
        >
          {{ minCareer.name }}
        </option>
      </select>
      <hr />
      <label for="day">근무 일수</label><br />
      <select v-model="payload.day" name="day" id="day">
        <option value="">근무 일수를 선택해주세요.</option>
        <option v-for="(day, index) in days" :key="index" :value="day.id">
          {{ day.name }}
        </option>
      </select>
      <hr />
      <label for="minSalary">급여</label><br />
      <input
        type="number"
        v-model="payload.minSalary"
        name="minSalary"
        id="minSalary"
        placeholder="숫자만 입력 가능합니다."
      />
      <div class="condition-create-info">
        <div class="condition-create-info-icon">
          <font-awesome-icon
            icon="fa-solid fa-scale-balanced"
            style="color: var(--color-red-1); font-size: 20px"
          />
        </div>
        <p>
          리니어즈는 고용상 성차별 모집 방지를 위해<br />성별, 연령 조건 설정
          기능을 제공하지 않고 있습니다.
        </p>
      </div>
      <div class="condition-create-submit">
        <button type="submit">적용하기</button>
      </div>
    </form>
  </div>
</template>

<script>
import { computed, getCurrentInstance } from "vue";
import { useStore } from "vuex";
import { isEmpty } from "lodash";

export default {
  name: "ConditionCreateView",
  props: {
    conditionData: Object,
  },
  setup(props) {
    const store = useStore();
    const instance = getCurrentInstance();

    const hopeareas = [null];
    const hopechilds = [null];
    const selectSido = { data: "" };

    const emptyArray = (array) => {
      array.splice(0, array.length);
      array.push(null);
    };

    const fetchChilds = (parent) => {
      emptyArray(hopechilds);
      return store.dispatch("category/getJobChild", parent);
    };

    const fetchGugun = (id) => {
      emptyArray(hopeareas);
      store.dispatch("category/getGugun", id);
    };

    const parents = computed(() => store.state.category.jobparents);
    const childs = computed(() => store.state.category.jobchilds);
    const sidos = computed(() => store.state.category.sidos);
    const guguns = computed(() => store.state.category.guguns);

    const selectHopearea = (event) => {
      if (hopeareas[0] === null) hopeareas.pop(0);
      if (!hopeareas.includes(event.target.value)) {
        hopeareas.push(event.target.value);
        instance?.proxy?.$forceUpdate();
      }
    };

    const deleteHope = (hopearea) => {
      hopeareas.splice(hopeareas.indexOf(hopearea), 1);
      if (isEmpty(hopeareas)) hopeareas.push(null);
      instance?.proxy?.$forceUpdate();
    };

    const selectHopechild = (event) => {
      if (hopechilds[0] === null) hopechilds.pop(0);
      if (!hopechilds.includes(event.target.value)) {
        hopechilds.push(event.target.value);
        instance?.proxy?.$forceUpdate();
      }
    };

    const deleteChild = (hopechild) => {
      hopechilds.splice(hopechilds.indexOf(hopechild), 1);
      if (isEmpty(hopechilds)) hopechilds.push(null);
      instance?.proxy?.$forceUpdate();
    };

    const types = computed(() => store.state.category.typeemployments);
    const lastEdus = computed(() => store.state.category.lastedus);
    const minCareers = [
      { id: "0", name: "경력무관" },
      { id: "1", name: "1년 미만" },
      { id: "2", name: "2년 미만" },
      { id: "3", name: "3년 이상" },
    ];

    const days = [
      { id: "1", name: "주 1일" },
      { id: "2", name: "주 2일" },
      { id: "3", name: "주 3일" },
      { id: "4", name: "주 4일" },
      { id: "5", name: "주 5일" },
      { id: "6", name: "주 6일" },
    ];

    const payload = {
      name: "",
      parent: "",
      lastEdu: "",
      minCareer: "",
      day: "",
      minSalary: "",
      type: "",
      selectSido,
      hopeareas,
      hopechilds,
    };
    const initPayload = () => {
      const propData = props.conditionData;
      (payload.name = propData?.name),
        (payload.parent = propData?.jobParentCategoryId),
        (payload.lastEdu = propData?.lastEdu),
        (payload.minCareer = propData?.minCareer),
        (payload.day = propData?.workingDay),
        (payload.minSalary = propData?.minSalary),
        (payload.type = propData?.typeEmployment),
        (selectSido.data = ""),
        emptyArray(hopechilds);
      emptyArray(hopeareas);
      instance?.proxy?.$forceUpdate();
    };

    const submit = (payload) =>
      store.dispatch("condition/createCondition", payload);

    return {
      fetchChilds,
      fetchGugun,
      submit,
      initPayload,
      selectHopearea,
      deleteHope,
      selectHopechild,
      deleteChild,
      selectSido,
      parents,
      childs,
      sidos,
      guguns,
      types,
      lastEdus,
      minCareers,
      days,
      hopeareas,
      hopechilds,
      payload,
    };
  },
  mounted() {
    this.initPayload();
  },
};
</script>

<style>
.condition-create-view {
  position: relative;
  text-align: start;
}

.condition-create-submit {
  display: flex;
  justify-content: center;
  background-color: white;
  padding: 20px;
  width: 90%;
}

.condition-create-submit > button {
  background-color: var(--color-green-1);
  border: none;
  border-radius: 1rem;
  text-align: center;
  font-size: 20px;
  font-weight: bold;
  color: white;
  width: 140px;
}

.condition-create-init {
  position: absolute;
  border: none;
  background-color: transparent;
  top: 0;
  right: 0;
}

.condition-create-init > span {
  font-size: 20px;
  font-weight: bolder;
}

.condition-create-view input,
.condition-create-view select {
  border: 1px solid var(--color-black-3);
  border-radius: 0.4rem;
  width: 100%;
  padding: 5px 10px;
}

.condition-create-type-employment {
  display: grid;
  grid-template-columns: 110px 110px 110px;
}

.condition-create-type-employment div {
  border: 1px solid var(--color-black-2);
  border-radius: 0.5rem;
  text-align: center;
  color: var(--color-black-2);
  margin: 5px;
  padding: 5px;
  cursor: pointer;
}

.condition-create-type-employment div.active {
  background-color: var(--color-red-3);
  border-color: var(--color-red-1);
  color: var(--color-red-1);
}

.condition-create-info {
  display: flex;
  border-radius: 0.2rem;
  background-color: var(--color-orange-4);
  margin-top: 20px;
  padding: 15px 30px 15px 30px;
}

.condition-create-info-icon {
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 2rem;
  background-color: white;
  height: 40px;
  width: 40px;
}

.condition-create-info p {
  margin: 0;
  margin-left: 10px;
  padding-top: 2px;
  font-size: 12px;
  font-weight: bold;
}

.condition-create-view .select-region-list {
  display: grid;
  grid-template-columns: 110px 110px 110px;
}

.condition-create-view .select-region-list > .region-item {
  position: relative;
}

.condition-create-view .select-region-list > .region-item p {
  box-sizing: border-box;
  background-color: var(--color-orange-4);
  border: 1px solid var(--color-orange-2);
  border-radius: 0.5rem;
  width: 100px;
  height: 32px;
  padding: 5px;
  margin: 5px;
  text-align: center;
  font-size: 12px;
  overflow-x: hidden;
}

.condition-create-view .select-region-list > .region-item svg {
  position: absolute;
  right: 13px;
  top: 3px;
  height: 12px;
  width: 12px;
  color: red;
}
</style>
