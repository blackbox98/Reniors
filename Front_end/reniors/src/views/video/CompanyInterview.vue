<template>
  <div id="main-container">
    <div id="join" v-if="!session" class="join row">
      <!-- left -->
      <div class="col-6 lefttop">
        <div class="left">
          <div class="header-logo">
            <img src="@/assets/logo.png" />
            <p>
              지원자 <span style="color: #37bf99">{{ applyinfo.name }}</span
              >의 면접입니다.
            </p>
            <br />
          </div>
        </div>
      </div>

      <!-- right -->
      <div class="righttop col-6">
        <div class="right">
          <div class="fomrs">
            <div style="margin: 16px 0">
              <p class="label">회사명</p>
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

    <div id="session" v-if="session" class="insession row">
      <div class="col-6">
        <div id="video-container" class="col-md-6">
          <user-video
            :stream-manager="publisher"
            @click="updateMainVideoStreamManager(publisher)"
            class="myvideo"
          />
          <user-video
            v-for="(sub, index) in subscribers"
            :index="index"
            :key="sub.stream.connection.connectionId"
            :stream-manager="sub"
            @click="updateMainVideoStreamManager(sub)"
            class="myvideo"
          />
        </div>
      </div>

      <!-- right -->
      <div class="col-6" style="margin: 0; padding: 0">
        <div class="tabs" v-if="!chatopenclose">
          <!-- <div>지원자 : {{ interviewer }}</div> -->
          <!-- resume -->
          <div v-if="tab && !chatopenclose" class="interview-info-box">
            <div>
              <resume-view :applyinfo="this.applyinfo"></resume-view>
            </div>
          </div>
          <!-- evaluation -->
          <div v-if="!tab && !chatopenclose" class="interview-info-box">
            <div>
              <openvidu-eval-list
                :applyinfo="this.applyinfo"
              ></openvidu-eval-list>
            </div>
          </div>
          <div class="tabBtn">
            <div
              @click="changeresume()"
              :class="{ noresume: !tab, yesresume: tab }"
            >
              이력서 보기
            </div>
            <div @click="changeeval()" :class="{ noeval: tab, yeseval: !tab }">
              평가하기
            </div>
          </div>
        </div>

        <!-- chatting -->
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
                  <p style="margin: 0">{{ applyinfo.name.slice(0, 1) }}</p>
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

        <div class="rightbtn">
          <button
            @click="chatopen"
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
import UserVideo from "@/components/openvidu/CompanyVideo.vue";
import ResumeView from "@/components/Company/interview/ResumeView.vue";
import { mapActions, mapGetters } from "vuex";
import OpenviduEvalList from "@/components/Company/interview/OpenviduEvalList.vue";

axios.defaults.headers.post["Content-Type"] = "application/json";

const OPENVIDU_SERVER_URL = "https://" + "i7b307openvidu.ssafy.io" + ":4443";
const OPENVIDU_SERVER_SECRET = "reniors";

