<template>
    <div class="header container-fluid">
        <router-link class="header-logo" :to="{ name: 'home' }">
            <img src="@/assets/logo_cut.png" alt="logo">
        </router-link>
    </div>
      <div class="head container-fluid">
        <router-link :to="{ name: 'VideoMain' }">
          <i
            class="bi bi-arrow-left-circle-fill"

          ></i>
        </router-link>
        <p>내 연습함</p>
      </div>
    <div class="total">
      <!-- header -->


      <!-- content -->
      <div class="content row">
        <div
          v-for="(record, index) in this.myrecords"
          :key="index"
          class="vediobox-wrap"
        >
          <!-- 비디오섬네일 -->
          <router-link :to="{ path: '/practice/box/' + index }">
            <div class="videobox col-6">
              <video :src="record.recordURL"></video>
              <p>{{ record.fileName }}</p>
            </div>
          </router-link>

          <!-- 비디오재생 -->
          <!-- <video autoplay :src='record.recordURL' controls></video> -->
          <!-- <video preload="metadata" src={{url}}></video> -->
        </div>
      </div>
    </div>
</template>
<script>
import { mapActions, mapGetters } from "vuex";

export default {
  name: "PracticeBox",
  components: {  },
  data() {
    return {
      myrecords: [],
    };
  },
  computed: {
    ...mapGetters(["records"]),
  },
  created() {
    this.fetchRecording();
  },
  watch: {
    records: function (data) {
      this.myrecords = { ...data };
    },
  },
  methods: {
    ...mapActions(["fetchRecording"]),
  },
};
</script>

<style scoped>
.header{
    height: 48px;
    background-color: white;
    box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px -2px;
    position: fixed;
    top: 0;
    display: flex;
    align-items: center;
    justify-content: center;
    padding: 0 !important; 
}
.header img{
    height: 38px;
    margin: auto;
}
.total {
  background-color: #fff5f0;
  width: 100%;
  height: 100vh;
  padding-top: 90px;
}
.head {
  position: fixed;
  top: 48px;
  height: 48px;
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: solid 2px;
  border-bottom-color: #ff843e;
  margin: 0;
  background-color: white;
}
.head p {
  font-size: 20px;
  font-weight: bold;
  color: #ffb400;
  margin: 0 16px;
}
.head .bi-arrow-left-circle-fill{
  color: #ffd39b; 
  font-size: 35px; 
  margin: 0 16px;
}
.vediobox-wrap{
  width: 300px;
  padding: 0;
}

.videobox {
  width: 300px;
  height: 200px;
  border-radius: 10px;
  border: solid #ff843e 1px;
  background-color: white;
  margin: 8px auto;
  color: black;
  text-decoration: none;
}
video {
  width: 280px;
  height: 150px;
  border: #c5c5c5 solid 0.5px;
  border-radius: 5px;
  margin: 4px auto 2px;
}
@media(max-width:760px){
  .head .bi-arrow-left-circle-fill{
    font-size: 30px; 
  }
  .vediobox-wrap{
    width: 144px;
  }
  .videobox {
    width: 144px;
    height: 160px;
  }
  
  video {
    width: 120px;
    height: 120px;
  }
}
.content {
  margin: 16px;
  display: flex;
  justify-content: space-around;
}


.videobox p {
  margin: auto;
}
</style>
