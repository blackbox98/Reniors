<template>
  <div>
    <form @submit.prevent="onSubmit">
      <div class="career">
        <p class="forminfo">
          기업명<span class="required">&nbsp;&nbsp;*</span>
        </p>
        <b-form-input
          class="mb-3"
          type="text"
          placeholder="기업명"
          v-model="newCareer.companyName"
        ></b-form-input>
        <p class="forminfo">
          입사날짜<span class="required">&nbsp;&nbsp;*</span>
        </p>
        <b-form-input
          class="mb-3"
          type="date"
          placeholder="생년-월-일"
          v-model="newCareer.startedAt"
        ></b-form-input>
        <p class="forminfo">
          퇴사날짜<span class="required">&nbsp;&nbsp;*</span>
        </p>
        <b-form-input
          class="mb-3"
          type="date"
          placeholder="생년-월-일"
          v-model="newCareer.finishedAt"
        ></b-form-input>
        <p class="forminfo">상세내용</p>
        <b-form-textarea
          id="textarea-default"
          class="mb-3"
          placeholder="맡으신 직무와 업무에 대해 입력해주세요"
          rows="8"
          v-model="newCareer.jobContents"
        ></b-form-textarea>
        <button>저장</button>
      </div>
    </form>
  </div>
</template>
<script>
import { mapActions } from "vuex";

export default {
  components: {},
  props: {
    career: Object,
    action: String,
  },
  data() {
    return {
      newCareer: {
        companyName: this.career.companyName,
        finishedAt: this.career.finishedAt,
        jobContents: this.career.jobContents,
        startedAt: this.career.startedAt,
      },
    };
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {
    ...mapActions(["createCareer", "updateCareer"]),
    onSubmit() {
      if (this.action === "create") {
        this.createCareer(this.newCareer);
        this.$emit("show");
      } else if (this.action === "update") {
        const payload = {
          id: this.career.id,
          ...this.newCareer,
        };
        console.log(payload);
        this.updateCareer(payload);
        this.$emit("test");
      }
    },
  },
};
</script>

<style scoped>
input[type="date"]::-webkit-datetime-edit-text,
input[type="date"]::-webkit-datetime-edit-month-field,
input[type="date"]::-webkit-datetime-edit-day-field,
input[type="date"]::-webkit-datetime-edit-year-field {
  color: #888;
}

p {
  text-align: left;
}

.required {
  font-size: 13px;
  color: var(--color-red-1);
}

.career {
  margin: 20px;
  height: 600px;
  border-style: none none dashed none;
  border-width: 0.5px;
  border-color: #eaeaea;
}

button {
  float: right;
  width: 80px;
  height: 32px;
  font-weight: 700;
  color: white;

  background-color: #8cd6c1;
  /* border: 1px solid #37BF99; */
  border: none;
  border-radius: 10px;
  box-shadow: 1px 1px 1px rgba(0, 0, 0, 0.05);
}
form {
  margin-top: 20px;
}
</style>