export default {
  name: "App",

  props: {
    userId: Number,
    jobOpeningId: Number,
  },

  components: {
    UserVideo,
    OpenviduEvalList,
    ResumeView,
  },

  data() {
    return {
      OV: undefined,
      session: undefined,
      mainStreamManager: undefined,
      publisher: undefined,
      subscribers: [],

      mySessionId: "InterviewSession" + this.$route.params.jobOpeningId,
      myUserName: "",
      videoflag: true,
      audioflag: false,
      tab: true,
      sendmsg: "",
      receivemsg: [],
      msgflag: true,
      chatopenclose: false,
      applyinfo: null,
      sessionleave: false,
      alram: false,
    };
  },
  computed: {
    ...mapGetters("company", ["companyinfo", "interviewer", "apply"]),
  },
  watch: {
    companyinfo: function (data) {
      this.myUserName = { ...data }.name;
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
    apply: function (data) {
      this.applyinfo = { ...data };
    },
  },
  created() {
    this.getCompany();
    this.getapply({
      jobOpeningId: this.$route.params.jobOpeningId,
      applyId: this.$route.params.no,
    });
  },
  methods: {
    ...mapActions("company", ["getCompany", "getapply"]),
    changeresume() {
      this.tab = true;
    },
    chatopen() {
      this.chatopenclose = !this.chatopenclose;
      this.alram = false;
    },
    changeeval() {
      if (!this.tab) {
        this.tab = true;
      } else {
        this.tab = false;
      }
    },
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

            // --- Publish your stream ---

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
      console.log(this.applyinfo.userId);
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
          name: "usereval",
          params: { no: this.applyinfo.userId },
        });
      }
    },

    updateMainVideoStreamManager(stream) {
      if (this.mainStreamManager === stream) return;
      this.mainStreamManager = stream;
    },

    /**
     * --------------------------
     * SERVER-SIDE RESPONSIBILITY
     * --------------------------
     * These methods retrieve the mandatory user token from OpenVidu Server.
     * This behavior MUST BE IN YOUR SERVER-SIDE IN PRODUCTION (by using
     * the API REST, openvidu-java-client or openvidu-node-client):
     *   1) Initialize a Session in OpenVidu Server	(POST /openvidu/api/sessions)
     *   2) Create a Connection in OpenVidu Server (POST /openvidu/api/sessions/<SESSION_ID>/connection)
     *   3) The Connection.token must be consumed in Session.connect() method
     */

    getToken(mySessionId) {
      return this.createSession(mySessionId).then((sessionId) =>
        this.createToken(sessionId),
      );
    },

    // See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-session
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
  justify-content: center;
  padding: 0 0 30px 0;
}

