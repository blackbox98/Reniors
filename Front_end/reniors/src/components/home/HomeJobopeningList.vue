<template>
  <div>
    <h2 class="home-jobopening-type">{{ type }}</h2>
    <ul class="home-jobopening-list">
      <router-link
        class="home-jobopening-item"
        :to="{
          name: 'JobopeningDetail',
          params: { jobopeningId: jobopening.id },
        }"
        v-for="jobopening in jobopenings"
        :key="jobopening.id"
      >
        <img
          class="company-image mb-2"
          :src="jobopening.baseURL + jobopening.companyProfile"
        />
        <h4 class="mb-2">{{ jobopening.companyName }}</h4>
        <div class="home-jobopening-info">
          <p class="mb-1">{{ jobopening.title }}</p>
          <p style="font-size: 14px; color: #ff843e">
            {{ jobopening.finishedDate.split("T")[0] }}
          </p>
        </div>
      </router-link>
    </ul>
  </div>
</template>

<script>
export default {
  name: "HomeJobopeningList",
  props: {
    type: String,
    jobopenings: Array,
  },
  created() {},
};
</script>

<style scoped>
.home-jobopening-type {
  margin-bottom: 0;
  margin-left: 10px;
  text-align: left;
  font-weight: bold;
  font-size: 20px;
}
.company-image {
  width: 100%;
  border: 3px solid var(--color-red-4);
  border-radius: 5px;
}
.home-jobopening-list {
  display: flex;
  margin: 0 8px;
  padding: 0;
  flex-wrap: no-wrap;
  overflow-x: scroll;
  overflow-y: hidden;
  text-align: start;
}
.home-jobopening-list::-webkit-scrollbar {
  height: 5px;
}
.home-jobopening-list::-webkit-scrollbar-thumb {
  background-color: var(--color-green-1);
  border-radius: 10px;
}
.home-jobopening-list::-webkit-scrollbar-track {
  background-color: var(--color-green-3);
  border-radius: 10px;
}
.home-jobopening-item {
  background-color: white;
  border: 1px solid var(--color-black-4);
  border-radius: 0.3rem;
  margin: 10px;
  padding: 10px;
  list-style: none;
  flex: 0 0 auto;
  text-decoration: none;
  width: 160px;
  box-shadow: rgba(149, 157, 165, 0.2) 0px 8px 24px;
}

.home-jobopening-item p,
.home-jobopening-item h4 {
  margin: 0;
  text-align: start;
  color: black;
}

.home-jobopening-item h4 {
  font-weight: bold;
  font-size: 16px;
}
.home-jobopening-info {
  background-color: var(--color-black-4);
  padding: 8px;
  border-radius: 5px;
}
</style>
