<template>
  <div >
    <template v-for="apply in applylist" :key="apply.id">
      <template
        v-if="
          apply.jobOpeningProcess == '서류심사중' ||
          apply.jobOpeningProcess == '서류불합격'
        "
      >
        <div>
          <input
            :value="apply"
            type="checkbox"
            v-model="pu.passUser"
            class="apply-resume-list-checkbox"
          />
          <applier-resume-list-item :apply="apply"> </applier-resume-list-item>
        </div>
      </template>
    </template>
  </div>
    <div class="apply-resume-pass-btn-box">
      <button @click="resumepass()" class="apply-resume-pass-btn">
        서류합격
      </button>

    </div>
</template>

<script setup>
import "@vuepic/vue-datepicker/dist/main.css";
import { mapActions, mapGetters } from "vuex";
</script>
<script>
import ApplierResumeListItem from "./ApplierResumeListItem.vue";
export default {
  components: {
    ApplierResumeListItem,
  },
  props: {
    jobopeningdetail: Object,
  },
  data() {
    return {
      pu:{
      passUser: [],
      }
    };
  },
  watch: {
  },
  async created() {
    await this.getapplylist(this.$route.params.no);
  },
  computed: {
    ...mapGetters("company", ["jobopening", "applylist"]),
  },
  methods: {
    ...mapActions("company", [
      "getapplylist",
      "progressJobOpening",
      "updateApply",
    ]),
    ...mapActions("home", ["createNotice"]),
    resumepass() {
      if (confirm("선택된 지원자의 상태를 서류 합격으로 변경하시겠습니까?")) {
        this.pu.passUser.forEach((data) => {
          this.updateApply({
            jobOpeningId: this.jobopeningdetail.id,
            applyId: data.id,
            apply: {
              jobOpeningProcess: "면접",
            },
          });
          this.createNotice({
            jobOpeningProcess: "면접",
            userId: data.userId,
            applyId: data.id,
          });
        });

        let tmparr = [];
        this.applylist.forEach((apply) => {
          if (apply.jobOpeningProcess == "서류심사중") {
            tmparr.push(apply);
          }
        });
        let unpassUser = tmparr.filter((x) => !this.pu.passUser.includes(x));
        unpassUser.forEach((data) => {
          this.updateApply({
            jobOpeningId: this.jobopeningdetail.id,
            applyId: data.id,
            apply: {
              jobOpeningProcess: "서류불합격",
            },
          });
          this.createNotice({
            jobOpeningProcess: "서류불합격",
            userId: data.userId,
            applyId: data.id,
          });
        });

        let data = {
          no: this.jobopeningdetail.id,
          progress: {
            jobOpeningProcess: "면접심사중",
          },
        };
        this.progressJobOpening(data);
        window.location.reload();
      }
    },

    resumeview() {
      this.$router.push({
        name: "resumeview",
        params: { no: this.apply.userId },
      });
    },
  },
};
</script>

<style scoped>
.apply-resume-list-box {
  margin-bottom: 100px;
}
.apply-resume-list-checkbox {
  float: right;
  margin-right: 10px;
  margin-top: 10px;
  width: 24px;
  height: 24px;
  cursor: pointer;
  border-radius: 5px;
  -webkit-appearance: none;
  border: 2px solid var(--color-black-3);
  position: relative;
  display: inline-block;
}
.apply-resume-list-checkbox:checked {
  background-color: white;
}
.apply-resume-list-checkbox:checked::after {
  content: "✔";
  font-size: 20px;
  width: 20px;
  height: 20px;
  text-align: center;
  position: absolute;
  left: 0;
  top: 0;
  padding: auto;
  line-height: 24px;
  color: var(--color-yellow-1);
}
.apply-resume-pass-btn-box{
  width: 100vw;
  left: 0;
  position: fixed;
  bottom: 50px;
  height: 64px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 0;
}
.apply-resume-pass-btn {
  width: 328px;
  height: 48px;
  border: none;
  padding: 6px 40px;
  font-size: 20px;
  font-weight: bold;
  border-radius: 10px;
  background-color: var(--color-green-1);
  color: white;
}
</style>
