<template>
  <div class="company-jobOpening-regist-box">
    <!-- <form action=""> -->
    <p class="sub-title">기본 채용 정보들을 입력해주세요.</p>
    <div class="mb-3 mt-3">
      <label for="title" class="form-label company-form-label">제목</label>
      <input
        type="text"
        class="form-control company-form-control"
        id="title"
        placeholder="채용 공고 제목을 입력해주세요."
        name="title"
        v-model="jobopening.title"
      />
    </div>
    <div class="mb-3 jobOpening-datepicker ">
      <label class="company-form-label">채용 종료일시</label>
      <datepicker 
      style="
      width: 100%; 
      border-radius: 5px;
      border: 1px solid black;
      border-color: var(--color-black-3);
      padding: 5px;
      padding-left: 10px;
      box-shadow: none;"
      v-model="jobopening.finishedDate" format="YYYY-MM-DD" />
    </div>

    <div class="mb-3 mt-3">
      <label for="numberPeople" class="form-label company-form-label">채용 인원</label>
      <input
        type="number"
        class="form-control company-form-control"
        id="numberPeople"
        placeholder="숫자로만 입력해주세요."
        name="numberPeople"
        v-model="jobopening.numberPeople"
      />
    </div>

    <div class="mb-3 mt-3 company-form-label">
      <label for="contents company-form-label">상세 내용</label>
      <textarea
        class="form-control company-form-control"
        rows="5"
        id="contents"
        name="text"
        v-model="jobopening.contents"
      ></textarea>
    </div>

    <div class="mb-3 mt-3">
      <label class="form-label company-form-label">채용공고 이미지 </label>
      <input
        type="file"
        class="form-control company-form-control"
        placeholder="이미지를 선택해주세요"
        ref="img"
        @change="changeImg()"
      />
    </div>
    <hr>
    <p class="sub-title">상세 채용 조건을 등록해주세요.</p>
    <div>
      <label class="company-form-label ">직무대분류</label>
      <select required v-model="category.jobparent" class="mb-3 company-form-control">
        <option
          v-for="jobparent in jobparents"
          :value="jobparent.id"
          :key="jobparent"
        >
          {{ jobparent.name }}
        </option>
      </select>

      <label class="company-form-label">직무소분류</label> 
      <select required v-model="jobopening.jobChildCategoryId" class="company-form-control">
        <option
          v-for="jobchild in jobchilds"
          :value="jobchild.id"
          :key="jobchild"
        >
          {{ jobchild.name }}
        </option>
      </select>
    </div>
    <div class="mb-3 mt-3">
      <label for="jobPosition" class="form-label company-form-label">직책</label>
      <input
        type="text"
        class="form-control company-form-control"
        id="jobPosition"
        placeholder="직책을 입력해주세요."
        name="jobPosition"
        v-model="jobopening.jobPosition"
      />
    </div>

    <div class="mb-3 mt-3">
      <label for="minSalary" class="form-label company-form-label">급여</label>
      <input
        type="text"
        class="form-control company-form-control"
        id="minSalary"
        placeholder="급여를 입력해주세요."
        name="minSalary"
        v-model="jobopening.minSalary"
      />
    </div>

    <div class="mb-3 mt-3">
      <label for="workingDay" class="form-label company-form-label">근무횟수</label>
      <input
        type="text"
        class="form-control company-form-control"
        id="workingDay"
        placeholder="급무횟수를 입력해주세요."
        name="workingDay"
        v-model="jobopening.workingDay"
      />
    </div>

    <div>
      <label class="company-form-label">시도</label>
      <select v-model="category.sido" class="mb-3 company-form-control">
        <option v-for="sido in sidos" :value="sido.id" :key="sido">
          {{ sido.name }}
        </option>
      </select>

      <label class="company-form-label">구군</label>
      <select v-model="jobopening.gugunId" class="mb-3 company-form-control">
        <option v-for="gugun in guguns" :value="gugun.id" :key="gugun">
          {{ gugun.name }}
        </option>
      </select>
    </div>

    <div class="mb-3 mt-3">
      <label class="company-form-label">학력</label>
      <select v-model="jobopening.lastEdu" class="company-form-control">
        <option
          v-for="lastedu in lastedus"
          :value="lastedu.value"
          :key="lastedu"
        >
          {{ lastedu.text }}
        </option>
      </select>
    </div>

    <div class="mb-3 mt-3">
      <label for="minCareer" class="form-label company-form-label">경력</label>
      <input
        type="text"
        class="form-control company-form-control"
        id="minCareer"
        name="minCareer"
        v-model="jobopening.minCareer"
      />
    </div>

    <div class="mb-3 mt-3">
      <label class="company-form-label ">근무형태</label>
      <select v-model="jobopening.typeEmployment" class="company-form-control">
        <option
          v-for="typeemployment in typeemployments"
          :value="typeemployment.value"
          :key="typeemployment"
        >
          {{ typeemployment.text }}
        </option>
      </select>
    </div>

    <button color="blue" class="ml-3" @click="regist">채용 공고 등록하기</button>
    <!-- </form> -->
  </div>
