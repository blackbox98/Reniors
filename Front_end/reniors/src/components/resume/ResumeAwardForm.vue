<template>
  <div>
    <form @submit.prevent="onSubmit">
      <div class="award">
        <p class="forminfo">
          수상명<span class="required">&nbsp;&nbsp;*</span>
        </p>
        <b-form-input
          class="mb-3"
          type="text"
          v-model="newAward.name"
          placeholder="수상명"
        ></b-form-input>
        <p class="forminfo">
          수상날짜<span class="required">&nbsp;&nbsp;*</span>
        </p>
        <b-form-input
          class="mb-3"
          type="date"
          v-model="newAward.awardedAt"
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
  name: "ResumeAwardForm",
  components: {},
  props: {
    award: Object,
    action: String,
  },
  data() {
    return {
      newAward: {
        name: this.award.name,
        awardedAt: this.award.awardedAt,
      },
    };
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {
    ...mapActions(["createAward", "updateAward"]),
    onSubmit() {
      if (this.action === "create") {
        this.createAward(this.newAward);
        this.$emit("show");
      } else if (this.action === "update") {
        const payload = {
          id: this.award.id,
          ...this.newAward,
        };
        this.updateAward(payload);
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

.award {
  margin: 20px;
  height: 240px;
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
