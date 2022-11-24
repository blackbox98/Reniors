<template>
  <div id="main-container">
    <div class="topheader">
      <router-link :to="{ name: 'home' }">
        <img src="@/assets/logo_cut.png" alt="logo" />
      </router-link>
    </div>
    <div id="join" v-if="!session" class="join">
      <!-- top -->
      <div class="row-6 lefttop">
        <div class="left">
          <div class="backBtnBox">
            <router-link :to="{ name: 'VideoMain' }">
              <i
                class="bi bi-arrow-left-circle-fill"
                style="color: #9b9b9b; font-size: 32px; margin: 0 16px"
              ></i>
            </router-link>
          </div>
          <div class="header-logo">
            <img src="@/assets/logo.png" />
            <p>
              <span style="color: #37bf99">{{ companyName }}</span
              >의
            </p>
            <p>면접입니다.</p>
          </div>
        </div>
      </div>

      <!-- bottom -->
      <div class="righttop">
        <div class="right">
          <div class="fomrs">
            <div style="margin: 16px 0">
              <p class="label">지원자명</p>
              <input
                v-model="myUserName"
                class="rightinput"
                type="text"
                required
                readonly
              />
            </div>
            <div style="margin: 16px 0">
              <p class="label">면접방 번호</p>
              <input
                v-model="mySessionId"
                class="rightinput"
                type="text"
                required
                readonly
              />
            </div>
            <div class="submitBtn">
              <button @click="joinSession()">면접방 들어가기</button>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div id="session" v-if="session" class="insession">
      <!-- left -->
      <div class="sessionvideo">
        <!-- 상대방 -->
        <div>
          <user-video
            v-for="(sub, index) in subscribers"
            :index="index"
            :key="sub.stream.connection.connectionId"
            :stream-manager="sub"
            @click="updateMainVideoStreamManager(sub)"
            class="myvideo"
          />
        </div>
        <!-- 본인 -->
        <div id="video-container" v-if="!chatopenclose">
          <user-video
            :stream-manager="publisher"
            @click="updateMainVideoStreamManager(publisher)"
            class="myvideo"
          />
        </div>

        <!-- 채팅 -->
        <div class="chatbox" v-if="chatopenclose">
          <div class="chatlist">
            <div v-for="msg in receivemsg" :key="msg">
              <div v-if="msg.name == this.myUserName" class="chatitem">
                <div class="mename">
                  <p style="margin: 0">{{ myUserName.slice(0, 1) }}</p>
                </div>
                <div class="datadata">
                  <p style="margin: 0">{{ msg.data }}</p>
                </div>
              </div>
              <div v-if="msg.name !== this.myUserName" class="chatitem">
                <div class="youname">
                  <p style="margin: 0">{{ companyName.slice(0, 1) }}</p>
                </div>
                <div class="datadata">
                  <p style="margin: 0">{{ msg.data }}</p>
                </div>
              </div>
            </div>
          </div>
          <div class="chatform">
            <p style="width: 1vw"></p>
            <input
              class="chatinput"
              @keyup.enter="sendchat()"
              type="text"
              v-model="sendmsg"
            />
            <button class="chatsubmit" @click="sendchat()">
              <i class="bi bi-send"></i>
            </button>
          </div>
        </div>
      </div>

      <!-- right -->
      <div style="margin: 0; padding: 0">
        <div class="rightbtn">
          <button
            @click="chatopen()"
            :class="{ chatbtn: !alram, chatbtn1: alram }"
          >
            <i class="bi bi-chat-dots-fill"></i>
          </button>
          <template v-if="audioflag">
            <button @click="audioonoff()" class="videobtn">
              <i class="bi bi-mic"></i>
            </button>
          </template>
          <template v-if="!audioflag">
            <button @click="audioonoff()" class="videobtn">
              <i class="bi bi-mic-mute"></i>
            </button>
          </template>
          <template v-if="videoflag">
            <button @click="videoonoff()" class="videobtn">
              <i class="bi bi-camera-video"></i>
            </button>
          </template>
          <template v-if="!videoflag">
            <button @click="videoonoff()" class="videobtn">
              <i class="bi bi-camera-video-off"></i>
            </button>
          </template>

          <button @click="leaveSession" class="leavebtn">
            <span><i class="bi bi-box-arrow-right"></i> 퇴장</span>
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { OpenVidu } from "openvidu-browser";
import UserVideo from "@/components/openvidu/UserVideo.vue";
import { mapActions, mapGetters } from "vuex";

