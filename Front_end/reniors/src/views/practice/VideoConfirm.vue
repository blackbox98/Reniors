<template>
  <div class="head container-fluid">
    <router-link :to="{ name: 'PracticeBox' }">
      <i
        class="bi bi-arrow-left-circle-fill"
        style="color: #ffd39b; font-size: 24px; margin: 0 16px"
      ></i>
    </router-link>
    <p>내 연습함</p>
  </div>
  <div>
    <div class="total">
      <!-- header -->

      <!-- content -->
      <div class="content">
        <div class="contentHead">
          <p class="p1">{{ record.fileName }}</p>
          <p class="p2">
            {{ record.updatedAt.slice(0, 4) }}.{{
              record.updatedAt.slice(5, 7)
            }}.{{ record.updatedAt.slice(8, 10) }}
          </p>
        </div>
        <video
          :class="{ smallVideo: isShow }"
          autoplay
          :src="this.record.recordURL"
          controls
        ></video>
      </div>
      <!-- 발음확인 창 -->
      <div class="sttTotal">
        <div class="sttScript" v-if="isShow">
          <p
            v-for="(subtitle, index) in subtitles"
            :key="index"
            style="margin: 2px; text-align: left"
          >
            {{ subtitle.msg }}
          </p>
        </div>
      </div>

      <!-- footer -->
      <div class="submit">
        <div>
          <button v-if="!isShow" class="showBtn" @click="isShowFunc">
            발음 확인하기
          </button>
          <button v-if="isShow" class="showBtn" @click="isShowFunc">
            발음 확인 닫기
          </button>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { mapActions, mapGetters } from "vuex";

export default {
  name: "VideoConfirm",
  components: {},
  data() {
    return {
      myrecords: [],
      idx: this.$route.params.videoId,
      record: {},
      isShow: false,
    };
  },
  computed: {
    ...mapGetters(["records", "vitotoken", "subtitles"]),
  },
  created() {
    this.fetchRecording();
  },
  watch: {
    records: function (data) {
      this.myrecords = { ...data };
      this.record = { ...data }[this.idx];
      this.issueToken();
    },
    vitotoken: function () {
      this.fetchSubtitles2(this.record.videoId);
    },
  },
  methods: {
    ...mapActions(["fetchRecording", "fetchSubtitles2", "issueToken"]),
    isShowFunc() {
      this.isShow = !this.isShow;
    },
  },
};
</script>

<style scoped>
.total {
  background-color: #fff5f0;
  height: 100vh;
  width: 100vw;
  margin: 0;
}
.head {
  height: 48px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: solid 2px;
  border-bottom-color: #ff843e;
  margin: 0;
  background-color: white;
  box-shadow: 0 0 1px gray;
  position: fixed;
  top: 0;
}
.head p {
  font-size: 20px;
  font-weight: bold;
  color: #ffb400;
  margin: 0 16px;
}
.contentHead {
  display: flex;
  justify-content: left;
  align-items: baseline;
  margin: 4px auto;
  width: 500px;
  margin-top: 50px;
}
.p1 {
  font-size: 24px;
  font-weight: bold;
  margin: 0 4px;
}
.p2 {
  font-size: 16px;
  margin: 0 4px;
}

.sttTotal {
  display: flex;
  justify-content: center;
  animation: fadeInUp 1s;
}
@keyframes fadeInUp {
  from {
    opacity: 0;
    transform: translateY(20px);
  }
  to {
    opacity: 1;
    transform: none;
  }
}
.sttScript {
  background-color: white;
  overflow: scroll;
  padding: 4px;
  bottom: 120px;
  height: 150px;
  width: 500px;
  border: solid #d9d9d9 1px;
  border-radius: 5px;
}
.submit {
  position: fixed;
  bottom: 50px;
  width: 100%;
  margin: 0;
  background-color: white;
  border-top: solid 0.5px #ffedbf;
  display: flex;
  justify-content: space-around;
}
.showBtn {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 40px;
  width: 320px;
  margin: 8px 0;
  border-radius: 10px;
  border: none;
  color: white;
  font-weight: bold;
  font-size: 16px;
  background-color: #ff843e;
  cursor: pointer;
}
.smallVideo {
  background-color: white;
  width: 500px;
  height: 550px;
  margin: 8px auto 4px auto;
  border: solid #ffd39b 1px;
  border-radius: 5px;
}
video {
  width: 500px;
  height: 700px;
  border: solid gray 1px;
  border-radius: 5px;
  background-color: white;
}
@media (max-width: 760px) {
  .contentHead {
    width: 100vw;
  }
  video {
    width: 344px;
    height: 480px;
  }
  .smallVideo {
    width: 344px;
    height: 400px;
  }
  .sttScript {
    bottom: 120px;
    height: 150px;
  }
}
</style>
