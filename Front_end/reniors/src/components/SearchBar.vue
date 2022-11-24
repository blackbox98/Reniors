<template>
  <div class="search-bar">
    <form class="home-search" action="" @submit.prevent="search">
      <select v-model="key" class="form-select search-bar-select">
        <option value="" disabled selected>키워드</option>
        <option value="company">회사명</option>
        <option value="jobopening">공고명</option>
      </select>

      <input
        type="text"
        name="keyword"
        class="home-search-text"
        placeholder="회사명, 공고명으로 검색"
        v-model="word"
      />

      <font-awesome-icon
        @click="search()"
        class="home-search-button"
        icon="fa-solid fa-magnifying-glass"
      />
    </form>
  </div>
</template>

<script>
import { mapActions } from "vuex";

export default {
  name: "SearchBar",
  components: {},
  setup() {},
  data() {
    return {
      key: "",
      word: "",
    };
  },
  methods: {
    ...mapActions("jobopening", ["fetchJobopeningsName"]),
    async search() {
      let keyword = {
        key: this.key,
        word: this.word,
      };
      await this.fetchJobopeningsName(keyword);
    },
  },
};
</script>

<style scoped>
.home-header {
  color: var(--color-red-1);
  font-weight: bold;
  margin: 20px;
}
.search-bar{
  width: 100vw;
}

.home-search {
  display: flex;
  justify-content: space-between;
  position: absolute;
  top: 56px;
  width: 95%;
  max-width: 720px;
  margin-bottom: 15px;
  margin-top: 10px;
}

.home-search-text {
  box-sizing: border-box;
  background-color: var(--color-black-5);
  border: 1px solid var(--color-black-4);
  border-radius: 2rem;
  height: 40px;
  width: 250px;
  padding: 0 20px;
  font-size: 14px;
}

.search-bar-select {
  width: 90px;
  font-size: 14px;
  margin-left: 10px;
  margin-right: 10px;
}

select option[value=""][disabled] {
  display: none;
}

@media screen and (min-width: 720px) {
  .search-bar {
    display: flex;
    justify-content: center;
  }

  .home-search {
    align-self: center;
    margin-top: 10px;
    width: 500px;
  }
  .home-search-text {
    width: 40vh;
  }
  .search-bar{
    width: 700px;
  }
}

.home-search-button {
  position: absolute;
  right: 15px;
  top: 6px;
  height: 25px;
  width: 15px;
  color: var(--color-black-2);
}

.home-search-button:hover {
  cursor: pointer;
}
</style>