axios.defaults.headers.post["Content-Type"] = "application/json";

const OPENVIDU_SERVER_URL = "https://" + "i7b307openvidu.ssafy.io" + ":4443";
const OPENVIDU_SERVER_SECRET = "reniors";

export default {
  name: "UserInterview",
  props: {
    userId: Number,
    jobOpeningId: Number,
  },

  components: {
    UserVideo,
  },

  data() {
    return {
      OV: undefined,
      session: undefined,
      mainStreamManager: undefined,
      publisher: undefined,
      subscribers: [],

      mySessionId: "",
      myUserName: "",
      videoflag: true,
      audioflag: false,
      sendmsg: "",
      receivemsg: [],
      msgflag: true,
      chatopenclose: false,
      companyName: "",
      sessionleave: false,
      alram: false,
    };
  },
  computed: {
    ...mapGetters(["currentUser", "rooms"]),
  },
  watch: {
    companyinfo: function (data) {
      this.myUserName = data.name;
    },
    session: function () {
      if (!this.sessionleave) {
        this.session.on("signal", (event) => {
          let name = event.from.data;
          name = name.substr(15);
          name = name.substring(0, name.length - 2);
          this.receivemsg.push({ name: name, data: event.data });
          if (!this.chatopenclose) {
            this.alram = true;
          }
        });
      }
    },
  },
  created() {
    this.fetchCurrentUser(), (this.myUserName = this.currentUser.name);
    this.fetchRooms(), (this.mySessionId = this.rooms[0].sessionId);
    this.companyName = this.rooms[0].companyName;
  },
  methods: {
    ...mapActions(["fetchCurrentUser", "fetchRooms"]),
    getTip() {},
    videoonoff() {
      this.videoflag = !this.videoflag;
      this.publisher.publishVideo(this.videoflag);
    },
    sendchat() {
      this.session
        .signal({
          data: this.sendmsg,
          to: [],
          type: "my-chat",
        })
        .then(() => {
          this.msgflag = !this.msgflag;
          this.sendmsg = "";
        })
        .catch((error) => {
          console.log(error);
        });
    },
    chatopen() {
      this.chatopenclose = !this.chatopenclose;
      this.alram = false;
    },
    audioonoff() {
      this.audioflag = !this.audioflag;
      this.publisher.publishAudio(this.audioflag);
    },
    joinSession() {
      this.OV = new OpenVidu();

      this.session = this.OV.initSession();

      this.session.on("streamCreated", ({ stream }) => {
        const subscriber = this.session.subscribe(stream);
        this.subscribers.push(subscriber);
      });

      this.session.on("streamDestroyed", ({ stream }) => {
        const index = this.subscribers.indexOf(stream.streamManager, 0);
        if (index >= 0) {
          this.subscribers.splice(index, 1);
        }
      });

      this.session.on("exception", ({ exception }) => {
        console.warn(exception);
      });

      this.getToken(this.mySessionId).then((token) => {
        this.session
          .connect(token, { clientData: this.myUserName })
          .then(() => {
            // --- Get your own camera stream with the desired properties ---

            let publisher = this.OV.initPublisher(undefined, {
              audioSource: undefined, // The source of audio. If undefined default microphone
              videoSource: undefined, // The source of video. If undefined default webcam
              publishAudio: false, // Whether you want to start publishing with your audio unmuted or not
              publishVideo: true, // Whether you want to start publishing with your video enabled or not
              resolution: "640x480", // The resolution of your video
              frameRate: 30, // The frame rate of your video
              insertMode: "APPEND", // How the video is inserted in the target element 'video-container'
              mirror: false, // Whether to mirror your local video or not
            });

            this.mainStreamManager = publisher;
            this.publisher = publisher;

            this.session.publish(this.publisher);
          })
          .catch((error) => {
            console.log(
              "There was an error connecting to the session:",
              error.code,
              error.message,
            );
          });
      });

      window.addEventListener("beforeunload", this.leaveSession);
    },

    leaveSession() {
      if (confirm("퇴장하시겠습니까?")) {
        this.sessionleave = true;
        if (this.session) this.session.disconnect();

        this.session = undefined;
        this.mainStreamManager = undefined;
        this.publisher = undefined;
        this.subscribers = [];
        this.OV = undefined;

        window.removeEventListener("beforeunload", this.leaveSession);

        this.$router.push({
          name: "VideoMain",
        });
      }
    },

    updateMainVideoStreamManager(stream) {
      if (this.mainStreamManager === stream) return;
      this.mainStreamManager = stream;
    },

    getToken(mySessionId) {
      return this.createSession(mySessionId).then((sessionId) =>
        this.createToken(sessionId),
      );
    },

    createSession(sessionId) {
      return new Promise((resolve, reject) => {
        axios
          .post(
            `${OPENVIDU_SERVER_URL}/openvidu/api/sessions`,
            JSON.stringify({
              customSessionId: sessionId,
            }),
            {
              auth: {
                username: "OPENVIDUAPP",
                password: OPENVIDU_SERVER_SECRET,
              },
            },
          )
          .then((response) => response.data)
          .then((data) => resolve(data.id))
          .catch((error) => {
            if (error.response.status === 409) {
              resolve(sessionId);
            } else {
              console.warn(
                `No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`,
              );
              if (
                window.confirm(
                  `No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`,
                )
              ) {
                location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
              }
              reject(error.response);
            }
          });
      });
    },

    // See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-connection
    createToken(sessionId) {
      return new Promise((resolve, reject) => {
        axios
          .post(
            `${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`,
            {},
            {
              auth: {
                username: "OPENVIDUAPP",
                password: OPENVIDU_SERVER_SECRET,
              },
            },
          )
          .then((response) => response.data)
          .then((data) => resolve(data.token))
          .catch((error) => reject(error.response));
      });
    },
  },
};
</script>
<style scoped>
#main-container {
  min-height: 100vh;
  min-width: 100vw;
  background-color: #fff5f0;
  display: flex;
  align-items: center;
}

