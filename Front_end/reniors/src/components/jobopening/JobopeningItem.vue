<template>
  <div @click="movejobopening()" class="jobopening-item">
    <div>
      <p class="jobopening-item-company">{{ jobopening.companyName }}</p>
      <p class="jobopening-item-title">{{ jobopening.title }}</p>
    </div>
    <div class="jobopening-item-second">
      <span class="jobopening-item-te">{{ jobopening.typeEmployment }}</span>
      <span class="jobopening-item-ms">연봉</span>
      <span>{{ jobopening.minSalary / 10000 }}만원</span>
    </div>
    <div class="jobopening-item-third">
      <i class="bi bi-geo-alt-fill"
        >{{ jobopening.sido }} {{ jobopening.gugun }}</i
      >
    </div>
    <p class="jobopening-item-period">{{ createDate }} ~ {{ finishedDate }}</p>
  </div>
</template>

<script scoped>
export default {
  name: "JobopeningItem",
  props: {
    jobopening: Object,
  },
  setup(props) {
    const stringToDate = (rawDate) => {
      let dateComponents = rawDate.split("T");
      let datePieces = dateComponents[0].split("-");
      return new Date(datePieces[0], datePieces[1] - 1, datePieces[2]);
    };
    // toStringByFormatting
    function tSBF(source) {
      function leftPad(value) {
        if (value >= 10) {
          return value;
        }

        return `0${value}`;
      }
      const days = ["일", "월", "화", "수", "목", "금", "토"];
      const year = source.getFullYear();
      const month = leftPad(source.getMonth() + 1);
      const day = leftPad(source.getDate());
      const dayName = days[source.getDay()];
      return [year, month, day].join("-") + ` (${dayName})`;
    }
    const createDate = tSBF(stringToDate(props.jobopening?.createdDate));
    const finishedDate = tSBF(stringToDate(props.jobopening?.finishedDate));

    return {
      createDate,
      finishedDate,
    };
  },
  methods: {
    movejobopening() {
      this.$router.push({
        name: "JobopeningDetail",
        params: { jobopeningId: this.jobopening.id },
      });
    },
  },
};
</script>

<style scoped>
.jobopening-item {
  box-shadow: rgba(149, 157, 165, 0.2) 0px 8px 24px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  box-sizing: border-box;
  background-color: var(--color-red-4);
  border-radius: 0.4rem;
  margin: 10px 10px;
  padding: 10px;
  height: 150px;
  text-decoration: none;
  cursor: pointer;
}

.jobopening-item p {
  margin: 0;
  text-align: start;
}

.jobopening-item-company {
  display: -webkit-box;
  -webkit-box-orient: vertical;
  overflow: hidden;
  -webkit-line-clamp: 1;
  color: black;
  font-size: 14px;
}

.jobopening-item-title {
  display: -webkit-box;
  -webkit-box-orient: vertical;
  overflow: hidden;
  -webkit-line-clamp: 1;
  color: black;
  font-size: 20px;
  /* text-decoration-line: underline; */
  -webkit-line-clamp: 3;
  font-weight: bold;
}

.jobopening-item-second {
  display: flex;
  font-size: 14px;
  font-weight: bold;
}
.jobopening-item-second > span:first-child {
  color: #37bf99;
  margin-right: 16px;
}
.jobopening-item-second > span:nth-child(2) {
  color: #f28a07;
  margin-right: 5px;
}
.jobopening-item-second > span:nth-child(3) {
  color: black;
}
.jobopening-item-third {
  color: var(--color-black-1);
  display: flex;
  font-size: 10px;
  font-weight: bold;
}
.jobopening-item-period {
  margin-top: 10px;
  color: var(--color-black-1);
  font-size: 10px;
}
</style>
