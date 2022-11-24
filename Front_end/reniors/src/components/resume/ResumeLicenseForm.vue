<template>
  <div>
    <form @submit.prevent="onSubmit">
      <div class="license">
        <p class="forminfo">
          자격증명<span class="required">&nbsp;&nbsp;*</span>
        </p>
        <b-form-input
          class="mb-3"
          type="text"
          v-model="newLicense.name"
          placeholder="자격증명을 입력해주세요."
        ></b-form-input>
        <p class="forminfo">등급<span class="required">&nbsp;&nbsp;*</span></p>
        <b-form-input
          class="mb-3"
          type="text"
          v-model="newLicense.grade"
          placeholder="자격증 등급을 입력해주세요."
        ></b-form-input>
        <p class="forminfo">
          취득일자<span class="required">&nbsp;&nbsp;*</span>
        </p>
        <b-form-input
          class="mb-3"
          type="date"
          v-model="newLicense.passedAt"
          placeholder="생년-월-일"
        ></b-form-input>
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
    license: Object,
    action: String,
  },
  data() {
    return {
      newLicense: {
        grade: this.license.grade,
        name: this.license.name,
        passedAt: this.license.passedAt,
      },
    };
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {
    ...mapActions(["createLicense", "updateLicense"]),
    onSubmit() {
      if (this.action === "create") {
        this.createLicense(this.newLicense);
        this.$emit("show");
      } else if (this.action === "update") {
        const payload = {
          id: this.license.id,
          ...this.newLicense,
        };
        this.updateLicense(payload);
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

.license {
  margin: 20px;
  height: 340px;
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
</style>
