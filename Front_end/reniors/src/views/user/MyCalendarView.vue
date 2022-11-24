<template>
    <div class="header">
      <i @click="prevpage()" class="bi bi-arrow-left-circle-fill"></i>
      <router-link class="header-logo" :to="{ name: 'home' }">
        <img src="@/assets/logo_cut.png" alt="logo" />
      </router-link>
      <div></div>
    </div>
  <div>
    <br>
    <br>
    <my-calendar :interview="interview" :bookmarksdate="bookmarksdate"></my-calendar>
  </div>
</template>
<script>
import MyCalendar from '@/components/user/MyCalendar.vue'
import { mapActions, mapGetters } from 'vuex'
export default {
  name: 'MyCalendarView',
  components: { MyCalendar },
  data() {
    return {
      example: '',
    }
  },
  computed: {
  ...mapGetters('jobopening', ['interview', 'bookmarksdate'])
  },
  setup() {},
  created() {
    this.fetchApply()
    this.fetchBookmark()
  },
  mounted() {},
  unmounted() {},
  methods: {
    ...mapActions('jobopening', ['fetchApply', 'fetchBookmark']),
    prevpage() {
      this.$router.go(-1);
    },
  }
}
</script>

<style scoped>

.header {
  height: 48px;
  background-color: white;
  box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px -2px;
  display: flex;
  align-items: center;
  justify-content: space-around;
  position: fixed;
  width: 100vw;
  top: 0;
}
.header img {
  height: 38px;
  margin: auto;
  width: auto;
}
.header > .bi {
  color: var(--color-red-1);
  font-size: 30px;
  cursor: pointer;
}
.header > div {
  width: 30px;
}

</style>