.join {
  width: 80vw;
  height: 80vh;
  border-radius: 20px;
  border: none;
  box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px 0px;
  background: linear-gradient(90deg, white 50%, #ff843e 50%);
}

.header-logo {
  width: 50%;
  height: auto;
  margin: 0 32px;
}

.header-logo p {
  font-weight: bold;
  font-size: 24px;
  width: 400px;
  text-align: left;
  margin: 0 32px;
}

.tips {
  width: 500px;
  height: 200px;
  border: none;
  border-radius: 10px;
  background-color: #fff5f0;
  box-shadow: rgba(149, 157, 165, 0.2) 0px 8px 24px;
  margin: 16px 0;
  display: flex;
  align-items: center;
}

.tips p {
  width: 450px;
  font-size: 14px;
  margin: 0 0 2px 28px;
}

.lefttop {
  display: flex;
  align-items: center;
  padding: 64px;
}

.left {
  display: flex;
  align-items: center;
}

.righttop {
  display: flex;
  align-items: center;
  padding: 64px;
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
  width: 480px;
  height: 48px;
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
  font-size: 20px;
  color: white;
  margin: 0 12px;
  font-weight: bold;
}

.submitBtn {
  margin: 8px;
}

.submitBtn button {
  width: 480px;
  height: 48px;
  background-color: #ffb400;
  opacity: 0.7;
  color: white;
  font-size: 24px;
  font-weight: bold;
  border: none;
  border-radius: 5px;
  margin: 8px 0;
}

.submitBtn button:hover {
  background-color: #ffb400;
  opacity: 1;
}

.header-logo > img {
  height: 100px;
  width: 400px;
  object-fit: cover;
  margin: 16px 0;
}

/* IN session */

.insession {
  width: 80vw;
  height: 80vh;
}

.myvideo {
  width: 35vw;
  height: 38vh;
  background-color: rgba(100, 100, 111, 0.2);
  border-radius: 5px;
  border: none;
  box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;
  margin: 16px 0;
}

.tabs {
  width: 35vw;
  height: 65vh;
  border-radius: 10px;
  background-color: #f9f9f9;
  box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px 0px;
  margin: 16px 0;
  padding: 8px 0 0 0;
}

.tabBtn {
  width: 35vw;
  height: 10%;
  background-color: #eeeeee;
  display: flex;
  align-items: center;
  justify-content: space-around;
}

.noresume {
  border-right: solid #c5c5c5 1px;
  width: 17.5vw;
  height: 8vh;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 20px;
  color: #6d6d6d;
  background-color: #eeeeee;
}

.noresume :hover {
  color: #8cd6c1;
}

.yesresume {
  border-right: solid #c5c5c5 1px;
  width: 17.5vw;
  height: 8vh;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 20px;
  color: #8cd6c1;
  font-weight: bold;
  background-color: #eeeeee;
}

.noeval {
  width: 17.5vw;
  height: 8vh;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 20px;
  color: #6d6d6d;
  background-color: #eeeeee;
}

.noeval :hover {
  color: #8cd6c1;
}

.yeseval {
  width: 17.5vw;
  height: 8vh;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 20px;
  color: #8cd6c1;
  font-weight: bold;
  background-color: #eeeeee;
}

.chatbox {
  width: 35vw;
  height: 65vh;
  border-radius: 10px;
  background-color: white;
  box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px 0px;
  margin: 16px;
  padding: 24px 24px 0 24px;
}

.chatlist {
  width: 35vw - 48px;
  height: 55vh;
  border: none;
  overflow-y: auto;
}

.chatlist::-webkit-scrollbar {
  width: 5px;
}

.chatlist::-webkit-scrollbar-thumb {
  background-color: var(--color-green-1);
  border-radius: 10px;
}

.chatlist::-webkit-scrollbar-track {
  background-color: var(--color-green-3);
  border-radius: 10px;
}

.chatform {
  width: 35vw - 48px;
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
  width: 28vw;
  height: 4vh;
  border: none;
  margin: 0 8px 0 0;
  background-color: #eaeaea;
  border-radius: 20px;
}

.chatinput:hover {
  border: none;
}

.chatsubmit {
  border: none;
  border-radius: 20px;
  margin: 2px 8px 0 2px;
  background-color: none;
  display: flex;
  align-items: center;
}

.chatsubmit > i {
  font-size: 28px;
  transform: rotate(45deg);
  margin: 0;
  background-color: none;
}

.chatitem {
  width: 30vw;
  height: 3vw;
  padding: 8px;
  display: flex;
  align-items: center;
}

.mename {
  width: 2vw;
  height: 2vw;
  border: none;
  border-radius: 100px;
  background-color: #ffb400;
  color: white;
  font-weight: bold;
  font-size: 24px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin: auto 4px;
}

.youname {
  width: 2vw;
  height: 2vw;
  border: none;
  border-radius: 100px;
  background-color: #37bf99;
  color: white;
  font-weight: bold;
  font-size: 24px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin: auto 8px auto 4px;
}

.datadata {
  text-align: center;
  font-size: 24px;
  margin: auto 8px;
}

.rightbtn {
  width: 35vw;
}

.chatbtn {
  width: 4vw;
  height: 4vw;
  border-radius: 100%;
  border: none;
  background-color: white;
  box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px 0px;
  margin: 4px;
}

.chatbtn i {
  font-size: 32px;
  color: #8cd6c1;
  font-weight: bold;
}

.chatbtn1 {
  width: 4vw;
  height: 4vw;
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
  width: 4vw;
  height: 4vw;
  border-radius: 100%;
  border: none;
  background-color: #8cd6c1;
  box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px 0px;
  margin: 6px;
}

.videobtn i {
  font-size: 32px;
  color: white;
  font-weight: bold;
}

.leavebtn {
  width: 8vw;
  height: 4vw;
  border-radius: 30px;
  border: none;
  background-color: #f3620f;
  box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px 0px;
  margin: 6px;
}

.leavebtn i {
  font-size: 32px;
  color: white;
  font-weight: bold;
}

.leavebtn span {
  font-size: 24px;
  color: white;
}

.interview-info-box {
  height: 90%;
  overflow-y: scroll;
}

.interview-info-box::-webkit-scrollbar {
  width: 5px;
}

.interview-info-box::-webkit-scrollbar-thumb {
  background-color: var(--color-green-1);
  border-radius: 10px;
}

.interview-info-box::-webkit-scrollbar-track {
  background-color: var(--color-green-3);
  border-radius: 10px;
}
</style>
