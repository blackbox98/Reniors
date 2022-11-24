<template>
  <div>
    <div class="box">
      <button @click="deleteLicense(this.license.id)" style="margin-right: 4px">
        <img class="DELETE" src="@/assets/DELETE.svg" alt="DELETE" />
      </button>
      <button @click="updateLicense">
        <img class="EDIT" src="@/assets/EDIT.svg" alt="EDIT" />
      </button>
      <p class="name">{{ this.license.name }}</p>

      <p class="title">등급</p>
      <p class="contents">{{ this.license.grade }}</p>
      <p class="title">취득 날짜</p>
      <p class="contents">{{ this.pass }}</p>
    </div>
    <resume-license-form
      v-show="editshow"
      :license="license"
      @test="test"
      action="update"
    ></resume-license-form>
  </div>
</template>
<script>
import ResumeLicenseForm from "@/components/resume/ResumeLicenseForm.vue";
import { mapActions } from "vuex";

export default {
  name: "ResumeLicenseDetail",
  props: {
    license: Object,
  },
  components: {
    ResumeLicenseForm,
  },
  data() {
    var pass = this.license.passedAt.substring(0, 10);
    return {
      pass,
      editshow: false,
    };
  },
  methods: {
    ...mapActions(["deleteLicense"]),
    updateLicense() {
      this.editshow = !this.editshow;
    },
    test() {
      this.editshow = !this.editshow;
    },
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
};
</script>

<style scoped>
p {
  margin-bottom: 0px;
  text-align: left;
}

.box {
  border-radius: 10px;
  background-color: #f9f9f9;
  box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.25);

  margin-bottom: 20px;
  padding: 15px;
}

.title {
  font-size: 12px;
  margin-bottom: 1px;
  margin-left: 1px;
  color: #6d6d6d;
}

.name {
  margin-bottom: 20px;
  font-weight: 700;
  color: black;
}

.contents {
  margin-bottom: 2px;
  font-weight: 500;
  color: black;
}

button {
  float: right;
  margin: 2px;
  border: none;
  background-color: transparent;
}
/* .during{
  font-size: 13px;
}

.company {
  font-weight: 700;
  margin-bottom: 5px;
} */
</style>
