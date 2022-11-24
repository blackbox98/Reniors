<template>
  <div class="header container-fluid">
    <router-link class="header-logo" :to="{ name: 'home' }">
      <img src="@/assets/logo_cut.png" alt="logo" />
    </router-link>
  </div>
  <div class="list">
    <!-- loggedin -->
    <div class="video-main-info-box" v-if="isLogginedIn">
      <div class="sub">
        <div class="subBox">
          <p class="sub-title">
            <span style="color: #ff843e">리니어즈</span>와 함께하는 면접
            페이지입니다🙂
          </p>
          <p>1. 화상면접까지 리니어즈에서 한번에!</p>
          <p>2. 면접연습을 통해 자신감을 키워보세요.</p>
          <p>
            3. 연습녹화본은 <span style="color: #37bf99">내 연습함</span>에서
            확인할 수 있습니다.
          </p>
        </div>
      </div>
      <div class="btns">
        <div class="one">
          <router-link :to="{ name: 'userInterview' }">
            <button class="btn11" v-if="this.title">
              <div class="btn112 have-interview-btn">
                <i
                  class="bi bi-chat-square-quote-fill"
                  style="font-size: 48px; color: white"
                ></i>
                <div style="margin: 4px 4px">
                  <p
                    style="
                      color: white;
                      font-size: 22px;
                      font-weight: bold;
                      margin: 0;
                    "
                  >
                    면접 보러 가기
                  </p>
                  <p style="color: white; font-size: 16px; margin: 0">
                    기업명: {{ title }}
                  </p>
                </div>
              </div>
            </button>
          </router-link>
          <button class="btn1" v-if="!this.title">
            <div>
              <i
                class="bi bi-chat-square-quote-fill"
                style="font-size: 48px; color: #ff843e"
              ></i>
              <p
                style="
                  color: #ff843e;
                  font-size: 20px;
                  font-weight: bold;
                  margin: 4px auto;
                "
              >
                예정된 면접이 없습니다.
              </p>
            </div>
          </button>
        </div>
        <div class="two">
          <button class="btn2">
            <router-link :to="{ name: 'QuestionList' }">
              <div class="prac">
                <i
                  class="bi bi-camera-video"
                  style="color: #37bf99; font-size: 24px; font-weight: bold"
                ></i>
                <span
                  style="
                    margin: 4px 4px;
                    font-size: 18px;
                    color: #37bf99;
                    font-weight: bold;
                  "
                  >면접 연습</span
                >
              </div>
            </router-link>
          </button>
          <button class="btn3">
            <router-link :to="{ name: 'PracticeBox' }">
              <div>
                <i
                  style="
                    font-size: 20px;
                    color: white;
                    font-weight: bold;
                    margin: auto;
                  "
                  class="bi bi-folder-check"
                ></i>
                <p
                  style="
                    font-size: 14px;
                    color: white;
                    font-weight: bold;
                    margin: auto;
                  "
                >
                  내 연습함
                </p>
              </div>
            </router-link>
          </button>
        </div>
      </div>
    </div>

    <!-- not loggedin -->
    <div v-if="!isLogginedIn">
      <div class="notlog">
        <div class="notlogBox">
          <p class="notlogp">화상 면접과 면접 연습 서비스는</p>
          <p class="notlogp">
            <router-link :to="{ name: 'Login' }" style="text-decoration: none"
              ><span style="color: #37bf99"> 로그인</span></router-link
            >
            후 이용해주세요🙂
          </p>
          <router-link :to="{ name: 'Login' }">
            <button class="notlogBtn">
              로그인 하러 가기 <i class="bi bi-arrow-right-square-fill"></i>
            </button>
          </router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";

export default {
  name: "VideoMain",
  components: {},
  data() {
    return {
      title: null,
      sub: ",",
      interviewId: "",
    };
  },
  setup() {},
  created() {
    if(this.isLogginedIn){
      this.fetchRooms();
      if (this.rooms.length >= 1) {
        this.title = this.rooms[0].companyName;
        this.sub = this.rooms[0].jobOpeningName;
        this.interviewId = this.rooms[0].sessionId;
      }
    }
  },
  methods: {
    ...mapActions(["fetchRooms"]),
  },
  computed: {
    ...mapGetters(["rooms", "isLogginedIn"]),
  },
};
</script>

<style scoped>
.list {
  display: flex;
  width: 100vw;
  height: 100vh;
  flex-direction: column;
  justify-content: center;
  background-color: #fff5f0;
}
.header {
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
.header img {
  height: 38px;
  margin: auto;
}
.noglog {
  display: flex;
  justify-content: center;
  align-items: center;
}
.notlogBox {
  background-color: rgba(256, 256, 256, 0.5);
  border: none;
  border-radius: 10px;
  margin: 8px;
  padding: 32px 8px;
}
.notlogp {
  font-size: 24px;
  font-weight: bold;
  color: #ff843e;
}
.notlogBtn {
  background-color: #ff843e;
  border: none;
  border-radius: 5px;
  color: white;
  font-weight: bold;
  font-size: 20px;
  width: 72%;
  padding: 8px;
  margin: 8px;
}
.notlogBtn i {
  font-size: 24px;
  margin: auto 8px;
}
.sub {
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 16px auto 40px;
  border-radius: 16px;
  background-color: rgba(256, 256, 256, 0.6);
  box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px 0px;
  padding: 8px;
  width: 92%;
  height: 24%;
  margin-top: 40px;
}
.subBox {
  text-align: left;
}
.sub-title {
  font-size: 16px;
  font-weight: bold;
}
.subBox p {
  font-size: 14px;
  margin: 0;
}
.one {
  justify-content: center;
  align-items: center;
  margin: 8px 0px;
}
.one a {
  text-decoration: none;
}
.two {
  width: 90%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 0 auto;
  margin-bottom: 120px;
}
.two a {
  text-decoration: none;
}
.btn1 {
  width: 90%;
  height: 160px;
  border: solid 1px;
  border-color: #ff843e;
  border-radius: 10px;
  background-color: white;
  box-shadow: 1px 1px 1px gray;
}
.btn2 {
  width: 75%;
  height: 100px;
  border: solid 1px;
  border-color: #37bf99;
  background-color: white;
  border-radius: 10px;
  margin: 0 4px 0 0;
  box-shadow: 0.5px 0.5px 0.5px gray;
}
.prac {
  display: flex;
  align-items: center;
  justify-content: center;
}
.btn3 {
  width: 23%;
  height: 100px;
  border: none;
  background-color: #37bf99;
  border-radius: 10px;
  margin: 0 0 0 4px;
  box-shadow: 0.5px 0.5px 0.5px gray;
}
.video-main-info-box {
  width: 100%;
}
.btn11 {
  width: 90%;
  height: 160px;
  border: none;
  background-color: #ff843e;
  border-radius: 10px;
  box-shadow: 1px 1px 1px gray;
}
.btn112 {
  display: flex;
  align-items: center;
  justify-content: center;
}
.btn112 > i {
  margin-right: 10px;
}
@media (max-width: 760px) {
  .sub {
    margin-top: 40px;
  }
  .btn112 {
    justify-content: space-evenly;
  }
  .btn112 > i {
    margin-right: 0;
  }
}
</style>
