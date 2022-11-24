<template>
  <div class="jobopening-main-page-btn">
    <div class="jobopening-main-page-btn-top">
      <div @click="whole()" class="jobopening-main-page-btn-top-item">
        <div>
          <i class="bi bi-zoom-in"></i>
        </div>
        <div>전체공고</div>
      </div>

      <div @click="condition()" class="jobopening-main-page-btn-top-item">
        <div>
          <svg
            style="margin: 11px"
            xmlns="http://www.w3.org/2000/svg"
            width="44px"
            height="44px"
            fill="currentColor"
            class="bi bi-search-heart"
            viewBox="0 0 16 16"
          >
            <path
              d="M6.5 4.482c1.664-1.673 5.825 1.254 0 5.018-5.825-3.764-1.664-6.69 0-5.018Z"
            />
            <path
              d="M13 6.5a6.471 6.471 0 0 1-1.258 3.844c.04.03.078.062.115.098l3.85 3.85a1 1 0 0 1-1.414 1.415l-3.85-3.85a1.007 1.007 0 0 1-.1-.115h.002A6.5 6.5 0 1 1 13 6.5ZM6.5 12a5.5 5.5 0 1 0 0-11 5.5 5.5 0 0 0 0 11Z"
            />
          </svg>
        </div>
        <div>맞춤공고</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    whole() {
      this.$router.push({ name: "WholeJobOpening" });
    },
    condition() {
      this.$router.push({ name: "Condition" });
    },
  },
};
</script>

<style scoped>
.jobopening-main-page-btn-top {
  display: flex;
  justify-content: center;
}

.jobopening-main-page-btn-top-item {
  width: 45%;
  height: 200px;
  margin: 12px;
  background-color: #f28a07;
  color: #eaeaea;
  border-radius: 12px;
  border: none;
  font-weight: bold;
  box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px 0px;
  cursor: pointer;
}

.jobopening-main-page-btn-top-item > div:first-child {
  margin-top: 50px;
}

.jobopening-main-page-btn-top-item > div > i {
  font-size: 44px;
}

.jobopening-main-page-btn-top-item > div > div {
  font-size: 20px;
}
</style>