.topheader {
  min-width: 100vw;
  height: 48px;
  background-color: white;
  box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px -2px;
  position: fixed;
  top: 0;
  display: flex;
  align-items: center;
  justify-content: center;
}

.topheader img {
  height: 38px;
  margin: auto;
  width: auto;
}

.join {
  width: 90vw;
  height: 80vh;
  border-radius: 20px;
  border: none;
  box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px 0px;
  background: linear-gradient(white 45%, #ff843e 0%);
  margin: auto;
}

.header-logo {
  height: 40vh;
  height: auto;
}

.header-logo p {
  font-weight: bold;
  font-size: 24px;

  margin: 0;
}

.lefttop {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 40vh;
}

.left {
  height: 40vh;
  width: 90vw;
}

.backBtnBox {
  display: flex;
  justify-content: start;
  align-content: flex-end;
  height: 6vh;
}

.righttop {
  display: flex;
  align-items: center;
  justify-content: center;
}

.right {
  display: flex;
  align-items: center;
  justify-content: center;
}

.rightinput {
  margin: auto;
  display: block;
  width: 80vw;
  height: 6vh;
  padding: 0.375rem 0.75rem;
  font-size: 1rem;
  font-weight: 400;
  line-height: 1.5;
  color: #212529;
  background-color: #fff;
  background-clip: padding-box;
  border: 1px solid #ced4da;
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  border-radius: 0.375rem;
  transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
}

.label {
  text-align: left;
  font-size: 16px;
  color: white;
  margin: 0 12px;
  font-weight: bold;
}

.submitBtn {
  margin: 8px;
}

.submitBtn button {
  width: 80vw;
  height: 6vh;
  background-color: #ffb400;
  color: white;
  font-size: 20px;
  font-weight: bold;
  border: none;
  border-radius: 5px;
  margin: 8px 0;
}

.header-logo > img {
  height: 50px;
  width: 240px;
  object-fit: cover;
  margin: 24px 0;
}

/* IN session */

.insession {
  width: 100vw;
  height: 100vh;
}

.sessionvideo {
  width: 90vw;
  height: 75vh;
  margin: 48px auto;
  padding: 8px 0 0 0 0;
}

.myvideo {
  width: 85vw;
  height: 35vh;
  background-color: rgba(100, 100, 111, 0.2);
  border-radius: 5px;
  border: none;
  box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;
  margin: 16px auto;
  display: flex;
  justify-content: center;
  align-items: center;
}

.chatbox {
  width: 85vw;
  height: 35vh;
  border-radius: 10px;
  background-color: white;
  box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px 0px;
  margin: 16px auto;
}

.chatlist {
  width: 85vw;
  height: 28vh;
  border: none;
  padding: 4px;
  overflow-y: scroll;
}

.chatform {
  width: 83vw;
  height: 5vh;
  border: none;
  border-radius: 30px;
  box-shadow: rgba(0, 0, 0, 0.16) 0px 1px 4px;
  background-color: #eaeaea;
  margin: 4px;
  display: flex;
  align-items: center;
}

.chatinput {
  width: 70vw;
  height: 4vh;
  border: none;
  margin: 0 2px 0 0;
  background-color: #eaeaea;
  border-radius: 20px;
}

.chatinput:hover {
  border: none;
}

.chatsubmit {
  width: 3vw;
  height: 3vw;
  border: none;
  border-radius: 20px;
  margin: 2px 2px 0 2px;
  background-color: #eaeaea;
  display: flex;
  align-items: center;
}

.chatsubmit > i {
  font-size: 16px;
  transform: rotate(45deg);
  margin: 0;
}

.chatitem {
  width: 78vw;
  height: 4vh;
  padding: 2px;
  display: flex;
  align-items: center;
}

.mename {
  width: 3vh;
  height: 3vh;
  border: none;
  border-radius: 100px;
  background-color: #ffb400;
  color: white;
  font-weight: bold;
  font-size: 16px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin: auto 4px;
}

.youname {
  width: 3vh;
  height: 3vh;
  border: none;
  border-radius: 100px;
  background-color: #37bf99;
  color: white;
  font-weight: bold;
  font-size: 14px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin: auto 8px auto 4px;
}

.datadata {
  text-align: center;
  font-size: 12px;
  margin: auto 4px;
}

.rightbtn {
  width: 100vw;
  height: 10vh;
  position: fixed;
  bottom: 50px;
}

.chatbtn {
  width: 16vw;
  height: 16vw;
  border-radius: 100%;
  border: none;
  background-color: white;
  box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px 0px;
  margin: 4px;
}

.chatbtn i {
  font-size: 24px;
  color: #8cd6c1;
  font-weight: bold;
}

.chatbtn1 {
  width: 16vw;
  height: 16vw;
  border-radius: 100%;
  border: none;
  background-color: white;
  box-shadow: #fecc4e 0px 3px 8px 0px;
  margin: 4px;
}

.chatbtn1 i {
  font-size: 24px;
  color: #8cd6c1;
  font-weight: bold;
}

.videobtn {
  width: 16vw;
  height: 16vw;
  border-radius: 100%;
  border: none;
  background-color: #8cd6c1;
  box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px 0px;
  margin: 4px;
}

.videobtn i {
  font-size: 24px;
  color: white;
  font-weight: bold;
}

.leavebtn {
  width: 26vw;
  height: 16vw;
  border-radius: 30px;
  border: none;
  background-color: #f3620f;
  box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px 0px;
  margin: 4px;
}

.leavebtn i {
  font-size: 24px;
  color: white;
  font-weight: bold;
}

.leavebtn span {
  font-size: 16px;
  color: white;
}
</style>