</template>

<script setup>
import Datepicker from "vue3-datepicker";
// import { ref } from "vue";
import { mapActions, mapGetters } from "vuex";
import moment from "moment";
// const picked = ref(new Date())
</script>

<script>
export default {
  components: {
    Datepicker,
  },
  data() {
    return {
      category: {
      sido: null,
      jobparent: null,
      },
      jobopening: {
        contents: null,
        contentsImgName: null,
        contentsImgPath: null,
        createdDate: new Date().now,
        finishedDate: new Date(),
        gugunId: 0,
        jobChildCategoryId: 0,
        jobPosition: null,
        lastEdu: null,
        minCareer: 0,
        minSalary: 0,
        numberPeople: 0,
        title: null,
        typeEmployment: null,
        workingDay: 0,
      },
      jobOpeningImg: "",
    };
  },
  watch: {
    'category.sido': function (data) {
      this.getGugun(data);
    },
    'category.jobparent': function (data) {
      this.getJobChild(data);
    },
  },
  computed: {
    ...mapGetters("category", [
      "sidos",
      "guguns",
      "jobparents",
      "jobchilds",
      "lastedus",
      "typeemployments",
    ]),
  },
  async created() {
    await this.setheader('채용등록')
    await this.getSido();
    await this.getJobParent();
  },
  methods: {
    ...mapActions("category", [
      "getSido",
      "getGugun",
      "getJobParent",
      "getJobChild",
    ]),
    ...mapActions("company", ["registJobOpening","setheader"]),
    regist() {
      this.jobopening.createdDate = new Date(
        moment(this.jobopening.createdDate).format("YYYY-MM-DD")
      );
      this.jobopening.finishedDate = new Date(
        moment(this.jobopening.finishedDate).format("YYYY-MM-DD")
      );

      const formData = new FormData();
      formData.append("img", this.jobOpeningImg[0]);
      formData.append(
        "data",
        new Blob([JSON.stringify(this.jobopening)], {
          type: "application/json",
        })
      );
      this.registJobOpening(formData);
      this.$router.push({ name: "companyjobopeninglist" });
    },
    changeImg() {
      this.jobOpeningImg = this.$refs.img.files;
    },
  },
};
</script>

<style scoped>
.company-form-label {
  width: 100%;
  text-align: left;
}
.company-form-control{
  width: 100% ;
  border-radius: 5px;
  border-color: var(--color-black-3);
}
select{
  padding: 10px;
}
.company-form-control:focus {
  border-color: var(--color-green-2) !important;
  box-shadow: inset 0 1px 1px var(--color-green-1), 0 0 8px var(--color-green-2) !important;
}
.company-jobOpening-regist-box{
  margin-bottom: 150px;
  padding: 10px;
  width: 100%;
}
textarea{
  resize: none;
}
.sub-title{
  width: 100%;
  text-align: left;
  font-weight: bold;
  font-size: 20px;
}
.company-jobOpening-regist-box > button {
  background-color: var(--color-green-1);
  width: 45%;
  height: 80%;
  border-radius: 10px;
  border: none;
  color: white;
  font-weight: bold;
  font-size: 18px;
  cursor: pointer;
  padding: 8px 20px;
}
</style>
